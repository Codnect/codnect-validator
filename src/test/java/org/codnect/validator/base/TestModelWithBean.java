package org.codnect.validator.base;

import org.codnect.validator.annotation.Assert;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class TestModelWithBean {

    @Assert(value = "@testBean.alwaysTrue()")
    private String testField;

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }

}

