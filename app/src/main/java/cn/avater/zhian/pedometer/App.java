package cn.avater.zhian.pedometer;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import cn.avater.zhian.pedometer.utils.AppContext;

public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.INSTANCE.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
