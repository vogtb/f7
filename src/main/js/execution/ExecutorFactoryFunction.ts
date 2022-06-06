import { Spreadsheet } from "../spreadsheet/Spreadsheet";
import { Executor } from "./Executor";

/**
 * Functional constructor to create a new executor from a spreadsheet.
 */
export type ExecutorFactoryFunction = (spreadsheet: Spreadsheet) => Executor;
