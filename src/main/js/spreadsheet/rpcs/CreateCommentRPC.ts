import { RPCName } from "./RPCName";

export interface ICreateCommentRPC {
  type: RPCName.CREATE_COMMENT_RPC;
  id: string;
  name: string;
  value: string;
}

export class CreateCommentRPC implements ICreateCommentRPC {
  type: RPCName.CREATE_COMMENT_RPC = RPCName.CREATE_COMMENT_RPC;
  id: string;
  name: string;
  value: string;

  constructor(id: string, name: string, value: string) {
    this.id = id;
    this.name = name;
    this.value = value;
  }

  static from(i: ICreateCommentRPC) {
    return new CreateCommentRPC(i.id, i.name, i.value);
  }
}
