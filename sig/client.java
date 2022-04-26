package sig;
import java.net.http.HttpResponse;

import sig.exceptions.FailedResponseException;
import sig.requests.GETRequest;

public class client {
    public static void main(String[] args) {
        try {
            GETRequest res = new GETRequest("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
            System.out.println(((HttpResponse<String>)res.run()).body());
        } catch (FailedResponseException e) {
            e.printStackTrace();
        }
    }
}
