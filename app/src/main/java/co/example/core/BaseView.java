package co.example.core;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

public interface BaseView {
    void showProgressBar();
    void showNoData();
    void showData();
    void showServerError();
    void showLoadingDialog();
    void hideLoadingDialog();
    void clearFocus();
}
