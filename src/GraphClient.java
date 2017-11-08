import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GraphClient {
        public static void main (String[] args) throws IOException {
            Socket socket = new Socket("localhost", 12345);
            BufferedInputStream bw = new BufferedInputStream(socket.getInputStream());
            File file = new File("d:\\CS5001\\beer1.gif");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String buffer = "GET 2.gif HTTP/1.1";
                out.println(buffer);
                String buffer1 = "Host: localhost: 8080";
                out.println(buffer1);
                out.println("\r\n");
                out.flush();

                if (!file.exists()) {
                    file.createNewFile();
                }

                int hasRead = 0;
                byte b[] = new byte[1024];
                while((hasRead = bw.read(b)) > 0){
                    bos.write(b, 0, hasRead);
                    System.out.println("reading");
                }

                bw.close();
                out.close();
                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
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
                if(bw != null){
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

