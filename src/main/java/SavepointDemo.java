import java.sql.*;

public class SavepointDemo {
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
        connection.setAutoCommit(false);

        statement = connection.createStatement();
        String sql = "select * from users";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("personid");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");

            System.out.println(id + ": " + city + ", " + email);
        }

        Savepoint savepointOne = connection.setSavepoint("SavepointOne");

        try {
            sql = "delete from users where personid = 7";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            connection.rollback(savepointOne);
        }

        System.out.println("===============================");

        sql = "select * from users";
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("personid");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");

            System.out.println(id + ": " + city + ", " + email);
        }


    }
}
