package shopify.app.shopifyme.domain.entities;

public class Wish {

    private String name;

    public Wish() {
    }

    public Wish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wish{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
