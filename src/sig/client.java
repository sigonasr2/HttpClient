package sig;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import sig.exceptions.FailedResponseException;
import sig.requests.GETRequest;
import sig.requests.POSTRequest;

public class client {
    static String SECRET = "";

    private static String JSON(HashMap<String, Object> testMap) {
        StringBuilder sb = new StringBuilder();
        String temp = testMap.toString();
        if (temp.charAt(0)=='{') {
            sb.append("{");
            int marker=1;
            boolean ending=false;
            while (marker<temp.length()) { 
                if (!ending&&temp.charAt(marker)!=' '&&temp.charAt(marker)!='{'&&temp.charAt(marker)!='}') {
                    ending=true;
                    sb.append("\"");
                } else 
                if (ending&&(temp.charAt(marker)=='='||temp.charAt(marker)==','||temp.charAt(marker)=='}')) {
                    ending=false;
                    sb.append("\"");
                }
                if (!ending&&temp.charAt(marker)=='=') {
                    sb.append(':');
                } else {
                    sb.append(temp.charAt(marker));
                }
                marker++;
            }
        } else {
            throw new UnsupportedOperationException("Not valid JSON!");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            SECRET = Files.readAllLines(Path.of("..",".clientsecret")).get(0);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            
            //Regular get request:
            //GETRequest res = new GETRequest("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
            //System.out.println(((HttpResponse<String>)res.run()).body());

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
            /*GETRequest res = new GETRequest("https://api.twitch.tv/helix/users?login=sigonitori",30000,"Client-Id","otppg8l1x7xbrfnqex1np1qba47mzf");
            System.out.println(((HttpResponse<String>)res.run()).body());*/
            HashMap<String,Object> obj = new HashMap();
            obj.put("type","stream.online");
            obj.put("version","1");
            obj.put("version","1");

            //Regular POST request with body:
            POSTRequest postRes = new POSTRequest("https://id.twitch.tv/oauth2/token","client_id=otppg8l1x7xbrfnqex1np1qba47mzf&client_secret="+SECRET+"&grant_type=client_credentials");
            postRes.setContentType("application/x-www-form-urlencoded");
            System.out.println(((HttpResponse<String>)postRes.run()).body());

            /*
            //POST request with body and headers:
            postRes = new POSTRequest("https://postman-echo.com/post","Test body", 30000, "header1","value1", "header2","value2");
            System.out.println(((HttpResponse<String>)postRes.run()).body());*/

            //POST request with a file.
            //POSTRequest postRes = new POSTRequest("https://postman-echo.com/post",Path.of("..",".gitignore"));
            //System.out.println(((HttpResponse<String>)postRes.run()).body());
        } catch (FailedResponseException e) {
            e.printStackTrace();
        }
    }
}
