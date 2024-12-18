import java.util.*;
import java.io.*;

interface Enumeration {
	boolean hasMoreElements();
	Object nextElement();
}

interface Iterator {
	boolean hasNext();
	Object next();
}
// EnumCollection implementing Enumeration
class EnumCollection implements Enumeration{
	private String[] data;
	private int currentIndex;
	
	public EnumCollection(String[] data){
		this.data = data;
        this.currentIndex = 0;
	}
	
	public boolean hasMoreElements() {
		System.out.println(currentIndex < data.length);
        return currentIndex < data.length;
    }
    
    public Object nextElement() {
        if (hasMoreElements()) {
            return data[currentIndex++];
        }
        return null;
    }
}
// EnumAdapter implementing Iterator
class EnumAdapter implements Iterator {
    Enumeration enumeration;

    public EnumAdapter(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public Object next() {
        return enumeration.nextElement();
    }
}
public class EnumIterator {
    public static void main(String[] args) {
        String[] arr = {"TATYA","AABA","BAPPU","APPA"};

        Enumeration enumeration = new EnumCollection(arr);
        Iterator iterator = new EnumAdapter(enumeration);

        System.out.println("Adapter Pattern for Enumeration Iterator:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
