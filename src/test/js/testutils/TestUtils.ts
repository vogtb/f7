import { assert } from "chai";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { CodeExecutor } from "../../../main/js/execution/CodeExecutor";
import { FormulaCaller } from "../../../main/js/formulas/FormulaCaller";
import { CollateralLookupFunction, LookupFunction } from "../../../main/js/models/common/Types";
import { Converters } from "../../../main/js/utils/Converters";
import { CommonModels } from "./CommonModels";
import { SpreadsheetTestRunner } from "./SpreadsheetTestRunner";
import { AbstractStandardError } from "../../../main/js/common/errors/StandardError";

export function run(code: string) {
  const executor: CodeExecutor = new CodeExecutor(
    {},
    null,
    null,
    new FormulaCaller(
      (value) => value,
      (origin, value) => value
    )
  );
  return executor.execute(CommonModels.A1, { f: code, t: "n" });
}

export function runWithCustomFormulas(
  code: string,
  customFormulas: { [name: string]: (a: unknown, b?: unknown, c?: unknown) => unknown }
) {
  const executor: CodeExecutor = new CodeExecutor(
    {},
    null,
    null,
    new FormulaCaller(
      (value) => value,
      (origin, value) => value,
      customFormulas
    )
  );
  return executor.execute(CommonModels.A1, { f: code, t: "n" });
}

export function runWithLookups(
  code: string,
  lookup: LookupFunction,
  collateralLookup: CollateralLookupFunction
) {
  const executor: CodeExecutor = new CodeExecutor(
    {},
    lookup,
    collateralLookup,
    new FormulaCaller(lookup, collateralLookup)
  );
  return executor.execute(CommonModels.A1, { f: code, t: "n" });
}

export function runner(): SpreadsheetTestRunner {
  return new SpreadsheetTestRunner();
}

/**
 * Cast the value as an F7Exception, and assert on the name.
 * @param value - Value to cast.
 * @param expectedName - Expected name for assertion.
 */
export function assertF7ExceptionByName(value: any, expectedName: F7ExceptionName) {
  assert.deepEqual(Converters.castAsF7Exception(value).name, expectedName);
}

/**
 * When ensuring that a function throws a particular error it's a pain to wrap each assertion.
 * This just accepts a Function, runs it, and returns the exception if one was thrown, or
 * returns null if it didn't throw anything.
 * @param f - Function to run.
 */
export function tryAndCatchError(f: (a?: any) => any): any {
  try {
    f();
  } catch (e) {
    return e;
  }
  return null;
}

/**
 * Quickly casting value as common error.
 * @param value - to cast.
 */
export function asCommonError(value: any): AbstractStandardError {
  return value as AbstractStandardError;
}

export function describe(description: string, specDefinitions: () => void): void {
  console.log(description);
  specDefinitions();
}

export function it(expectation: string, assertion: () => void, timeout?: number): void {
  internalSetup();
  console.log(`\t ${expectation}`);
  assertion();
}

let internalSetup: any = undefined;

export function beforeEach(setup: () => void): void {
  internalSetup = setup;
}
