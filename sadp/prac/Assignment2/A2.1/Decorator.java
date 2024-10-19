import java.util.*;
import java.io.*;

interface Text {
	void displayString();
}

class OriginalText implements Text {
	String str;
	public OriginalText(String str){
		this.str=str;
	}
	@Override public void displayString(){
		System.out.println("Original Text : "+str);
	}

}
class InputChange implements Text {
	Text newText;
	
	public InputChange(Text newText){
		this.newText=newText;
	}
	@Override public void displayString(){
		newText.displayString();
	}
}
class ToLowerCase extends InputChange{
	String str;
	
	public ToLowerCase(Text newText, String s){
		super(newText);
		str=s;
	}
	@Override public void displayString (){
		System.out.println("Converted to Lower Case : "+str.toLowerCase());
	}
}

public class Decorator {
	public static void main(String[] args) throws IOException {
	System.out.println("Enter the String in Upper Case : ");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str = br.readLine().toUpperCase();
	
	Text temp= new OriginalText(str);
	temp.displayString();
	
	Text newstr = new ToLowerCase(new OriginalText(str),str);
	newstr.displayString();
	
	}
}
