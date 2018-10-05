package gabrielgrs.com.br.provaidwall.service.api.feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FeedService {

    @GET("/feed")
    Call<FeedDto> getLinkImages(@Query("category") String category, @Header("authorization") String token);
}
