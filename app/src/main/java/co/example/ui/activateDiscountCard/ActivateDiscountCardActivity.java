package co.example.ui.activateDiscountCard;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import io.reactivex.functions.Consumer;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.utils.PreferencesHelper;
import co.example.utils.validator.MultipleCallback;

public class ActivateDiscountCardActivity extends BaseActivity implements ActivateDiscountCardView {
    @Inject
    ActivateDiscountCardPresenter presenter;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 1)
    @BindView(R.id.etActivationCode)
    EditText etActivationCode;
    @NotEmpty(messageId = R.string.this_field_must_be_filled, order = 2)
    @BindView(R.id.etSerialNumber)
    EditText etSerialNumber;
    @BindView(R.id.btnActivate)
    Button btnActivate;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.activate_discount_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxView.clicks(btnActivate).subscribe(o -> {
            activateDiscountCard();
        });

        etActivationCode.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                activateDiscountCard();

                return true;
            }

            return false;
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_activate_discount_card;
    }

    private void activateDiscountCard() {
        if (FormValidator.validate(this, new MultipleCallback())) {
            presenter.activateDiscountCard(
                    etSerialNumber.getText().toString(),
                    etActivationCode.getText().toString()
            );
        }
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
    public void showActivateDiscountCardSuccessful() {
        Toast.makeText(this, R.string.discount_card_activated, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showActivateDiscountCardFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearViews() {
        etSerialNumber.getText().clear();
        etActivationCode.getText().clear();
    }
}
