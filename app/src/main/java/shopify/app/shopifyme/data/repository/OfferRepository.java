package shopify.app.shopifyme.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import shopify.app.shopifyme.data.api.OfferListApi;
import shopify.app.shopifyme.domain.entities.OfferList;

public class OfferRepository {

    @Singleton
    private OfferListApi offerListApi;

    @Inject
    public OfferRepository() {
        offerListApi = new OfferListApi();
    }

    public Observable<OfferList> get() {
        return offerListApi.get();
    }
}