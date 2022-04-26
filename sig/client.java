package sig;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import sig.exceptions.FailedResponseException;
import sig.requests.GETRequest;

public class client {
    public static void main(String[] args) {
        try {

            //Regular get request:
            GETRequest res = new GETRequest("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
            System.out.println(((HttpResponse<String>)res.run()).body());

            //Download to file:
            //GETRequest res = new GETRequest("https://postman-echo.com/get?foo1=bar1&foo2=bar2",30000,Paths.get("test.html"),"default","value");
            //System.out.println((Path)((HttpResponse<Path>)res.run()).body());

            //Get request with headers:
            res = new GETRequest("https://postman-echo.com/headers",30000,"test-header1","value1","test-header2","value2");
            System.out.println(((HttpResponse<String>)res.run()).body());

            //Get request with headers:
            res = new GETRequest("https://postman-echo.com/response-headers?foo1=bar1&foo2=bar2",30000,"test-header1","value1","test-header2","value2");
            System.out.println(((HttpResponse<String>)res.run()).body());

            //Get request with authentication:
            res = new GETRequest("https://postman-echo.com/basic-auth","postman","password",30000,null);
            System.out.println(((HttpResponse<String>)res.run()).body());
        } catch (FailedResponseException e) {
            e.printStackTrace();
        }
    }
}
