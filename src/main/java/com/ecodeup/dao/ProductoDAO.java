package com.ecodeup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecodeup.conexion.Conexion;
import com.ecodeup.model.Producto;

public class ProductoDAO {

	private Connection conexion;

	private Boolean estadoOperacion;

	public Boolean guardar(Producto producto) throws SQLException {

		estadoOperacion = false;
		conexion = obtenerConexion();
		conexion.setAutoCommit(false);

		try {
			String sql = "INSERT INTO Productos (id, nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES (?, ?, ? , ? , ? , ?)";
			PreparedStatement miPST = conexion.prepareStatement(sql);

			miPST.setString(1, null);
			miPST.setString(2, producto.getNombre());
			miPST.setDouble(3, producto.getCantidad());
			miPST.setDouble(4, producto.getPrecio());
			miPST.setDate(5, producto.getFechaCrear());
			miPST.setDate(6, producto.getFechaActualizar());

			miPST.executeUpdate();
			conexion.commit();
			estadoOperacion = true;
			conexion.close();
			miPST.close();

		} catch (Exception e) {
			e.printStackTrace();
			estadoOperacion = false;
			conexion.rollback();
		}

		return estadoOperacion;
	}

	public Boolean editar(Producto producto) throws SQLException {

		estadoOperacion = false;
		conexion = obtenerConexion();
		conexion.setAutoCommit(false);

		try {
			String sql = "UPDATE Productos SET nombre = ?, cantidad= ? , precio = ? , fecha_actualizar = ? WHERE id = ?";
			PreparedStatement miPST = conexion.prepareStatement(sql);

			miPST.setString(1, producto.getNombre());
			miPST.setDouble(2, producto.getCantidad());
			miPST.setDouble(3, producto.getPrecio());
			miPST.setDate(4, producto.getFechaActualizar());
			miPST.setInt(5, producto.getId());

			miPST.executeUpdate();
			conexion.commit();
			estadoOperacion = true;
			conexion.close();
			miPST.close();

		} catch (Exception e) {
			e.printStackTrace();
			conexion.rollback();
			estadoOperacion = false;
		}

		return estadoOperacion;
	}

	public Boolean eliminar(Integer idProducto) throws SQLException {

		estadoOperacion = false;
		conexion = obtenerConexion();
		conexion.setAutoCommit(false);

		try {

			String sql = "DELETE FROM Productos WHERE id = ?";

			PreparedStatement miPST = conexion.prepareStatement(sql);
			miPST.setInt(1, idProducto);

			miPST.executeUpdate();
			conexion.commit();
			estadoOperacion = true;
			conexion.close();
			miPST.close();

		} catch (Exception e) {
			e.printStackTrace();
			conexion.rollback();
			estadoOperacion = false;
		}

		return estadoOperacion;
	}

	public List<Producto> obtenerProductos() throws SQLException {

		Statement stm;
		estadoOperacion = false;
		conexion = obtenerConexion();
		conexion.setAutoCommit(false);
		ResultSet res;
		List<Producto> listaProductos = new ArrayList<Producto>();

		try {
			
			String sql = "SELECT * FROM Productos";
			stm = conexion.prepareStatement(sql);
			res = stm.executeQuery(sql);
			
			while(res.next()) {
				Producto producto = new Producto(res.getInt("id"), res.getString("nombre"), res.getDouble("cantidad"), res.getDouble("precio"), res.getDate("fecha_crear"), res.getDate("fecha_actualizar"));
				listaProductos.add(producto);
			}
			
			estadoOperacion = true;
			conexion.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
			conexion.rollback();
			estadoOperacion = false;
		}

		return listaProductos;
	}

	public Producto obtenerProducto(Integer idProducto) throws SQLException {

		estadoOperacion = false;
		conexion = obtenerConexion();
		conexion.setAutoCommit(false);
		Producto producto = null;
		ResultSet res;

		try {

			String sql = "SELECT * FROM Productos WHERE id = ?";

			PreparedStatement miPST = conexion.prepareStatement(sql);
			miPST.setInt(1, idProducto);
			res = miPST.executeQuery();
			
			if(res.next()) {
				producto = new Producto(res.getInt("id"), res.getString("nombre"), res.getDouble("cantidad"), res.getDouble("precio"), res.getDate("fecha_crear"), res.getDate("fecha_actualizar"));
			}

			estadoOperacion = true;
			conexion.close();
			miPST.close();

		} catch (Exception e) {
			e.printStackTrace();
			conexion.rollback();
			estadoOperacion = false;
		}

		return producto;
	}

	public Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
