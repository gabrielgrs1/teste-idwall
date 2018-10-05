package gabrielgrs.com.br.provaidwall.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.irozon.sneaker.Sneaker;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;
import gabrielgrs.com.br.provaidwall.service.api.login.LoginRepository;
import gabrielgrs.com.br.provaidwall.service.api.login.LoginResponse;
import gabrielgrs.com.br.provaidwall.utils.validator.ValidateEmail;

public class LoginActivity extends GenericActivity implements LoginRepository.LoginServiceListener {

    private LoginActivity mContext = this;
    @BindView(R.id.login_progress_progressbar)
    ProgressBar mLoginProgressbar;

    @BindView(R.id.login_login_inputlayout)
    TextInputLayout mEmailInputLayout;

    @BindView(R.id.login_login_edittext)
    EditText mEmailEditText;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void loadingMethods() {
    }

    @Override
    public void response(LoginResponse loginResponse) {
        Intent goToFeed = new Intent(mContext, FeedActivity.class);
        startActivity(goToFeed);
    }

    @Override
    public void startLoading() {
        mLoginProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoginProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void serverError(String message) {
        Sneaker.with(mContext)
                .setTitle(DogsViewerApplication.getInstance().getString(R.string.generic_erro_title))
                .setDuration(8000)
                .setMessage(message)
                .sneakError();
    }

    @OnClick(R.id.login_signup_button)
    void login() {
        final ValidateEmail validateEmail = new ValidateEmail(mEmailInputLayout);

        if (validateEmail.isValid() && checkInternet()) {
            loginService();
        }
    }

    @OnFocusChange(R.id.login_login_edittext)
    void focusLostLogin(boolean hasFocus) {
        if (!hasFocus) {
            new ValidateEmail(mEmailInputLayout).isValid();
        } else {
            mEmailInputLayout.setError(null);
        }
    }

    private void loginService() {
        String email = Objects.requireNonNull(mEmailEditText.getText().toString());

        new LoginRepository(mContext).login(email);
    }

}
