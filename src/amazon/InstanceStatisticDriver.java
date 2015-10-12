package amazon;

//packages from java.util
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

//IO packages
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;

public class InstanceStatisticDriver {
	
	private static String inputFileName = "FleetState.txt";
	private static String outputFileName = "Statistics.txt";
	
	public static void main(String[] args) {
		//Use the above define files if no command line input is provided
		if(args.length == 0){
			System.out.println("FleetState.txt will be used as input file and the output will be writtedn to Statistics.txt");
		}
		else if(args.length==1){//use the one input as input file
			String tmp = args[0];
			File file = new File(tmp);
			if(!file.exists()){
				System.out.println("The input file "+ tmp + " does not exist");
				showUsageInfo();
				System.exit(0);
			}
			inputFileName = tmp;
		}
		else if(args.length==2){//use the one input as input file
			String tmp = args[0];
			String tmp2 = args[1];
			File file = new File(tmp);
			if(!file.exists()){
				System.out.println("The input file "+ tmp + " does not exist");
				showUsageInfo();
				System.exit(0);
			}
			inputFileName = tmp;
			outputFileName = tmp2;
		}
		else{
			showUsageInfo();
		}
		
		Scanner input = null;
		PrintWriter output = null;
		
		try{
			input = new Scanner(new File(inputFileName));
			output = new PrintWriter(new File(outputFileName));
		}
		catch(FileNotFoundException exp){
			System.out.println("The file "+ inputFileName + " coould not be found.");
		}
		
		String line = null;
		
		ArrayList<Host> hosts = new ArrayList<Host>();
		long id = -1; //assuming all ids are positive values
		int n = 0;
		InstantType type = null;
		boolean states[] = null;
		String next;
		int pass = 0;
		int counter = 0;
		boolean valid_host = true; //Assume no input malformation in input data
		do {
			line = input.nextLine();
			counter++;
			StringTokenizer st = new StringTokenizer(line,", \n");
			pass = 0;
			while(st.hasMoreTokens() && valid_host){
				next = st.nextToken();
				if(pass == 0){				//first time passing through the token created from line
					if(isLong(next)){
						id = Long.parseLong(next);
						pass++;
					}
					if(st.hasMoreTokens()){
						next = st.nextToken();
						switch (next) {
						case "M1":
							type = InstantType.M1;
							break;
						case "M2":
							type = InstantType.M2;
							break;
						case "M3":
							type = InstantType.M3;
							break;
						default:
							valid_host = false;
							break;
						}
					}
					else{
						valid_host = false;
					}
					if(st.hasMoreTokens()){
						next = st.nextToken();
						if(isInteger(next));
						n = Integer.parseInt(next);
					}else{
						valid_host = false;
					}
				}
				else{//read slot states
					if(n-1==st.countTokens()){
						states = new boolean[n];
						int curr = 0;
						if(next.equals("1"))
							states[curr] = true;
						else if(next.equals("0"))
							states[curr] = false;
						else{
							valid_host = false;
							break;
						}
						while (st.hasMoreTokens() && valid_host){
								
								next = st.nextToken();
								curr++;
								if(next.equals("1"))
									states[curr] = true;
								else if(next.equals("0"))
									states[curr] = false;
								else{
									valid_host = false;
									break;
								}
						} 
					}
					else{
						System.out.println("Again");
					}
				}
			}
			if(valid_host)
				hosts.add(new Host(id, n, type, states));
			else{
				System.out.println("Line " + counter+ " of the input file is not well formated, host will be dropped");
				System.err.println(line);
			}
		} while (input.hasNextLine());
		
		System.out.println(hosts.size() + " hosts have been correctly read from "+ counter + " lines");
		if(hosts.size() < counter){
			System.out.println((counter - hosts.size()) + " lines were malformed");
		}
		
		HostsManager hm = new HostsManager();
		hm.setHosts(hosts);
		hm.processHosts();
		output.println(hm);
		output.flush();
		output.close();
		input.close();

	}
	
	
/**==========================================================================================/
 * 
 * 					HELPER FUNCTIONS
 * 
 ============================================================================================*/
	
	/**
	 * @param s string that might be a number of type long
	 * @return true if the string is a number of type long
	 */
	public static boolean isLong(String s) {
	    Scanner sc = new Scanner(s.trim());
	    if(!sc.hasNextLong(10)){
	    	sc.close();
	    	return false;
	    }
	    sc.nextLong(10);
	    if(sc.hasNext()){
	    	sc.close();
	    	return false;
	    }
	    sc.close();
	    return true;
	}
	
	/**
	 * @param s string that might be a number of type Integer
	 * @return true if the string is a number of type Integer
	 */
	public static boolean isInteger(String s) {
	    Scanner sc = new Scanner(s.trim());
	    if(!sc.hasNextInt(10)){
	    	sc.close();
	    	return false;
	    }
	    sc.nextInt(10);
	    if(sc.hasNext()){
	    	sc.close();
	    	return false;
	    }
	    sc.close();
	    return true;
	}

	/**
	 * Message to be displayed if using command line to provide input and output file names
	 */
	public static void showUsageInfo(){
		System.out.println("Usage: InstanceStatisticDriver <input filename> or \nInstanceStatisticDriver <input filename> <outputfile name>");
	}
}
