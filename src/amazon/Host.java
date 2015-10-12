/**
 * 
 */
package amazon;

enum InstantType {
	M1, M2, M3
};

public class Host {

	private long hostID;
	private int numberOfSlots; // assuming at most pow(2,32) slots
	private InstantType instanceType;
	private boolean slotState[]; // could have been static array but size is
									// only known at runtime

	public Host(long id, int n, InstantType type, boolean states[]) {
		hostID = id;
		instanceType = type;
		numberOfSlots = n;
		assert states.length == numberOfSlots;
		slotState = states;
	}

	long getHostId() {
		return hostID;
	}

	InstantType getHostInstantType() {
		return instanceType;
	}

	int emptySlots() {
		int count = 0;
		for (int i = 0; i < numberOfSlots; ++i) {
			if (!slotState[i])
				count++;
		}
		return count;
	}

	int fullSlot() {
		return numberOfSlots - emptySlots();
	}

	boolean isEmpty() {
		return emptySlots() == numberOfSlots;
	}

	boolean isFull() {
		return fullSlot() == numberOfSlots;
	}

	@Override
	public String toString() {
		return "Host: " + hostID + ", type: " + instanceType + ", slots count: " + numberOfSlots;
	}
}
