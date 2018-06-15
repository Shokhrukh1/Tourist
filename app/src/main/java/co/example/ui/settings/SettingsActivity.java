package co.example.ui.settings;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.ui.languages.LanguagesActivity;

public class SettingsActivity extends BaseActivity implements SettingsView {
    @Inject
    SettingsPresenter presenter;
    @BindView(R.id.cvLanguages)
    CardView cvLanguages;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RxView.clicks(cvLanguages).subscribe(o -> {
            startActivity(new Intent(this, LanguagesActivity.class));
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void getData() {

    }
}
