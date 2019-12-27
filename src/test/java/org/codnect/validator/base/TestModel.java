package org.codnect.validator.base;

import org.codnect.validator.annotation.Assert;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class TestModel {

    @Assert("#this == null")
    private String testFieldForNullCheck;

    @Assert("#this != null")
    private String testFieldForNotNullCheck;

    @Assert("'hello'.equals(#this)")
    private String testFieldForEquals;

    @Assert("!'hello'.equals(#this)")
    private String testFieldForNotEquals;

    @Assert("#this == 5")
    private int testFieldForEqual;

    @Assert("#this > 5")
    private int testFieldForGreaterThan;

    @Assert("#this >= 5")
    private int testFieldForGreaterThanOrEqualTo;

    @Assert("#this < 5")
    private int testFieldForLessThan;

    @Assert("#this >= 5")
    private int testFieldForLessThanOrEqualTo;

    @Assert("#this >= 5 AND #this <= 9")
    private int testFieldForAndOperator;

    @Assert("#this == 5 OR #this == 9")
    private int testFieldForOrOperator;

    public String getTestFieldForNullCheck() {
        return testFieldForNullCheck;
    }

    public void setTestFieldForNullCheck(String testFieldForNullCheck) {
        this.testFieldForNullCheck = testFieldForNullCheck;
    }

    public String getTestFieldForNotNullCheck() {
        return testFieldForNotNullCheck;
    }

    public void setTestFieldForNotNullCheck(String testFieldForNotNullCheck) {
        this.testFieldForNotNullCheck = testFieldForNotNullCheck;
    }

    public String getTestFieldForEquals() {
        return testFieldForEquals;
    }

    public void setTestFieldForEquals(String testFieldForEquals) {
        this.testFieldForEquals = testFieldForEquals;
    }

    public String getTestFieldForNotEquals() {
        return testFieldForNotEquals;
    }

    public void setTestFieldForNotEquals(String testFieldForNotEquals) {
        this.testFieldForNotEquals = testFieldForNotEquals;
    }

    public int getTestFieldForEqual() {
        return testFieldForEqual;
    }

    public void setTestFieldForEqual(int testFieldForEqual) {
        this.testFieldForEqual = testFieldForEqual;
    }

    public int getTestFieldForGreaterThan() {
        return testFieldForGreaterThan;
    }

    public void setTestFieldForGreaterThan(int testFieldForGreaterThan) {
        this.testFieldForGreaterThan = testFieldForGreaterThan;
    }

    public int getTestFieldForGreaterThanOrEqualTo() {
        return testFieldForGreaterThanOrEqualTo;
    }

    public void setTestFieldForGreaterThanOrEqualTo(int testFieldForGreaterThanOrEqualTo) {
        this.testFieldForGreaterThanOrEqualTo = testFieldForGreaterThanOrEqualTo;
    }

    public int getTestFieldForLessThan() {
        return testFieldForLessThan;
    }

    public void setTestFieldForLessThan(int testFieldForLessThan) {
        this.testFieldForLessThan = testFieldForLessThan;
    }

    public int getTestFieldForLessThanOrEqualTo() {
        return testFieldForLessThanOrEqualTo;
    }

    public void setTestFieldForLessThanOrEqualTo(int testFieldForLessThanOrEqualTo) {
        this.testFieldForLessThanOrEqualTo = testFieldForLessThanOrEqualTo;
    }


    public int getTestFieldForAndOperator() {
        return testFieldForAndOperator;
    }

    public void setTestFieldForAndOperator(int testFieldForAndOperator) {
        this.testFieldForAndOperator = testFieldForAndOperator;
    }

    public int getTestFieldForOrOperator() {
        return testFieldForOrOperator;
    }

    public void setTestFieldForOrOperator(int testFieldForOrOperator) {
        this.testFieldForOrOperator = testFieldForOrOperator;
    }

}
