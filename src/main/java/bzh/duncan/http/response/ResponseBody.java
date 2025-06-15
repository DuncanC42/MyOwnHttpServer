package bzh.duncan.http.response;

public class ResponseBody {
    private String bodyContent = "";
    private byte[] binaryContent = null;
    private boolean isBinary = false;

    // Constructeur pour contenu texte
    public ResponseBody(String bodyContent) {
        this.bodyContent = bodyContent;
        this.isBinary = false;
    }

    // Constructeur pour contenu binaire (gzip)
    public ResponseBody(byte[] binaryContent) {
        this.binaryContent = binaryContent;
        this.isBinary = true;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
        this.isBinary = false;
        this.binaryContent = null;
    }

    public byte[] getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(byte[] binaryContent) {
        this.binaryContent = binaryContent;
        this.isBinary = true;
        this.bodyContent = "";
    }

    public boolean isBinary() {
        return isBinary;
    }

    public long getContentLength() {
        if (isBinary && binaryContent != null) {
            return binaryContent.length;
        } else if (!isBinary && bodyContent != null) {
            return bodyContent.getBytes().length;
        }
        return 0;
    }
}