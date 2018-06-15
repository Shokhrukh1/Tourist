package co.example.ui.languages;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import co.example.core.BasePresenterImpl;
import co.example.model.Language;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 28.12.2017.
 */

public class LanguagesPresenterImpl extends BasePresenterImpl<LanguagesView> implements LanguagesPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;
    private List<Language> languages;

    @Inject
    public LanguagesPresenterImpl(LanguagesView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getLanguages() {
        view.showProgressBar();

        disposable = apiManager.getLanguages()
                .subscribe(s -> {
                    try {
                        List<Language> languages = new ArrayList<>();
                        JSONArray jsonLanguages = new JSONArray(s);

                        for (int i = 0; i < jsonLanguages.length(); i++) {
                            JSONObject jsonLanguage = jsonLanguages.getJSONObject(i);
                            Language tempLanguage = new Language();
                            tempLanguage.setLanguageId(jsonLanguage.getInt("language_id"));
                            tempLanguage.setTitle(jsonLanguage.getString("title"));
                            tempLanguage.setAbbr(jsonLanguage.getString("abbr"));
                            tempLanguage.setFlag(jsonLanguage.getString("flag"));
                            tempLanguage.setOrder(jsonLanguage.getInt("order"));
                            tempLanguage.setIcon(jsonLanguage.getString("icon"));

                            languages.add(tempLanguage);
                        }

                        this.languages = languages;
                        view.showLanguages(languages);
                        view.showData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {
                    Log.d("myLogs", throwable.getMessage());
                });
    }

    @Override
    public void setLanguages(int position) {
        preferencesHelper.setLanguage(languages.get(position).getLanguageId());
        view.showLanguageSaved();
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
