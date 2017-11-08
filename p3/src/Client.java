import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main (String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
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
                if (i == 0) {
                    rec = s.nextLine() + "\n";
                } else {
                    rec = rec + s.nextLine() + "\n";
                }
                i++;
            }
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

/*
*  Scanner s = new Scanner(sock.getInputStream());
            int loop1 = 0;
            while(s.hasNext()) {
                if(loop1 == 0) {
                    recvBuffer = s.nextLine() + "\n";
                } else {
                    recvBuffer = recvBuffer + s.nextLine() + "\n";
                }
                loop1 ++;
                if (s.nextLine() == null || s.nextLine().isEmpty() || s.nextLine().contains("\r\n")) {
                    break;
                }
            }*/