package uk.co.ribot.androidboilerplate.data.remote;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.Ribot;

public interface ApiService {

  @GET("ribots") Observable<List<Ribot>> getRibots();

  class Creator {
    public static ApiService newRibotsService(Retrofit retrofit) {
      return retrofit.create(ApiService.class);
    }
  }
}
