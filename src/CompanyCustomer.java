import java.util.Arrays;

public class CompanyCustomer extends Customer {
    private String companyName;
    private String companyAddress;
    private String cvr;

    public CompanyCustomer(String name, String address, String city, String email, int zipcode, int phoneNumber, String companyName, String companyAddress, String cvr) {
        super(name, address, city, email, zipcode, phoneNumber);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.cvr = cvr;
    }

    public String toFileString() {
        return super.toFileString() + ";" + companyName + ";" + companyAddress + ";" + cvr;
    }

    public static CompanyCustomer fromString(String str) {
        String[] parts = str.split(";");
        if (parts.length<9) {
            throw new IllegalArgumentException("String format incorrect, missing field");
        }
        Customer base = Customer.fromString(String.join(";", Arrays.copyOf(parts, 6)));
        String companyName = parts[6];
        String companyAddress = parts[7];
        String cvr = parts[8];

        return new CompanyCustomer(base.getName(), base.getAddress(), base.getCity(), base.getEmail(), base.getZipcode(), base.getPhoneNumber(), companyName, companyAddress, cvr);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCvr() {
        return cvr;
    }
}
