package segmentedfilesystem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class BinaryFile {
    byte id;
    int packetCounter = 0;
    int maxPackets = -1;
    String name;
    SortedMap<Integer, byte[]> packetData = new TreeMap<Integer, byte[]>();
    public void addNewPacket(byte[] array, int length){
        //Check to see if the packet is a header packet, if so set the name of the file
        //If not a header treat it like a data packet
        if (array[0]%2 == 0){
            byte[] newArr = new byte[length-2];
            for(int i = 0; i < length - 2; i++){
                newArr[i] = array[i+2];
            }
            name = new String(newArr);
        }
        else{
            int x = Byte.toUnsignedInt(array[2]);
            int y = Byte.toUnsignedInt(array[3]);
            int packetIndex = 256*x + y;
            //Check to see if this is the data packet with the highest index
            //Set the max packets to index+1 since index starts at 0
            if((array[0]%4)==3){
                maxPackets = packetIndex+1;
            }
            //Copy all the data bytes into a new array so we can store that in the sortedmap with its index as the key
            byte[] data = new byte[length-4];
            for(int i = 0; i < length-4; i++){
                data[i] = array[i+4];
            }
            packetData.put(packetIndex, data);
            packetCounter++;
        }
        
    }

    //Sees if all the packets we have are equal to the total amount of required packets
    public boolean checkIfDone(){
        return packetCounter == maxPackets;
    }

    //Takes all the byte[]'s in the sortedmap and combines them into one byte[], 
    //then we convert it into a regular file
    public void writeToDisk() throws IOException{
        byte[] both = new byte[0];
        for(int i = 0; i < packetData.size(); i++){
            int oldArrayLength = both.length;
            int newArrayLength = packetData.get(i).length;
            byte[] output = new byte[oldArrayLength + newArrayLength];
            System.arraycopy(both, 0, output, 0, oldArrayLength);
            System.arraycopy(packetData.get(i), 0, output, oldArrayLength, newArrayLength);
            both = output;
        }
        try (FileOutputStream fos = new FileOutputStream(name)) {
            fos.write(both);
         }
    }
}
