package gabrielgrs.com.br.provaidwall.service.api.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabrielgrs
 * Date: 03/10/18
 * Time: 1:05 PM
 * Project: ProvaIDwall
 */
public class LoginDto {

    @SerializedName("email")
    private String email;

    public LoginDto() {
    }

    public LoginDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
