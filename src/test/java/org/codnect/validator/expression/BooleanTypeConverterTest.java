package org.codnect.validator.expression;

import org.codnect.validator.base.TestContext;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.core.convert.TypeDescriptor;

import java.util.*;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by Burak Köken on 27.12.2019.
 */
@PrepareForTest({BooleanTypeConverter.class})
public class BooleanTypeConverterTest extends TestContext {

    @Test
    public void givenSourceAndTargetTypeConvertableToBoolean_WhenCanConvertIsCalled_ThenShouldReturnTrue() throws Exception {
        BooleanTypeConverter converter = spy(new BooleanTypeConverter());
        TypeDescriptor targetType = TypeDescriptor.valueOf(Boolean.class);

        TypeDescriptor sourceType = TypeDescriptor.valueOf(Number.class);
        assertTrue(converter.canConvert(sourceType, targetType));
        verifyPrivate(converter).invoke("isSourceTypeAssignableToBoolean", sourceType);
        verifyPrivate(converter).invoke("isTargetTypeAssignableToBoolean", targetType);

        sourceType = TypeDescriptor.valueOf(Collection.class);
        assertTrue(converter.canConvert(sourceType, targetType));
        verifyPrivate(converter).invoke("isSourceTypeAssignableToBoolean", sourceType);

        sourceType = TypeDescriptor.valueOf(Map.class);
        assertTrue(converter.canConvert(sourceType, targetType));
        verifyPrivate(converter).invoke("isSourceTypeAssignableToBoolean", sourceType);

        sourceType = TypeDescriptor.array(TypeDescriptor.valueOf(Object.class));
        assertTrue(converter.canConvert(sourceType, targetType));
        verifyPrivate(converter).invoke("isSourceTypeAssignableToBoolean", sourceType);
    }

    @Test
    public void givenSourceAndTargetTypeNonConvertableToBoolean_WhenCanConvertIsCalled_ThenShouldReturnFalse() {
        BooleanTypeConverter converter = new BooleanTypeConverter();
        assertFalse(converter.canConvert(TypeDescriptor.valueOf(Object.class), TypeDescriptor.valueOf(Number.class)));
        assertFalse(converter.canConvert(TypeDescriptor.valueOf(Boolean.class), TypeDescriptor.valueOf(Object.class)));
    }

    @Test
    public void givenAnyValue_WhenConvertValueIsCalled_ThenShouldBeConvertedToBoolean() {
        BooleanTypeConverter converter = spy(new BooleanTypeConverter());
        TypeDescriptor targetType = TypeDescriptor.valueOf(Boolean.class);

        Integer number = 0;
        assertFalse((Boolean) converter.convertValue(number, null, targetType));
        number = 13;
        assertTrue((Boolean) converter.convertValue(number, null, targetType));

        Collection collection = new ArrayList();
        TypeDescriptor sourceType = TypeDescriptor.valueOf(Collection.class);
        assertFalse((Boolean) converter.convertValue(collection, sourceType, targetType));
        collection.add("test");
        assertTrue((Boolean) converter.convertValue(collection, sourceType, targetType));

        Map<Object, Object> map = new HashMap<>();
        sourceType = TypeDescriptor.valueOf(Map.class);
        assertFalse((Boolean) converter.convertValue(map, sourceType, targetType));
        map.put("test", "test");
        assertTrue((Boolean) converter.convertValue(map, sourceType, targetType));

        Integer[] array = new Integer[0];
        sourceType = TypeDescriptor.array(TypeDescriptor.valueOf(Integer.class));
        assertFalse((Boolean) converter.convertValue(array, sourceType, targetType));
        array = new Integer[5];
        assertTrue((Boolean) converter.convertValue(array, sourceType, targetType));
    }


}