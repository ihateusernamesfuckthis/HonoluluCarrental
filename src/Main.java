import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String CAR_FILE_PATH = "src/cars.txt";
    private static final String CUSTOMER_FILE_PATH = "src/customer.txt";

    public static void main(String[] args){
        List<Car> cars = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        try{
            cars = FileHandler.loadCars();
            customers = FileHandler.loadCustomers();
        } catch (Exception e) {
            System.out.println("Failed to load data: "+ e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add car");
            System.out.println("2. Add customer");
            System.out.println("3. Display Cars");
            System.out.println("4. Display Customers");
            System.out.println("5. Save data");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left-over

            switch (choice) {
                case 1:
                    addCar(scanner, cars);
                    break;
                case 2:
                    addCustomer(scanner, customers);
                    break;
                case 3:
                    for (Car car : cars){
                        System.out.println(car.toFileString());
                    }
                    break;
                case 4:
                    for(Customer customer : customers){
                        System.out.println(customer.toFileString());
                    }
                    break;
                case 5:
                    FileHandler.saveCars(cars, "src/cars.txt");
                    FileHandler.saveCustomers(customers, "src/customer.txt");
                    System.out.println("Data saved succesfully.");
                    break;
                case 6:
                    System.out.println("Exiting program..");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void addCustomer(Scanner scanner, List<Customer> customers) {
        System.out.println("Enter customer type (private or company):");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter zipcode:");
        int zipcode = scanner.nextInt();
        System.out.println("Enter phone number:");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left-over

        if (type.equals("private")) {
            System.out.println("Enter driver's license number:");
            String licenseNumber = scanner.nextLine();
            PrivateCustomer privateCustomer = new PrivateCustomer(name, address, city, email, zipcode, phoneNumber, licenseNumber);
            customers.add(privateCustomer);
        } else if (type.equals("company")) {
            System.out.println("Enter company name:");
            String companyName = scanner.nextLine();
            System.out.println("Enter company address:");
            String companyAddress = scanner.nextLine();
            System.out.println("Enter company registration number (CVR):");
            String cvr = scanner.nextLine();
            CompanyCustomer companyCustomer = new CompanyCustomer(name, address, city, email, zipcode, phoneNumber, companyName, companyAddress, cvr);
            customers.add(companyCustomer);
        } else {
            System.out.println("Unknown customer type. Please enter 'private' or 'company'.");
        }
    }

    private static void addCar(Scanner scanner, List<Car> cars) {
        System.out.println("Enter car type (luxury or family):");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter brand:");
        String brand = scanner.nextLine();
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        System.out.println("Enter fuel type:");
        String fuelType = scanner.nextLine();
        System.out.println("Enter registration number:");
        String registrationNumber = scanner.nextLine();
        System.out.println("Enter first registration year:");
        int firstRegistrationYear = scanner.nextInt();
        System.out.println("Enter first registration month:");
        int firstRegistrationMonth = scanner.nextInt();
        System.out.println("Enter odometer reading:");
        int odometer = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline

        if (type.equals("luxury")) {
            System.out.println("Does it have automatic gear? (yes/no):");
            boolean hasAutomaticGear = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Does it have air conditioning? (yes/no):");
            boolean hasAirCondition = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Does it have cruise control? (yes/no):");
            boolean hasCruiseControl = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Does it have leather seats? (yes/no):");
            boolean hasLeatherSeats = scanner.nextLine().trim().toLowerCase().equals("yes");

            LuxuryCar luxuryCar = new LuxuryCar(brand, model, fuelType, registrationNumber,
                    firstRegistrationYear, firstRegistrationMonth, odometer,
                    hasAutomaticGear, hasAirCondition, hasCruiseControl, hasLeatherSeats);
            cars.add(luxuryCar);
            System.out.println("Luxury car added successfully.");
        } else if (type.equals("family")) {
            System.out.println("Does it have manual gear? (yes/no):");
            boolean hasManualGear = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Does it have air conditioning? (yes/no):");
            boolean hasAirCondition = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Does it have cruise control? (yes/no):");
            boolean hasCruiseControl = scanner.nextLine().trim().toLowerCase().equals("yes");
            System.out.println("Enter number of seats:");
            int seats = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            FamilyCar familyCar = new FamilyCar(brand, model, fuelType, registrationNumber,
                    firstRegistrationYear, firstRegistrationMonth, odometer,
                    hasManualGear, hasAirCondition, hasCruiseControl, seats);
            cars.add(familyCar);
            System.out.println("Family car added successfully.");
        } else {
            System.out.println("Unknown car type. Please enter 'luxury' or 'family'.");
        }
    }

}
