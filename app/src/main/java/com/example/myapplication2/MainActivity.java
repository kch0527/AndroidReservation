package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private Button btnDate, btnTime;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);

    }

    public void onClick(View view) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        if (view == btnDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    btnDate.setText(i + "/" + (i1 + 1) + "/" + i2);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (view == btnTime) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            btnTime.setText(i + ":" + i1);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("앱의 세계로 온것을 뜨겁게 환영합니다.");
            progressDialog.setCancelable(true); //뒤로가기
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            doInitialTask();
        }
    }
    private void doInitialTask(){
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                progressDialog.dismiss();
            }
        };
        handler.sendEmptyMessageDelayed(0, 5 * 1000);

    }


}