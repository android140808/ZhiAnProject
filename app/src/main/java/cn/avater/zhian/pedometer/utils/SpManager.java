package cn.avater.zhian.pedometer.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SpManager {

    public static final int DATA_STRING = 1;                                                        // string
    public static final int DATA_INT = 2;                                                           // int
    public static final int DATA_LONG = 3;                                                          // long
    public static final int DATA_BOOLEAN = 4;                                                       // boolean
    public static final int DATA_FLOAT = 5;                                                         // float
    public static int DEFAULT_INT_VALUE = -100;
    public static long DEFAULT_LONG_VALUE = -100L;
    public static float DEFAULT_FLOAT_VALUE = -100f;
    public static boolean DEFAULT_BOOLEAN_VALUE = false;
    private static SpManager manager;

    private SpManager() {
    }

    public static SpManager getInstance() {
        if (manager == null) {
            synchronized (SpManager.class) {
                if (manager == null) {
                    manager = new SpManager();
                }
            }
        }
        return manager;
    }

    private void setSpValue(String configKey, Object configValue) {
        if (AppContext.INSTANCE.getContext() != null) {
            SharedPreferences sp = AppContext.INSTANCE.getContext().getSharedPreferences(CommKeys.share_sp_name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            if (configValue.getClass() == String.class) {                                               // String类型
                String str = (String) configValue;
                editor.putString(configKey, str);
            } else if (configValue.getClass() == Integer.class) {                                       // Integer类型
                Integer i = (Integer) configValue;
                editor.putInt(configKey, i);
            } else if (configValue.getClass() == Boolean.class) {                                       // Boolean类型
                Boolean b = (Boolean) configValue;
                editor.putBoolean(configKey, b);
            } else if (configValue.getClass() == Long.class) {                                          // Long类型
                Long l = (Long) configValue;
                editor.putLong(configKey, l);
            } else if (configValue.getClass() == Float.class) {                                         // Float类型
                Float f = (Float) configValue;
                editor.putFloat(configKey, f);
            }
            editor.apply();
        }
    }

    private Object getSpValue(String configKey, int dataType) {
        SharedPreferences sp = null;
        if (AppContext.INSTANCE.getContext() != null) {
            sp = AppContext.INSTANCE.getContext().getSharedPreferences(CommKeys.share_sp_name, Context.MODE_PRIVATE);
        }
        if (sp != null) {
            switch (dataType) {
                case DATA_STRING:
                    return sp.getString(configKey, "");
                case DATA_INT:
                    return sp.getInt(configKey, -1);
                case DATA_LONG:
                    return sp.getLong(configKey, 0L);
                case DATA_BOOLEAN:
                    return sp.getBoolean(configKey, false);
                case DATA_FLOAT:
                    return sp.getFloat(configKey, 0.0f);
                default:
                    return null;
            }
        } else {
            return getDefaultValue(dataType);
        }
    }

    // 整型、长整型、浮点型默认返回-100，布尔默认返回false，其他默认返回null
    private Object getDefaultValue(int dataType) {
        switch (dataType) {
            case DATA_INT:
                return DEFAULT_INT_VALUE;
            case DATA_LONG:
                return DEFAULT_LONG_VALUE;
            case DATA_BOOLEAN:
                return DEFAULT_BOOLEAN_VALUE;
            case DATA_FLOAT:
                return DEFAULT_FLOAT_VALUE;
        }
        return null;
    }

}
