package gabrielgrs.com.br.provaidwall.service.api.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabrielgrs
 * Date: 03/10/18
 * Time: 1:05 PM
 * Project: ProvaIDwall
 */
public class LoginDto implements Parcelable {

    @SerializedName("email")
    private String email;

    LoginDto(String email) {
        this.email = email;
    }

    private LoginDto(Parcel in) {
        email = in.readString();
    }

    public static final Creator<LoginDto> CREATOR = new Creator<LoginDto>() {
        @Override
        public LoginDto createFromParcel(Parcel in) {
            return new LoginDto(in);
        }

        @Override
        public LoginDto[] newArray(int size) {
            return new LoginDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
    }
}
