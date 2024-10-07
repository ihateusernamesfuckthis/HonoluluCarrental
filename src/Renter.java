import java.time.LocalDate;

public class Renter {
    private int id;
    private String driversLicense;
    private LocalDate driversLicenseIssueDate; // Can be Date type if preferred
    private String name;
    private String mobilePhone;
    private String phone;
    private String email;
    private String streetAddress;
    private String zipCode;

    public Renter(int id, String driversLicense, LocalDate driversLicenseIssueDate, String name, String mobilePhone, String phone, String email, String streetAddress, String zipCode) {
        this.id = id;
        this.driversLicense = driversLicense;
        this.driversLicenseIssueDate = driversLicenseIssueDate;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }

    public LocalDate getDriversLicenseIssueDate() {
        return driversLicenseIssueDate;
    }

    public void setDriversLicenseIssueDate(LocalDate driversLicenseIssueDate) {
        this.driversLicenseIssueDate = driversLicenseIssueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
