package co.example.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Crish on 29.12.2017.
 */

public class Utils {
    public static float convertPxToDp(Context context, float value) {
        return value / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }

    public static int convertLangTextFormatToLangId(String lang) {
        int langId;

        switch (lang) {
            case "ru":
                langId = 1;
                break;
            case "en":
                langId = 2;
                break;
            case "it":
                langId = 3;
                break;
            case "de":
                langId = 4;
                break;
            case "fr":
                langId = 5;
                break;
            case "ja":
                langId = 6;
                break;
            case "zh":
                langId = 7;
                break;
            case "es":
                langId = 8;
                break;
            case "ko":
                langId = 9;
                break;
            case "tr":
                langId = 10;
                break;
            default:
                langId = 2;
        }

        return langId;
    }

    public static String convertLangIdToTextFormat(int langId) {
        String lang;

        switch (langId) {
            case 1:
                lang = "ru";
                break;
            case 2:
                lang = "en";
                break;
            case 3:
                lang = "it";
                break;
            case 4:
                lang = "de";
                break;
            case 5:
                lang = "fr";
                break;
            case 6:
                lang = "ja";
                break;
            case 7:
                lang = "zh";
                break;
            case 8:
                lang = "es";
                break;
            case 9:
                lang = "ko";
                break;
            case 10:
                lang = "tr";
                break;
            default:
                lang = "en";
        }

        return lang;
    }
}
