import java.util.Arrays;

public class FamilyCar extends Car {

    private boolean hasManualGear;
    private boolean hasAirCondition;
    private boolean hasCruiseControl;
    private int seats;

    public FamilyCar(String brand, String model, String fuelType, String registrationNumber, int firstRegistrationYear, int firstRegistrationMonth, int odometer, boolean hasManualGear, boolean hasAirCondition, boolean hasCruiseControl, int seats) {
        super(brand, model, fuelType, registrationNumber, firstRegistrationYear, firstRegistrationMonth, odometer);
        this.hasManualGear = hasManualGear;
        this.hasAirCondition = hasAirCondition;
        this.hasCruiseControl = hasCruiseControl;
        this.seats = seats;
    }


    public String toFileString() {
        return super.toFileString() + ";" + hasManualGear + ";" + hasAirCondition + ";" + hasCruiseControl  + ";" + seats;
    }

    public static FamilyCar fromString(String str) {
        String[] parts = str.split(";");
        if (parts.length < 11){
            throw new IllegalArgumentException("String format incorrect, missing fields");
        }
        Car baseCar = Car.fromString(String.join(";", Arrays.copyOf(parts, 7)));
        boolean hasManualGear = Boolean.parseBoolean(parts[7]);
        boolean hasAirCondition = Boolean.parseBoolean(parts[8]);
        boolean hasCruiseControl = Boolean.parseBoolean(parts[9]);
        int seats = Integer.parseInt(parts[10]);

        return new FamilyCar(baseCar.getBrand(), baseCar.getModel(), baseCar.getFuelType(), baseCar.getRegistrationNumber(), baseCar.getFirstRegistrationYear(), baseCar.getFirstRegistrationMonth(), baseCar.getOdometer(), hasManualGear, hasAirCondition, hasCruiseControl, seats);
    }

    public boolean isHasManualGear() {
        return hasManualGear;
    }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public boolean isHasCruiseControl() {
        return hasCruiseControl;
    }

    public int getSeats() {
        return seats;
    }
}
