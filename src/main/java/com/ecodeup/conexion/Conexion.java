package com.ecodeup.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

//Pool de Conexiones
public class Conexion {

	private static BasicDataSource dataSource = null;

	private static DataSource getDataSource() {

		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("gonzalo290378");
			dataSource.setPassword("granada290378");
			dataSource.setUrl("jdbc:mysql://localhost:3306/CRUD");

			// Pool de conexiones
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			dataSource.setMaxWaitMillis(5000);

		}
		return dataSource;

	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

}
