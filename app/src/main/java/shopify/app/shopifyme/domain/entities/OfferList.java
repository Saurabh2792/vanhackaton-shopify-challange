package shopify.app.shopifyme.domain.entities;

import java.util.List;

public class OfferList {

    List<Offer> items;

    public List<Offer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfferList{");
        sb.append("items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
