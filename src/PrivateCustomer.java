import java.util.*;

public class PrivateCustomer extends Customer {
    private String driverLicenseNumber;

    public PrivateCustomer(String name, String address, String city, String email, int zipcode, int phoneNumber, String driverLicenseNumber) {
        super(name, address, city, email, zipcode, phoneNumber);
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String toFileString() {
        return super.toFileString() + ";" + driverLicenseNumber;
    }

    public static PrivateCustomer fromString(String str) {
        String[] parts = str.split(";");
        if (parts.length < 7) {
            throw new IllegalArgumentException("String format incorrect, missing field");
        }
        Customer base = Customer.fromString(String.join(";", Arrays.copyOf(parts,6)));
        String driverLicenseNumber = parts[6];
        return new PrivateCustomer(base.getName(), base.getAddress(), base.getCity(), base.getEmail(), base.getZipcode(), base.getPhoneNumber(), driverLicenseNumber);
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

}
