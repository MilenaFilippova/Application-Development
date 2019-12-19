package com.example.gameset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    //TextView q;
    //q = findViewById(R.id.question);
    EditText name;
    public String JSstring;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);

    }

    

    public void onClick(View v)
    {
        Register register = new Register();
        register.name = name.getText().toString();
        register.action = "action";

        Gson gson = new Gson();
        JSstring = gson.toJson(register);
        Log.i("GSON", JSstring);
    }
}
