package segmentedfilesystem;

import java.io.IOException;
import java.util.ArrayList;

public class PacketManager{
    ArrayList<BinaryFile> files = new ArrayList<BinaryFile>();
    String name;
    Byte data[];
    byte id;
    public void addPacket(byte[] array, int length){
        //Here we check if a BinaryFile with the ID of a newly received packet has been created yet
        //If not the "lookup" boolean will be false and we have to make a new BinaryFile
        boolean lookup = false;
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).id == array[1]){
                files.get(i).addNewPacket(array, length);
                lookup = true;
                break;
            }
        }
        //If the BinaryFile doesn't exist yet, add it to the arraylist and add the packet to it
        if(lookup == false){
            BinaryFile newPacket = new BinaryFile();
            newPacket.addNewPacket(array, length);
            newPacket.id = array[1];
            files.add(newPacket);
        }
    }

    //Checks to see if every BinaryFile has all its data
    public boolean isDone(){
        boolean isDone = true;
        for(int i = 0; i < files.size(); i++){
            //There is an || here to make sure we have gotten at least 3 different files
            //We do this because if the first 2 packets we recieve are the small.txt packets
            //the client will terminate early since the only BinaryFile in the "files" arraylist will return true
            if(files.size() < 3 || files.get(i).checkIfDone() == false){
                isDone = false;
                break;
            }
        }
        return isDone;
    }
    //Calls each BinaryFile in the arraylist and has them create the files they hold
    public void writeAllToDisk(){
        for(int i = 0; i < files.size(); i++){
            try {
                files.get(i).writeToDisk();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}