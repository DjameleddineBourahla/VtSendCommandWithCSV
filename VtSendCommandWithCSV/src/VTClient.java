
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;
/** 
 * @author Djameleddine Bourahla
 * @version 1.0
 * 
 */
public class VTClient {
 
	@SuppressWarnings("resource")
	public static void main(String args[]) throws InterruptedException {

 
		try {
 			 try {

 				Scanner dh = new Scanner(System.in);
				System.out.println("tapes pour envoye");

				dh.nextLine();
//				Socket s = new Socket("localhost", 8550);
				 Socket s = new Socket("162.221.204.12", 13000);
				InputStream dtis = s.getInputStream();

				DataInputStream datain = new DataInputStream(dtis);

				BufferedReader inStream = new BufferedReader(new InputStreamReader(datain, "UTF-8"));
				PrintStream outStream = new PrintStream(s.getOutputStream());
				PrintWriter resp = new PrintWriter(outStream, true);

			 	String deviceId = "2000006915";
				String operator = "798132584";
				String message = "";

				if (operator.toUpperCase().startsWith("7")) {
					if (operator.equals("798534048")//
							|| operator.equals("798132584")) {
						message = deviceId
								+ ":$WP+COMMTYPE=0000,4,0799140449,,djezzy.internet,internet,internet,track.algeofleet.com,13555,0,168.95.1.1";

					} else {
						message = deviceId
								+ ":$WP+COMMTYPE=0000,4,0799140449,,djezzy.m2m,internet,internet,track.algeofleet.com,13555,0,168.95.1.1";

					}

				} else {
					message = deviceId
							+ ":$WP+COMMTYPE=0000,4,0799140449,,internet,internet,internet,track.algeofleet.com,13555,0,168.95.1.1";

				}
				System.out.println(message);

				resp.println(message + "\n");

				BufferedReader in;
				String response = "";

				try {

					response = inStream.readLine();
					System.out.println(response);
				} catch (IOException e) {
					System.err.println("ERROR when reading Line from InputStream");
				}
				System.out.println("receive " + response);
			 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// writer.flush();
				// writer.close();

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		Thread.sleep(15000);

	}
}
// }
