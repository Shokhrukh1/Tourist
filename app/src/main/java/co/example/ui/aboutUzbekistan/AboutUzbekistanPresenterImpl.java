package co.example.ui.aboutUzbekistan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.model.AboutUzbekistan;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 11.12.2017.
 */

public class AboutUzbekistanPresenterImpl extends BasePresenterImpl<AboutUzbekistanView> implements AboutUzbekistanPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public AboutUzbekistanPresenterImpl(AboutUzbekistanView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getAboutUzbekistan() {
        view.showProgressBar();

        disposable = apiManager.getAboutUzbekistan(preferencesHelper.getLanguage())
                .subscribe(s -> {
                    List<AboutUzbekistan> aboutUzbekistanList1 = new ArrayList<>();

                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(s);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            AboutUzbekistan aboutUzbekistan = new AboutUzbekistan();
                            aboutUzbekistan.setPageId(jsonObject.getLong("page_id"));
                            aboutUzbekistan.setImageUrl(jsonObject.getString("image"));
                            aboutUzbekistan.setTemplate(jsonObject.getString("template"));
                            aboutUzbekistan.setTitle(jsonObject.getString("title"));
                            aboutUzbekistan.setText(jsonObject.getString("text"));

                            aboutUzbekistanList1.add(aboutUzbekistan);
                        }

                        view.showAboutUzbekistan(aboutUzbekistanList1);
                        view.showData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, throwable -> {

                });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}
