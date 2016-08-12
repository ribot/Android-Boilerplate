package com.incendiary.androidboilerplate.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
import com.incendiary.androidboilerplate.data.remote.ApiService;

/**
 * Created by esafirm on 7/27/16.
 */
@Module public class NetworkModule {

  private static final String ENDPOINT = "https://api.ribot.io/";

  @Singleton @Provides Gson provideGson() {
    return new GsonBuilder().registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create();
  }

  @Singleton @Provides OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor(message -> Timber.d(message));
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder().connectTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @Singleton @Provides Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
    return new Retrofit.Builder().client(okHttpClient)
        .baseUrl(ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  /* --------------------------------------------------- */
  /* > Retrofit Interfaces*/
  /* --------------------------------------------------- */

  @Singleton @Provides public static ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }
}
