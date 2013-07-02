package bytecrawl.evtj.tests;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bytecrawl.evtj.server.EvtJModule;
import bytecrawl.evtj.server.EvtJServer;
import bytecrawl.evtj.utils.EvtJClient;
 
public class EvtJServerTest {
	
	private static final Logger logger =
	    LoggerFactory.getLogger(EvtJServerTest.class);
	
	private EvtJModule mock_module;
	private EvtJClient client;

	public EvtJServerTest() {
		mock_module = new MockModule();
		client = new EvtJClient();
	}
	
	@org.junit.Test
	public void ServerInitialisation() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		server.start();
		assertEquals(server.isActive(), true);
		server.stop();
	}
	
	@org.junit.Test
	public void ServerPause() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		server.start();
		server.pause();
		assertEquals(server.isPaused(), true);
		server.stop();
	}
	
	@org.junit.Test
	public void ServerStop() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		server.stop();
		assertEquals(server.isActive(), false);
	}
	
	@org.junit.Test
	public void ServerPauseAndResume() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		server.start();
		server.pause();
		assertEquals(server.isPaused(), true);
		server.resume();
		assertEquals(server.isPaused(), false);
		server.stop();
	}
	
	@org.junit.Test
	public void ServerGetModule() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		assertNotNull(server.getModule());
		assertEquals(server.getModule() == mock_module, true);
	}
	
	@org.junit.Test
	public void ServerGetConnectedClients() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		assertEquals(server.getConnectedClients(), 0);
	}
	
	@org.junit.Test
	public void ServerIntegrity() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		server.start();
		server.stop();
	}
	
	@org.junit.Test
	public void ServerAcceptedConnection() {
		EvtJServer server = new EvtJServer(4444, mock_module);
		assertEquals(server.getConnectedClients(), 0);
		server.newAcceptedConnection(client);
		assertEquals(server.getConnectedClients(), 1);
	}	

}