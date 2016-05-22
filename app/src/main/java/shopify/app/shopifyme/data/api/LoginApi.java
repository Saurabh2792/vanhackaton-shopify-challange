package shopify.app.shopifyme.data.api;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;
import shopify.app.shopifyme.data.api.service.LoginService;
import shopify.app.shopifyme.domain.entities.User;

public class LoginApi extends SimpleApi {

    @Singleton
    private LoginService loginService;

    @Inject
    public LoginApi() {
        loginService = retrofit().create(LoginService.class);
    }

    public Observable<Void> login(final String username, final String password) {
        return Observable.create(subscriber -> {
            final User user = new User(username, password, ShopifyMeSharedPreferences.getInstance().token());
            Call<Void> loginCall = loginService.login(user);

            Log.i("loginCall", loginCall.request().body().toString());

            loginCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccess()) {
                        ShopifyMeSharedPreferences.getInstance().saveUser(user);
                        subscriber.onCompleted();

                    } else
                        subscriber.onError(null);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    subscriber.onError(t);
                }
            });

        });
    }
}
