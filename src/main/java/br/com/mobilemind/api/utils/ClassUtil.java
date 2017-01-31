package br.com.mobilemind.api.utils;

/*
 * #%L
 * Mobile Mind - Utils
 * %%
 * Copyright (C) 2012 Mobile Mind Empresa de Tecnologia
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ricardo Bocchi
 */
public class ClassUtil {

    public final static int GET_METHOD = 0;
    public final static int SET_METHOD = 1;
    public final static int ANY_METHOD = 2;

    public static boolean isAssignableFrom(Class source, Class assigned) {
        if (source == null) {
            return false;
        }

        if (source == assigned) {
            return true;
        }

        Class[] interfaces = source.getInterfaces();

        if (!source.equals(Object.class)) {
            if (isAssignableFrom(source.getSuperclass(), assigned)) {
                return true;
            }
        }

        for (Class c : interfaces) {
            if (c == assigned) {
                return true;
            }
        }
        return false;
    }

    public static boolean isString(Object o) {
        if (o instanceof Class) {
            return o == String.class;
        }
        return o instanceof String;
    }

    public static boolean isInteger(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Class)) {
            o = o.getClass();
        }

        return o == Integer.class || o == Integer.TYPE;
    }

    public static boolean isDouble(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Class)) {
            o = o.getClass();
        }

        return o == Double.class || o == Double.TYPE;
    }

    public static boolean isLong(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Class)) {
            o = o.getClass();
        }

        return o == Long.class || o == Long.TYPE;
    }

    public static boolean isBoolean(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Class)) {
            o = o.getClass();
        }

        return o == Boolean.class || o == Boolean.TYPE;
    }

    public static boolean isDate(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Class)) {
            o = o.getClass();
        }

        return o == Date.class;
    }

    public static boolean isByteArray(Object o) {

        if (byte[].class.equals(o)) {
            return true;
        }

        return o instanceof byte[];
    }

    public synchronized static List<java.lang.reflect.Field> getAnnotatedsFields(Class clazz,
            Class<? extends Annotation> annotation) {
        List<java.lang.reflect.Field> fields = new LinkedList<java.lang.reflect.Field>();

        getAnnotatedsFields(clazz, annotation, fields);

        return fields;
    }

    public synchronized static java.lang.reflect.Field getAnnotatedField(Class clazz,
            Class<? extends Annotation> annotation, String field) {
        List<java.lang.reflect.Field> fields = new ArrayList<java.lang.reflect.Field>();

        getAnnotatedsFields(clazz, annotation, fields);

        for (java.lang.reflect.Field t : fields) {
            if (t.getName().equals(field)) {
                return t;
            }
        }

        return null;
    }

    private synchronized static void getAnnotatedsFields(Class clazz, Class<? extends Annotation> annotation,
            List items) {
        Annotation field;
        java.lang.reflect.Field[] fls = clazz.getDeclaredFields();

        for (java.lang.reflect.Field f : fls) {
            field = f.getAnnotation(annotation);
            if (field != null) {
                items.add(f);
            }
        }

        Class[] interfaces = clazz.getInterfaces();

        for (Class c : interfaces) {
            getAnnotatedsFields(c, annotation, items);
        }

        Class superClass = clazz.getSuperclass();

        if (superClass != null && superClass != Object.class) {
            getAnnotatedsFields(superClass, annotation, items);
        }
    }

    public synchronized static List<java.lang.reflect.Field> getAllFields(Class clazz) {
        List<java.lang.reflect.Field> list = new ArrayList<Field>();
        getAllFields(clazz, list);
        return list;

    }

    public synchronized static java.lang.reflect.Field getField(Class clazz, String fieldName) {
        List<java.lang.reflect.Field> list = new ArrayList<Field>();
        getAllFields(clazz, list);

        for (Field field : list) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        return null;

    }

    private synchronized static void getAllFields(Class clazz, List<java.lang.reflect.Field> list) {
        java.lang.reflect.Field[] fls = clazz.getDeclaredFields();

        for (java.lang.reflect.Field f : fls) {
            list.add(f);
        }

        Class superClass = clazz.getSuperclass();

        if (superClass != null && superClass != Object.class) {
            getAllFields(superClass, list);
        }
    }

    public synchronized static List<Method> getAllBeanMethods(Class clazz, int type) {
        List<Method> list = new ArrayList<Method>();
        getAllBeanMethods(clazz, list, type);
        return list;

    }

    public synchronized static void getAllBeanMethods(Class clazz, List<Method> list, int type) {
        Method[] fls = clazz.getDeclaredMethods();

        for (Method f : fls) {

            if (GET_METHOD == type) {
                if (!f.getName().startsWith("get") && !f.getName().startsWith("is")) {
                    continue;
                }
            } else if (SET_METHOD == type) {
                if (!f.getName().startsWith("set")) {
                    continue;
                }
            }

            list.add(f);
        }

        Class superClass = clazz.getSuperclass();

        if (superClass != null && superClass != Object.class) {
            getAllBeanMethods(superClass, list, type);
        }
    }

    public static boolean isCompatibleMethods(String methodGet, String methodSet) {
        methodGet = methodGet.startsWith("get") ? methodGet.substring(3, methodGet.length()) : methodGet.substring(2, methodGet.length());

        methodSet = methodSet.substring(3, methodSet.length());

        if (!methodGet.equals(methodSet)) {
            return false;
        }
        return true;
    }

    public static boolean isSetMethod(String method) {
        return method.startsWith("set");
    }

    //verifica se Ã© um metodo get
    public static boolean isGetMethod(String method) {
        return method.startsWith("get") || method.startsWith("is");
    }

    public static Method getGetMethod(String fieldName, Class clazz) {
        Field field = getField(clazz, fieldName);

        if (field == null) {
            throw new RuntimeException("field [" + fieldName + "] not found in class [" + clazz.getName() + "]");
        }

        String method = "get" + Character.toString(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);

        try {
            return clazz.getDeclaredMethod(method);
        } catch (Exception e) {
            throw new RuntimeException("method [" + method + "] not found in class [" + clazz.getName() + "]");
        }
    }

    public static Method getGetMethod(Method setMethod, Method[] ms) {
        for (Method method : ms) {
            if (isCompatibleMethods(method.getName(), setMethod.getName())) {
                return method;
            }
        }
        return null;
    }

    public static List<Method> getAnnotatedMethods(Class clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new LinkedList<Method>();
        getAnnotatedMethods(clazz, annotation, annotatedMethods);
        return annotatedMethods;
    }

    private static void getAnnotatedMethods(Class clazz, Class<? extends Annotation> annotation, List<Method> annotatedMethods) {
        
        if(clazz == null)
            return;

        Method[] ms = clazz.getDeclaredMethods();
        for (Method method : ms) {
            if(method.isAnnotationPresent(annotation)){
                annotatedMethods.add(method);
            }
        }

        getAnnotatedMethods(clazz.getSuperclass(), annotation, annotatedMethods);
    }    

    public static Method getSetMethod(String fieldName, Class clazz) {
        Field field = getField(clazz, fieldName);

        if (field == null) {
            throw new RuntimeException("field [" + fieldName + "] not found in class [" + clazz.getName() + "]");
        }

        String method = "set" + Character.toString(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);

        try {
            return clazz.getDeclaredMethod(method, field.getType());
        } catch (Exception e) {
            throw new RuntimeException("method [" + method + "] not found in class [" + clazz.getName() + "]");
        }
    }

    public static Method getSetMethod(Method getMethod, List<Method> ms) {
        for (Method method : ms) {
            if (isCompatibleMethods(getMethod.getName(), method.getName())) {
                return method;
            }
        }
        return null;
    }

    public static boolean isPrimitive(Object obj) {

        return isBoolean(obj) || isDouble(obj) || isInteger(obj) || isLong(obj) || isString(obj);
    }
}
