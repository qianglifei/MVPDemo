
package com.moible.qlf.baseframework.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.moible.qlf.baseframework.com.ACache;

import java.io.Serializable;


/**
 * 缓存工具
 *
 * @author lml
 * @TODO android_bluesearemit
 * @2015-7-21
 */
public class ASimpleCacheUtils {

    private ACache mCache;

    public ASimpleCacheUtils(Context mContext) {
        mCache = ACache.get(mContext);
    }

    /**
     * 添加进入缓存列表
     *
     * @param key
     * @param value
     */
    public void addJsonACache(String key, String value) {
        mCache.put(key, value);

    }


    /**
     * 添加进入缓存列表
     *
     * @param key
     * @param value
     */
    public void addJsonACache(String key, String value, int time) {
        mCache.put(key, value, time);

    }

    /**
     * 实体
     *
     * @TODO
     * @void
     * @2015-8-11
     */
    public void addObjectACache(String key, Object value) {
        mCache.put(key, (Serializable) value, 365 * ACache.TIME_DAY);

    }
    public void addBitmapACache(String key, Bitmap value) {
        mCache.put(key, value);
    }

    public void addBitmapACache(String key, Bitmap value, int time) {
        mCache.put(key, value, time);
    }

    /**
     * 从缓存列表中拿出来
     *
     * @param key
     * @return
     */
    public String getJsonCache(String key) {
        return mCache.getAsString(key);
    }

    /**
     * 获得缓存实体
     *
     * @TODO
     * @Object
     * @2015-8-11
     */
    public Object getObjectCache(String key) {
        return mCache.getAsObject(key);
    }

    public Bitmap getBitmapCache(String key) {
        return mCache.getAsBitmap(key);
    }

    /**
     * 清除
     */
    public boolean remove(String key) {
        return mCache.remove(key);
    }

}
