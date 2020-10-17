package com.example.poojaapp;

import android.app.Application;
import android.content.res.Configuration;

import com.yariksoffice.lingver.Lingver;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLanguage();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initLanguage();
    }

    private void initLanguage() {
        String lang = LanguageHelper.getUserLanguage(this);
        if (lang != null)
            Lingver.init(this, lang);
        else
            Lingver.init(this, "en");
    }
}