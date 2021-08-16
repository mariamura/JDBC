import java.sql.*;


public class StatementDemo {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/maria";

    static final String USER = "postgres";
    static final String PASSWORD = "123";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }

        statement = connection.createStatement();
        String sql = "select * from users";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("personid");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");

            System.out.println(id + ": " + city + ", " + email);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
