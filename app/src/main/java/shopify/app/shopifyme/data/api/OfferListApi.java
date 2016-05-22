package shopify.app.shopifyme.data.api;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import shopify.app.shopifyme.data.api.service.OfferService;
import shopify.app.shopifyme.domain.entities.OfferList;

public class OfferListApi extends ShopifyMeApi {

    @Singleton
    private OfferService offerService;

    @Inject
    public OfferListApi() {
        offerService = retrofit().create(OfferService.class);
    }

    public Observable<OfferList> get() {
        return Observable.create(subscriber -> {
            Call<OfferList> wishListCall = offerService.get();

            Log.i("wishListCall.get", wishListCall.request().toString());

            wishListCall.enqueue(new Callback<OfferList>() {
                @Override
                public void onResponse(Call<OfferList> call, Response<OfferList> response) {
                    Log.i("response", ""+response.isSuccess());
                    Log.i("response", ""+response.code());
                    if (response.isSuccess()) {
                        subscriber.onNext(response.body());
                        subscriber.onCompleted();
                    } else {
                        Log.e("wishListCall.update", "" + response.code());
                        subscriber.onError(null);
                    }
                }

                @Override
                public void onFailure(Call<OfferList> call, Throwable t) {
                    subscriber.onError(t);
                }
            });
        });
    }

}
