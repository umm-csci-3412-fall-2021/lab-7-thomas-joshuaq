package segmentedfilesystem;

import java.nio.charset.StandardCharsets;
import java.util.SortedMap;


public class HeaderPacket{
    byte[] name;
    byte[] data;
    int length;
    Byte id;
    public HeaderPacket(byte[] array, int length){
        byte[] newArr = new byte[length-2];
        for(int i = 0; i < length - 2; i++){
            newArr[i] = array[i+2];
        }
        name = newArr;
        id = array[1];
    }

private int getLength(){
    return this.getLength();
}

}

