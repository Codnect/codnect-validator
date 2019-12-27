package org.codnect.validator.base;

import org.codnect.validator.annotation.Assert;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class TestModelWithAssertHelpers {

    @Assert(value = "#isNull(#this)", assertHelpers = TestAssertHelpers.class)
    private String testFieldForNullCheck;

    @Assert(value = "#isNotNull(#this)", assertHelpers = TestAssertHelpers.class)
    private String testFieldForNotNullCheck;

    @Assert(value = "#length(#this) == 5", assertHelpers = TestAssertHelpers.class)
    private String tesFieldForLength;

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

    public String getTesFieldForLength() {
        return tesFieldForLength;
    }

    public void setTesFieldForLength(String tesFieldForLength) {
        this.tesFieldForLength = tesFieldForLength;
    }

}