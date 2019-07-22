package com.radauer.genericio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

public class GenericReaderWriter
{

    private static String SEP = ";";

    public <T> List<T> readObjects(String fileName, Class<T> clazz)
        throws IOException, InstantiationException, IllegalAccessException
    {
        File f = getFile(fileName);

        List<Field> fields = OrderedFieldUtil.getOrderedFieldsFromClass(clazz);
        List<T> result = new ArrayList<>();
        @Cleanup BufferedReader br = new BufferedReader(new FileReader(f));
        int c = 0;
        String line;
        while ((line = br.readLine()) != null)
        {
            c++;
            if (c % 10000 == 0)
            {
                System.out.print(".");
            }
            result.add(stringToObject(line, clazz, fields));
        }

        System.out.println();
        return result;
    }

    public <T> void writeObjects(String fileName, List<T> objects, Class<T> clazz)
        throws IOException, IllegalAccessException
    {
        File f = getFile(fileName);

        List<Field> fields = OrderedFieldUtil.getOrderedFieldsFromClass(clazz);
        @Cleanup BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        int c = 0;
        for (T obj : objects)
        {
            bw.write(objectToString(obj, fields));
            bw.write("\n");

            c++;
            if (c % 10000 == 0)
            {
                System.out.print(".");
            }
        }

        System.out.println();
    }

    private File getFile(String fileName) throws IOException
    {
        File f = new File(fileName);
        if (!f.exists())
        {
            f.createNewFile();
        }
        return f;
    }

    private <T> T stringToObject(String line, Class<T> clazz, List<Field> fields)
        throws IllegalAccessException, InstantiationException
    {
        T obj = clazz.newInstance();

        String[] parts = line.split(SEP);
        for (int i = 0; i < Math.min(parts.length, fields.size()); i++)
        {
            fields.get(i).set(obj, parts[i]);
        }
        return obj;
    }

    private <T> String objectToString(T obj, List<Field> fields)
        throws IllegalAccessException
    {

        StringBuilder result = new StringBuilder();
        for (Field field : fields)
        {
            result.append(field.get(obj)).append(SEP);
        }
        return result.toString();
    }
}
