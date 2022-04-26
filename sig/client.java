package sig;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import sig.exceptions.FailedResponseException;
import sig.requests.GETRequest;
public class client {
    public static void main(String[] args) {
        try {
            GETRequest res = new GETRequest("https://postman-echo.com/get");
            System.out.println(((HttpResponse<String>)res.run()).body());
        } catch (FailedResponseException e) {
            e.printStackTrace();
        }
    }
}
