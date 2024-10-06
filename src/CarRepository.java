import java.sql.*;

public class CarRepository {
    public void addCar(Car car) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO car " +
                    "(car_registration_number, car_registration_date, car_odometer, car_motor_size, car_has_automatic_gear, car_has_aircondition, car_has_cruise_control, car_seat_amount, car_horse_power, car_model_id, car_fuel_type_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,(SELECT car_model_id FROM car_model WHERE car_model_name = ? ORDER BY car_model_id LIMIT 1), (SELECT fuel_type_id FROM fuel_type WHERE fuel_type_name = ? ORDER BY fuel_type_id LIMIT 1));";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, car.getRegistrationNumber()); // car_registration_number
            stmt.setDate(2, java.sql.Date.valueOf(car.getRegistrationDate().toString())); // car_registration_date
            stmt.setInt(3, car.getOdometer()); // car_odometer
            stmt.setInt(4, car.getMotorSize()); // car_motor_size
            stmt.setBoolean(5, car.isHasAutomaticGear()); // car_has_automatic_gear
            stmt.setBoolean(6, car.isHasAircondition()); // car_has_aircondition
            stmt.setBoolean(7, car.isHasCruiseControl()); // car_has_cruise_control
            stmt.setInt(8, car.getSeatAmount()); // car_seat_amount
            stmt.setInt(9, car.getHorsePower()); // car_horse_power
            stmt.setString(10, car.getModelName()); // car_model_name (Car brand is a foreign key in car model table, so no need to update it)
            stmt.setString(11, car.getFuelTypeName()); // fuel_type_name
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

    public Car getCarById(int carId) {
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
                Car car = new Car(
                        carId,
                        rs.getString("car_registration_number"),
                        rs.getDate("car_registration_date").toLocalDate(),
                        rs.getInt("car_odometer"),
                        rs.getInt("car_motor_size"),
                        rs.getBoolean("car_has_automatic_gear"),
                        rs.getBoolean("car_has_aircondition"),
                        rs.getBoolean("car_has_cruise_control"),
                        rs.getInt("car_seat_amount"),
                        rs.getInt("car_horse_power"),
                        rs.getString("car_brand_name"),
                        rs.getString("car_model_name"),
                        rs.getString("fuel_type_name")
                );
                return car;
            } else {
                System.out.println("No car found with ID " + carId);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }

        return null;
    }

    public boolean deleteCarById(int carId) {
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

    public void updateCar(Car car) {
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
                    "(SELECT car_model_id FROM car_model WHERE car_model_name = ? ORDER BY car_model_id LIMIT 1), " +
                    "(SELECT fuel_type_id FROM fuel_type WHERE fuel_type_name = ? ORDER BY fuel_type_id LIMIT 1) " +
                    "WHERE car_id = ?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, car.getRegistrationNumber()); // car_registration_number
            stmt.setDate(2, java.sql.Date.valueOf(car.getRegistrationDate().toString())); // car_registration_date
            stmt.setInt(3, car.getOdometer()); // car_odometer
            stmt.setInt(4, car.getMotorSize()); // car_motor_size
            stmt.setBoolean(5, car.isHasAutomaticGear()); // car_has_automatic_gear
            stmt.setBoolean(6, car.isHasAircondition()); // car_has_aircondition
            stmt.setBoolean(7, car.isHasCruiseControl()); // car_has_cruise_control
            stmt.setInt(8, car.getSeatAmount()); // car_seat_amount
            stmt.setInt(9, car.getHorsePower()); // car_horse_power
            stmt.setString(10, car.getModelName()); // car_model_id
            stmt.setString(11, car.getFuelTypeName()); // car_fuel_type_id
            stmt.setInt(12, car.getId()); // car_id
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
