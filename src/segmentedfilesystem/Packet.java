package segmentedfilesystem;

import java.util.SortedMap;

public class Packet{
    String name;
    Byte data[];

private int getFileID(){
    return data[2];
}
}