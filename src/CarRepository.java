import java.sql.*;

public class CarRepository {
    public static void addCar() {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO car " +
                    "(car_registration_number, car_registration_date, car_odometer, car_motor_size, car_has_automatic_gear, car_has_aircondition, car_has_cruise_control, car_seat_amount, car_horse_power, car_model_id, car_fuel_type_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "X-123456"); // car_registration_number
            stmt.setDate(2, java.sql.Date.valueOf("1999-01-01")); // car_registration_date
            stmt.setInt(3, 98050); // car_odometer
            stmt.setInt(4, 3500); // car_motor_size
            stmt.setBoolean(5, true); // car_has_automatic_gear
            stmt.setBoolean(6, true); // car_has_aircondition
            stmt.setBoolean(7, true); // car_has_cruise_control
            stmt.setInt(8, 7); // car_seat_amount
            stmt.setInt(9, 275); // car_horse_power
            stmt.setInt(10, 1); // car_model_id
            stmt.setInt(11, 1); // car_fuel_type_id
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Car model or fuel type does not exist in table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public static void getCarById(int carId) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "SELECT c.car_registration_number, c.car_registration_date, c.car_odometer, c.car_motor_size, c.car_has_automatic_gear, c.car_has_aircondition, c.car_has_cruise_control, c.car_seat_amount, c.car_horse_power, cb.car_brand_name, cm.car_model_name, ft.fuel_type_name " +
                    "FROM car c " +
                    "JOIN car_model cm ON c.car_model_id = cm.car_model_id " +
                    "JOIN car_brand cb ON cm.car_model_brand_id = cb.car_brand_id " +
                    "JOIN fuel_type ft ON c.car_fuel_type_id = ft.fuel_type_id " +
                    "WHERE c.car_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, carId);

            rs = stmt.executeQuery();
            // rs.next() will return false if no match found.
            // We only want the first result found, as ID should be unique and only return 1
            if (rs.next()) {
                // TODO Update to use car object and return car
                System.out.println("Car registration number: " + rs.getString("car_registration_number"));
                System.out.println("Car registration date: " + rs.getDate("car_registration_date"));
                System.out.println("Car odometer: " + rs.getInt("car_odometer"));
                System.out.println("Car motor size: " + rs.getInt("car_motor_size"));
                System.out.println("Car has automatic gear: " + rs.getBoolean("car_has_automatic_gear"));
                System.out.println("Car has aircondition: " + rs.getBoolean("car_has_aircondition"));
                System.out.println("Car has cruise control: " + rs.getBoolean("car_has_cruise_control"));
                System.out.println("Car seat amount: " + rs.getInt("car_seat_amount"));
                System.out.println("Car horse power: " + rs.getInt("car_horse_power"));
                System.out.println("Car brand name: " + rs.getString("car_brand_name"));
                System.out.println("Car model name: " + rs.getString("car_model_name"));
                System.out.println("Car fuel type name: " + rs.getString("fuel_type_name"));
            } else {
                // TODO Update to use car object and return null if no car found
                System.out.println("No car found with ID " + carId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public static boolean deleteCarById(int carId) {
        Connection connection;
        PreparedStatement stmt;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "DELETE FROM car " +
                    "WHERE car_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, carId);

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

    // TODO update to take in car object
    public static void updateCar(int carId) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "UPDATE car SET " +
                    "car_registration_number = ?, " +
                    "car_registration_date = ?, " +
                    "car_odometer = ?, " +
                    "car_motor_size = ?, " +
                    "car_has_automatic_gear = ?, " +
                    "car_has_aircondition = ?, " +
                    "car_has_cruise_control = ?, " +
                    "car_seat_amount = ?, " +
                    "car_horse_power = ?, " +
                    "car_model_id = ?, " +
                    "car_fuel_type_id = ? " +
                    "WHERE car_id = ?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, "X-123456"); // car_registration_number
            stmt.setDate(2, java.sql.Date.valueOf("1999-01-01")); // car_registration_date
            stmt.setInt(3, 98050); // car_odometer
            stmt.setInt(4, 3500); // car_motor_size
            stmt.setBoolean(5, true); // car_has_automatic_gear
            stmt.setBoolean(6, true); // car_has_aircondition
            stmt.setBoolean(7, true); // car_has_cruise_control
            stmt.setInt(8, 7); // car_seat_amount
            stmt.setInt(9, 275); // car_horse_power
            stmt.setInt(10, 1); // car_model_id
            stmt.setInt(11, 1); // car_fuel_type_id
            stmt.setInt(12, carId); // car_id
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Car model or fuel type does not exist in table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }
}
