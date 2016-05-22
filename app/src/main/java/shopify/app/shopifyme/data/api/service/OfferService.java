package shopify.app.shopifyme.data.api.service;

import retrofit2.Call;
import retrofit2.http.GET;
import shopify.app.shopifyme.domain.entities.OfferList;

public interface OfferService {

    String BASE_URL = "api/offers";

    @GET(BASE_URL)
    Call<OfferList> get();
}
