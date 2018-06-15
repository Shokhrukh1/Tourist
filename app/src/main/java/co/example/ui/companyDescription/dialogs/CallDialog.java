package co.example.ui.companyDescription.dialogs;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.example.R;

import static co.example.ui.companyDescription.Constants.PHONES;

/**
 * Created by Crish on 26.12.2017.
 */

public class CallDialog extends DialogFragment {
    public static CallDialog newInstance(List<String> phones) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PHONES, (ArrayList<String>) phones);

        CallDialog callDialog = new CallDialog();
        callDialog.setArguments(bundle);

        return callDialog;
    }

    @BindView(R.id.rgPhones)
    RadioGroup rgPhones;
    @BindView(R.id.btnCall)
    Button btnCall;
    private List<String> phones;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        phones = getArguments().getStringArrayList("PHONES");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_call, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        unbinder = ButterKnife.bind(this, view);

        for (int i = 0; i < phones.size(); i++) {

            RadioButton chbPhone = new RadioButton(getContext());
            chbPhone.setText(phones.get(i));

            rgPhones.addView(chbPhone);

            if (i == 0) {
                chbPhone.setChecked(true);
            }
        }

        RxView.clicks(btnCall).subscribe(o -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + ((RadioButton) rgPhones.findViewById(rgPhones.getCheckedRadioButtonId())).getText()));

            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
