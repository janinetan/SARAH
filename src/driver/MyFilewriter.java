package driver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFilewriter {
	private DateFormat dateFormat;
	private Date date;
	private FileWriter fw;
	private PrintWriter pw;
	
	public MyFilewriter(String name) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		date = new Date();
		try {
			fw = new FileWriter(new File(name + dateFormat.format(date) +".txt"));
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            closeFile();
	        }
	    }, "Shutdown-thread"));
	}
	
	public void writeToFile (String s){
		pw.println(s);
	}
	
	public void closeFile(){
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
