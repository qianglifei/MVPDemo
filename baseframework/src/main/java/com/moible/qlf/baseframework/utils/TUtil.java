package com.moible.qlf.baseframework.utils;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.moible.qlf.baseframework.widght.baseadapter.listener.SimpleClickListener.TAG;

/**
 * 类转换初始化
 */
public class TUtil{

    public static <T> T getT(Object o, int i) {
        try {
            /**
             * 得到实际的泛型类
             */
            ParameterizedType parameterizedType =  (ParameterizedType) o.getClass().getGenericSuperclass();
            Log.i("TAG", "===getT: "  + parameterizedType.toString());
            Type[] types = parameterizedType.getActualTypeArguments();
            Class<T> beanClass = (Class<T>) types[i];

            return beanClass.newInstance();

//            return ((Class<T>) ((ParameterizedType) (o.getClass()
//                    .getGenericSuperclass())).getActualTypeArguments()[i])
//                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.i(TAG, "===getT: " + e.getMessage());
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
