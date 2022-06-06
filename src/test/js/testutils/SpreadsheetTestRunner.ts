import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { Executor } from "../../../main/js/execution/Executor";
import { Complex } from "../../../main/js/models/common/Types";
import { CellObject } from "../../../main/js/spreadsheet/CellObject";
import { excelDataTypeFromActualType } from "../../../main/js/spreadsheet/ExcelDataType";
import { NamedRange } from "../../../main/js/spreadsheet/NamedRange";
import { Sheet } from "../../../main/js/spreadsheet/Sheet";
import { Spreadsheet } from "../../../main/js/spreadsheet/Spreadsheet";
import { Converters } from "../../../main/js/utils/Converters";
import { isNull } from "../../../main/js/common/utils/Types";

export class MoreMath {
  static maxUInt32 = 4294967296;

  // Generate a random UInt32 between 0 and `uint32::max`.
  static randomUInt32(): number {
    return Math.floor(Math.random() * this.maxUInt32);
  }
}

/**
 * Runner class lets us build tests dynamically by adding individual cells,
 * without a ton of boiler-plating.
 */
export class SpreadsheetTestRunner {
  private sheets: { [key: string]: Sheet } = {};
  private expectedGridValues: { [index: string]: { [index: string]: Complex } } = {};
  private expectedEmptyKeys: { [index: string]: Array<string> } = {};
  private expectedEmptyKeysButComputed: { [index: string]: Array<string> } = {};
  private namedRanges: { [index: string]: NamedRange } = {};

  addCell(sheet: string, a1Key: string, value: string): SpreadsheetTestRunner {
    if (!(sheet in this.sheets)) {
      this.sheets[sheet] = new Sheet({ id: `s${MoreMath.randomUInt32()}`, name: sheet });
    }
    const parsedType = value.startsWith("=") ? "f" : null;
    const parsedValue = value.startsWith("=") ? value.replace("=", "") : value;
    const cell: CellObject = {
      t: excelDataTypeFromActualType(value),
    };
    if (parsedType) {
      cell.f = parsedValue;
    } else {
      cell.v = parsedValue;
    }
    this.sheets[sheet].setCell(a1Key, cell);
    return this;
  }

  addNamedRange(name: string, ref: string): SpreadsheetTestRunner {
    this.namedRanges[name] = {
      name,
      ref,
    };
    return this;
  }

  addExpectedValue(gridName: string, a1Key: string, value: Complex): SpreadsheetTestRunner {
    if (!(gridName in this.expectedGridValues)) {
      this.expectedGridValues[gridName] = {};
    }
    this.expectedGridValues[gridName][a1Key] = value;
    return this;
  }

  addExpectedEmptyValue(gridName: string, a1Key: string): SpreadsheetTestRunner {
    if (!(gridName in this.expectedEmptyKeys)) {
      this.expectedEmptyKeys[gridName] = [];
    }
    this.expectedEmptyKeys[gridName].push(a1Key);
    return this;
  }

  addExpectedEmptyComputedValue(gridName: string, a1Key: string): SpreadsheetTestRunner {
    if (!(gridName in this.expectedEmptyKeysButComputed)) {
      this.expectedEmptyKeysButComputed[gridName] = [];
    }
    this.expectedEmptyKeysButComputed[gridName].push(a1Key);
    return this;
  }

  /**
   * Run the tests in several steps:
   * 1) Build sheet executor and run it.
   * 2) For each expected entry, expect result.
   * 3) For each empty value, ensure it's empty.
   * 4) For each computed empty value, ensure it's empty.
   */
  public run() {
    // 1) Build sheet executor and run it.
    const executor = new Executor(new Spreadsheet(this.namedRanges, this.sheets));
    executor.execute();

    // 2) For each expected entry, expect result.
    for (const gridName in this.expectedGridValues) {
      // Doing "OR-empty-logic" to get the IDE to stop complaining about it.
      for (const a1key in this.expectedGridValues[gridName] || {}) {
        const cell = executor.getCell(gridName, a1key);
        const expectedValue = this.expectedGridValues[gridName][a1key];
        if (expectedValue instanceof F7Exception) {
          const eName = Converters.castAsF7Exception(cell.v).name;
          assert.deepEqual(
            eName,
            Converters.castAsF7Exception(expectedValue).name,
            JSON.stringify(eName)
          );
        } else {
          assert.deepEqual(cell.v, expectedValue as Complex);
        }
      }
    }

    // 3) For each empty value, ensure it's empty.
    for (const sheet in this.expectedEmptyKeys) {
      for (const a1key of this.expectedEmptyKeys[sheet]) {
        const foundCell = executor.getCell(sheet, a1key);
        if (isNull(foundCell)) {
          assert.isNull(foundCell);
        } else {
          assert.fail(`${JSON.stringify(foundCell)} is not empty.`);
        }
      }
    }
    // 4) For each expected empty computed value, ensure it's empty.
    for (const sheet in this.expectedEmptyKeys) {
      for (const a1key of this.expectedEmptyKeys[sheet]) {
        assert.isNull(executor.getCell(sheet, a1key));
      }
    }
  }
}
