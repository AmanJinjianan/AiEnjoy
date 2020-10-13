package com.aman.aienjoy.ui.main.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by kiros on 2019/7/19.
 * 本地储存的封装类
 */

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    /*
     * 保存手机里面的名字
     */
    private SharedPreferences.Editor editor;
    private static SharedPreferencesHelper instance;

    public SharedPreferencesHelper(Context context, String FILE_NAME) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("sharedPrefer_data",
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static synchronized SharedPreferencesHelper getInstance(Context context) {
        if (null == instance) {
            instance = new SharedPreferencesHelper(context);
        }
        return instance;
    }
    /**
     * 存储
     */
    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取保存的数据
     */
    public Object getSharedPreference(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }


    public String getStringKey(String key) {
        return (String) getSharedPreference(key, "");
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}
