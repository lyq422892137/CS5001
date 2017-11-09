import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * this client is used for testing the normal communication with WebServerMain.
 */
public class Client {
    /**
     * the main method.
     * @param args is not used
     * */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", Number.PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String buffer = "GET index.html HTTP/1.1";
            out.println(buffer);
            String buffer1 = "Host: localhost: 8080";
            out.println(buffer1);
            out.println("\r\n");
            out.flush();
            String rec = null;
            Scanner s = new Scanner(socket.getInputStream());
            int i = 0;
            while (s.hasNext()) {
                if (i == Number.READ_FLAG) {
                    rec = s.nextLine() + "\n";
                } else {
                    rec = rec + s.nextLine() + "\n";
                }
                i++;
            }
            /*
            * print it to see the result
            * then store the file to see whether it can work
            * index111.html is the downloaded file of index.html from the server
            * */
            System.out.println(rec);
            File file = new File("d:\\CS5001\\index111.html");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(rec);
            bw.close();
                out.close();
                socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
