import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the main method for it.
 * */
public class GraphServer {
    /**
     * this is a simple server which is nothing about HTTP.
     * it is used to test image files
     * @param args not
     *             @throws IOException I/O
     * */
    public static void main(String[] args) throws IOException {
        int portNumber;
        portNumber = Integer.parseInt("12345");
        ServerSocket ss = new ServerSocket(portNumber);
        Socket sock = ss.accept();
        BufferedInputStream bis;
        String filepath;
        filepath = "d:";
        BufferedOutputStream bos;
        String recvBuffer = null;
        String line;
        bos = new BufferedOutputStream(sock.getOutputStream());
        InputStreamReader input = new InputStreamReader(sock.getInputStream());
        BufferedReader in = new BufferedReader(input);
        int loop = Number.READ_FLAG;
            for (;;) {
                line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                if (loop == 0) {
                    recvBuffer = line;
                    loop++;
                } else {
                    recvBuffer = recvBuffer + line;
                }
            }
            String[] request = recvBuffer.split(" ");
            File file = new File(filepath + "\\" + request[1]);
        bis = new BufferedInputStream(new FileInputStream(file));
        try {
            int hasRead;
            byte[] b = new byte[Number.MAX];
            String buffer = new String(b, "UTF-8");
            byte[] b2 = buffer.getBytes("UTF8");
            while ((hasRead = bis.read(b2)) > Number.READ_FLAG) {
                bos.write(b2, Number.READ_FLAG, hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


