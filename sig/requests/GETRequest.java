package sig.requests;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.time.Duration;

import sig.exceptions.FailedResponseException;

public class GETRequest{
    String url;
    String[] headers;
    long timeout;
    Path file;
    private HttpRequest req;
    private HttpClient client;
    /**
     * @param file The file path info, use this for file downloads or set to null for standard text.
     * @param timeout in milliseconds
     * */
    public GETRequest(String url, long timeout, Path file, String...headers){
        this.url = url;
        this.headers = headers;
        this.timeout = timeout;
        this.file=file;
        build();
    }
    /**
     * @param timeout in milliseconds
     * */
    public GETRequest(String url, long timeout, String...headers){
        this(url,timeout,null,headers);
    }
    public GETRequest(String url){
        this(url,30000,"default","default");
    }
    public HttpResponse<?> run() throws FailedResponseException {
        try {
            if (file==null) {
                return client.send(req,BodyHandlers.ofString());
            } else {
                return client.send(req,BodyHandlers.ofFile(file));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new FailedResponseException("No proper response returned. THIS SHOULD NOT BE HAPPENING!");
    }
    private void build(){
        try {
            req = HttpRequest.newBuilder(new URI(url))
            .version(HttpClient.Version.HTTP_2)
            .headers(headers)
            .timeout(Duration.ofMillis(timeout))
            .GET().build();
            client = HttpClient.newBuilder().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}