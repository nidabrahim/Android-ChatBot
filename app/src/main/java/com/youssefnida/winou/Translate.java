package com.youssefnida.winou;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 */
public class Translate {


    public Translate() {}

    String translte(String text, String from, String to) {

        StringBuilder result = new StringBuilder();

        try {
            String encodedText = URLEncoder.encode(text, "UTF-8");
            String urlStr = "http://www.transltr.org/api/translate?text=" + encodedText + "&to=" + to + "&from=" + from;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream stream;
            if (conn.getResponseCode() == 200) //success
            {
                stream = conn.getInputStream();
            } else
                stream = conn.getErrorStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result.toString());

            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    String translatedText = obj.get("translationText").getAsString();
                    return translatedText;
                }
            }

            if (conn.getResponseCode() != 200) {
                System.err.println(result);
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return result.toString();
    }



}


