import java.util.Date;

/**
* Log class.
 * */
public class Log {
    private String requestBuffer;
    private String responseBuffer;
    private String date;
    private int counter;
    /**
     * log requests.
     * @param counter counter
     * @param requestBuffer  the request
     *                       @param responseBuffer  response
     * */
    public Log(String requestBuffer, String responseBuffer, int counter) {
        this.requestBuffer = requestBuffer;
        this.responseBuffer = responseBuffer;
        this.counter = counter;
        this.date = getUTCDate();
    }
    /**
     * override toString.
     * @return str
     * */
    @Override
    public String toString() {
        String str;
        str = "\r\nNo. " + counter + " Received Request\r\n" + "\nReceived Time: " + date + "\r\nContent: " + requestBuffer + "\r\n";
        str = str + "\r\n" + "Response: " + responseBuffer + "\r\n";
        str = str + "******************************************\r\n";
        return str;
    }
    /**
     * get UTC.
     * */
    private String getUTCDate() {
        /**
         * get current time.
         * then get the time difference
         * finally transfer it
         * */
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        date = "UTC:" + new Date(cal.getTimeInMillis());
        return date;
    }
}
