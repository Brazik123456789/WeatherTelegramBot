import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class OpenWeather {
    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=85b1acc10d34f3227bbb78286691761a");
        Scanner scanner = new Scanner((InputStream) url.getContent());

        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        JSONObject jsonObject = new JSONObject(result);
        model.setName(jsonObject.getString("name"));
        JSONObject main = jsonObject.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        model.setPressure(main.getInt("pressure"));


        return "OpenWeather: \n" +
                "Город: " + model.getName() + "\n" +
                "Температура " + model.getTemp() + " °С \n" +
                "Влажность " + model.getHumidity() + " % \n" +
                "Давление " + model.getPressure() + " мб";
    }
}
