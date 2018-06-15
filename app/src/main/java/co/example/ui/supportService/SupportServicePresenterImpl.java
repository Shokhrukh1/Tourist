package co.example.ui.supportService;

import javax.inject.Inject;

import co.example.core.BasePresenterImpl;

/**
 * Created by Crish on 14.12.2017.
 */

public class SupportServicePresenterImpl extends BasePresenterImpl<SupportServiceView> implements SupportServicePresenter {
    @Inject
    public SupportServicePresenterImpl(SupportServiceView view) {
        super(view);
    }
}
