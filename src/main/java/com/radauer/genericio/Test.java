package com.radauer.genericio;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class Test
{

    public static void main(String[] args) throws Exception
    {


        test(1 * 1000 * 1000, "models.txt", Model.class);

    }

    public static <T> void test(int count, String file, Class<T> clazz) throws Exception
    {
        long t = System.currentTimeMillis();
        System.out.println("Start generating");
        RandomObjectGenerator objectGenerator = new RandomObjectGenerator();
        List<T> objects = objectGenerator.generateRandomObjects(count, clazz);

        System.out.println();
        System.out.println(objects.size());
        System.out.println("Finish generating " + (System.currentTimeMillis() - t) / 1000 + " s");
        t = System.currentTimeMillis();

        System.out.println("Start writing");
        GenericReaderWriter writer = new GenericReaderWriter();
        writer.writeObjects(file, objects, clazz);
        System.out.println("Finish writing " + (System.currentTimeMillis() - t) / 1000 + " s");
        objects = null;
        t = System.currentTimeMillis();
        System.out.println("Start reading");
        GenericReaderWriter reader = new GenericReaderWriter();
        objects = reader.readObjects(file, clazz);
        System.out.println(objects.size() + " objects read");
        System.out.println("Finish reading " + (System.currentTimeMillis() - t) / 1000 + " s");
    }

}
