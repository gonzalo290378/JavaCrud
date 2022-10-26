package com.ecodeup.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.dao.ProductoDAO;
import com.ecodeup.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "Administra peticiones para la tabla producto", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if (opcion.equalsIgnoreCase("crear")) {
			RequestDispatcher despachador = request.getRequestDispatcher("/views/crear.jsp");
			despachador.forward(request, response);
			System.out.println("Se crea un producto");

		} else if (opcion.equalsIgnoreCase("listar")) {
			ProductoDAO productoDAO = new ProductoDAO();

			try {
				List<Producto> lista = productoDAO.obtenerProductos();
				request.setAttribute("lista", lista);
				for (Producto p : lista) {
					System.out.println(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher despachador = request.getRequestDispatcher("/views/listar.jsp");
			despachador.forward(request, response);
			System.out.println("Se lista un producto");
		}

		else if (opcion.equalsIgnoreCase("editar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = null;
			Integer id = Integer.parseInt(request.getParameter("id"));

			try {
				producto = productoDAO.obtenerProducto(id);
				System.out.println("Producto a actualizar: " + producto);
				request.setAttribute("producto", producto);
				RequestDispatcher despachador = request.getRequestDispatcher("/views/editar.jsp");
				despachador.forward(request, response);
				System.out.println("Se hace update al producto");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		else if (opcion.equalsIgnoreCase("eliminar")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			ProductoDAO productoDAO = new ProductoDAO();
			try {
				productoDAO.eliminar(id);
				System.out.println("Se elimino un producto");
				RequestDispatcher despachador = request.getRequestDispatcher("/index.jsp");
				despachador.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if (opcion.equalsIgnoreCase("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			String nombre = request.getParameter("nombre");
			Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
			Double precio = Double.parseDouble(request.getParameter("precio"));
			Date fechaHoy = new Date();
			producto.setNombre(nombre);
			producto.setCantidad(cantidad);
			producto.setPrecio(precio);
			producto.setFechaCrear(new java.sql.Date(fechaHoy.getTime()));

			try {
				productoDAO.guardar(producto);
				RequestDispatcher despachador = request.getRequestDispatcher("/index.jsp");
				despachador.forward(request, response);
				System.out.println("Se ha persistido un producto");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (opcion.equalsIgnoreCase("editar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			Integer id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
			Double precio = Double.parseDouble(request.getParameter("precio"));
			Date fechaHoy = new Date();
			producto.setId(id);
			producto.setNombre(nombre);
			producto.setCantidad(cantidad);
			producto.setPrecio(precio);
			producto.setFechaActualizar(new java.sql.Date(fechaHoy.getTime()));

			try {
				productoDAO.editar(producto);
				RequestDispatcher despachador = request.getRequestDispatcher("/index.jsp");
				despachador.forward(request, response);
				System.out.println("Se ha actualizado un producto");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
