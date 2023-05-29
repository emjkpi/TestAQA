package org.example;

import org.example.reflectionUtils.Reflection;
import org.example.testClasses.*;
import org.junit.Assert;
import org.junit.Test;


public class ReflectionTest
{
    @Test
    public void simpleInt()
    {
        String actual = Reflection.classReflection(TestSimpleInt.class);
        String expected ="{\"age\": \"int\"}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void arrayLast()
    {
        String actual = Reflection.classReflection(ArrayLast.class);
        String expected ="{\"age\": \"int\",\"money\": \"int\",\"children\" : " +
                "[[[\"String\",\"...\"],\"...\"],\"...\"]}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void baseTest()
    {
        String actual = Reflection.classReflection(BaseTest.class);
        String expected ="{\"result\": \"int\",\"error\": \"String\",\"keywords\": [{\"String\": [{\"isDirectory\": " +
                "\"boolean\",\"name\": \"String\",\"filePath\": \"String\",\"size\": \"long\"}, \"...\"]}, \"...\"]}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void collectionLast()
    {
        String actual = Reflection.classReflection(CollectionLast.class);
        String expected ="{\"age\": \"int\",\"money\": \"int\",\"grades\":" +
                " [\"int\", \"...\"],\"temp\": [\"bool\", \"...\"]}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void wrappedAndSimple()
    {
        String actual = Reflection.classReflection(WrappedAndSimple.class);
        String expected ="{\"age\": \"int\",\"money\": \"int\",\"isTrue\": \"bool\",\"zero\": \"Byte\"," +
                "\"sale\": \"Short\",\"price\": \"Long\",\"temp\": \"Float\",\"discount\": \"Double\"," +
                "\"word\": \"char\",\"file\": [{\"isDirectory\": \"boolean\",\"name\": \"String\"," +
                "\"filePath\": \"String\",\"size\": \"long\"}]}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void innerClass()
    {
        String actual = Reflection.classReflection(InnerClass.class);
        String expected ="{\"Six\" : [{\"Five\" : [{\"Four\" : [{\"id\": \"int\",\"name\": \"String\"}]," +
                "\"isTrue\": \"bool\"}],\"grades\": [\"int\", \"...\"]}]}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void keyIsClass()
    {
        String actual = Reflection.classReflection(KeyIsClass.class);
        String expected ="{\"age\" : [[[\"int\",\"...\"],\"...\"],\"...\"],\"grades\": [\"int\", \"...\"]," +
                "\"keywords\": [{\"RemouteFile\": [\"int\", \"...\"]}, \"...\"],\"discount\": \"bool\"}";
        Assert.assertEquals(expected,actual);
    }
}











