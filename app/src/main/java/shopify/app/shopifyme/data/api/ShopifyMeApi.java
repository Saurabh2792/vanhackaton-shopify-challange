package shopify.app.shopifyme.data.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;
import shopify.app.shopifyme.data.parser.DateTimeTypeAdapter;

public class ShopifyMeApi extends SimpleApi {

    private final OkHttpClient mClient = new OkHttpClient.Builder().
            readTimeout(30, TimeUnit.SECONDS).
            connectTimeout(30, TimeUnit.SECONDS).
            addNetworkInterceptor(new HeaderInterceptor()).
            build();


    @Override
    public Retrofit retrofit() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .registerTypeAdapter(java.util.Date.class, new DateTimeTypeAdapter())
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build();
    }

    @Override
    public Retrofit retrofit(final JsonDeserializer deserializer) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .registerTypeAdapter(java.util.Date.class, deserializer)
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build();
    }
}

class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.
                newBuilder().
                addHeader("Authorization", ShopifyMeSharedPreferences.getInstance().user().authorization()).
                build();
        Response response = chain.proceed(request);

        return response;
    }
}