import { RPCName } from "./RPCName";

export interface ICreateSheetRPC {
  type: RPCName.CREATE_COMMENT_RPC;
  id: string;
  name: string;
}

export class CreateSheetRPC implements ICreateSheetRPC {
  type: RPCName.CREATE_COMMENT_RPC = RPCName.CREATE_COMMENT_RPC;
  id: string;
  name: string;

  constructor(id: string, name: string) {
    this.id = id;
    this.name = name;
  }

  static from(i: ICreateSheetRPC) {
    return new CreateSheetRPC(i.id, i.name);
  }
}
