package sig.requests;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Builder;
import java.nio.file.Path;

import sig.exceptions.FailedResponseException;

public class POSTRequest extends GETRequest{

    String body = "";

    public POSTRequest(String url, String body, String username, String password, long timeout, Path file, String... headers) {
        super(url, username, password, timeout, file, headers);
        this.body=body;
    }

    public POSTRequest(String url, String body, long timeout, Path file, String... headers) {
        super(url, timeout, file, headers);
        this.body=body;
    }

    public POSTRequest(String url, String body, long timeout, String... headers) {
        super(url, timeout, headers);
        this.body=body;
    }

    public POSTRequest(String url, String body) {
        super(url);
        this.body=body;
    }

    @Override
    protected java.net.http.HttpRequest.Builder finalizeRequestPreBuild(
            java.net.http.HttpRequest.Builder requestBuild) throws FailedResponseException{
                requestBuild.headers("Content-Type","application/json");
        try {
            return file!=null?requestBuild.POST(HttpRequest.BodyPublishers.ofFile(file)):
            body.length()>0?requestBuild.POST(HttpRequest.BodyPublishers.ofString(body)):
            requestBuild.POST(HttpRequest.BodyPublishers.noBody());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new FailedResponseException("Could not complete request build. THIS SHOULD NOT BE HAPPENING!");
    }

    @Override
    protected Builder finalizeClientPreBuild(Builder clientBuild) {
        return clientBuild;
    }
    
}
