package com.incendiary.androidboilerplate.data.remote;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import com.incendiary.androidboilerplate.data.model.Ribot;

public interface ApiService {

  @GET("ribots") Observable<List<Ribot>> getRibots();

  class Creator {
    public static ApiService newRibotsService(Retrofit retrofit) {
      return retrofit.create(ApiService.class);
    }
  }
}
