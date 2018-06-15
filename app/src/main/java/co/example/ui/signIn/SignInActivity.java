package co.example.ui.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MaxLength;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.ui.restorePassword.RestorePasswordActivity;
import co.example.ui.signUp.SignUpActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.UIUtils;
import co.example.utils.validator.MultipleCallback;

import static co.example.ui.signIn.Constants.LOGIN_REG_EX;

public class SignInActivity extends BaseActivity implements SignInView {
    @Inject
    SignInPresenter presenter;
    @NotEmpty(messageId = R.string.login_can_not_be_empty, order = 1)
    @MinLength(value = 2, messageId = R.string.login_must_be_at_least_2_characters, order = 2)
    @MaxLength(value = 35, messageId = R.string.login_must_not_be_more_than_35_characters, order = 3)
    @RegExp(value = LOGIN_REG_EX, messageId = R.string.can_use_latin_alphabet_and_numbers, order = 4)
    @BindView(R.id.etLogin)
    EditText etLogin;
    @NotEmpty(messageId = R.string.password_can_not_be_empty)
    @MinLength(value = 4, messageId = R.string.password_must_be_at_least_4_characters, order = 5)
    @MaxLength(value = 15, messageId = R.string.password_must_not_be_more_than_15_characters, order = 6)
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.sign_in);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxView.clicks(btnSignIn).subscribe(o -> {
            UIUtils.closeKeyboard(this, btnSignIn);
            signIn();
        });

        RxView.clicks(tvForgotPassword).subscribe(o -> {
            UIUtils.closeKeyboard(this, tvForgotPassword);
            startActivity(new Intent(getBaseContext(), RestorePasswordActivity.class));
        });

        RxView.clicks(btnSignUp).subscribe(o -> {
            UIUtils.closeKeyboard(this, btnSignUp);
            //startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
        });

        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            UIUtils.closeKeyboard(this, v);

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signIn();

                return true;
            }

            return false;
        });
    }

    private void signIn() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            presenter.signIn(
                    etLogin.getText().toString(),
                    etPassword.getText().toString()
            );
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void clearViews() {
        etLogin.setText("");
        etPassword.setText("");
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {

    }

    @Override
    public void showSignInSuccessful() {
        Toast.makeText(this, R.string.sign_in_successful, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSignInFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
