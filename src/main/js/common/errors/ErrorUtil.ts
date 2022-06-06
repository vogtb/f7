import { AbortedError } from "./AbortedError";
import { AlreadyExistsError } from "./AlreadyExistsError";
import { CanceledError } from "./CanceledError";
import { DeadlineExceededError } from "./DeadlineExceededError";
import { InternalError } from "./InternalError";
import { InvalidArgumentError } from "./InvalidArgumentError";
import { NotFoundError } from "./NotFoundError";
import { NotImplementedError } from "./NotImplementedError";
import { ResourceExhaustedError } from "./ResourceExhaustedError";
import { StandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { UnauthenticatedError } from "./UnauthenticatedError";
import { UnavailableError } from "./UnavailableError";
import ABORTED = StatusCodes.ABORTED;
import ALREADY_EXISTS = StatusCodes.ALREADY_EXISTS;
import CANCELLED = StatusCodes.CANCELLED;
import DEADLINE_EXCEEDED = StatusCodes.DEADLINE_EXCEEDED;
import FAILED_PRECONDITION = StatusCodes.FAILED_PRECONDITION;
import INTERNAL = StatusCodes.INTERNAL;
import INVALID_ARGUMENT = StatusCodes.INVALID_ARGUMENT;
import NOT_FOUND = StatusCodes.NOT_FOUND;
import NOT_IMPLEMENTED = StatusCodes.NOT_IMPLEMENTED;
import OUT_OF_RANGE = StatusCodes.OUT_OF_RANGE;
import RESOURCE_EXHAUSTED = StatusCodes.RESOURCE_EXHAUSTED;
import UNAUTHENTICATED = StatusCodes.UNAUTHENTICATED;
import UNAVAILABLE = StatusCodes.UNAVAILABLE;
import UNKNOWN = StatusCodes.UNKNOWN;

/**
 * Cast interface (usually parsed from JSON) as error.
 * @param error to throw.
 */
export const throwStandardErr = (error: StandardError) => {
  switch (error.code) {
    case INVALID_ARGUMENT:
    case FAILED_PRECONDITION:
    case OUT_OF_RANGE:
      throw new InvalidArgumentError(error.message);
    case UNAUTHENTICATED:
      throw new UnauthenticatedError(error.message);
    case NOT_FOUND:
      throw new NotFoundError(error.message);
    case ABORTED:
      throw new AbortedError(error.message);
    case ALREADY_EXISTS:
      throw new AlreadyExistsError(error.message);
    case RESOURCE_EXHAUSTED:
      throw new ResourceExhaustedError(error.message);
    case CANCELLED:
      throw new CanceledError(error.message);
    case UNKNOWN:
    case INTERNAL:
      throw new InternalError(error.message);
    case NOT_IMPLEMENTED:
      throw new NotImplementedError(error.message);
    case UNAVAILABLE:
      throw new UnavailableError(error.message);
    case DEADLINE_EXCEEDED:
      throw new DeadlineExceededError(error.message);
  }
};

/**
 * Way to throw from just a code, and maybe a message.
 */
export const throwStandardErrFromCode = (code: number, message = "") => {
  switch (code) {
    case INVALID_ARGUMENT:
    case FAILED_PRECONDITION:
    case OUT_OF_RANGE:
      throw new InvalidArgumentError(message);
    case UNAUTHENTICATED:
      throw new UnauthenticatedError(message);
    case NOT_FOUND:
      throw new NotFoundError(message);
    case ABORTED:
      throw new AbortedError(message);
    case ALREADY_EXISTS:
      throw new AlreadyExistsError(message);
    case RESOURCE_EXHAUSTED:
      throw new ResourceExhaustedError(message);
    case CANCELLED:
      throw new CanceledError(message);
    case UNKNOWN:
    case INTERNAL:
      throw new InternalError(message);
    case NOT_IMPLEMENTED:
      throw new NotImplementedError(message);
    case UNAVAILABLE:
      throw new UnavailableError(message);
    case DEADLINE_EXCEEDED:
      throw new DeadlineExceededError(message);
  }
};
