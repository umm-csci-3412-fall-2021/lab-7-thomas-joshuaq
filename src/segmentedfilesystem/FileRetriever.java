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
                        this.server = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }     
	}

        

	public void downloadFiles() throws IOException {
        // Do all the heavy lifting here.
        // This should
        //   * Connect to the server
        //   * Download packets in some sort of loop
        //   * Handle the packets as they come in by, e.g.,
        //     handing them to some PacketManager class
        // Your loop will need to be able to ask someone
        // if you've received all the packets, and can thus
        // terminate. You might have a method like
        // PacketManager.allPacketsReceived() that you could
        // call for that, but there are a bunch of possible
        // ways.
                byte buf[] = new byte[0];
                byte buf2[] = new byte[1028];
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket send = new DatagramPacket(buf, 0, server, port);
                ds.send(send);
                PacketManager packetManager = new PacketManager();
                while (true){
                        send = new DatagramPacket(buf2, buf2.length);
                        ds.receive(send);
                        //String received = new String(send.getData(), 0, send.getLength());
                        //PacketManager(send.getData(), send.getLength());
                        packetManager.addPacket(send.getData(), send.getLength());
                        if(packetManager.isDone() == true){
                                System.out.println("done");
                                break;
                        }
                }
	}
        public void PacketManager(byte[] data, int length){
                
                if ((Byte.toUnsignedInt(data[0]) % 2 ) == 0){
                        System.out.println((Byte.toUnsignedInt(data[0]) % 2 ) == 0);
                        HandleHeader(data, length);     
                }
                else {HandleData(data, length);}
        }

        public void HandleHeader(byte[] data, int length){
                HeaderPacket header = new HeaderPacket(data, length);
                //ReceivedFile.AddHeader(header, length);
                // try {
                //         System.out.write(header.name);
                // } catch (IOException e) {
                //         // TODO Auto-generated catch block
                //         e.printStackTrace();
                // }
      
        }
        public void HandleData(byte[] data, int length){
                DataPacket dataPacket = new DataPacket(data, length);
                //ReceivedFile.AddData(dataPacket, length);
                     
        }
        
}
