import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpClient;

public class client {
    public static void main(String[] args) {
        try {
            HttpRequest req = HttpRequest.newBuilder(new URI("https://postman-echo.com/get"))
            .version(HttpClient.Version.HTTP_2)
            .GET().build();

            HttpClient client = HttpClient.newBuilder().build();
            
            HttpResponse<String> response = client.send(req,BodyHandlers.ofString());

           System.out.println(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
