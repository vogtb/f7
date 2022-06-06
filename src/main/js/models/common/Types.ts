import { F7Exception } from "../../errors/F7Exception";
import { CellQuery } from "../nodes/CellQuery";
import { Grid } from "./Grid";
import { SheetColumnRowKey } from "./SheetColumnRowKey";

/**
 * The primitive values.
 */
export type Primitive = null | string | number | boolean;

/**
 * Simple types that represent a value, or a lookup.
 */
export type Computed = null | string | number | boolean | CellQuery | F7Exception;

/**
 * The common type that F7 formulas use.
 */
export type Complex = null | string | number | boolean | CellQuery | F7Exception | Grid<Computed>;

/**
 * A lookup function is a typed function that takes a value of common type and returns the same
 */
export type LookupFunction = (value: Complex) => Complex;

/**
 * A collateral lookup function is a typed function that takes a value of common type and returns the same, using the
 * origin to possibly lookup another value if value is of the type CellQuery.
 */
export type CollateralLookupFunction = (origin: SheetColumnRowKey, value: Complex) => Complex;
