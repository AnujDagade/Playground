import java.io.*;

class LowercaseInputStream extends FilterInputStream {
    public LowercaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c != -1) {
            c = Character.toLowerCase((char) c);
        }
        return c;
    }
}

public class Deco {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("./input.txt");
            LowercaseInputStream lowercaseInputStream = new LowercaseInputStream(fileInputStream);

            int c;
            while ((c = lowercaseInputStream.read()) != -1) {
                System.out.print((char) c);
            }

            lowercaseInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}