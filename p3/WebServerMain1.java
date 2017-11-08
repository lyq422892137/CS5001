import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;
/**
 * This is the main method for it
 * */
public class WebServerMain1 {
    public static void main(String[] args) {
        try {
            String filepath;
            int portNumber;
            filepath = args[0];
            portNumber = Integer.parseInt(args[1]);
            ServerSocket ss = new ServerSocket(portNumber);
            WebServerMain server = new WebServerMain();
            for(;;) {
                Socket sock = ss.accept();
                server.handleRequest(filepath, sock);
                sock.close();
            }
        } catch (Exception e) {
            System.out.println("Usage: java WebServerMain <document_root> <port>");
            exit(1);
        }
    }
    /**
     * receive request and send response
     * */
    public void handleRequest(String filepath, Socket sock) throws IOException {
        InputStreamReader input = new InputStreamReader(sock.getInputStream());
        BufferedReader in = new BufferedReader(input);
        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
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
        //System.out.println(recvBuffer);
        String[] request = recvBuffer.split(" ");
        String sendBuffer = generateResponse(sock, filepath, request);
        // for(int i = 0; i < request.length;i++)
        // out.println(i+request[i]);
        try {
            int hasRead;
            byte b[] = sendBuffer.getBytes("UTF8");
            // while ((hasRead = (b)) > 0) {
            bos.write(b);
            //  }
            System.out.println("finished");
            //  out.flush();
            //  out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * generate response and send it
     * */
    public String generateResponse(Socket sock, String filepath, String[] request) throws IOException {
        String responseStr = new String();
        /*
        * flag is used to indicate whether the file exists or not
        * */
        int flag = 0;
        File file = new File(filepath + "\\" +request[1]);
        try {
            String request1 = "HEAD";
            String request2 = "GET";
            //File file = new File("www" + request[1]);
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            long fileLen = file.length();
            String fileLength = String.valueOf(fileLen);
            if (!file.exists()) {
                flag = -1;
                httpResponse response = new httpResponse(flag, fileType, fileLength, null);
                responseStr = response.toString();
                //System.out.println(responseStr);
            } else {
                if (request[0].equals(request1)) {
                    httpResponse response = new httpResponse(flag, fileType, fileLength, null);
                    responseStr = response.toString();
                    // System.out.println(responseStr);
                } else if (request[0].equals(request2)) {
                    int readLen;
                    FileInputStream fis = new FileInputStream(file);
                    readLen = fis.available();
                    byte[] bytes = new byte[readLen];
                    fis.read(bytes);
                    fis.close();
                    String buffer = new String(bytes, "UTF-8");
                    httpResponse response = new httpResponse(flag, fileType, fileLength, buffer);
                    responseStr = response.toString();
                    System.out.println(responseStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//        } finally{
//        if(bos != null){
//            try {
//                bos.flush();
//                bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if(bis != null){
//            try {
//                bis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        }
        return  responseStr;
    }
}
