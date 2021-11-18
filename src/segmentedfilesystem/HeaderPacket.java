package segmentedfilesystem;

import java.nio.charset.StandardCharsets;
import java.util.SortedMap;


public class HeaderPacket{
    byte name[];
    Byte data[];
    int length;
    Byte id;
    public HeaderPacket(byte[] array, int length){
        byte[] newArr = new byte[array.length-2];
        for(int i = 0; i<newArr.length; i++){
            newArr[i] = array[i+2];
        }
        name = newArr;
        id = array[1];
    }
// private Byte getName(){
//     return this.name;
// }

private int getLength(){
    return this.getLength();
}

}

