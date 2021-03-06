package com.example.pocketmoneydiary.ButtomFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketmoneydiary.Main.MainActivity;
import com.example.pocketmoneydiary.R;

import java.io.InputStream;
import java.util.Calendar;

public class ScanActivity extends AppCompatActivity {

    Button btn_2;
    ImageView iv_main;
    private static final int REQUEST_CODE = 0;
    AlertDialog.Builder builder;
    private static TextView year;
    ImageButton back;
    Button nowaday;
    Button cate, commit;
    String[] cates;
    EditText Money;
    TextView totalm;
    public static int a = 0;
    public static String str = null;
    private static TextView month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        back = (ImageButton) findViewById(R.id.back);
        year = (TextView) findViewById(R.id.year);
        month = (TextView) findViewById(R.id.month);
        day = (TextView) findViewById(R.id.day);
        nowaday = (Button) findViewById(R.id.nowaday);
        cate = (Button) findViewById(R.id.cate);
        Money = (EditText) findViewById(R.id.Money);
        Money = (EditText) findViewById(R.id.Money);
        commit = findViewById(R.id.commit);
        totalm = (TextView) findViewById(R.id.totalm);
        iv_main = findViewById(R.id.iv_main);
        btn_2 = findViewById(R.id.help);

        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT); // ????????? ??????
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanActivity.this, HelpActivity.class);
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

                DatePickerDialog oDialog = new DatePickerDialog(ScanActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        mDateSetListener, nYear, nMon, nDay);
                oDialog.show();
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 0;
                if (!Money.getText().toString().equals("")) {
                    str = String.valueOf(Money.getText());
                    finish();
                    a++;
                    MainActivity.tempBoolean2 = true;
                } else {
                    Toast.makeText(getApplicationContext(), "????????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        totalm.setText("$ " + MainActivity.SumMoney.getText());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent Money) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) Money.getX(), y = (int) Money.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(Money);
    }

    public void showDialog() {
        cates = getResources().getStringArray(R.array.cate);

        builder = new AlertDialog.Builder(ScanActivity.this);

        builder.setTitle("????????????");

        // ?????????????????? ????????? ??????

        builder.setItems(cates, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                cate.setText(cates[which]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    iv_main.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "?????? ????????? ?????????????????????.", Toast.LENGTH_LONG).show();
            }
        }
    }
}