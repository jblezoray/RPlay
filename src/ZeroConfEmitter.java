import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.JmmDNSImpl;
import javax.jmdns.impl.NetworkTopologyEventImpl;

/**
 * Emetteur Bonjour pour qu'iTunes detecte la borne airport
 * Needs Bonjour for Windows (apple.com)
 * @author bencall
 *
 */

//
public class ZeroConfEmitter{
	 private static JmmDNS dns;
	
	 public ZeroConfEmitter(String name, String identifier, int port) throws UnknownHostException, IOException, InterruptedException{

	    // Announce Raop Service
	    Hashtable<String, String> props = new Hashtable<String, String>();
	    props.put("txtvers", "1");
	    props.put("pw", "false");
	    props.put("sr", "44100");
	    props.put("ss", "16");
	    props.put("ch", "2");
	    props.put("tp", "UDP");
	    props.put("sm", "false");
	    props.put("sv", "false");
	    props.put("ek", "1");
	    props.put("et", "0,1");
	    props.put("cn", "0,1");
	    props.put("vn", "3");

	    ServiceInfo info = ServiceInfo.create("._raop._tcp", identifier+"@"+name, port, "dfjgjgghhfh");
	    info.setText(props);
	    
	    dns = JmmDNS.Factory.getInstance();

	    dns.registerService(info);
	    System.out.println("Service registered");
	}
	 
	 public void stop(){
	      if (dns != null) {
	          dns.unregisterAllServices();
	          try {
	            dns.close();
	          } catch (IOException e) {
	            // ignore
	          }
	          dns = null;
	        }
	 }

}