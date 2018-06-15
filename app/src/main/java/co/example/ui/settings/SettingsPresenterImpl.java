package co.example.ui.settings;

import javax.inject.Inject;

import co.example.core.BasePresenterImpl;

/**
 * Created by Crish on 28.12.2017.
 */

public class SettingsPresenterImpl extends BasePresenterImpl<SettingsView> implements SettingsPresenter {
    @Inject
    public SettingsPresenterImpl(SettingsView view) {
        super(view);
    }
}
