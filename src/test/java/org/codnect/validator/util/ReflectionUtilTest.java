package org.codnect.validator.util;

import org.codnect.validator.base.TestContext;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class ReflectionUtilTest extends TestContext {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public static void testStaticMethod() {
    }

    public static void testAnotherStaticMethod() {
    }

    public void testNonStaticMethod() {
    }

    @Test
    public void givenNull_WhenIsStaticIsCalled_ThenShouldThrowAnException() throws NoSuchMethodException {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Method must not be null.");
        ReflectionUtil.isStatic(null);
    }

    @Test
    public void givenAnyStaticMethod_WhenIsStaticIsCalled_ThenShouldBeOk() throws NoSuchMethodException {
        Method staticMethod = ReflectionUtilTest.class.getMethod("testStaticMethod");
        Assert.assertTrue(ReflectionUtil.isStatic(staticMethod));
    }

    @Test
    public void givenAnyNonStaticMethod_WhenIsStaticIsCalled_ThenShouldBeOk() throws NoSuchMethodException {
        Method nonStaticMethod = ReflectionUtilTest.class.getMethod("testNonStaticMethod");
        Assert.assertFalse(ReflectionUtil.isStatic(nonStaticMethod));
    }

    @Test
    public void givenNull_WhenGetAllStaticMethodsIsCalled_ThenShouldThrowAnException() throws NoSuchMethodException {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Class must not be null.");
        ReflectionUtil.getAllStaticMethods(null);
    }

    @Test
    public void givenAnyClass_WhenGetAllStaticMethodsIsCalled_ThenShouldReturnAllStaticMethods() throws NoSuchMethodException {
        Set<Method> staticMethods = ReflectionUtil.getAllStaticMethods(ReflectionUtilTest.class);
        Assert.assertEquals(staticMethods.size(), 2);
    }

}