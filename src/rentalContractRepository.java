import java.sql.*;

public class rentalContractRepository {

    public static void addRentalContract() {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO rental_contract " +
                    "(rental_contract_renter_id, rental_contract_car_id, rental_contract_from_date, rental_contract_to_date, rental_contract_max_km_allowed, rental_contract_car_odometer_at_start) " +
                    "VALUES (?,?,?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1); // rental_contract_renter_id
            stmt.setInt(2, 1); // rental_contract_car_id
            stmt.setDate(3, java.sql.Date.valueOf("2020-01-01")); // rental_contract_from_date
            stmt.setDate(4, java.sql.Date.valueOf("2020-01-31")); // rental_contract_to_date
            stmt.setInt(5, 500); // rental_contract_max_km_allowed
            stmt.setInt(6, 0); // rental_contract_car_odometer_at_start
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

    public static void getRentalContractById(int rentalContractId) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "SELECT rental_contract_renter_id, rental_contract_car_id, rental_contract_from_date, rental_contract_to_date, rental_contract_max_km_allowed, rental_contract_car_odometer_at_start " +
                    "FROM rental_contract " +
                    "WHERE rental_contract_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalContractId);

            rs = stmt.executeQuery();
            // rs.next() will return false if no match found.
            // We only want the first result found, as ID should be unique and only return 1
            if (rs.next()) {
                // TODO Update to use rentalContract object and return rentalContract
                Renter renter = RenterRepository.getRenterById(rs.getInt("rental_contract_renter_id"));
                Car car = CarRepository.getCarById(rs.getInt("rental_contract_car_id"));
                System.out.println("From date: " + rs.getDate("rental_contract_from_date"));
                System.out.println("To date: " + rs.getDate("rental_contract_to_date"));
                System.out.println("Max km allowed: " + rs.getInt("rental_contract_max_km_allowed"));
                System.out.println("Car odometer at start: " + rs.getInt("rental_contract_car_odometer_at_start"));
            } else {
                // TODO Update to use rentalContract object and return null if no result found
                System.out.println("No rentalContract found with ID " + rentalContractId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public static boolean deleteRentalContractById(int rentalContractId) {
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

    // TODO Update to accept contract object as parameter
    public static void updateRentalContract(int RentalContractId) {
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
            stmt.setInt(1, 1); // rental_contract_renter_id
            stmt.setInt(2, 1); // rental_contract_car_id
            stmt.setDate(3, java.sql.Date.valueOf("1999-01-01")); // rental_contract_from_date
            stmt.setDate(4, java.sql.Date.valueOf("1999-01-01")); // rental_contract_to_date
            stmt.setInt(5, 1000); // rental_contract_max_km_allowed
            stmt.setInt(6, 0); // rental_contract_car_odometer_at_start
            stmt.setInt(7, RentalContractId); // rental_contract_id
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
