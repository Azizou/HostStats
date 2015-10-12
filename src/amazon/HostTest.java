package amazon;

import static org.junit.Assert.*;

import org.junit.Test;

public class HostTest {

	boolean states[] = {false,true,false,false};
	Host host = new Host(10, 4, InstantType.M1, states);

	@Test
	public void testGetHostId() {
		assertEquals(10, host.getHostId());
	}

	@Test
	public void testGetHostInstantType() {
		assertEquals(InstantType.M1,host.getHostInstantType());
	}

	@Test
	public void testEmptySlots() {
		assertEquals(3, host.emptySlots());
	}

	@Test
	public void testFullSlot() {
		assertEquals(1, host.fullSlot());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(host.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(host.isFull());
	}

	@Test
	public void testToString() {
		assertEquals("Host: 10, type: M1, slots count: 4", host.toString());
	}

}
