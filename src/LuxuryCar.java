import java.util.Arrays;

public class LuxuryCar extends Car {
    private boolean hasAutomaticGear;
    private boolean hasAirCondition;
    private boolean hasCruiseControl;
    private boolean hasLeatherSeats;

    public LuxuryCar (String brand, String model, String fuelType, String registrationNumber, int firstRegistrationYear, int firstRegistrationMonth, int odometer, boolean hasAutomaticGear, boolean hasAirCondition, boolean hasCruiseControl, boolean hasLeatherSeats){
        super(brand, model, fuelType, registrationNumber, firstRegistrationYear, firstRegistrationMonth, odometer);
        this.hasAutomaticGear = hasAutomaticGear;
        this.hasAirCondition = hasAirCondition;
        this.hasCruiseControl = hasCruiseControl;
        this.hasLeatherSeats = hasLeatherSeats;
    }

    public String toFileString() {
        return super.toFileString() + ";" + hasAutomaticGear + ";" + hasAirCondition + ";" + hasCruiseControl  + ";" + hasLeatherSeats;
    }

    public static LuxuryCar fromString(String str) {
        String[] parts = str.split(";");
        if (parts.length < 11) {
            throw new IllegalArgumentException("String format incorrect, missing fields");
        }
        Car baseCar = Car.fromString(String.join(";", Arrays.copyOf(parts, 7)));
        boolean hasAutomaticGear = Boolean.parseBoolean(parts[7]);
        boolean hasAirCondition = Boolean.parseBoolean(parts[8]);
        boolean hasCruiseControl = Boolean.parseBoolean(parts[9]);
        boolean hasLeatherSeats = Boolean.parseBoolean(parts[10]);
        return new LuxuryCar(baseCar.getBrand(), baseCar.getModel(), baseCar.getFuelType(), baseCar.getRegistrationNumber(), baseCar.getFirstRegistrationYear(), baseCar.getFirstRegistrationMonth(), baseCar.getOdometer(), hasAutomaticGear, hasAirCondition, hasCruiseControl, hasLeatherSeats);

    }



    public boolean isHasAutomaticGear() {
        return hasAutomaticGear;
    }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public boolean isHasCruiseControl() {
        return hasCruiseControl;
    }

    public boolean isHasLeatherSeats() {
        return hasLeatherSeats;
    }
}
