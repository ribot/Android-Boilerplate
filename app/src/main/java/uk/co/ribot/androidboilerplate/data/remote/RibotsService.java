package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.util.MyGsonTypeAdapterFactory;

public interface RibotsService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RibotsService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RibotsService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RibotsService.class);
        }
    }
}
