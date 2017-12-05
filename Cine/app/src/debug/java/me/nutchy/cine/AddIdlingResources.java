package me.nutchy.cine;

import android.support.test.espresso.IdlingRegistry;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import okhttp3.OkHttpClient;

public class AddIdlingResources {
    public static void registerOkHttp3(OkHttpClient client) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("OkHttp", client));
    }
}
