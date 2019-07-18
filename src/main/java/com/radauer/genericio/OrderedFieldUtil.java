package com.radauer.genericio;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderedFieldUtil
{

    public static <T> List<Field> getOrderedFieldsFromClass(Class<T> clazz)
    {

        List<Field> result = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields())
        {
            field.setAccessible(true);
            if (field.isAnnotationPresent(OrderedField.class))
            {
                result.add(field);
            }
        }

        result.sort(Comparator.comparingInt(o -> o.getAnnotation(OrderedField.class).order()));
        return result;
    }
}
