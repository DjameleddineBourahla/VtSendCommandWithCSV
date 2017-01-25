
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
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VTClientCSV {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws InterruptedException {

		try {

			// Socket s = new Socket("162.221.204.20", 8550);
			int IMEI_IDX = 1;
			int OPERATOR_IDX = 0;

			String csvFile = "vtTrackers.csv";
//			new Date();
			String csvResult = "vtListTrack.csv";
			String line = "";
			// Delimiter used in CSV file
			final String COMMA_DELIMITER = ";";
			FileWriter writer = new FileWriter(csvResult);

			// FileWriter fileWriter = new FileWriter(csvFiles);
			try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
				List<TrackerVT> trackers = new ArrayList<>();
				while ((line = br.readLine()) != null) {
					String[] tokens = line.split(COMMA_DELIMITER);
					if (tokens.length > 0) {
						TrackerVT tracker = new TrackerVT(tokens[IMEI_IDX], tokens[OPERATOR_IDX]);
						trackers.add(tracker);
						System.out.println(tracker);

					}
				}
				// for (TrackerVT t : trackers) {
				// System.out.println(t);
				//
				// }
				while (!trackers.isEmpty()) {
					List<TrackerVT> trackersToDelete = new ArrayList<>();

					for (TrackerVT tracker : trackers) {
						Thread.sleep(100);
						// Socket s = new Socket("localhost", 8550);
						// Socket s = new Socket("162.221.204.20", 8550);
						Socket s = null;
						try {
							s = new Socket("162.221.204.12", 13000);

						} catch (Exception e) {
							System.out.println(e.getMessage());
							continue;
						}
						InputStream dtis = s.getInputStream();

						DataInputStream datain = new DataInputStream(dtis);

						BufferedReader inStream = new BufferedReader(new InputStreamReader(datain, "UTF-8"));
						PrintStream outStream = new PrintStream(s.getOutputStream());
						PrintWriter resp = new PrintWriter(outStream, true);

						// use comma as separator
						String deviceId = tracker.getImei();
						String operator = tracker.getOperator();
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
						resp.println(message);

						BufferedReader in;
						String response = "";
						try {

							response = inStream.readLine();
							System.out.println("tracker :" + tracker.getImei() + "   >>>  " + response);
							if (response.equals("commande envoy�.") || response.equals("commande envoyé.")
									|| response.equals("commande envoyÃ©.")) {
								CSVUtils.writeLine(writer, Arrays.asList(deviceId, operator));
								trackersToDelete.add(tracker);

							}
						} catch (Exception e) {
							// TODO: handle exception
						}

						resp.close();
						s.close();

					}

					// if (!trackersToDelete.isEmpty()) {
					//
					// }
					System.out.println("#######################");
					System.out.println(trackersToDelete.size() + " commands   envoyé ");
					System.out.println("#######################");

					for (Iterator iterator = trackersToDelete.iterator(); iterator.hasNext();) {
						TrackerVT trackerVT = (TrackerVT) iterator.next();
						trackers.remove(trackerVT);

					}
					System.out.println("***********************");
					System.out.println("Rest " + trackers.size() + " commands a envoyé ");
					System.out.println("***********************");

					writer.flush();
					Thread.sleep(180000);

				}
				System.out.println("tous les commands sont envoyé");

			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				writer.close();

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		Thread.sleep(15000);

	}
}
// }