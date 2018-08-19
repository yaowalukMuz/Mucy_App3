package com.luckky.yaowaluk.learnstartup;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello,tvCalculate;
    EditText etHello,etOper1,etOper2;
    Button btnCopy,btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();

    }


    private void initInstances(){
        tvHello = findViewById(R.id.tvHello);

        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>He<u>ll</u><i>World</i><font color=\"#ff0000\">:: </font><a href=\"http://service.eternity.co.th/mmth\">http://service.eternity.co.th/mmth</a>"));


        etHello = findViewById(R.id.etHello);
        etHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    /// get value for edittext show textview
                    tvHello.setText(etHello.getText());

                    return true;
                }
                return false;
            }
        });

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        etOper1 = findViewById(R.id.et1);
        etOper2 = findViewById(R.id.et2);
        tvCalculate = findViewById(R.id.tv1);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val1 = 0;
                int val2 = 0;
                int  sum;
                try {
                     val1 = Integer.parseInt(String.valueOf(etOper1.getText()));

                } catch (NumberFormatException e) {
                    Log.d("TAG", "exception e");
                }
                try {

                    val2 = Integer.parseInt(String.valueOf(etOper2.getText()));
                } catch (NumberFormatException e) {
                    Log.d("TAG", "exception e");
                }
                sum = val1 + val2;
                tvCalculate.setText("=" + sum + "");
            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view == btnCopy){
            tvHello.setText(etHello.getText());
        }
       /* if (view == btnCalculate) {

        }*/
    }
}
