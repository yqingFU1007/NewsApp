package com.example.asa.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryUtils {

    public static ArrayList<News> parseNewsDate(String urlString) {
        ArrayList<News> news = new ArrayList<>();
        try {
            String jsonResponse = getJsonResponse(urlString);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject jsonObjectResponse = jsonObject.getJSONObject("response");
            JSONArray jsonArrayResults = jsonObjectResponse.getJSONArray("results");
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                if (i == 3 || i == 5 || i == 6) continue;
                JSONObject jsonObjectSub = jsonArrayResults.getJSONObject(i);
                String sectionName = jsonObjectSub.getString("sectionName");
                String title = jsonObjectSub.getString("webTitle");
                String timeFull = jsonObjectSub.getString("webPublicationDate");
                String url = jsonObjectSub.getString("webUrl");
                news.add(new News(title, sectionName, timeFull, url));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Get JSONObject failed.");
        }

        return news;
    }

    private static String getJsonResponse(String urlString) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e("QueryUtiles", "MalformedURLException.");
        }
        String jsonResponse = "";
        InputStream inputStream;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e("QueryUtils", "ERROR open connection!", e);
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
