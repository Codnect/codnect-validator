package org.codnect.validator.base;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class TestAssertHelpers {

    static boolean isNull(Object object) {
        return object == null;
    }

    static boolean isNotNull(Object object) {
        return object != null;
    }

    static boolean isEmpty(Object object) {
        if(object == null) {
            return true;
        }
        if(object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else if(object instanceof Map) {
            return ((Map)object).isEmpty();
        } else if(object instanceof String){
            return ((String)object).isEmpty();
        }
        return false;
    }

    static int length(String str) {
        return str == null ? 0 : str.length();
    }

}
