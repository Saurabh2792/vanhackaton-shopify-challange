package shopify.app.shopifyme.presentation.application;

import android.app.Application;

import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;


public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    public void initializeInjector() {
        ShopifyMeSharedPreferences.setInstance(this);
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
