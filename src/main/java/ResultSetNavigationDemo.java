import java.sql.*;

public class ResultSetNavigationDemo {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/maria";

    static final String USER = "postgres";
    static final String PASSWORD = "123";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
        String SQL = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(SQL);

        System.out.println("Moving cursor to the last position...");
        resultSet.last();
        System.out.println("===========");
        int id = resultSet.getInt("personid");
        String email = resultSet.getString("email");
        String city = resultSet.getString("city");

        System.out.println(id + ":" + email + ", " + city);
        resultSet.previous();
        System.out.println("===========");
        id = resultSet.getInt("personid");
        email = resultSet.getString("email");
        city = resultSet.getString("city");

        System.out.println(id + ":" + email + ", " + city);

        resultSet.close();
    }
}
