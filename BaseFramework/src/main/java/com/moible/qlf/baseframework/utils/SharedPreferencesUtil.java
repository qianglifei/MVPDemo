package com.moible.qlf.baseframework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * 类描述： SharedPreferences 工具
 * 项目名称：haoyue
 * 创建人：lml
 * 创建时间：2015/3/14 15:51
 */
public class SharedPreferencesUtil {

    public static final String SP_NAME = "haozhiyue";

    public static SharedPreferences sp;

    public static SharedPreferences.Editor editor;

    public static void initialize(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        if (editor == null) {
            editor = sp.edit();
        }
    }

    public static void initialize(Context context, String name) {
        if (sp == null) {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        if (editor == null) {
            editor = sp.edit();
        }
    }

    public SharedPreferencesUtil(Context context, String name) {

        if (sp == null) {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        if (editor == null) {
            editor = sp.edit();
        }
    }

    public static void put(Context context, String key, String value) {
        initialize(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, String value) {
        initialize(context, name);
        editor.putString(key, value);
        editor.commit();
    }


    public static void putMap(Context context, String key, Object value) {
        try {
            initialize(context, key);
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(toByte);
            oos.writeObject(value);

            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));// 存储
            editor.putString(key, payCityMapBase64);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //获得对象
    public static Object getMap(Context context, String key) {
        Map<String, String> map;
        try {
            byte[] base64Bytes = Base64.decode(getPrefString(context, key, "d"), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            map = (Map<String, String>) ois.readObject();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object();
        }


    }


    public static void writeToSp(Context context, WriteToSP writeEntity) {
        initialize(context);
        // 执行写入操作
        writeEntity.write();
    }

    public interface WriteToSP {
        void write();
    }

    /**
     * 根据SP的名称返回相应json对象
     *
     * @param context
     * @param key
     * @return
     * @author WongYoung
     */
    public static JSONObject getJson(Context context, String key) {
        JSONObject json = null;
        try {
            initialize(context);
            String str = sp.getString(key, "");
            if (!"".equals(str)) {
                json = new JSONObject(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getPrefString(Context context, String key, final String defaultValue) {
        initialize(context);
        return sp.getString(key, defaultValue);
    }

    public static String getPrefString(Context context, String name, String key,
                                       final String defaultValue) {
        initialize(context, name);
        return sp.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        initialize(context);
        sp.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        initialize(context);
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }

    public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
        initialize(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        initialize(context);
        sp.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        initialize(context);
        return sp.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key, final float value) {
        initialize(context);
        sp.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context, final String key, final float defaultValue) {
        initialize(context);
        return sp.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key, final long value) {
        initialize(context);
        sp.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        initialize(context);
        return sp.getLong(key, defaultValue);
    }

    public static void clearPreference() {
        final SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
