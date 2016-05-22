package shopify.app.shopifyme.data.api.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import shopify.app.shopifyme.domain.entities.WishList;

public interface WishlistService {

    String BASE_URL = "api/wishlist";

    @POST(BASE_URL)
    Call<WishList> update(@Body WishList wishList);

    @GET(BASE_URL)
    Call<WishList> get();
}
