import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The Component Interface
interface PatientData {
    String getDetails();
}

// The Concrete Component
class BasicPatientData implements PatientData {
    private String name;
    private int age;
    private String contactInfo;

    public BasicPatientData(String name, int age, String contactInfo) {
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    @Override
    public String getDetails() {
        return "Patient Name: " + name + "\nAge: " + age + "\nContact Info: " + contactInfo;
    }
}

// The Decorator Class
abstract class PatientDataDecorator implements PatientData {
    protected PatientData decoratedPatientData;

    public PatientDataDecorator(PatientData decoratedPatientData) {
        this.decoratedPatientData = decoratedPatientData;
    }

    @Override
    public String getDetails() {
        return decoratedPatientData.getDetails();
    }
}

// Medical Record Decorator
class MedicalRecordDecorator extends PatientDataDecorator {
    private String diagnosis;

    public MedicalRecordDecorator(PatientData decoratedPatientData, String diagnosis) {
        super(decoratedPatientData);
        this.diagnosis = diagnosis;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nDiagnosis: " + diagnosis;
    }
}

// Billing Info Decorator
class BillingInfoDecorator extends PatientDataDecorator {
    private String billingInfo;

    public BillingInfoDecorator(PatientData decoratedPatientData, String billingInfo) {
        super(decoratedPatientData);
        this.billingInfo = billingInfo;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nBilling Info: " + billingInfo;
    }
}

// Prescription Data Decorator
class PrescriptionDataDecorator extends PatientDataDecorator {
    private String prescriptionDetails;

    public PrescriptionDataDecorator(PatientData decoratedPatientData, String prescriptionDetails) {
        super(decoratedPatientData);
        this.prescriptionDetails = prescriptionDetails;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nPrescription: " + prescriptionDetails;
    }
}

// Main class with Menu
public class HealthCareSystem {

    private static List<PatientData> patientDataList = new ArrayList<>();
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option = "";

        while (!option.equals("3")) {
            printMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    insertPatientData(scanner);
                    break;
                case "2":
                    viewPatientData();
                    break;
                case "3":
                    System.out.println(GREEN + "Exiting the system. Goodbye!" + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid option. Please select 1, 2, or 3." + RESET);
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        // Red plus sign for hospital symbol
        String redPlusSign = RED + "\u2795" + RESET;
        System.out.println(CYAN + "\n--- " + redPlusSign + " Health Care - Patient Data Management System " + redPlusSign + " ---" + RESET);
        System.out.println(PURPLE + "1. Insert Patient Data" + RESET);
        System.out.println(PURPLE + "2. View All Patient Data" + RESET);
        System.out.println(PURPLE + "3. Exit" + RESET);
        System.out.print(YELLOW + "Select an option: " + RESET);
    }

    private static void insertPatientData(Scanner scanner) {
        String name, contactInfo, diagnosis, billingInfo, prescription;
        int age;

        while (true) {
            System.out.print(BLUE + "Enter patient name (or 'q' to quit): " + RESET);
            name = scanner.nextLine();
            if (name.equals("q")) break;

            System.out.print(BLUE + "Enter patient age: " + RESET);
            age = Integer.parseInt(scanner.nextLine());

            System.out.print(BLUE + "Enter patient contact info: " + RESET);
            contactInfo = scanner.nextLine();

            // Create basic patient data
            PatientData patientData = new BasicPatientData(name, age, contactInfo);

            System.out.print(BLUE + "Enter diagnosis (or press Enter to skip): " + RESET);
            diagnosis = scanner.nextLine();
            if (!diagnosis.isEmpty()) {
                patientData = new MedicalRecordDecorator(patientData, diagnosis);
            }

            System.out.print(BLUE + "Enter billing info (or press Enter to skip): " + RESET);
            billingInfo = scanner.nextLine();
            if (!billingInfo.isEmpty()) {
                patientData = new BillingInfoDecorator(patientData, billingInfo);
            }

            System.out.print(BLUE + "Enter prescription details (or press Enter to skip): " + RESET);
            prescription = scanner.nextLine();
            if (!prescription.isEmpty()) {
                patientData = new PrescriptionDataDecorator(patientData, prescription);
            }

            // Add patient data to list
            patientDataList.add(patientData);
            System.out.println(GREEN + "\nPatient data added successfully!\n" + RESET);
        }
    }

    private static void viewPatientData() {
        if (patientDataList.isEmpty()) {
            System.out.println(RED + "No patient data available." + RESET);
            return;
        }

        System.out.println(CYAN + "\n--- All Patient Data ---" + RESET);
        for (int i = 0; i < patientDataList.size(); i++) {
            System.out.println(PURPLE + "\nPatient " + (i + 1) + ": " + RESET);
            System.out.println(patientDataList.get(i).getDetails());
        }
    }
}
