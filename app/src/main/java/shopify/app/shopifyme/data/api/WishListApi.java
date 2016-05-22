package shopify.app.shopifyme.data.api;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import shopify.app.shopifyme.data.api.service.WishlistService;
import shopify.app.shopifyme.domain.entities.WishList;

public class WishListApi extends ShopifyMeApi {

    @Singleton
    private WishlistService wishlistService;

    @Inject
    public WishListApi() {
        wishlistService = retrofit().create(WishlistService.class);
    }

    public Observable<WishList> get() {
        return Observable.create(subscriber -> {
            Call<WishList> wishListCall = wishlistService.get();

            Log.i("wishListCall.get", wishListCall.request().toString());

            wishListCall.enqueue(new Callback<WishList>() {
                @Override
                public void onResponse(Call<WishList> call, Response<WishList> response) {
                    Log.i("response", "" + response.isSuccess());
                    Log.i("response", "" + response.code());
                    if (response.isSuccess()) {
                        subscriber.onNext(response.body());
                        subscriber.onCompleted();
                    } else {
                        Log.e("wishListCall.update", "" + response.code());
                        subscriber.onError(null);
                    }
                }

                @Override
                public void onFailure(Call<WishList> call, Throwable t) {
                    subscriber.onError(t);
                }
            });
        });
    }

    public Observable<WishList> update(final WishList wishList) {
        return Observable.create(subscriber -> {
            Call<WishList> wishListCall = wishlistService.update(wishList);

            Log.i("wishListCall.update", wishListCall.request().toString());

            wishListCall.enqueue(new Callback<WishList>() {

                @Override
                public void onResponse(Call<WishList> call, Response<WishList> response) {
                    if (response.isSuccess()) {
                        subscriber.onNext(response.body());
                        subscriber.onCompleted();
                    } else {
                        Log.e("wishListCall.update", "" + response.code());
                        subscriber.onError(null);
                    }
                }

                @Override
                public void onFailure(Call<WishList> call, Throwable t) {
                    subscriber.onError(t);
                }
            });
        });
    }
}