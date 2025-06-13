package bzh.duncan.http.response;

public class ResponseBody {
    private String bodyContent = "";

    public ResponseBody(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
}
