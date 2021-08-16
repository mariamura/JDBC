import java.sql.*;

public class h {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/PROSELYTE_TUTORIALS";

    static final String USER = "ИМЯ_ПОЛЬЗОВАТЕЛЯ";
    static final String PASSWORD = "ВАШ_ПАРОЛЬ";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Connection connection = null;
            CallableStatement callableStatement = null;

            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Creating connection...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Creating callable statement...");

            try {


                String SQL = "{call getDeveloperName (?, ?)}";
                callableStatement = connection.prepareCall(SQL);

                int developerID = 1;
                callableStatement.setInt(1, developerID);
                callableStatement.registerOutParameter(2, Types.VARCHAR);

                System.out.println("Executing procedure...");
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
            System.out.println("Thank You.");
    }
}
