package shopify.app.shopifyme.domain.entities;

public class User {

    private String email, password, token;

    public User(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String authorization() {
        return "Basic " + base64Encode(email + ":" + password);
    }

    private String base64Encode(final String toEncode) {
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(toEncode.getBytes()));
    }
}
