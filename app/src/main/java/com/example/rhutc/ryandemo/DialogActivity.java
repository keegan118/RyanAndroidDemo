package com.example.rhutc.ryandemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.rhutc.ryandemo.dialog.CustomDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    private int checkedID;

    @BindView(R.id.radio_group)
    RadioGroup rg;

    @OnClick(R.id.select_dialog)
    public void onClick() {
        switch (checkedID) {
            case R.id.rb1:
                normalDialog();
                break;
            case R.id.rb2:
                listDialog();
                break;
            case R.id.rb3:
                singleChoiceDialog();
                break;
            case R.id.rb4:
                multiChoiceDialog();
                break;
            case R.id.rb5:
                waitingDialog();
                break;
            case R.id.rb6:
                progressDialog();
                break;
            case R.id.rb7:
                inputDialog();
                break;
            case R.id.rb8:
                customDialog();
                break;
            default:
        }
    }

    private void customDialog() {
        CustomDialog dialog = new CustomDialog(this, new CustomDialog.ICustomDialogEventListener() {
            @Override
            public void onClickListener() {
                toastShort("OK was selected");
                Intent i = new Intent();
                i.putExtra("message", "ViewPager");
                setResult(RESULT_OK, i);
                finish();
            }
        });
        dialog.show();
    }

    private void inputDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle("I'm an Input Dialog").setView(editText);
        inputDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort(editText.getText().toString());
            }
        });
        inputDialog.setNegativeButton("Cancel", null).show();
    }

    private void progressDialog() {
        final int MAX = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("Progress");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX) {
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    ProgressDialog waitingDialog;

    private void waitingDialog() {
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle("I'm a Waiting Dialog");
        waitingDialog.setMessage("Waiting...");
        waitingDialog.setCancelable(true);
        waitingDialog.show();
        waitingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                toastShort("The Waiting Dialog was canceled.");
            }
        });
    }

    ArrayList<String> choices = new ArrayList<>();

    private void multiChoiceDialog() {
        final String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        final boolean[] initChoiceSet = {false, false, false, false};
        choices.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a Multi Choice Dialog");
        builder.setMultiChoiceItems(items, initChoiceSet, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(items[which]);
                } else {
                    choices.remove(which);
                }
            }
        });
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                for (String s : choices) {
                    str = str + s + " ";
                }
                toastShort(str);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked cancel.");
            }
        });

        builder.show();
    }

    int choice = 0;

    private void singleChoiceDialog() {
        final String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a Single Choice Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You chose: " + choice);
            }
        });

        builder.show();
    }

    private void listDialog() {
        final String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm a List Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked: " + items[which]);
            }
        });

        builder.show();
    }

    private void normalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Alert Title");
        builder.setMessage("This is the normal Dialog");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked yes.");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked cancel.");
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked Neutral.");
            }
        });

        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                toastShort("You checked radio button: " + checkedId);
                checkedID = checkedId;
            }
        });
    }
}