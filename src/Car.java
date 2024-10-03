public class Car {
    private String brand;
    private String model;
    private String fuelType;
    private String registrationNumber;

    private int firstRegistrationYear;
    private int firstRegistrationMonth;
    private int odometer;

    public Car(String brand, String model, String fuelType, String registrationNumber, int firstRegistrationYear, int firstRegistrationMonth, int odometer) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.registrationNumber = registrationNumber;
        this.firstRegistrationYear = firstRegistrationYear;
        this.firstRegistrationMonth = firstRegistrationMonth;
        this.odometer = odometer;
    }

    public String toFileString() {
        return brand + ";" + model + ";" + fuelType + ";" + registrationNumber + ";" + firstRegistrationYear + ";" + firstRegistrationMonth + ";" + odometer;
    }

    public static Car fromString(String str){
        String[] parts = str.split(";");
        if (parts.length < 7) {
            throw new IllegalArgumentException("String format incorrect, missing fields");
        }
        try{
            String brand = parts[0];
            String model = parts[1];
            String fuelType = parts[2];
            String registrationNumber = parts[3];
            int firstRegistrationYear = Integer.parseInt(parts[4]);
            int firstRegistrationMonth = Integer.parseInt(parts[5]);
            int odometer = Integer.parseInt(parts[6]);

            return new Car(brand, model, fuelType, registrationNumber, firstRegistrationYear, firstRegistrationMonth, odometer);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("One of the integers fields is not valid", e);
        }
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getFirstRegistrationYear() {
        return firstRegistrationYear;
    }

    public int getFirstRegistrationMonth() {
        return firstRegistrationMonth;
    }

    public int getOdometer() {
        return odometer;
    }
}

