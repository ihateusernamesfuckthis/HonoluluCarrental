public class Customer {
    private String name;

    private String address;

    private String city;

    private String email;

    private int zipcode;

    private int phoneNumber;

    public Customer(String name, String address, String city, String email, int zipcode, int phoneNumber) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = email;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }

    public String toFileString(){
        return name + ";" + address + ";" + city + ";" + email  + ";" + zipcode + ";" + phoneNumber;
    }

    public static Customer fromString(String str){
        String[] parts = str.split(";");
        if (parts.length < 6) {
            throw new IllegalArgumentException("String format incorrect, missing fields. Expected but found " + parts.length);
        }
        try{
            String name = parts[0];
            String address = parts[1];
            String city = parts[2];
            String email = parts[3];
            int zipcode = Integer.parseInt(parts[4]);
            int phoneNumber = Integer.parseInt(parts[5]);

            return new Customer(name, address, city, email, zipcode, phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("One of the integers fields (zipcode or phone number) is not valid", e);
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public int getZipcode() {
        return zipcode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
