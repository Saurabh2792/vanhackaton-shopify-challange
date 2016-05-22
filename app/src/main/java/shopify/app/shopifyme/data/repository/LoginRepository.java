package shopify.app.shopifyme.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import shopify.app.shopifyme.data.api.LoginApi;

public class LoginRepository {
    @Singleton
    private LoginApi loginApi;

    @Inject
    public LoginRepository() {
        loginApi = new LoginApi();
    }

    public Observable<Void> login(final String username, final String password) {
        return loginApi.login(username, password);
    }
}
