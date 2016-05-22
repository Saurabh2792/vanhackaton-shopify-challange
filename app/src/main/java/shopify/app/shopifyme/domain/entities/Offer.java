package shopify.app.shopifyme.domain.entities;

public class Offer {

    public String wish, store;
    private int price;

    public String getWish() {
        return wish;
    }

    public String getStore() {
        return store;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("wish='").append(wish).append('\'');
        sb.append(", store='").append(store).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
