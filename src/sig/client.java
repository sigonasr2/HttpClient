package sig;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import sig.exceptions.FailedResponseException;
import sig.requests.GETRequest;
import sig.requests.POSTRequest;

public class client {
    public static void main(String[] args) {
        try {
            
            //Regular get request:
            GETRequest res = new GETRequest("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
            System.out.println(((HttpResponse<String>)res.run()).body());

            /*GET Block

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
            */

            /*
            //Regular POST request with body:
            POSTRequest postRes = new POSTRequest("https://postman-echo.com/post","Test body");
            System.out.println(((HttpResponse<String>)postRes.run()).body());

            //POST request with body and headers:
            postRes = new POSTRequest("https://postman-echo.com/post","Test body", 30000, "header1","value1", "header2","value2");
            System.out.println(((HttpResponse<String>)postRes.run()).body());*/

            POSTRequest postRes = new POSTRequest("https://postman-echo.com/post",Path.of(".gitignore"));
            System.out.println(((HttpResponse<String>)postRes.run()).body());


        } catch (FailedResponseException e) {
            e.printStackTrace();
        }
    }
}
