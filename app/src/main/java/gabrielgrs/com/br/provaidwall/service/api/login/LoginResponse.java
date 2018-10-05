
package gabrielgrs.com.br.provaidwall.service.api.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @SerializedName("user")
    private User user;

    private LoginResponse(Parcel in) {
        user = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(user, i);
    }

    public static class User implements Parcelable {

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

        User(Parcel in) {
            id = in.readString();
            email = in.readString();
            token = in.readString();
            createdAt = in.readString();
            updatedAt = in.readString();
            if (in.readByte() == 0) {
                v = null;
            } else {
                v = in.readLong();
            }
        }

        public static final Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(email);
            parcel.writeString(token);
            parcel.writeString(createdAt);
            parcel.writeString(updatedAt);
            if (v == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeLong(v);
            }
        }
    }
}