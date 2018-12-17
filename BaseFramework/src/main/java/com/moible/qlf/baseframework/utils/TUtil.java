package com.moible.qlf.baseframework.utils;

import android.util.Log;

import com.moible.qlf.baseframework.base.BaseActivity;

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
             * 获取超类（父类）的泛型参数的实际类型
             */


//            ParameterizedType parameterizedType = (ParameterizedType) o.getClass().getGenericSuperclass();
//
//            Type[] types = parameterizedType.getActualTypeArguments();
//            Class<T> beanClass = (Class<T>) types[i];
            Class<T> beanClass = GenericsUtils.getSuperClassGenricType(o.getClass(),i);

            return beanClass == null ? null:beanClass.newInstance();
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
