import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            // 设置API的URL
            String apiUrl = "https://api.chucknorris.io/jokes/random";

            // 创建URL对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取响应状态码
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                // 读取响应数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 打印API响应
                System.out.println(response.toString());
            } else {
                // 响应错误处理
                System.out.println("API请求失败，状态码：" + responseCode);
            }

            // 关闭连接
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
