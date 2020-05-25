package com.example.tutorial_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        EditText editText,editText2;
        Button btnAdd,btnUpdate,btnDelete,btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        btnSign = findViewById(R.id.btnSign);
        btnSign = findViewById(R.id.btnSign);

    }
}
