package com.company;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {

        String[] vals = getCsvVals("http://localhost:8888/");
        System.out.println("x is: " + vals[0]);
        System.out.println("y is: " + vals[1]);
        System.out.println("z is: " + vals[2]);
        System.out.println("time is: " + vals[3]);

    }

    // returns csv vals with a get request, returns null if can connect
    // note, only returns up to a newline
    public static String[] getCsvVals(String urlString){

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = in.readLine()) != null){
                String[] split = line.split(",");
                return split;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
