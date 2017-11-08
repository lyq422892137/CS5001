import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadP1 {
    public static void main(String[] args) throws IOException {
//        MyFirstRunnable xyz = new MyFirstRunnable();
//        Thread thread = new Thread(xyz);
//        thread.start();
        MyThread thread = null;
        ServerSocket ss = new ServerSocket(12345);
        for(int i =0; i<25;i++) {
            Socket sock = ss.accept();
            thread = new MyThread(sock);
            thread.start();
        }
    }
}
