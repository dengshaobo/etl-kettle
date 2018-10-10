package com.khsh.etl.kettle;

import com.ejet.comm.utils.random.RandomUtils;
import com.ejet.comm.utils.reflect.ReflectUtils;
import com.ejet.comm.utils.time.TimeUtils;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: MockUtils
 * Author:   Ejet
 * CreateDate:     2018-10-08 14:30
 * Description:
 * History:
 * Version: 1.0
 */
public class MockUtils {

    private static Gson gson = new Gson();

    public static <T> T newInstance(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        // Class clazz = Class.forName(clazzName);
        Class[] paramTypes = {};
        T inter = null;
        Constructor<T> constructor = clazz.getConstructor(paramTypes);
        inter = constructor.newInstance();
        return inter;
    }

    /**
     * 获取测试数据
     * @param clazz
     * @return
     */
    public static <T> String getMockJson(Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Field> fields = ReflectUtils.getDeclaredFields(clazz);
        T model = newInstance(clazz);
        for (Field item: fields) {
            String typeName = item.getGenericType().getTypeName();
            Object value = null;
            synchronized (model) {
                //System.out.println(item.getName() + "[" + item.getGenericType() + "] set value: " + value);
                if (typeName.equalsIgnoreCase("java.lang.String")) {
                    if(item.getName().equalsIgnoreCase("modifyTime")) {
                        value = TimeUtils.getCurrentFullTime();
                    } else if(item.getName().equalsIgnoreCase("modifyUser")) {
                        value = "Admin";
                    }else if(item.getName().equalsIgnoreCase("remark")) {
                        value = "备注信息";
                    }else if(item.getName().equalsIgnoreCase("ext")) {
                        value = null;
                    } else {
                        value = item.getName() + "测试";
                    }
                } else if (typeName.equalsIgnoreCase("byte")) {
                    value = new Byte(RandomUtils.getRandomNumbers(1));
                } else if (typeName.contains("byte[]")) {
                    value = RandomUtils.getRandomNumbersAndUperLetters(20).getBytes();
                } else if (typeName.equalsIgnoreCase("char")) {
                    value = RandomUtils.getRandomNumbersAndUperLetters(2).charAt(1);
                } else if (typeName.equalsIgnoreCase("java.lang.Integer")) {
                    if(item.getName().equalsIgnoreCase("status")) {
                        value = 1;
                    } else {
                        value = RandomUtils.getRandom(100, 200);
                    }
                } else if (typeName.equalsIgnoreCase("java.lang.Float")) {
                    value = Float.parseFloat(RandomUtils.getRandom(100, 200) + ".021");
                } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
                    value = Double.parseDouble(RandomUtils.getRandom(100, 200) + ".021");
                } else if (typeName.equalsIgnoreCase("java.math.BigDecimal")) {
                    value = BigDecimal.valueOf(Double.parseDouble(RandomUtils.getRandom(100, 200) + ".021"));
                }
                if (value != null) {
                    System.out.println(item.getName() + "[" + item.getGenericType() + "] set value: " + value);
                    ReflectUtils.invokeSet(model, item.getName(), value);
                } else {
                    System.out.println(item.getName() + "[" + item.getGenericType() + "] ★ set value is null: " + value);
                }
            }
        }
        return gson.toJson(model);
    }


}
