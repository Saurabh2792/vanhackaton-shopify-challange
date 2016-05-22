package shopify.app.shopifyme.presentation.application;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;
import shopify.app.shopifyme.data.repository.LoginRepository;
import shopify.app.shopifyme.data.repository.OfferRepository;
import shopify.app.shopifyme.data.repository.WishRepository;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(Activity activity);

    LoginRepository provideLoginRepository();
    WishRepository provideWishListRepository();
    OfferRepository provideOfferListRepository();

}
