package cn.avater.zhian.pedometer.utils;

import android.content.Context;

public enum AppContext {
    INSTANCE;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
