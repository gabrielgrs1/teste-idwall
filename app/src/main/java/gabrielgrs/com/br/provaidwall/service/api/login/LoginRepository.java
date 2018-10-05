package gabrielgrs.com.br.provaidwall.service.api.login;

import android.support.annotation.NonNull;
import android.util.Log;

import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;
import gabrielgrs.com.br.provaidwall.service.api.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository implements ILoginService {

    private LoginServiceListener loginListener;
    private APIClient apiClient;

    public LoginRepository(LoginServiceListener loginListener) {
        this.apiClient = DogsViewerApplication.getInstance().getApiClient();
        this.loginListener = loginListener;
    }

    @Override
    public void login(String email) {
        loginListener.startLoading();

        LoginService loginService = this.apiClient.getRetrofit().create(LoginService.class);
        Call<LoginResponse> loginResponse = loginService.login(new LoginDto(email));

        loginResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                loginListener.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    loginListener.response(response.body());
                    DogsViewerApplication.getInstance().setUser(response.body());
                } else if (response.code() == 400) {
                    loginListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_invalid_mail));
                } else if (response.code() == 500) {
                    loginListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_server_error));
                } else if (response.code() != 200) {
                    loginListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_unkown_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginListener.hideLoading();
                loginListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_server_error));
            }
        });
    }

    public interface LoginServiceListener {
        void response(LoginResponse loginResponse);

        void startLoading();

        void hideLoading();

        void serverError(String message);
    }

}
