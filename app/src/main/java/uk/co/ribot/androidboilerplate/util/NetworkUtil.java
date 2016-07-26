package uk.co.ribot.androidboilerplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.adapter.rxjava.HttpException;

public class NetworkUtil {

    /**
     * Returns true if the Throwable is an instance of RetrofitError with an
     * http status code equals to the given one.
     */
    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return throwable instanceof HttpException
                && ((HttpException) throwable).code() == statusCode;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Returns an {@link Interceptor} instance that appends the query parameter
     * passed as an argument to every request made.
     *
     * <p>This method should be used whilst building an OkHttpClient instance that needs
     * to append a query parameter for every api call (e.g. Api Keys).
     * To build such OkHttpClient follow the next example:</p>
     *
     * OkHttpClient = new OkHttpClient.Builder()
     *      .addInterceptor(NetworkUtil.makeQueryInterceptor(key, value))
     *      .build();
     *
     * @param key A string containing the key associated with the query parameter
     * @param value A string containing the value of the query parameter
     * @return An Interceptor that adds the query parameter to each api call
     */
    @NonNull
    public static Interceptor makeQueryInterceptor(final String key, final String value) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl newUrl = originalUrl.newBuilder()
                        .addQueryParameter(key, value)
                        .build();

                Request.Builder newBuilder = originalRequest.newBuilder()
                        .url(newUrl);

                Request newRequest = newBuilder.build();
                return chain.proceed(newRequest);
            }
        };
    }

}
