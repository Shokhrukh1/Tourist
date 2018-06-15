package co.example.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Crish on 20.12.2017.
 */

public class UIUtils {
    public static void closeKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showAlertDialog(Context context, int messageId, int textId, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(textId, listener)
                .create()
                .show();
    }
}