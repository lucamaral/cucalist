package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
	public static String status = "Não conectou...";

	public static java.sql.Connection getConexaoMySQL() {
		Connection connection = null;

		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName); //
			String serverName = "localhost";
			String mydatabase = "mydb";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root";
			String password = "035248";
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				status = "STATUS--->Conectado com sucesso!";
			} else {
				status = "STATUS--->Não foi possivel realizar conexão";
			}
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
		}
		System.out.println(status);
		return connection;
	}

}

