
package gabrielgrs.com.br.provaidwall.service.api.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("user")
    private User user;

    public LoginResponse() {
    }

    public LoginResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                '}';
    }

    public static class User implements Serializable {

        @SerializedName("_id")
        private String id;

        @SerializedName("email")
        private String email;

        @SerializedName("token")
        private String token;

        @SerializedName("createdAt")
        private String createdAt;

        @SerializedName("updatedAt")
        private String updatedAt;

        @SerializedName("__v")
        private Long v;

        public User() {
        }

        public User(String id, String email, String token, String createdAt, String updatedAt, Long v) {
            this.id = id;
            this.email = email;
            this.token = token;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.v = v;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Long getV() {
            return v;
        }

        public void setV(Long v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", email='" + email + '\'' +
                    ", token='" + token + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", v=" + v +
                    '}';
        }
    }
}