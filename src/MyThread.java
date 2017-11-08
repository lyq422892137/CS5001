import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread extends Thread {
    private Socket socket;
    public  MyThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        System.out.println("My Thread Running");
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(socket.getInputStream());

        BufferedReader in = new BufferedReader(input);
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        String recvBuffer = null;
        String line;
    int loop = 0;
        for(;;) {
        line = in.readLine();
        if (line == null || line.isEmpty()) {
            break;
        }
        if (loop == 0) {
            recvBuffer = line;
            loop ++;
        } else {
            recvBuffer = recvBuffer + line;
        }
    }
    System.out.println(recvBuffer);
}  catch (IOException e) {
        e.printStackTrace();
    }
    }
}
