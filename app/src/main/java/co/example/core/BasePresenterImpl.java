package co.example.core;

import android.os.Bundle;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public class BasePresenterImpl<T extends BaseView> implements Presenter {
    protected final T view;

    protected BasePresenterImpl(T view) {
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreateView(Bundle bundle) {

    }
}
