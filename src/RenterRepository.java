
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RenterRepository {

    public void addRenter(Renter renter) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO Renter " +
                    "(renter_drivers_license, renter_drivers_license_issue_date, renter_name, renter_mobile_phone, renter_phone, renter_email, renter_street_address, renter_zip_code) " +
                    "VALUES (?,?,?,?,?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, renter.getDriversLicense()); // renter_drivers_license
            stmt.setDate(2, java.sql.Date.valueOf(renter.getDriversLicenseIssueDate().toString())); // renter_drivers_license_issue_date
            stmt.setString(3, renter.getName()); // renter_name
            stmt.setString(4, renter.getMobilePhone()); // renter_mobile_phone
            stmt.setString(5, renter.getPhone()); // renter_phone
            stmt.setString(6, renter.getEmail()); // renter_email
            stmt.setString(7, renter.getStreetAddress()); // renter_street_address
            stmt.setString(8, renter.getZipCode()); // renter_zip_code (Zipcode is foreign key to Location table)
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Zip code does not exist in location table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public Renter getRenterById(int renterId) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "SELECT renter.*, l.city_name FROM Renter " +
                    "JOIN location l ON renter.renter_zip_code = l.zip_code " +
                    "WHERE renter_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, renterId);

            rs = stmt.executeQuery();
            // rs.next() will return false if no match found.
            // We only want the first result found, as ID should be unique and only return 1
            if (rs.next()) {
                Renter renter = new Renter(
                        rs.getInt("renter_id"),
                        rs.getString("renter_drivers_license"),
                        rs.getDate("renter_drivers_license_issue_date").toLocalDate(),
                        rs.getString("renter_name"),
                        rs.getString("renter_mobile_phone"),
                        rs.getString("renter_phone"),
                        rs.getString("renter_email"),
                        rs.getString("renter_street_address"),
                        rs.getString("renter_zip_code")
                );
                return renter;
            } else {
                System.out.println("No renter found with ID " + renterId);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }

        return null;
    }

    public List<Renter> getRentersByName(String name) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "SELECT renter.*, l.city_name FROM Renter " +
                    "JOIN location l ON renter.renter_zip_code = l.zip_code " +
                    "WHERE renter_name = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            List<Renter> renters = new ArrayList<>();
            while (rs.next()) {
                Renter renter = new Renter(
                        rs.getInt("renter_id"),
                        rs.getString("renter_drivers_license"),
                        rs.getDate("renter_drivers_license_issue_date").toLocalDate(),
                        rs.getString("renter_name"),
                        rs.getString("renter_mobile_phone"),
                        rs.getString("renter_phone"),
                        rs.getString("renter_email"),
                        rs.getString("renter_street_address"),
                        rs.getString("renter_zip_code")
                );
                renters.add(renter);
            }

            return renters;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }

        return null;
    }

    public boolean deleteRenterById(int id) {
        Connection connection;
        PreparedStatement stmt;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "DELETE FROM Renter " +
                    "WHERE renter_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

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

    public void updateRenter(Renter renter) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "UPDATE Renter SET renter_drivers_license = ?, " +
                    "renter_drivers_license_issue_date = ?, renter_name = ?, " +
                    "renter_mobile_phone = ?, renter_phone = ?, " +
                    "renter_email = ?, renter_street_address = ?, " +
                    "renter_zip_code = ? " +
                    "WHERE renter_id = ?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, renter.getDriversLicense());
            stmt.setDate(2, java.sql.Date.valueOf(renter.getDriversLicenseIssueDate()));
            stmt.setString(3, renter.getName());
            stmt.setString(4, renter.getMobilePhone());
            stmt.setString(5, renter.getPhone());
            stmt.setString(6, renter.getEmail());
            stmt.setString(7, renter.getStreetAddress());
            stmt.setString(8, renter.getZipCode());
            stmt.setInt(9, renter.getId());
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // This will catch foreign key constraint violations (error code 1452)
            System.out.println("Zip code does not exist in location table");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }
}
