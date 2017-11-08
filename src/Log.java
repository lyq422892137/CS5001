import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String buffer;
    private String date;
    private int counter;
    public Log(String buffer, int counter) {
        this.buffer = buffer;
        this.counter = counter;
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = dateFormat.format(now);
    }
    @Override
    public String toString() {
        String str;
        str = "\rNo. " + counter + " Received Request\r\n" + "\nReceived Time: " + date + "\r\nContent: " + buffer + "\r\n";
        str = str + "******************************************\r\n";
        return str;
    }
}
