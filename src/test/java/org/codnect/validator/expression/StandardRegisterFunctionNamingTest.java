package org.codnect.validator.expression;

import org.codnect.validator.base.TestContext;
import org.codnect.validator.util.ReflectionUtilTest;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class StandardRegisterFunctionNamingTest  extends TestContext {

    @Test
    public void givenAnyClassAndMethod_WhenGetFunctionName_ThenShouldReturnFunctionName() throws NoSuchMethodException {
        StandardRegisterFunctionNaming standardRegisterFunctionNaming = new StandardRegisterFunctionNaming();
        Method staticMethod = ReflectionUtilTest.class.getMethod("testStaticMethod");
        String functionName = standardRegisterFunctionNaming.getFunctionName(ReflectionUtilTest.class, staticMethod);
        Assert.assertEquals(staticMethod.getName(), functionName);
    }

}