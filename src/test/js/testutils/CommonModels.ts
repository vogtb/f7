import { A1Key } from "../../../main/js/models/common/A1Key";
import { SheetColumnRowKey } from "../../../main/js/models/common/SheetColumnRowKey";
import { CellQuery } from "../../../main/js/models/nodes/CellQuery";

export class CommonModels {
  static A1 = SheetColumnRowKey.from("Alpha", A1Key.fromString("A1").toColumnRowKey());
  static M22 = SheetColumnRowKey.from("Alpha", A1Key.fromString("M22").toColumnRowKey());
  static M22_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.M22.column, CommonModels.M22.column)
    .rowsBetween(CommonModels.M22.row, CommonModels.M22.row)
    .build();

  static G19 = SheetColumnRowKey.from("Alpha", A1Key.fromString("G19").toColumnRowKey());
  static G19_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.G19.column, CommonModels.G19.column)
    .rowsBetween(CommonModels.G19.row, CommonModels.G19.row)
    .build();

  static J6 = SheetColumnRowKey.from("Alpha", A1Key.fromString("J6").toColumnRowKey());
  static J6_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.J6.column, CommonModels.J6.column)
    .rowsBetween(CommonModels.J6.row, CommonModels.J6.row)
    .build();

  static D1 = SheetColumnRowKey.from("Alpha", A1Key.fromString("D1").toColumnRowKey());
  static D3 = SheetColumnRowKey.from("Alpha", A1Key.fromString("D3").toColumnRowKey());
  static D5 = SheetColumnRowKey.from("Alpha", A1Key.fromString("D5").toColumnRowKey());
  static D1_TO_D3_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.D1.column, CommonModels.D3.column)
    .rowsBetween(CommonModels.D1.row, CommonModels.D3.row)
    .build();
  static D1_TO_D5_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.D1.column, CommonModels.D5.column)
    .rowsBetween(CommonModels.D1.row, CommonModels.D5.row)
    .build();

  static M44 = A1Key.fromString("M44").toColumnRowKey();
  static M44_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.M44.column, CommonModels.M44.column)
    .rowsBetween(CommonModels.M44.row, CommonModels.M44.row)
    .build();
  static B1 = A1Key.fromString("B1").toColumnRowKey();
  static B4 = A1Key.fromString("B4").toColumnRowKey();
  static B1_B4_RANGE = CellQuery.builder()
    .setSheet("Alpha")
    .columnsBetween(CommonModels.B1.column, CommonModels.B4.column)
    .rowsBetween(CommonModels.B1.row, CommonModels.B4.row)
    .build();
}
