package co.example.ui.languages;

import co.example.core.Presenter;

/**
 * Created by Crish on 28.12.2017.
 */

public interface LanguagesPresenter extends Presenter {
    void getLanguages();
    void setLanguages(int position);
}
