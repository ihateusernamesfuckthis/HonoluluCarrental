import java.time.LocalDate;

public class Car {
    private int id;
    private String registrationNumber;
    private LocalDate registrationDate;
    private int odometer;
    private int motorSize;
    private boolean hasAutomaticGear;
    private boolean hasAirCondition;
    private boolean hasCruiseControl;
    private int seatAmount;
    private int horsePower;
    private String brand;
    private String model;
    private String fuelType;

    public Car(int id, String registrationNumber, LocalDate registrationDate, int odometer, int motorSize, boolean hasAutomaticGear, boolean hasAirCondition, boolean hasCruiseControl, int seatAmount, int horsePower, String brand, String model, String fuelType) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.odometer = odometer;
        this.motorSize = motorSize;
        this.hasAutomaticGear = hasAutomaticGear;
        this.hasAirCondition = hasAirCondition;
        this.hasCruiseControl = hasCruiseControl;
        this.seatAmount = seatAmount;
        this.horsePower = horsePower;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getMotorSize() {
        return motorSize;
    }

    public void setMotorSize(int motorSize) {
        this.motorSize = motorSize;
    }

    public boolean isHasAutomaticGear() {
        return hasAutomaticGear;
    }

    public void setHasAutomaticGear(boolean hasAutomaticGear) {
        this.hasAutomaticGear = hasAutomaticGear;
    }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    public boolean isHasCruiseControl() {
        return hasCruiseControl;
    }

    public void setHasCruiseControl(boolean hasCruiseControl) {
        this.hasCruiseControl = hasCruiseControl;
    }

    public int getSeatAmount() {
        return seatAmount;
    }

    public void setSeatAmount(int seatAmount) {
        this.seatAmount = seatAmount;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}

