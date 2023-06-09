package org.example.reflection;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import static org.example.classprocessor.Processor.*;
import static org.example.utils.ReflectionUtils.*;

public class Reflection {

    public static String classReflection(Class<?> clazz) {
        StringBuilder jsonBuilder = new StringBuilder();
        typeChecker(clazz, jsonBuilder);

        return jsonBuilder.toString();
    }


    public static void typeChecker (Class<?> clazz, StringBuilder jsonBuilder) {
        jsonBuilder.append("{");
        Field[] fields = clazz.getDeclaredFields();

        jsonBuilder.append(processingInnerClass(clazz, fields));

        for (int i=0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            Class<?> fieldType = fields[i].getType();
            Type fieldGenericType = fields[i].getGenericType();

            if (Collection.class.isAssignableFrom(fieldType)) {
                jsonBuilder.append(processingCollection(fieldGenericType, fieldName , fields, i));


            } else if (Map.class.isAssignableFrom(fieldType)) {
                jsonBuilder.append(processingMap(fieldGenericType, fieldName , fields, i));


            } else if (fieldType.isArray()) {
                processingArray(fieldType, jsonBuilder, fieldName , fields, i);


            } else if (isJDKOrPrimitive(fieldType)) {

                jsonBuilder.append("\"")
                        .append(fieldName)
                        .append("\": \"")
                        .append(printJDK(fieldType))
                        .append("\"");
                if (i != fields.length - 1)
                    jsonBuilder.append(",");

            } else {
                jsonBuilder.append("\"")
                        .append(fieldName)
                        .append("\": [")
                        .append(simpleFields(fieldType))
                        .append("]");
                if (i != fields.length - 1)
                    jsonBuilder.append(",");

            }

        }
        jsonBuilder.append("}");
    }

    public static StringBuilder simpleFields(Type type) {

        StringBuilder jsonBuilder = new StringBuilder();
        if (type instanceof Class) {
            if(isJDKOrPrimitive((Class<?>) type)){
                jsonBuilder.append("\"")
                        .append(printJDK((Class<?>) type))
                        .append("\"");
            } else {
                typeChecker((Class<?>) type, jsonBuilder);
            }
        } else if (type instanceof ParameterizedType) {
            Type[] typeArguments =
                    ((ParameterizedType) type).getActualTypeArguments();
            jsonBuilder.append("[");
            for (Type typeArgument : typeArguments) {
                jsonBuilder.append(simpleFields(typeArgument));
            }
            jsonBuilder.append(", \"...\"]");
        } else {
            jsonBuilder.append("\"")
                       .append(type.toString())
                       .append("\"");
        }
        return jsonBuilder;
    }

}