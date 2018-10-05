package gabrielgrs.com.br.provaidwall.service.api.feed;

import android.support.annotation.NonNull;

import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;
import gabrielgrs.com.br.provaidwall.service.api.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository implements IFeedService {

    private FeedServiceListener feedListener;
    private APIClient apiClient;
    private String token = DogsViewerApplication.getInstance().getUser().getUser().getToken();

    public FeedRepository(FeedServiceListener feedListener) {
        this.apiClient = DogsViewerApplication.getInstance().getApiClient();
        this.feedListener = feedListener;
    }

    @Override
    public void getLinkImages(String category) {
        feedListener.startLoading();

        category = verifyCategoryIsNotNull(category);
        verifyTokenIsValid();

        FeedService feedService = this.apiClient.getRetrofit().create(FeedService.class);
        Call<FeedDto> feedResponse = feedService.getLinkImages(category.toLowerCase(), token);

        feedResponse.enqueue(new Callback<FeedDto>() {
            @Override
            public void onResponse(@NonNull Call<FeedDto> call, @NonNull Response<FeedDto> response) {
                feedListener.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    feedListener.response(response.body());
                } else if (response.code() == 500) {
                    feedListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_server_error));
                } else if (response.code() == 401) {
                    feedListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_not_auth_error));
                } else if (response.code() != 200) {
                    feedListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_unkown_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedDto> call, @NonNull Throwable t) {
                feedListener.hideLoading();
                feedListener.serverError(DogsViewerApplication.getInstance().getString(R.string.generic_server_error));
            }
        });
    }

    private void verifyTokenIsValid() {
        if (token == null) {
            this.token = "";
        }
    }

    @NonNull
    private String verifyCategoryIsNotNull(String category) {
        if (category == null || category.isEmpty()) {
            category = "Husky";
        }
        return category;
    }

    public interface FeedServiceListener {
        void response(FeedDto feedDto);

        void startLoading();

        void hideLoading();

        void serverError(String message);
    }

}
