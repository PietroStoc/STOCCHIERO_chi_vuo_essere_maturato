import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        System.out.println(apiClient.fetchQuestions(10, "easy", "multiple"));

        String playerName = "Test";
        int correctAnswers = 0;
        boolean used5050 = false;
        boolean usedAudience = false;

        PlayerStatistics stats = new PlayerStatistics(
                playerName,
                correctAnswers,
                used5050,
                usedAudience
        );

        Gson gson = new Gson();
        try(FileWriter writer = new FileWriter(playerName + "_stats.json")) {
            gson.toJson(stats, writer);
            System.out.println("Statistiche salvate correttamente in " + playerName + "_stats.json");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio delle statistiche: " + e.getMessage());
        }
    }
}



