package shopify.app.shopifyme.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;

import shopify.app.shopifyme.domain.entities.User;

public class ShopifyMeSharedPreferences {
    private Context instance;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static final String SHOPIFYME = "shopifyMe";

    private static final String USER = "user";
    private static final String TOKEN = "token";

    private static ShopifyMeSharedPreferences shopifyMeSharedPreferences;

    public ShopifyMeSharedPreferences(final Context context) {
        instance = context;

        prefs = instance.getSharedPreferences(SHOPIFYME, instance.MODE_PRIVATE);
        editor = instance.getSharedPreferences(SHOPIFYME, instance.MODE_PRIVATE).edit();
    }


    public static ShopifyMeSharedPreferences setInstance(final Context context) {
        if (shopifyMeSharedPreferences == null)
            shopifyMeSharedPreferences = new ShopifyMeSharedPreferences(context);
        return shopifyMeSharedPreferences;
    }

    public static ShopifyMeSharedPreferences getInstance() {
        return shopifyMeSharedPreferences;
    }

    private void saveOldPreferences() {
        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            editor.putString(entry.getKey(), prefs.getString(entry.getKey(), null));
        }
    }

    public boolean saveUser(final User user) {
        saveOldPreferences();
        editor.putString(USER, new Gson().toJson(user));
        return editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public User user() {
        return new Gson().fromJson(prefs.getString(USER, null), User.class);
    }

    public boolean isLogged() {
        return prefs.contains(USER);
    }

    public boolean saveToken(final String token) {
        saveOldPreferences();
        editor.putString(TOKEN, token);
        return editor.commit();
    }

    public String token() {
        return prefs.getString(TOKEN, null);
    }
}
