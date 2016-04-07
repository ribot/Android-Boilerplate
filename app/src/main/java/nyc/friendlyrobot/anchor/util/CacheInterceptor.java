package nyc.friendlyrobot.anchor.util;

import android.support.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 206847 on 1/16/16.
 */

public class CacheInterceptor implements Interceptor {
    private final NetworkStatus networkStatus;

    @Inject
    public CacheInterceptor(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder request = originalRequest.newBuilder();
        if (originalRequest.header("fresh") != null) {
            request.cacheControl(CacheControl.FORCE_NETWORK);
        }
        Response response = chain.proceed(request.build());
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl())
                .build();
    }

    @NonNull
    private String cacheControl() {
        String cacheHeaderValue;
        if (networkStatus.isOnGoodConnection()) {
            cacheHeaderValue = "public, max-age=2419200";
        } else {
            cacheHeaderValue = "public, only-if-cached, max-stale=2419200";
        }
        return cacheHeaderValue;
    }
}
