import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.commons.net.io.Util;

public class ReadWrite {

	private InputStream inputStream;
	private OutputStream outputStream;
	private InputStream in;
	private PrintStream out;
	private Thread reader;
	private Thread writer;

	public ReadWrite(InputStream inputStream, OutputStream outputStream, InputStream in, PrintStream out) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.in = in;
		this.out = out;

		this.reader = new Thread() {
			public void run() {
				int ch;
				try {
					while ((ch = in.read()) != -1) {
						outputStream.write(ch);
						outputStream.flush();
					}
				} 
				catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		
		this.writer = new Thread() {
			public void run() {
				try {
					Util.copyStream(inputStream, out);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		
		this.reader.start();
		this.writer.start();
		
		try {
			writer.join();
			reader.interrupt();
		} 
		catch (Exception e) {
		}

	}
}
