package gabrielgrs.com.br.provaidwall;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import gabrielgrs.com.br.provaidwall.service.api.APIClient;
import gabrielgrs.com.br.provaidwall.service.api.login.LoginResponse;

public class DogsViewerApplication extends Application {

    private static DogsViewerApplication dogsViewerApplication;
    private APIClient apiClient;
    private LoginResponse user;

    @Override
    public void onCreate() {
        super.onCreate();

        dogsViewerApplication = this;
        setApiClient();
    }

    private void setApiClient() {
        apiClient = new APIClient(BuildConfig.SERVER_URL);
    }

    public synchronized static DogsViewerApplication getInstance() {
        return dogsViewerApplication;
    }

    public LoginResponse getUser() {
        return user;
    }

    public void setUser(LoginResponse user) {
        this.user = user;
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public APIClient getApiClient() {
        return apiClient;
    }

}
