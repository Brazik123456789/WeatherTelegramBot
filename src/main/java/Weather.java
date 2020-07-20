import org.json.JSONObject;
import sun.plugin2.message.Message;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //  85b1acc10d34f3227bbb78286691761a
    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=85b1acc10d34f3227bbb78286691761a");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String result = "";
        while (in.readLine() != null) {
            result += in.readLine();
        }

        JSONObject jsonObject = new JSONObject(result);

        model.setName(jsonObject.getString("name"));
        JSONObject main = new JSONObject(jsonObject.get("main"));
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

//        return result;

        return "Город:" + model.getName() +"\n" +
                "Температура" + model.getTemp() + "\n" +
                "Влажность" + model.getHumidity();

    }
}
