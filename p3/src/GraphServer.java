import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

/**
 * This is the main method for it
 * */
public class GraphServer {
    public static void main(String[] args) throws IOException {
        int portNumber;
        portNumber = Integer.parseInt("12346");
        ServerSocket ss = new ServerSocket(portNumber);
        Socket sock = ss.accept();
        BufferedInputStream bis = null;
        String filepath;

        filepath = "d:";
        BufferedOutputStream bos = null;
        String recvBuffer = null;
        String line;


        bos = new BufferedOutputStream(sock.getOutputStream());




            InputStreamReader input = new InputStreamReader(sock.getInputStream());
            BufferedReader in = new BufferedReader(input);

        int loop = 0;
            for (; ; ) {
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
            int hasRead = 0;
            byte b[] = new byte[1024];
            while ((hasRead = bis.read(b)) > 0) {
                bos.write(b, 0, hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bos != null){
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


