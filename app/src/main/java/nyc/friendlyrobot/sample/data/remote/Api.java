package nyc.friendlyrobot.sample.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import nyc.friendlyrobot.sample.data.model.GsonAdaptersModel;
import nyc.friendlyrobot.sample.util.DateDeserializer;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface Api {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Object>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static Api newRibotsService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new GsonAdaptersModel())
                    .registerTypeAdapter(Date.class,new DateDeserializer())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(Api.class);
        }
    }
}
