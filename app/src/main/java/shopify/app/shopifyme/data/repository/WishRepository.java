package shopify.app.shopifyme.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import shopify.app.shopifyme.data.api.WishListApi;
import shopify.app.shopifyme.domain.entities.WishList;

public class WishRepository {

    @Singleton
    private WishListApi wishListApi;

    @Inject
    public WishRepository() {
        wishListApi = new WishListApi();
    }

    public Observable<WishList> get() {
        return wishListApi.get();
    }

    public Observable<WishList> update(final WishList wishList) {
        return wishListApi.update(wishList);
    }
}
