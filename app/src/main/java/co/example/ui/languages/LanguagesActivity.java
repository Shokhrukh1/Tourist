package co.example.ui.languages;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.core.ClickableBaseAdapter;
import co.example.model.Language;
import co.example.ui.languages.adapters.LanguageAdapter;
import co.example.utils.PreferencesHelper;
import co.example.utils.Utils;

public class LanguagesActivity extends BaseActivity implements LanguagesView, ClickableBaseAdapter.OnItemClickListener<Language> {
    @Inject
    LanguagesPresenter presenter;
    @BindView(R.id.rvLanguages)
    RecyclerView rvLanguages;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle(R.string.languages);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_languages;
    }

    @Override
    public void showLanguages(List<Language> languages) {
        LanguageAdapter adapter = new LanguageAdapter(languages);

        rvLanguages.setLayoutManager(new LinearLayoutManager(this));
        rvLanguages.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClicked(int position) {
        presenter.setLanguages(position);
    }

    @Override
    public void onItemClicked(Language item) {
    }

    @Override
    public void showLanguageSaved() {
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getData() {
        if (Utils.isNetworkAvailable(this)) {
            presenter.getLanguages();
        } else {
            showNoNetwork();
        }
    }
}
