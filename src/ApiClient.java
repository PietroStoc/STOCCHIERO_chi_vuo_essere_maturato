import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchQuestions(int amount, String difficulty, String type) {

        String url = "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET().build();

        HttpResponse<String> resp = null;
        try {
            resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e) {
            throw  new RuntimeException("Failed to fetch questions : " + e.getMessage());
        }
        if (resp == null) {
            throw new RuntimeException("No response received from API");
        }

        Gson gson = new Gson();

        ApiResponse response = gson.fromJson(resp.body(), ApiResponse.class);

        for (ApiQuestion q : response.results) {
            System.out.println(q.question);
            System.out.println("Risposta corretta : " + q.correct_answer);
        }

        return resp.body();
        }
}
