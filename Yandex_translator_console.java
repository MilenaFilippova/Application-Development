package com.company;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;


class Translate
{
    public String[] text;
    public String lang;

    @Override
    public String toString()
    {
        return "text=" + Arrays.toString(text) + ", lang=" + lang;
    }
}


public class Main {
    final static String API_KEY = "trnsl.1.1.20191025T051005Z.f02c134f529a2ab1.c4c931b5b40ad943515580bdebfb9c4b72a330ad";
    final static String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    public static void main(String[] args) throws IOException
    {

        System.out.println("Enter text");
        Scanner sc=new Scanner(System.in);
        String text = sc.nextLine();

        System.out.println("Enter lang from. For example en , if you want to translate from English.");
        String lang1 = sc.nextLine();

        System.out.println("Enter lang to. For example ru , if you want to translate into Russian.");
        String lang2 = sc.nextLine();

        String cod_text= URLEncoder.encode(text);

        URL url = new URL(API_URL + "?text="+cod_text+ "&format=plain&lang="+lang1+"-"+lang2+"&key="+API_KEY);

        InputStream stream=(InputStream) url.getContent();
        Scanner in = new Scanner(stream);
        String s = in.nextLine();

        Gson gson = new Gson();
        Translate tr = gson.fromJson(s, Translate.class);   //из всей строки берем только текст и язык

        System.out.println(tr);
    }
}
