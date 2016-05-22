package shopify.app.shopifyme.data.api.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import shopify.app.shopifyme.domain.entities.User;

public interface LoginService {

    String BASE_URL = "api/shoppers";

    @POST(BASE_URL)
    Call<Void> login(@Body User user);
}
