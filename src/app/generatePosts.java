package app;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class generatePosts {

    public String getFakePost() throws Exception {

        String apiUrl = "https://api.chucknorris.io/jokes/random";


        URL url = new URL(apiUrl);


        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(reader).getAsJsonObject();

            String joke = jsonObject.get("value").getAsString();
            connection.disconnect();
            return joke;
        } else {
            connection.disconnect();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        generatePosts a = new generatePosts();
        System.out.println(a.getFakePost());
    }

}

