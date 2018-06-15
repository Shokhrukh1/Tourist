package co.example.ui.restorePassword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.UIUtils;
import co.example.utils.validator.MultipleCallback;

import static co.example.ui.restorePassword.Constants.REG_EXP_EMAIL;

public class RestorePasswordActivity extends BaseActivity implements RestorePasswordView {
    @Inject
    RestorePasswordPresenter presenter;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @RegExp(value = REG_EXP_EMAIL, messageId = R.string.type_correct_email_address, order = 2)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.btnRestorePassword)
    Button btnRestorePassword;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.password_recovery);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxView.clicks(btnRestorePassword).subscribe(o -> {
            restorePassword();
        });

        etEmail.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                restorePassword();

                return true;
            }

            return false;
        });
    }

    private void restorePassword() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            UIUtils.closeKeyboard(this, btnRestorePassword);

            presenter.restorePassword(etEmail.getText().toString());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_restore_password;
    }

    @Override
    public void clearViews() {
        etEmail.setText("");
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
    public void showRestorePasswordSuccessful() {
        Toast.makeText(this, R.string.instructions_password_recovery_sent_to_email, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRestorePasswordFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
