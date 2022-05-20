package sig.requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Builder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import sig.exceptions.FailedResponseException;

public class POSTRequest extends GETRequest{

    String body = "";
    Path uploadFile = null;

    public POSTRequest(String url, String body, String username, String password, long timeout, Path outputFile, String... headers) {
        super(url, username, password, timeout, outputFile, headers);
        this.body=body;
    }

    public POSTRequest(String url, String body, long timeout, Path outputFile, String... headers) {
        super(url, timeout, outputFile, headers);
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

    public POSTRequest(String url, Path uploadFile) {
        super(url);
        this.uploadFile=uploadFile;
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