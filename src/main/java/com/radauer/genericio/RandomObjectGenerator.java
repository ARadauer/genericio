package com.radauer.genericio;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomObjectGenerator
{

    public <T> List<T> generateRandomObjects(int count, Class<T> clazz)
        throws InstantiationException, IllegalAccessException
    {
        List<Field> fields = OrderedFieldUtil.getOrderedFieldsFromClass(clazz);
        List<T> result = new ArrayList();
        for (int i = 0; i < count; i++)
        {
            if (i % 10000 == 0)
            {
                System.out.print(".");
            }
            result.add(generateObject(fields, clazz));
        }

        return result;

    }

    private <T> T generateObject(List<Field> fields, Class<T> clazz)
        throws IllegalAccessException, InstantiationException
    {
        T obj = clazz.newInstance();
        for (Field field : fields)
        {

            field.set(obj, RandomStringUtils.random(50, true, true));
        }
        return obj;
    }

}
