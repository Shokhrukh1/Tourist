package co.example.ui.activateDiscountCard;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;
import co.example.utils.PreferencesHelper;

/**
 * Created by Crish on 20.12.2017.
 */

public class ActivateDiscountCardPresenterImpl extends BasePresenterImpl<ActivateDiscountCardView> implements ActivateDiscountCardPresenter {
    private ApiManager apiManager;
    private Disposable disposable;
    private PreferencesHelper preferencesHelper;

    @Inject
    public ActivateDiscountCardPresenterImpl(ActivateDiscountCardView view, ApiManager apiManager, PreferencesHelper preferencesHelper) {
        super(view);
        this.apiManager = apiManager;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void activateDiscountCard(String serialNumber, String activationCode) {
        view.showLoadingDialog();

        disposable = apiManager.activateDiscountCard(preferencesHelper.getLanguage(), serialNumber, activationCode, "11ewpoweirpo")
                .subscribe(s -> {
                            JSONObject jsonObject = new JSONObject(s);

                            if (jsonObject.getInt("code") == 1) {
                                view.clearViews();
                                view.showActivateDiscountCardSuccessful();
                            } else {
                                view.showActivateDiscountCardFailed(jsonObject.getString("message"));
                            }

                            view.clearFocus();
                            view.hideLoadingDialog();
                        },
                        throwable -> {
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
