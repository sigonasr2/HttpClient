package sig.requests;
import sig.exceptions.FailedResponseException;

public class DELETERequest extends GETRequest{

    public DELETERequest(String url, long timeout, String...headers) {
        super(url, timeout, headers);
    }
    protected java.net.http.HttpRequest.Builder finalizeRequestPreBuild(java.net.http.HttpRequest.Builder requestBuild) throws FailedResponseException {
        return requestBuild.DELETE();
    }
}
