/**
 * httpResponse class.
 * */
public class httpResponse {
    private String protocl;
    private String statusCode;
    private String metadata;
    private String contentLen;
    private String contentType;
    private String body;
    private String content;
    private int length;
    /**
     * The response format.
     * @param content content
     * @param contentLen the file length
     * @param contentType the file type
     * @param flag flag is the code
     * @param protocl is the http version
     */
    public httpResponse(String protocl, int flag, String contentType, String contentLen, String content) {
        this.protocl = protocl;
        this.metadata = "Server: Simple Java Http Server";
        this.contentType = contentType;
        this.contentLen = contentLen;
        this.content = content;
        if (flag == Numbers.NOT_FOUND) {
            this.statusCode = "404 Not Found";
            this.body = "response message in this case containing " + " xxx bytes of error message as an " + this.contentType + " page";
            length = this.body.length();
            this.body = "response message in this case containing " + length + " bytes of error message as an " + this.contentType + " page";
            this.contentLen = String.valueOf(length);
        } else if (flag == Numbers.OK) {
            this.statusCode = "200 OK";
            this.body = "The " + contentType + " page from the file in this case containing " + this.contentLen + " bytes";
        } else if (flag == Numbers.NOT_IMPLEMENTED) {
            this.statusCode = "501 Not Implemented";
            this.body = "response message in this case containing " + " xxx bytes of error message";
            length = this.body.length();
            this.body = "response message in this case containing " + length + " bytes of error message";
            this.contentLen = String.valueOf(length);
        } else if (flag == Numbers.METHOD_NOT_ALLOWED) {
            this.statusCode = "405 Method Not Allowed";
            this.body = "response message in this case containing " + " xxx bytes of not allowed message";
            length = this.body.length();
            this.body = "response message in this case containing " + length + " bytes of not allowed message";
            this.contentLen = String.valueOf(length);
        } else if (flag == Numbers.CREATED) {
            this.statusCode = "201 Created";
            this.body = "The " + this.contentType + " URI is created with " + this.contentLen + " bytes";
        } else if (flag == Numbers.ACCEPTED) {
            this.statusCode = "202 Accepted";
            this.body = "The Request is accepted but not implemented";
        }
        if (this.contentType.equals("html") || this.contentType.equals("htm")) {
            this.contentType = "text/html";
        } else if (this.contentType.equals("txt")) {
            this.contentType = "text/plain";
        } else if (this.contentType.equals("jpg") || this.contentType.equals("jpeg")) {
            this.contentType = "image/jpeg";
        } else if (this.contentType.equals("gif")) {
            this.contentType = "image/gif";
        } else if (this.contentType.equals("png")) {
            this.contentType = "image/png";
        } else {
            this.contentType = "others";
        }
    }
    /**
     * override toString.
     * @return the response
     * */
    public String toString() {
        String strHeader = protocl + " " + statusCode + "\r\n" + metadata + "\r\nContent-Type: " + contentType + "\r\nContent-Length: " + contentLen + "\r\n";
        String strBody = body;
        String str;
        if (content == null || content.isEmpty()) {
            str = strHeader + strBody + "\r\n";
        } else {
            str = strHeader + strBody + "\r\n\r\n" + content + "\r\n";
        }
        return str;
    }
}
