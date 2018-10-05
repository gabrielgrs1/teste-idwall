package gabrielgrs.com.br.provaidwall.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import gabrielgrs.com.br.provaidwall.DogsViewerApplication;


public class Utils {

    public static boolean isOnline() {
        Context context = DogsViewerApplication.getInstance();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (connectivityManager != null) {
            netInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (netInfo != null && netInfo.isConnected());
    }
}
