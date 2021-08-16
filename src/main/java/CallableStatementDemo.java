import java.sql.*;

public class CallableStatementDemo {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/maria";

    static final String USER = "postgres";
    static final String PASSWORD = "123";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        try {

            String SQL = "{call //customProcedure// (?, ?)}"; //procedure not created
            callableStatement = connection.prepareCall(SQL);

            int developerID = 1;
            callableStatement.setInt(1, developerID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String developerName = callableStatement.getString(2);
            System.out.println("Developer INFO");
            System.out.println("id: " + developerID);
            System.out.println("Name: " + developerName);
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
