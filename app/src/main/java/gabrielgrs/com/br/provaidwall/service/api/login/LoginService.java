package gabrielgrs.com.br.provaidwall.service.api.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/signup")
    Call<LoginResponse> login(@Body LoginDto loginDto);
}
