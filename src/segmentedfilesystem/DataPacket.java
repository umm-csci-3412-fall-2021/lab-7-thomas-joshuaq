package segmentedfilesystem;

import java.util.SortedMap;

public class DataPacket{
    int number;
    Byte array[];
    Byte data[];
    int length;
    Byte id;
    public DataPacket(byte[] arr, int length){
        this.data = new Byte[arr.length-4];
        for(int i = 0; i<data.length; i++){
            data[i] = arr[i+4];
        }
        id = arr[1];
        int x = Byte.toUnsignedInt(arr[2]);
        int y = Byte.toUnsignedInt(arr[3]);
        this.number = 256*x + y;
        this.length = length;
    }

// private String getName(){
//     return this.name;
// }

static boolean isLast(byte[] array){
    boolean result = false;
    if((array[0]%3)==4){
        result = true;
    }
    return result;
}
}