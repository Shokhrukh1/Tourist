package co.example.ui.signUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import co.example.utils.PreferencesHelper;
import co.example.utils.UIUtils;
import co.example.utils.validator.MultipleCallback;

import static co.example.ui.signUp.Constants.REG_EXP_EMAIL;
import static co.example.ui.signUp.Constants.LOGIN_REG_EX;
import static co.example.ui.signUp.Constants.REG_EXP_PHONE;

public class SignUpActivity extends BaseActivity implements SignUpView {
    @Inject
    SignUpPresenter presenter;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @MinLength(value = 2, messageId = R.string.allowable_characters_2_35, order = 2)
    @MaxLength(value = 35, messageId = R.string.allowable_characters_2_35, order = 3)
    @RegExp(value = LOGIN_REG_EX, messageId = R.string.can_use_latin_alphabet_and_numbers, order = 4)
    @BindView(R.id.etLogin)
    EditText etLogin;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 5)
    @RegExp(value = REG_EXP_EMAIL, messageId = R.string.type_correct_email_address, order = 6)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 7)
    @MinLength(value = 2, messageId = R.string.allowable_characters_2_35, order = 8)
    @MaxLength(value = 35, messageId = R.string.allowable_characters_2_35, order = 9)
    @RegExp(value = LOGIN_REG_EX, messageId = R.string.can_use_latin_alphabet_and_numbers, order = 10)
    @BindView(R.id.etName)
    EditText etName;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 11)
    @RegExp(value = REG_EXP_PHONE, messageId = R.string.enter_correct_phone, order = 12)
    @BindView(R.id.etPhone)
    EditText etPhone;
    @MaxLength(value = 255, messageId = R.string.maximum_number_of_characters_255, order = 13)
    @BindView(R.id.etAddress)
    EditText etAddress;
    @NotEmpty(messageId = R.string.password_can_not_be_empty, order = 14)
    @MinLength(value = 4, messageId = R.string.password_must_be_at_least_4_characters, order = 15)
    @MaxLength(value = 15, messageId = R.string.password_must_not_be_more_than_15_characters, order = 16)
    @BindView(R.id.etPassword)
    EditText etPassword;
    @NotEmpty(messageId = R.string.password_can_not_be_empty, order = 17)
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.sign_up);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxView.clicks(btnSignUp).subscribe(o -> {
            signUp();
        });

        etConfirmPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signUp();

                return true;
            }

            return false;
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void clearViews() {
        etLogin.setText("");
        etEmail.setText("");
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etPassword.setText("");
        etConfirmPassword.setText("");
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {

    }

    private void signUp() {
        if (isValid()) {
            UIUtils.closeKeyboard(this, btnSignUp);

            presenter.signUp(
                    etLogin.getText().toString(),
                    etEmail.getText().toString(),
                    etName.getText().toString(),
                    etPhone.getText().toString(),
                    etAddress.getText().toString(),
                    etPassword.getText().toString()
            );
        }
    }

    private boolean isValid() {
        boolean result = FormValidator.validate(this, new MultipleCallback());

        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(getString(R.string.passwords_are_different));

            return false;
        }

        return result;
    }
}
