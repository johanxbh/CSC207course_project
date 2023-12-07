import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class chucknorrisjoke {
    public static void main(String[] args) {
        try {

            String apiUrl = "https://api.chucknorris.io/jokes/random";


            URL url = new URL(apiUrl);


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();


                System.out.println(response.toString());
            } else {

                System.out.println("API请求失败，状态码：" + responseCode);
            }


            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
