package shopify.app.shopifyme.presentation.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shopify.app.shopifyme.data.repository.LoginRepository;
import shopify.app.shopifyme.data.repository.OfferRepository;
import shopify.app.shopifyme.data.repository.WishRepository;

@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(final AndroidApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    LoginRepository provideLoginRepository() {
        return new LoginRepository();
    }

    @Provides
    @Singleton
    WishRepository provideWishListRepository() {
        return new WishRepository();
    }

    @Provides
    @Singleton
    OfferRepository provideOfferListRepository() {
        return new OfferRepository();
    }

}
