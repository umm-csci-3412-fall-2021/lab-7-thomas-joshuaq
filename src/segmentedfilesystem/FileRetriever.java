package segmentedfilesystem;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class FileRetriever {
        InetAddress server;
        int port = 0;

	public FileRetriever(String server, int port) {
                this.port = port;
                try {
                        this.server = InetAddress.getByName(server);
                        
                } catch (UnknownHostException e) {
                        e.printStackTrace();
                }     
	}

	public void downloadFiles() throws IOException {
                byte buf[] = new byte[0];
                byte buf2[] = new byte[1028];
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket send = new DatagramPacket(buf, 0, server, port);
                //Send empty packet so server will send us the packets
                ds.send(send);
                PacketManager packetManager = new PacketManager();
                while (true){
                        send = new DatagramPacket(buf2, buf2.length);
                        ds.receive(send);
                        packetManager.addPacket(send.getData(), send.getLength());
                        //Checks if all the packets have arrived
                        if(packetManager.isDone() == true){
                                packetManager.writeAllToDisk();
                                break;
                        }
                }
	}
}
