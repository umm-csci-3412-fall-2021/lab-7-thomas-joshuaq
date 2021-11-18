package segmentedfilesystem;

import java.util.SortedMap;
import java.util.TreeMap;

public class ReceivedFile{
    static SortedMap<Byte, Byte[]> map = new TreeMap<Byte, Byte[]>();

static void AddHeader(HeaderPacket header, int length){
    map.put(header.id, header.data);
}

static void AddData(DataPacket packet, int length){
    map.put(packet.id, packet.data);
}


}
