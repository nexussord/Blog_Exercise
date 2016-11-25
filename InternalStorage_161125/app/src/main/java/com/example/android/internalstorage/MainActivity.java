package com.example.android.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                String data = edit.getText().toString();
                edit.setText("");

                try {
                    //FileOutputStream 객체를 생성, 파일명은 data.txt, 새로운 텍스트를 추가하는 모드
                    FileOutputStream fos = openFileOutput("data.txt", Context.MODE_APPEND);

                    PrintWriter writer = new PrintWriter(fos);
                    writer.println(data);
                    writer.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                break;

            case R.id.btn_2:

                StringBuffer buffer = new StringBuffer();

                try {
                    FileInputStream fls = openFileInput("data.txt");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(fls));

                    String str = reader.readLine();

                    while (str != null) {
                        buffer.append(str + "\n");
                        str = reader.readLine();
                    }
                    text.setText(buffer.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
