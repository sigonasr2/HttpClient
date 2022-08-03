package sig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

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
                if (!ending&&temp.charAt(marker)!=' '&&temp.charAt(marker)!='{'&&temp.charAt(marker)!='}'&&temp.charAt(marker)!=',') {
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
            /*GETRequest res = new GETRequest("https://api.twitch.tv/helix/users?login=sigonitori",30000,"Authorization","Bearer vk5jyguposazau2gc1e0kjktv5xc4y","Client-Id","otppg8l1x7xbrfnqex1np1qba47mzf");
            System.out.println(((HttpResponse<String>)res.run()).body());*/
            HashMap<String,Object> obj = new HashMap<>();
            HashMap<String,Object> condition = new HashMap<>();
            HashMap<String,Object> transport = new HashMap<>();
            condition.put("broadcaster_user_id","39404108");
            transport.put("method","webhook");
            transport.put("callback","http://projectdivar.com/twitch/streamonline");
            transport.put("secret",SECRET);
            obj.put("type","stream.online");
            obj.put("version","1");
            obj.put("condition",condition);
            obj.put("transport",transport);

            /*
             * {"data":[{"id":"39404108","login":"sigonitori","display_name":"SigoNitori","type":"","broadcaster_type":"","description":"Hey there! I go by sigonasr2 or Sig. Japanese rhythm game nerd, dream is to have an arcade-inspired home setup for all my favs. I am also a hobbyist computer programmer. Been developing software since I was a kid and it's absolutely my passion. All things programming is right up my alley!","profile_image_url":"https://static-cdn.jtvnw.net/jtv_user_pictures/814bbf2a-edb2-458b-b402-6e457f5598ab-profile_image-300x300.png","offline_image_url":"https://static-cdn.jtvnw.net/jtv_user_pictures/22ce77ee-5900-4a7f-9e3e-ca28f19ded06-channel_offline_image-1920x1080.png","view_count":122300,"created_at":"2013-01-17T09:46:14Z"}]}
             */

            //Get a new twitch oauth token:
            //oauth: vk5jyguposazau2gc1e0kjktv5xc4y
            /*POSTRequest postRes = new POSTRequest("https://id.twitch.tv/oauth2/token","client_id=otppg8l1x7xbrfnqex1np1qba47mzf&client_secret="+SECRET+"&grant_type=client_credentials");
            postRes.setContentType("application/x-www-form-urlencoded");
            System.out.println(((HttpResponse<String>)postRes.run()).body());*/

            
            //POST request with body and headers:
            //POSTRequest postRes = new POSTRequest("https://api.twitch.tv/helix/eventsub/subscriptions",JSON(obj), 30000);
            //System.out.println(((HttpResponse<String>)postRes.run()).body());
            System.out.println(JSON(obj));

            //POST request with a file.
            //POSTRequest postRes = new POSTRequest("https://postman-echo.com/post",Path.of("..",".gitignore"));
            //System.out.println(((HttpResponse<String>)postRes.run()).body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
