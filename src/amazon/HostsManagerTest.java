package amazon;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HostsManagerTest {
	boolean states1 [] = {false,false,true,true,true};
	Host h1 = new Host(10,5 ,InstantType.M2,states1);
	boolean states2[] = {true,true,true,true};
	Host h2 = new Host(12,4 ,InstantType.M1, states2);
	boolean states3[] = {true,false,true};
	Host h3 = new Host(20,3 ,InstantType.M3,states3);
	boolean states4[] = {true,false,true,true,true,true};
	Host h4 = new Host(30,5 ,InstantType.M2, states4);
	boolean states5[] = {false,false,false,false};
	Host h5 = new Host(15,4 ,InstantType.M1, states5);
	boolean states6[] = {true,true,false,true,true};
	Host h6 = new Host(98,5 ,InstantType.M2, states6);
	boolean states7[] = {true,true,false};
	Host h7 = new Host(79,3 ,InstantType.M3, states7);
	HostsManager hm = new HostsManager();
	


	@Test
	public void testHostManager() {
		ArrayList<Host> hosts = new ArrayList<Host>();
		HostsManager  newhm = new HostsManager();
		hosts.add(h1);
		hosts.add(h2);
		hosts.add(h3);
		hosts.add(h4);
		hosts.add(h5);
		hosts.add(h6);
		hosts.add(h7);
		hm.setHosts(hosts);
		hm.processHosts();
	}
	
	@Test
	public void testEmpptyCount(){
		ArrayList<Host> hosts = new ArrayList<Host>();
		hosts.add(h1);
		hosts.add(h2);
		hosts.add(h3);
		hosts.add(h4);
		hosts.add(h5);
		hosts.add(h6);
		hosts.add(h7);
		hm.setHosts(hosts);
		hm.processHosts();
		assertEquals(1, hm.getEmptyCount()[0]);
	}
	
	@Test
	public void testFullCount(){
		ArrayList<Host> hosts = new ArrayList<Host>();
		hosts.add(h1);
		hosts.add(h2);
		hosts.add(h3);
		hosts.add(h4);
		hosts.add(h5);
		hosts.add(h6);
		hosts.add(h7);
		hm.setHosts(hosts);
		hm.processHosts();
		assertEquals(1, hm.getFullCount()[0]);
	}

	@Test
	public void testProcessHosts() {
		ArrayList<Host> hosts = new ArrayList<Host>();
		hosts.add(h1);
		hosts.add(h2);
		hosts.add(h3);
		hosts.add(h4);
		hosts.add(h5);
		hosts.add(h6);
		hosts.add(h7);
		hm.setHosts(hosts);
		assertEquals(7, hm.getNumHosts());
	}

	@Test
	public void testSetHosts() {
		ArrayList<Host> hosts = new ArrayList<Host>();
		hosts.add(h1);
		hosts.add(h2);
		hosts.add(h3);
		hosts.add(h4);
		hosts.add(h5);
		hosts.add(h6);
		hosts.add(h7);
		hm.setHosts(hosts);
		assertEquals(7, hm.getNumHosts());
		
	}

}
