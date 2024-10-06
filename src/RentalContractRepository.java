import java.sql.*;

public class RentalContractRepository {

    public RenterRepository renterRepository;

    public CarRepository carRepository;

    public RentalContractRepository(RenterRepository renterRepository, CarRepository carRepository) {
        this.renterRepository = renterRepository;
        this.carRepository = carRepository;
    }

    public void addRentalContract(RentalContract rentalContract) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO rental_contract " +
                    "(rental_contract_renter_id, rental_contract_car_id, rental_contract_from_date, rental_contract_to_date, rental_contract_max_km_allowed, rental_contract_car_odometer_at_start) " +
                    "VALUES (?,?,?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalContract.getRenter().getId()); // rental_contract_renter_id
            stmt.setInt(2, rentalContract.getCar().getId()); // rental_contract_car_id
            stmt.setDate(3, java.sql.Date.valueOf(rentalContract.getFromDate().toString())); // rental_contract_from_date
            stmt.setDate(4, java.sql.Date.valueOf(rentalContract.getToDate().toString())); // rental_contract_to_date
            stmt.setInt(5, rentalContract.getMaxKmAllowed()); // rental_contract_max_km_allowed
            stmt.setInt(6, rentalContract.getCarOdometerAtStart()); // rental_contract_car_odometer_at_start
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Car or renter does not exist in table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public RentalContract getRentalContractById(int rentalContractId) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "SELECT rental_contract_id, rental_contract_renter_id, rental_contract_car_id, rental_contract_from_date, rental_contract_to_date, rental_contract_max_km_allowed, rental_contract_car_odometer_at_start " +
                    "FROM rental_contract " +
                    "WHERE rental_contract_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalContractId);

            rs = stmt.executeQuery();
            // rs.next() will return false if no match found.
            // We only want the first result found, as ID should be unique and only return 1
            if (rs.next()) {
                RentalContract rentalContract = new RentalContract(
                        rs.getInt("rental_contract_id"),
                        renterRepository.getRenterById(rs.getInt("rental_contract_renter_id")),
                        carRepository.getCarById(rs.getInt("rental_contract_car_id")),
                        rs.getDate("rental_contract_from_date").toLocalDate(),
                        rs.getDate("rental_contract_to_date").toLocalDate(),
                        rs.getInt("rental_contract_max_km_allowed"),
                        rs.getInt("rental_contract_car_odometer_at_start")
                );
                return rentalContract;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
        return null;
    }

    public boolean deleteRentalContractById(int rentalContractId) {
        Connection connection;
        PreparedStatement stmt;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "DELETE FROM rental_contract " +
                    "WHERE rental_contract_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalContractId);

            int effectedRows = stmt.executeUpdate();

            // Returns true if at least 1 row is effected
            return effectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
        return false;
    }

    public void updateRentalContract(RentalContract rentalContract) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "UPDATE rental_contract SET " +
                    "rental_contract_renter_id = ?, " +
                    "rental_contract_car_id = ?, " +
                    "rental_contract_from_date = ?, " +
                    "rental_contract_to_date = ?, " +
                    "rental_contract_max_km_allowed = ?, " +
                    "rental_contract_car_odometer_at_start = ? " +
                    "WHERE rental_contract_id = ?";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalContract.getRenter().getId()); // rental_contract_renter_id
            stmt.setInt(2, rentalContract.getCar().getId()); // rental_contract_car_id
            stmt.setDate(3, java.sql.Date.valueOf(rentalContract.getFromDate().toString())); // rental_contract_from_date
            stmt.setDate(4, java.sql.Date.valueOf(rentalContract.getToDate().toString())); // rental_contract_to_date
            stmt.setInt(5, rentalContract.getMaxKmAllowed()); // rental_contract_max_km_allowed
            stmt.setInt(6, rentalContract.getCarOdometerAtStart()); // rental_contract_car_odometer_at_start
            stmt.setInt(7, rentalContract.getId()); // rental_contract_id
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Car or renter does not exist in table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

}