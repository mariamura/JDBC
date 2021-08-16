import java.sql.*;


public class PreparedStatementDemo {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/maria";

    static final String USER = "postgres";
    static final String PASSWORD = "123";


    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from users";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("personid");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");

            System.out.println(id + ": " + city + ", " + email);
        }
        System.out.println("===========================");

        sql = "select * from users where city = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "Moscow");

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("personid");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");

            System.out.println(id + ": " + city + ", " + email);
        }
        connection.close();
        resultSet.close();
        preparedStatement.close();



    }
}
