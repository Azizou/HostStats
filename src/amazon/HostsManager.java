package amazon;

import java.util.List;

public class HostsManager {
	/**
	 * List of hosts to be processed
	 */
	private List<Host> hosts;
	
	/**
	 * An array of size InstantType.values.length, it contains the number of host of type Instant type
	 * that have all slots full
	 */
	private int fullCount[];
	
	/**
	 * An array of size InstantType.values.length, it contains the number of host of type Instant type
	 * that have all slots empty
	 */
	private int emptyCount[];
	
	/**
	 * An array of size InstantType.values.length, it contains the number of host of type Instant type
	 * that have the least number of empty slot
	 */
	private int mostFilledCount[];
	
	/**
	 * An array of size InstantType.values.length, it contains the least number of empty slots of a host of type IntantType
	 */
	private int mostFilledEmptySlot[];
	
	/**
	 * Number of instant types available. In the context of our problem, it is assumed to be 3. 
	 * This makes provision for new Instant types
	 */
	private static final int numIntances = InstantType.values().length;

	/**
	 * Default constructor, it initializes the different counters need by the Host manager 
	 * 
	 */
	public HostsManager(){
		fullCount = new int[numIntances];
		emptyCount = new int[numIntances];
		mostFilledCount = new int[numIntances];
		mostFilledEmptySlot = new int[numIntances];
		for(int i=0; i<numIntances;i++){
			mostFilledEmptySlot[i] = Integer.MAX_VALUE;//Initialized to biggest integer value
		}
	}
	
	/**
	 * Iterate through the different host and update the different counters based on
	 * host Instant type, free/full slots
	 * 
	 * This implementation assume full knowledge of the types of host instances (i.e. M1, M2, M3)
	 * A better solution might be to make it dynamic by storing the values of the InstantType and
	 * iterate through them
	 */
	void processHosts(){
		for(Host host : hosts){
			System.out.println(host);
			System.out.println("Empty count for host: " + host.emptySlots());
			System.out.println("Full count for host: " + host.fullSlot());
			InstantType type = host.getHostInstantType();
			switch (type) {
			case M1:
				if(host.isEmpty()){
					++emptyCount[0];
				}
				else if(host.isFull()){
					++fullCount[0];
				}
				else{
					if(host.emptySlots()<mostFilledEmptySlot[0]){
						mostFilledEmptySlot[0] = host.emptySlots();
						mostFilledCount[0] = 1;
					}
					else if(host.emptySlots() == mostFilledEmptySlot[0]){
						mostFilledCount[0]++;
					}
					else{
						
					}
				}
				break;
			case M2:
				if(host.isEmpty()){
					++emptyCount[1];
				}
				else if(host.isFull()){
					++fullCount[1];
				}
				else{
					if(host.emptySlots()<mostFilledEmptySlot[1]){
						mostFilledEmptySlot[1] = host.emptySlots();
						mostFilledCount[1] = 1;
					}
					else if(host.emptySlots() == mostFilledEmptySlot[1]){
						mostFilledCount[1]++;
					}
				}
				break;
			case M3:
				if(host.isEmpty()){
					++emptyCount[2];
				}
				else if(host.isFull()){
					++fullCount[2];
				}
				else{
					if(host.emptySlots()<mostFilledEmptySlot[2]){
						mostFilledEmptySlot[2] = host.emptySlots();
						mostFilledCount[2] = 1;
					}
					else if(host.emptySlots() == mostFilledEmptySlot[2]){
						mostFilledCount[2]++;
					}
				}
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param hosts use to set host that needs to be managed. Could be moved inside the constructor
	 */
	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}
	@Override
	public String toString() {
		return "EMPTY: M1 = "+ emptyCount[0] + "; M2 = " + emptyCount[1] + "; M3 = " + emptyCount[2] + "\n" + 
			"FULL: M1 = "+ fullCount[0] + "; M2 = " + fullCount[1] + "; M3 = " + fullCount[2] + "\n" + 
				"MOST FILLED: M1 = " + mostFilledCount[0] + ", " + mostFilledEmptySlot[0] + "; M2 = " + mostFilledCount[1] + ", " + mostFilledEmptySlot[1] + "; M3 = " + mostFilledCount[2] + ","  + mostFilledEmptySlot[2];
	}
}
