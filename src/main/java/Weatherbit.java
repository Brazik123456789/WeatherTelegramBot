import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Weatherbit {
    public static String getWeather(String message, Model model) throws IOException {

        String URL_1 = "https://api.weatherbit.io/v2.0/current?city=";
        String URL_2 = "&key=25844ad0de42419692b8bd87e466440a";
        URL_1 += URLEncoder.encode(message, "UTF-8");

        URL url = new URL(URL_1 + URL_2);
        Scanner scanner = new Scanner((InputStream) url.getContent());

        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        JSONObject jsonObject = new JSONObject(result);
        JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
        model.setName(data.getString("city_name"));
        model.setTemp(data.getDouble("temp"));
        model.setHumidity(data.getDouble("rh"));
        model.setPressure(data.getInt("pres") * 0.750063755419211);

        return "Weatherbit: \n" +
                "Город: " + model.getName() + "\n" +
                "Температура " + model.getTemp() + " °С \n" +
                "Влажность " + model.getHumidity() + " % \n" +
                "Давление " + String.format("%.3f",model.getPressure()) + " мм. рт. ст.";
    }
}
