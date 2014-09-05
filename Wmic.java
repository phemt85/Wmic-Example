import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wmic{

	public static void main(String[] args) {

		String cpuCmd = "wmic /node:\"127.0.0.1\" cpu get Name ,NumberOfCores ,NumberOfLogicalProcessors /format:list";
		String baseboardCmd = "wmic /node:\"127.0.0.1\" baseboard get Manufacturer,Product,SerialNumber /format:list";
		String biosCmd = "wmic /node:\"127.0.0.1\" bios get BIOSVersion /format:list";
		String diskdriveCmd = "wmic /node:\"127.0.0.1\" diskdrive get Caption,Size /format:list";

		System.out.println("+++++ CPU INFO +++++");
		executeCommand(cpuCmd);
		System.out.println("");

		System.out.println("+++++ BASEBOARD INFO +++++");
		executeCommand(baseboardCmd);
		System.out.println("");

		System.out.println("+++++ BIOS INFO +++++");
		executeCommand(biosCmd);
		System.out.println("");

		System.out.println("+++++ DISKDRIVE INFO +++++");
		executeCommand(diskdriveCmd);
		System.out.println("");

	}

	private static int executeCommand(String command){

		try {

			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command);

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				if(inputLine.length() > 0){
					System.out.println(inputLine);
				}

			}
			in.close();

		} catch (IOException e) {
			System.out.println(e);
		}

		return 0;
	}
}