package shopify.app.shopifyme.domain.entities.listeners;

import shopify.app.shopifyme.domain.entities.Wish;

public interface OnAddWish {
    void add(Wish wish);
}
