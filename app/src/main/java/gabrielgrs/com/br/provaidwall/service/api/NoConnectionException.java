package gabrielgrs.com.br.provaidwall.service.api;

import java.io.IOException;

import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;

public class NoConnectionException extends IOException {

    @Override
    public String getMessage() {
        return DogsViewerApplication.getInstance().getString(R.string.generic_no_connection_message);
    }
}
