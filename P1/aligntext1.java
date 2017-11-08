/** 
 * p1-aligntext
 * @author yl90
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Formatter;

public class AlignText {
	/**
	 *@param str1 is the name of the file
	 *@param fileLen is the file length the user want to align 
	 *@param str2 is the return value of the file
	 */
	public static void main(String[] args)
	{
		
		try {
			String str1 = new String(args[0]);
			int fileLen = Integer.parseInt(args[1]);
			System.out.println(str1);
			String[] str2 = new String[]{};
			str2 = FileUtil.readFile(str1);
			ArrangeLines(str2,str1,fileLen);
		}
		catch (Exception e) {
			
				System.out.println("usage: java AlignText file_name line_length");
			
			}

	}
	
	public static void ArrangeLines(String[] string, String filename, int fileLen)
	{
		try{
			String str3 = new String();
			StringBuffer str4 = new StringBuffer("");
			File file = new File(filename);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            
			
			for(int i=0;i<string.length;i++)
			   {
					int d = string[i].length()/fileLen;
		
					int end = fileLen;
					int begin = 0;
					for(int m = 0; m < d ; m++)
					{	
						str3 = string[i].substring(begin,end);
						begin = begin+ fileLen;
						end = end + fileLen;
						str4.append(str3+"\n");
						str3 = null;
					}
				str3 = string[i].substring(begin);
				str4.append(str3+"\n");
				str3 = null;
			  
			   }
			System.out.println(str4);
			ps.println(str4.toString());
			str4 = new StringBuffer("");
		} catch(Exception e){
			System.out.println(e);		
		}
	    
		
		
	}
}
