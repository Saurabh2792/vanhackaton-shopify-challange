package shopify.app.shopifyme.presentation;

import android.app.Activity;
import android.content.Intent;

import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;
import shopify.app.shopifyme.presentation.view.activity.LoginActivity;

public class Actions {


    public static void logout(final Activity activity) {
        ShopifyMeSharedPreferences.getInstance().clear();
        to(activity, LoginActivity.class, true);
    }

    public static void to(final Activity activity, final Class clazz, boolean finish) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        if (finish)
            activity.finish();
    }
}