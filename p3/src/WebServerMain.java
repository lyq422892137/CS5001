import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import static java.lang.System.exit;
/**
 * This is the main method for it
 * */
public class WebServerMain {
    public static void main(String[] args) {
        try {
            String filepath;
            int portNumber;
            int counter = 0;
            filepath = args[0];
            portNumber = Integer.parseInt(args[1]);
            Server thread;
            ServerSocket ss = new ServerSocket(portNumber);
            File logFile = new File(filepath + "/" + "log.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            for(;;) {
                counter ++;
                Socket sock = ss.accept();
                thread = new Server(sock, filepath, logFile, counter);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("Usage: java WebServerMain <document_root> <port>");
            exit(1);
        }
    }
}
