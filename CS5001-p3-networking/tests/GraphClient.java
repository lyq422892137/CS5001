import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * the client is only used to check whether image files can be sent successfully without a HTTP header.
 * */
public class GraphClient {
    /**
     * the main method.
     * @param args not used
     *             @throws IOException I/O
     * */
        public static void main(String[] args) throws IOException {
            Socket socket = new Socket("localhost", Number.PORT);
            BufferedInputStream bw = new BufferedInputStream(socket.getInputStream());
            /*
            sample1.gif is the requested file by the client
            sample2.gif is the downloaded file from the server
            * */
            //File file = new File("d:\\CS5001\\sample2.gif");
            File file = new File("d:\\CS5001\\beer123.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //String buffer = "GET sample1.gif HTTP/1.1";
                String buffer = "GET beer.jpg HTTP/1.1";
                out.println(buffer);
                String buffer1 = "Host: localhost: 8080";
                out.println(buffer1);
                out.println("\r\n");
                out.flush();
                if (!file.exists()) {
                    file.createNewFile();
                }
                int hasRead;
                byte[] b = new byte[Number.MAX];
                while ((hasRead = bw.read(b)) > Number.READ_FLAG) {
                    bos.write(b, Number.READ_FLAG, hasRead);
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
            } finally {
                if (bos != null) {
                    try {
                        bos.flush();
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
