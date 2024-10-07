import java.time.LocalDate;

public class RentalContract {
    private int id;
    private Renter renter;
    private Car car;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int maxKmAllowed;
    private int carOdometerAtStart;

    public RentalContract(int id, Renter renter, Car car, LocalDate fromDate, LocalDate toDate, int maxKmAllowed, int carOdometerAtStart) {
        this.id = id;
        this.renter = renter;
        this.car = car;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxKmAllowed = maxKmAllowed;
        this.carOdometerAtStart = carOdometerAtStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getMaxKmAllowed() {
        return maxKmAllowed;
    }

    public void setMaxKmAllowed(int maxKmAllowed) {
        this.maxKmAllowed = maxKmAllowed;
    }

    public int getCarOdometerAtStart() {
        return carOdometerAtStart;
    }

    public void setCarOdometerAtStart(int carOdometerAtStart) {
        this.carOdometerAtStart = carOdometerAtStart;
    }
}
