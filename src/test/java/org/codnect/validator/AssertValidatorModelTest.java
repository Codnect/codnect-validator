package org.codnect.validator;

import org.codnect.validator.base.TestContext;
import org.codnect.validator.base.TestModel;
import org.codnect.validator.base.TestModelWithAssertHelpers;
import org.codnect.validator.base.TestModelWithBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class AssertValidatorModelTest extends TestContext {

    @Autowired
    private ValidatorFactory validatorFactory;

    @Test
    public void givenTestModel_WhenValidate_ThenShouldPass() throws Exception {
        Validator validator = validatorFactory.getValidator();
        TestModel testModel = new TestModel();
        testModel.setTestFieldForEquals("hello");
        testModel.setTestFieldForNotNullCheck("test");
        testModel.setTestFieldForNotEquals("hello world!");
        testModel.setTestFieldForEqual(5);
        testModel.setTestFieldForGreaterThan(6);
        testModel.setTestFieldForGreaterThanOrEqualTo(5);
        testModel.setTestFieldForLessThan(4);
        testModel.setTestFieldForLessThanOrEqualTo(5);
        testModel.setTestFieldForAndOperator(8);
        testModel.setTestFieldForOrOperator(9);
        Set<ConstraintViolation<TestModel>> constraintViolations =
                validator.validate(testModel);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenTestModelWithAssertHelpers_WhenValidate_ThenShouldPass() {
        Validator validator = validatorFactory.getValidator();
        TestModelWithAssertHelpers testModel = new TestModelWithAssertHelpers();
        testModel.setTestFieldForNotNullCheck("test");
        testModel.setTesFieldForLength("hello");
        Set<ConstraintViolation<TestModelWithAssertHelpers>> constraintViolations =
                validator.validate(testModel);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenTestModelWithBean_WhenValidate_ThenShouldPass() {
        Validator validator = validatorFactory.getValidator();
        TestModelWithBean testModel = new TestModelWithBean();
        Set<ConstraintViolation<TestModelWithBean>> constraintViolations =
                validator.validate(testModel);
        assertEquals(0, constraintViolations.size());
    }

}

