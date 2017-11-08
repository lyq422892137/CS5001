//http://huangqiqing123.iteye.com/blog/1476386
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

    public static void main(String[] args){

        String sourceTxt = "d:\\beer.jpg";
        String destTxt = "d:\\888.jpg";
        String destTxt2 = "d:\\999.jpg";


        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        BufferedOutputStream bos2 = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(sourceTxt));
            bos = new BufferedOutputStream(new FileOutputStream(destTxt));
            bos2 = new BufferedOutputStream(new FileOutputStream(destTxt2));

            int hasRead = 0;
            byte b[] = new byte[1024];
            while((hasRead = bis.read(b)) > 0){
                bos.write(b, 0, hasRead);
                bos2.write(b, 0, hasRead);
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
