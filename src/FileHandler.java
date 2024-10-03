import java.io.*;
import java.util.*;

public class FileHandler {

    public static void saveCars(List<Car> cars, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/cars.txt"))) {
            for (Car car : cars) {
                writer.write(car.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/cars.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cars.add(Car.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return cars;
    }

    public static void saveCustomers(List<Customer> customers, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/customer.txt"))) {
             for(Customer customer : customers) {
                writer.write(customer.toFileString() + "\n");
            }
        } catch(IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Customer> loadCustomers(){
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/customer.txt"))){
            String line;
            while ((line = reader.readLine()) != null) customers.add(Customer.fromString(line));
        }catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return customers;
    }
}

