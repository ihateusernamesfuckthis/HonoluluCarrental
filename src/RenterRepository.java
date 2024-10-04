import java.sql.*;

public class RenterRepository {

    public static void addRenter() {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseManager.getInstance().getConnection(); // Get connection from Singleton
            String query = "INSERT INTO Renter " +
                    "(renter_drivers_license, renter_drivers_license_issue_date, renter_name, renter_mobile_phone, renter_phone, renter_email, renter_street_address, renter_zip_code) " +
                    "VALUES (?,?,?,?,?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "1545897");//renter.getRenterDriversLicense());
            stmt.setDate(2, java.sql.Date.valueOf("1999-01-01"));//renter.getRenterDriversLicenseIssueDate());
            stmt.setString(3, "renter.getRenterName()");
            stmt.setString(4, "60815237");// renter.getRenterMobilePhone());
            stmt.setString(5, "60815237");//renter.getRenterPhone());
            stmt.setString(6, "renter.getRenterEmail()");
            stmt.setString(7, "renter.getRenterStreetAddress()");
            stmt.setString(8, "3520"); // renter.getRenterZipCode());
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

    public static void getRenterById(int renterId) {
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
                // TODO Update to use renter object and return renter
                System.out.println(rs.getString("renter_drivers_license"));
                System.out.println(rs.getDate("renter_drivers_license_issue_date"));
                System.out.println(rs.getString("renter_name"));
                System.out.println(rs.getString("renter_mobile_phone"));
                System.out.println(rs.getString("renter_phone"));
                System.out.println(rs.getString("renter_email"));
                System.out.println(rs.getString("renter_street_address"));
                System.out.println(rs.getString("renter_zip_code"));
                System.out.println(rs.getString("city_name"));
            } else {
                // TODO Update to use renter object and return null if no renter found
                System.out.println("No renter found with ID " + renterId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public static void getRenterByName(String name) {
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
            while (rs.next()) {
                // TODO Update to use renter object and return list of renters
                System.out.println(rs.getString("renter_drivers_license"));
                System.out.println(rs.getDate("renter_drivers_license_issue_date"));
                System.out.println(rs.getString("renter_name"));
                System.out.println(rs.getString("renter_mobile_phone"));
                System.out.println(rs.getString("renter_phone"));
                System.out.println(rs.getString("renter_email"));
                System.out.println(rs.getString("renter_street_address"));
                System.out.println(rs.getString("renter_zip_code"));
                System.out.println(rs.getString("city_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(); // Close connection
        }
    }

    public static boolean deleteRenterById(int id) {
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

    // TODO Update to use renter object
    public static void updateRenter(int renterId) {
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
            stmt.setString(1, "1545893");//renter.getRenterDriversLicense());
            stmt.setDate(2, java.sql.Date.valueOf("1999-01-02"));//renter.getRenterDriversLicenseIssueDate());
            stmt.setString(3, "Rune");
            stmt.setString(4, "60815233");// renter.getRenterMobilePhone());
            stmt.setString(5, "60815234");//renter.getRenterPhone());
            stmt.setString(6, "rune@roeddik.net");
            stmt.setString(7, "pilegårdsvej 85");
            stmt.setString(8, "3500"); // renter.getRenterZipCode());
            stmt.setInt(9, renterId);
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
