import java.util.*;

public class Adap {
    public static void main(String[] args) {
        Enumeration<Integer> enumeration = new Vector<Integer>(Arrays.asList(1,2,3)).elements();
        Iterator<Integer> iterator = new EnumerationIteratorAdapter<Integer>(enumeration);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
