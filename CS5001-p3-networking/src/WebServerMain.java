import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import static java.lang.System.exit;
/**
 * This is the main class.
 * */
public class WebServerMain {
    /**
     * the main method.
     * @param args arg[0] is the filepath
     *             arg[1] is the port number
     * */
    public static void main(String[] args) {
        /**
         * one socket is for listing & accepting connections.
         * a new socket is created with a thread every time a new connection is handled
         * */
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
            for (;;) {
                counter++;
                Socket sock = ss.accept();
                /*
                * if the number of connections are bigger than 100, the server will sleep for 100s then start
                * */
                thread = new Server(sock, filepath, logFile, counter);
                if (counter >= Numbers.THREAD_LIMIT) {
                    thread.sleep(Numbers.THREAD_SLEEP_TIME);
                }
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("Usage: java WebServerMain <document_root> <port>");
            exit(1);
        }
    }
}
