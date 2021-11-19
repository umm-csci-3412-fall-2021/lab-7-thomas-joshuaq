package segmentedfilesystem;

import java.util.ArrayList;

public class Packet {
    byte id;
    int packetCounter = 0;
    int maxPackets = 1000;
    byte[] name;
    ArrayList<byte[]> packetData = new ArrayList<>(maxPackets);
    public Packet(byte[] array, int length){
        id = array[1];
        if (array[0]%2 == 0){
            byte[] newArr = new byte[length-2];
            for(int i = 0; i < length - 2; i++){
                newArr[i] = array[i+2];
            }
            name = newArr;
        }
        else{
            int x = Byte.toUnsignedInt(array[2]);
            int y = Byte.toUnsignedInt(array[3]);
            int number = 256*x + y;
            if((array[0]%3)==4){
                maxPackets = number;
                packetData.subList(number, 1000).clear();
            }
            byte[] data = new byte[array.length-4];
            for(int i = 0; i < data.length; i++){
                data[i] = array[i+4];
            }
            packetData.add(number, data);
        }
        packetCounter++;
    }

    public void addNewPacket(byte[] array, int length){
        if (array[0]%2 == 0){
            byte[] newArr = new byte[length-2];
            for(int i = 0; i < length - 2; i++){
                newArr[i] = array[i+2];
            }
            name = newArr;
        }
        else{
            int x = Byte.toUnsignedInt(array[2]);
            int y = Byte.toUnsignedInt(array[3]);
            int number = 256*x + y;
            if((array[0]%3)==4){
                maxPackets = number;
                packetData.subList(number, 1000).clear();
            }
            byte[] data = new byte[array.length-4];
            for(int i = 0; i < data.length; i++){
                data[i] = array[i+4];
            }
            packetData.add(number, data);
        }
        packetCounter++;
    }
    public boolean checkIfDone(){
        return packetCounter == maxPackets;
    }
    

}
