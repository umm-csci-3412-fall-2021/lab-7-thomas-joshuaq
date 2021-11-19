package segmentedfilesystem;

import java.util.ArrayList;

public class PacketManager{
    ArrayList<Packet> aList = new ArrayList<Packet>();

    String name;
    Byte data[];
    byte id;
    public void addPacket(byte[] array, int length){
        boolean lookup = false;
        for(int i = 0; i < aList.size(); i++){
            if(aList.get(i).id == array[1]){
                aList.get(i).addNewPacket(array, length);
                lookup = true;
            }
        }
        if(lookup == false){
            Packet newPacket = new Packet(array, length);
            aList.add(newPacket);
        }
    }

    public boolean isDone(){
        boolean isDone = true;
        for(int i = 0; i < aList.size(); i++){
            if(aList.get(i).checkIfDone() == false){
                isDone = false;
                break;
            }
        }
        return isDone;
    }
}