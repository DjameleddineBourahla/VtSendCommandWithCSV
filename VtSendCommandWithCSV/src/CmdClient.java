
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

public class CmdClient {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws InterruptedException {

		// while (true) {

		try {

			// Socket s = new Socket("192.168.1.210", 20000);

			// FileWriter fileWriter = new FileWriter(csvFiles);
			try {

				Scanner dh = new Scanner(System.in);
				// dh.nextLine();
				// Socket s = new Socket("localhost", 8550);
//				Socket s = new Socket("162.221.204.20", 8550);
				 Socket s = new Socket("162.221.204.12", 13000);
//				 Socket s = new Socket("192.168.1.210", 19556);

				System.out.println("tapes pour envoye");
				InputStream dtis = s.getInputStream();

				DataInputStream datain = new DataInputStream(dtis);

				BufferedReader inStream = new BufferedReader(new InputStreamReader(datain, "UTF-8"));
				PrintStream outStream = new PrintStream(s.getOutputStream());
				PrintWriter resp = new PrintWriter(outStream, true);

				// use comma as separator
				// String[] deviceCMD = line.split(cvsSplitBy);
				// String deviceId = "15dbf5c3-c0c9-4c59-8469-c2f2f0d6b7e4";
				// String message = "getver";
//				String deviceId = "2000007683";
				 String deviceId = "2000006313";
//				 String deviceId = "2000009560";

				// 1 $WP+TRACK

				// $WP+TRACK=0000,Time,Distance,TrackingTimes,TrackBasis,CommSelect,Heading
				// String message = "$WP+TRACK=0000,1,60,0,0,1,4,15";

				// 2 $WP+SPD

				 String message = "d0d7db5027ae3577";
				// $WP+SPD=Password,Mode,MinSp,MaxSp,SpDur,Output,OutCtl,SpForm,OffSpDur
				// String message ="$WP+SPD=0000,0,0,0,0,0,0,0,0";

//				String message = "$WP+SPD=0000,3,79,80,15,0,0,1,30";

				// 3 $WP+COMMTYPE
				// "$WP+COMMTYPE=0000,4,0770403447,,internet,internet,internet,track.algeofleet.com,20000,0,168.95.1.1";

				resp.println(deviceId + ":" + message + "\n");

				BufferedReader in;
				String response = "";

				try {

					response = inStream.readLine();
					System.out.println(response);
				} catch (IOException e) {
					System.err.println("ERROR when reading Line from InputStream");
				}
				System.out.println("receive " + response);

				// }
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		Thread.sleep(15000);

	}
}
// }