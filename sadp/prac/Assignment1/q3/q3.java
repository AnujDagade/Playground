import java.util.Scanner;

// Abstract Products
interface Photo {
    void takePhoto();
}

interface Video {
    void recordVideo();
}

// Concrete Products for Android
class AndroidPhoto implements Photo {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo with Android phone.");
    }
}

class AndroidVideo implements Video {
    @Override
    public void recordVideo() {
        System.out.println("Recording video with Android phone.");
    }
}

// Concrete Products for iPhone
class iPhonePhoto implements Photo {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo with iPhone.");
    }
}

class iPhoneVideo implements Video {
    @Override
    public void recordVideo() {
        System.out.println("Recording video with iPhone.");
    }
}

// Abstract Factory
interface PhoneFactory {
    Photo createPhoto();
    Video createVideo();
}

// Concrete Factory for Android
class AndroidFactory implements PhoneFactory {
    @Override
    public Photo createPhoto() {
        return new AndroidPhoto();
    }

    @Override
    public Video createVideo() {
        return new AndroidVideo();
    }
}

// Concrete Factory for iPhone
class iPhoneFactory implements PhoneFactory {
    @Override
    public Photo createPhoto() {
        return new iPhonePhoto();
    }

    @Override
    public Video createVideo() {
        return new iPhoneVideo();
    }
}

// Client Code
public class q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Phone Type (Android/iPhone):");
        String phoneType = scanner.nextLine().trim();

        PhoneFactory phoneFactory = null;
        
        if (phoneType.equalsIgnoreCase("Android")) {
            phoneFactory = new AndroidFactory();
        } else if (phoneType.equalsIgnoreCase("iPhone")) {
            phoneFactory = new iPhoneFactory();
        } else {
            System.out.println("Invalid phone type entered.");
            scanner.close();
            return;
        }

        System.out.println("Select Functionality (Photo/Video):");
        String functionality = scanner.nextLine().trim();

        if (functionality.equalsIgnoreCase("Photo")) {
            Photo photo = phoneFactory.createPhoto();
            photo.takePhoto();
        } else if (functionality.equalsIgnoreCase("Video")) {
            Video video = phoneFactory.createVideo();
            video.recordVideo();
        } else {
            System.out.println("Invalid functionality entered.");
        }

        scanner.close();
    }
}

