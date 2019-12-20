package com.example.gameset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    //TextView q;
    //q = findViewById(R.id.question);
    MyAsyncTask myAsyncTask;
    ListView listView;
    ArrayAdapter<String> adapter ;
    EditText nickname;
    public String JSstring;
    public String JScardsRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nickname = findViewById(R.id.name);
        listView = findViewById(R.id.listView);
    }




    public void onClick(View v) {
        Register register = new Register();
        register.nickname = nickname.getText().toString();
        register.action = "register";
        myAsyncTask = new MyAsyncTask();
        Gson gson = new Gson();
        JSstring = gson.toJson(register);
        myAsyncTask.execute(JSstring);
        Log.i("GSON", JSstring);
    }


    class MyAsyncTask extends AsyncTask<String, Void, String> {

        String HOST = "194.176.114.21";
        String PORT = "8050";
        String set_server_url = "HOST" + ":" + "PORT";

        public String getData(String... string)
        {
            try {
                URL url = new URL(set_server_url);
                // creating connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true); // setting POST method

                // creating stream for writing request
                OutputStream out = urlConnection.getOutputStream();

                // String data = "{\"action\": \"register\", \"nickname\": \"Petya123\"}"; // Your JSON request here
                Log.d("mytag", JSstring); // выводим строку ,которую сформировали при регистрации
                out.write(JSstring.getBytes());


                Gson gson = new Gson();


                // Register- класс, созданный для структоры данных как в JSON-е
                SetToken token = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), SetToken.class);
                System.out.println(token);//ответ сервера
                urlConnection.disconnect();


                if(token.status.equals("ok"))
                {
                    //отправляем запрос на карточки
                    SetCards cardsRequest = new SetCards();

                    cardsRequest.action = "fetch_cards";
                    cardsRequest.token = token.tokenNumber;

                    JScardsRequest = new Gson().toJson(cardsRequest);

                    URL url2 = new URL(set_server_url);
                    // creating connection
                    HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                    urlConnection2.setDoOutput(true); // setting POST method
                    // creating stream for writing request
                    OutputStream out2 = urlConnection2.getOutputStream();

                    // String data = "{\"action\": \"register\", \"nickname\": \"Petya123\"}"; // Your JSON request here
                    Log.d("mytag", JScardsRequest); // выводим строку ,которую сформировали при регистрации
                    out2.write(JScardsRequest.getBytes());




                    Gson twoGson = new Gson();
                    GetCards cards = twoGson.fromJson(new InputStreamReader(urlConnection2.getInputStream()), GetCards.class);
                    System.out.println(token);  //ответ сервера
                    urlConnection.disconnect();
                    Log.d("mylog", "finish " + token);


                   

                    //выводим лист
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards.datacards);
                    listView.setAdapter(adapter);
                    
                   // setData();
                }
                else
                {
                    Log.d("mylog", "net otveta");
                }





                return String.valueOf(token);


            } catch (IOException e) {

                //Log.d("mytag", e.getLocalizedMessage()); // выводим ошибку в журнал
            }
            return "error";

        }


       /* public void setData(){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, cards.datacards);
            listView.setAdapter(adapter);

        }*/
        @Override
        protected String doInBackground(String... params)
        {
            return String.valueOf(getData());
        }




    }


}

