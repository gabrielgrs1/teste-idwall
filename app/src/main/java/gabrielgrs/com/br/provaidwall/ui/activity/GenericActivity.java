package gabrielgrs.com.br.provaidwall.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.irozon.sneaker.Sneaker;

import butterknife.ButterKnife;
import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;
import gabrielgrs.com.br.provaidwall.utils.Utils;

public abstract class GenericActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMethods();
        ButterKnife.bind(this);
    }

    protected void startMethods() {
        fragmentManager = getSupportFragmentManager();
        setLayout();
        loadingMethods();
    }

    protected void setHomeButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected boolean checkInternet() {
        if (!Utils.isOnline()) {
            Sneaker.with(this)
                    .setTitle(DogsViewerApplication.getInstance().getString(R.string.generic_erro_title))
                    .setDuration(8000)
                    .setIcon(R.drawable.ic_signal_wifi_off_black, Color.WHITE, false)
                    .setMessage(DogsViewerApplication.getInstance().getString(R.string.generic_no_connection_message))
                    .sneak(Color.DKGRAY);
            return false;
        }

        return true;
    }

    protected void buildGenericAlert(String title, String message, int duration, int color) {
        Sneaker.with(this)
                .setTitle(title)
                .setDuration(duration)
                .setMessage(message)
                .sneak(color);
    }

    public abstract void setLayout();

    public abstract void loadingMethods();
}
