package org.codnect.validator.expression;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.support.StandardTypeConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class BooleanTypeConverter implements TypeConverter {

    private TypeConverter defaultTypeConverter;

    public BooleanTypeConverter() {
        this.defaultTypeConverter = new StandardTypeConverter();
    }

    @Override
    public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return isTargetTypeAssignableToBoolean(targetType) &&
                (isSourceTypeAssignableToBoolean(sourceType)
                        || defaultTypeConverter.canConvert(sourceType, targetType));
    }

    @Nullable
    @Override
    public Object convertValue(@Nullable Object value, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        if(isTargetTypeAssignableToBoolean(targetType)) {
            return convertValueToBoolean(value, sourceType);
        } else {
            return defaultTypeConverter.convertValue(value, sourceType, targetType);
        }
    }

    private boolean convertValueToBoolean(Object value, TypeDescriptor sourceType) {
        if (value instanceof Number) {
            return ((Number)value).intValue() != 0;
        }
        if(sourceType.isCollection()) {
            return !CollectionUtils.isEmpty((Collection)value);
        }
        if(sourceType.isMap()) {
            return !CollectionUtils.isEmpty((Map)value);
        }
        if (sourceType.isArray()) {
            return ((Object[]) value).length != 0;
        }
        return (boolean) value;
    }

    private boolean isTargetTypeAssignableToBoolean(TypeDescriptor typeDescriptor) {
        return typeDescriptor.isAssignableTo(TypeDescriptor.valueOf(Boolean.class));
    }

    private boolean isSourceTypeAssignableToBoolean(TypeDescriptor typeDescriptor) {
        return typeDescriptor.isAssignableTo(TypeDescriptor.valueOf(Number.class))
                || typeDescriptor.isCollection()
                || typeDescriptor.isMap()
                || typeDescriptor.isArray();
    }

}
