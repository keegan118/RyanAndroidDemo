package com.example.rhutc.ryandemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;

import com.example.rhutc.ryandemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialog2 extends Dialog {

    private ICustomDialogEventListener2 listener;

    @BindView(R.id.custom_dialog_rg)
    RadioGroup rg;

    RadioGroup radiogroup = (RadioGroup)findViewById(R.id.custom_dialog_rg);

    @OnClick(R.id.select)
    public void onClickSelectButton(){

        // NOT WORKING
//        listener.onClickOk(radiogroup.getCheckedRadioButtonId());

        // WORKING
        listener.onClickOk(rg.getCheckedRadioButtonId());
        dismiss();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel(){
        listener.onClickCancel();
        dismiss();
    }

    public interface ICustomDialogEventListener2 {
        public void onClickCancel();
        public void onClickOk(int radioID);
    }

    public CustomDialog2(@NonNull Context context, ICustomDialogEventListener2 listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog2);
        ButterKnife.bind(this);
    }
}