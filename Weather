package com.company;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Scanner;


public class Main
{

    public static void main(String[] args) throws
            IOException, MalformedURLException, JSONException {

        String weather = "{\"coord\":{\"lon\":104.3,\"lat\":52.3},\"weather\":[{\"id\":741,\"main\":\"Fog\",\"description\":\"fog\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":272.15,\"pressure\":1021,\"humidity\":100,\"temp_min\":272.15,\"temp_max\":272.15},\"visibility\":750,\"wind\":{\"speed\":2,\"deg\":90},\"clouds\":{\"all\":90},\"dt\":1508470200,\"sys\":{\"type\":1,\"id\":7254,\"message\":0.0038,\"country\":\"RU\",\"sunrise\":1508456206,\"sunset\":1508493449},\"id\":2023469,\"name\":\"Irkutsk\",\"cod\":200}";

        JSONObject jdata = new JSONObject(weather);
        String name = jdata.getString("name");

        JSONObject main = jdata.getJSONObject("main");
        int temp_city = (int) (main.getInt("temp") - 273.15);  //перевод в гр. Цельсия
        int humidity_city = main.getInt("humidity");

        JSONObject sys = jdata.getJSONObject("sys");
        long sunrise_city = sys.getLong("sunrise");
        long sunset_city = sys.getLong("sunset");

        long longitude_city = sunset_city - sunrise_city;

        String sunrise_date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(sunrise_city * 1000));
        String sunset_date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(sunset_city * 1000));
        String longitude_date = new java.text.SimpleDateFormat(" HH:mm:ss").format(new java.util.Date(longitude_city * 1000));

        // 1) вывести название города, температуру (в гр. Цельсия) и влажность
        System.out.println("City - " + name + ", temp = " + temp_city + ", humidity = " + humidity_city + ";\n");

        //2) время восхода и заката, долготу дня
        System.out.println("In " + name + " sunrise - " + sunrise_date + ", sunset - " + sunset_date + ", longitude of the day - " +  longitude_date + ";\n");
    }
}
