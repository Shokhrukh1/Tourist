package co.example.ui.homePage;

import javax.inject.Inject;

import co.example.core.BasePresenterImpl;
import co.example.network.ApiManager;

/**
 * Created by Portable-Acer on 06.12.2017.
 */

public class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    private ApiManager apiManager;

    @Inject
    public HomePresenterImpl(HomeView view, ApiManager apiManager) {
        super(view);
        this.apiManager = apiManager;
    }
}
