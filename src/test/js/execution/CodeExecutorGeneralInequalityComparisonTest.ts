import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { CommonModels } from "../testutils/CommonModels";
import { run, runWithLookups } from "../testutils/TestUtils";

describe("CodeExecutor - Inequality Comparison Test", function () {
  it("should work with numbers", function () {
    assert.deepEqual(run("1 <> 1"), false);
    assert.deepEqual(run("1 <> 2"), true);
    assert.deepEqual(run("1.1928731 <> 1.1928731"), false);
  });

  it("should work with strings", function () {
    assert.deepEqual(run('"Yes" <> "Yes"'), false);
    assert.deepEqual(run('"Yes" <> "No"'), true);
    assert.deepEqual(run('"" <> ""'), false);
  });

  it("should work with booleans", function () {
    assert.deepEqual(run("TRUE <> TRUE"), false);
    assert.deepEqual(run("FALSE <> FALSE"), false);
    assert.deepEqual(run("TRUE <> FALSE"), true);
  });

  it("should work with arrays", function () {
    assert.deepEqual(run("{1, 2, 3} <> {1, 2, 3}"), false);
    assert.deepEqual(run("{1, 2, 3} <> {44}"), true);
    assert.deepEqual(run("{44, #REF!} <> {44, #REF!}"), false);
  });

  it("should compare numbers to booleans", function () {
    assert.deepEqual(run("0 <> TRUE"), true);
    assert.deepEqual(run("1 <> TRUE"), true);
    assert.deepEqual(run("1 <> FALSE"), true);
    assert.deepEqual(run("0 <> FALSE"), true);
  });

  it("should compare numbers to strings", function () {
    assert.deepEqual(run('0 <> ""'), true);
    assert.deepEqual(run('0 <> "0"'), true);
    assert.deepEqual(run('1 <> "0"'), true);
    assert.deepEqual(run('1 <> "1"'), true);
  });

  it("should compare numbers to array literals", function () {
    assert.deepEqual(run("0 <> {0, 1, 2}"), false);
    assert.deepEqual(run("1 <> {0, 1, 2}"), true);
  });

  it("should compare numbers to blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    collateralLookup.withArgs(CommonModels.A1, 0).returns(0);
    collateralLookup.withArgs(CommonModels.A1, 1).returns(1);
    collateralLookup.withArgs(CommonModels.A1, -1).returns(-1);
    assert.deepEqual(runWithLookups("0 <> M44", lookup, collateralLookup), false);
    assert.deepEqual(runWithLookups("1 <> M44", lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups("M44 <> 0", lookup, collateralLookup), false);
    assert.deepEqual(runWithLookups("M44 <> 1", lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups("-1 <> M44", lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups("M44 <> -1", lookup, collateralLookup), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, 0));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, 1));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, -1));
    assert.isTrue(lookup.notCalled);
  });

  it("should compare strings to booleans", function () {
    assert.deepEqual(run('"TRUE" <> TRUE'), true);
    assert.deepEqual(run('"FALSE" <> FALSE'), true);
  });

  it("should compare strings to blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    collateralLookup.withArgs(CommonModels.A1, "").returns("");
    collateralLookup.withArgs(CommonModels.A1, "One").returns("One");
    collateralLookup.withArgs(CommonModels.A1, " ").returns(" ");
    assert.deepEqual(runWithLookups('"" <> M44', lookup, collateralLookup), false);
    assert.deepEqual(runWithLookups('M44 <> ""', lookup, collateralLookup), false);
    assert.deepEqual(runWithLookups('" " <> M44', lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups('M44 <> " "', lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups('"One" <> M44', lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups('M44 <> "One"', lookup, collateralLookup), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, ""));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, "One"));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, " "));
    assert.isTrue(lookup.notCalled);
  });

  it("should compare booleans to blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    collateralLookup.withArgs(CommonModels.A1, true).returns(true);
    collateralLookup.withArgs(CommonModels.A1, false).returns(false);
    assert.deepEqual(runWithLookups("TRUE <> M44", lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups("FALSE <> M44", lookup, collateralLookup), false);
    assert.deepEqual(runWithLookups("M44 <> TRUE", lookup, collateralLookup), true);
    assert.deepEqual(runWithLookups("M44 <> FALSE", lookup, collateralLookup), false);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, true));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, false));
    assert.isTrue(lookup.notCalled);
  });

  it("should compare array literals to strings", function () {
    assert.deepEqual(run('{"A", "B"} <> "A"'), false);
    assert.deepEqual(run('"A" <> {"A", "B"}'), false);
    assert.deepEqual(run('"B" <> {"A", "B"}'), true);
  });

  it("should compare array literals to booleans", function () {
    assert.deepEqual(run("{TRUE, FALSE} <> TRUE"), false);
    assert.deepEqual(run("TRUE <> {TRUE, FALSE}"), false);
    assert.deepEqual(run("{FALSE, FALSE} <> TRUE"), true);
    assert.deepEqual(run("TRUE <> {FALSE, FALSE}"), true);
  });

  it("should not compare errors", function () {
    assert.deepEqual((run("TRUE <> #REF!") as F7Exception).name, F7ExceptionName.REF);
    assert.deepEqual((run("#REF! <> TRUE") as F7Exception).name, F7ExceptionName.REF);
  });
});
