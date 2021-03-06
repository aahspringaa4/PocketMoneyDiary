package com.example.pocketmoneydiary.Memo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoneydiary.ButtomFragment.HelpActivity;
import com.example.pocketmoneydiary.R;

import java.util.Calendar;

public class MemoScanActivity extends AppCompatActivity {

    Button help;
    TextView topic;
    ImageButton back;
    private int Request_Re = 202;
    public static Button nowaday;
    Button save;
    public static int a = 0;
    public static EditText memo;
    public static String str, stt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_scan);

        help = findViewById(R.id.help);
        back = (ImageButton) findViewById(R.id.back);
        topic = (TextView) findViewById(R.id.textView2);
        nowaday = (Button) findViewById(R.id.nowaday);
        save = (Button) findViewById(R.id.save);
        memo = (EditText) findViewById(R.id.memo);
        back.bringToFront();
        topic.bringToFront();

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MemoScanActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nowaday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int nYear = c.get(Calendar.YEAR);
                int nMon = c.get(Calendar.MONTH);
                int nDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener mDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                nowaday.setText(String.valueOf(year) + "??? " + String.valueOf(monthOfYear + 1) + "??? " + String.valueOf(dayOfMonth) + "???");
                            }
                        };

                DatePickerDialog oDialog = new DatePickerDialog(MemoScanActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        mDateSetListener, nYear, nMon, nDay);
                oDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 0;
                if (memo.getText().length() > 0 && nowaday.getText().length() > 0) {
                    String str = nowaday.getText().toString();
                    String stt = memo.getText().toString();
                    MemoActivity.post();
                    a++;
                    finish();
                } else {
                    Toast.makeText(MemoScanActivity.this, "?????? ?????? ?????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}