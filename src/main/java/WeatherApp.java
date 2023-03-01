import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "239497cdfe7e49c1ba6194400232802";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // TODO: Write main function
    public static void main(String[] args) {

        do {
            clearScreen();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String wheather_data=  getWeatherData(input);

            if (wheather_data != null){
                System.out.println("humidity: "+getHumidity(wheather_data));
                System.out.print("temperature: "+getTemperature(wheather_data));
                break;
            }
            else{
                System.out.print("It didn't found. Enter again: ");
            }

        }while (true);




    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        double answer = 0.0;

        JSONObject j = new JSONObject(weatherJson);
        answer= j.getJSONObject("current").getInt("temp_c");
        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject j = new JSONObject(weatherJson);
        answer= j.getJSONObject("current").getInt("humidity");
        return answer;
    }
}
