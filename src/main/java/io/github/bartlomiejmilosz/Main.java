package io.github.bartlomiejmilosz;

import io.github.bartlomiejmilosz.simple_object_relational_mapper.model.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //String personClassName = Person.class.getName();
        Class<?> personClass = Class.forName(Person.class.getName());

        System.out.printf("Person class: %s%n", personClass);

        // Empty because there are no non-private fields
        Field[] classFields = personClass.getFields();
        System.out.println("Person class fields: " + Arrays.toString(classFields));

        // Returns list of all fields, also private
        Field[] classDeclaredFields = personClass.getDeclaredFields();
        System.out.println("Person declared class fields: " + Arrays.toString(classDeclaredFields));

        // Returns list of methods used in given Class, Object included because in Java all classes are objects too Class<Object>
        System.out.println("Methods:");
        Method[] classMethods = personClass.getMethods();
        for (Method method : classMethods) {
            System.out.println(method);
        }

        // Same as previous but includes only visible in Class methods (without Object specific)
        System.out.println("Declared Methods:");
        Method[] classDeclaredMethods = personClass.getDeclaredMethods();
        Arrays.stream(classDeclaredMethods)
                .forEach(System.out::println);

        // Returns static methods only
        System.out.println("Static Declared Methods:");
        Arrays.stream(classDeclaredMethods)
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .forEach(System.out::println);
    }
}