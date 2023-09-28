package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/agencia_viagem_lazer_ferias";
        String username = "root";
        String password = "5316";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
