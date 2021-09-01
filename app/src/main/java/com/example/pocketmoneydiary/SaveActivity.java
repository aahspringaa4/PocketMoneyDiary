package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16, commit;
    public static TextView textView, textView2;
    int num1 = 0;
    int num2 = 0;
    int result = 0;
    boolean checkOper = false, plus = false, minus = false, gob = false, nanu = false;

    char c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        textView = findViewById(R.id.result_tv);
        textView2 = findViewById(R.id.textView2);
        btn1 = findViewById(R.id.one_btn);
        btn2 = findViewById(R.id.two_btn);
        btn3 = findViewById(R.id.three_btn);
        btn4 = findViewById(R.id.equal_btn);
        btn5 = findViewById(R.id.four_btn);
        btn6 = findViewById(R.id.five_btn);
        btn7 = findViewById(R.id.six_btn);
        btn8 = findViewById(R.id.nanu_btn);
        btn9 = findViewById(R.id.seven_btn);
        btn10 = findViewById(R.id.eight_btn);
        btn11 = findViewById(R.id.nine_btn);
        btn12 = findViewById(R.id.gob_btn);
        btn13 = findViewById(R.id.double_zero);
        btn14 = findViewById(R.id.zero_btn);
        btn15 = findViewById(R.id.plus_btn);
        btn16 = findViewById(R.id.del);
        commit = findViewById(R.id.commit);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper) {
                    num2 = Integer.parseInt(textView.getText().toString());
                    textView2.append(Integer.toString(num2));
                    textView2.append("=");
                    textView.setText(null);
                    checkOper = true;
                    if(plus){
                        textView.append(Integer.toString(num1+num2));
                        plus = false;
                    }
                    else if(minus){
                        textView.append(Integer.toString(num1-num2));
                        minus = false;
                    }
                    else if(gob){
                        textView.append(Integer.toString(num1*num2));
                        gob = false;
                    }
                    else if (nanu) {
                        textView.append(Integer.toString(num1/num2));
                        nanu = false;
                    }
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("4");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("5");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("6");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkOper) {
                    onClickMethod(" / ");
                    nanu = true;
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("7");
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("8");
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("9");
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkOper) {
                    onClickMethod(" x ");
                    gob = true;
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("00");
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkOper){
                    textView.setText(null);
                }
                textView.append("0");
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkOper) {
                    onClickMethod(" + ");
                    plus = true;
                }
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOper = false;
                textView.setText(null);
                textView2.setText(null);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!textView.getText().toString().equals("")) {
//                    Intent intent = new Intent(NumberPadActivity.this, MainActivity.class);
//                    intent.putExtra("str", String.valueOf(textView));
//                    Log.d("결과", "성공");
//                    finish();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "값을 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void onClickMethod(String message) {
        num1 = Integer.parseInt(textView.getText().toString());
        textView2.append(Integer.toString(num1));
        textView2.append(message);
        checkOper = true;
    }
}