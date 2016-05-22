package shopify.app.shopifyme.data.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shopify.app.shopifyme.data.parser.DateTimeTypeAdapter;

public class SimpleApi {

    final String BASE_URL = "https://shopifyme.herokuapp.com/";

    private final OkHttpClient mClient = new OkHttpClient.Builder().
            readTimeout(30, TimeUnit.SECONDS).
            connectTimeout(30, TimeUnit.SECONDS).
            build();


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