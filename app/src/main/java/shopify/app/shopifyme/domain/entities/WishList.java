package shopify.app.shopifyme.domain.entities;

import java.util.List;

public class WishList {

    private List<Wish> items;

    public List<Wish> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WishList{");
        sb.append("items=").append(items);
        sb.append('}');
        return sb.toString();
    }

    public void add(final Wish wish) {
        items.add(wish);
    }
}
