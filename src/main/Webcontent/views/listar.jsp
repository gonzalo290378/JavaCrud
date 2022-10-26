<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Productos</title>
</head>
<body>
	<h1>Listar Productos</h1>

	<table border="1">

		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Cantidad</td>
			<td>Precio</td>
			<td>Fecha Crear</td>
			<td>Fecha Actualizar</td>
			<td>Accion</td>


		</tr>
		<c:forEach var="producto" items="${lista}">
			<tr>
				<td><a
					href="productos?opcion=editar&id=<c:out value="${producto.id}"></c:out>"><c:out
							value="${producto.id}"></c:out></a></td>
				<td><c:out value="${producto.nombre}"></c:out></td>
				<td><c:out value="${producto.cantidad}"></c:out></td>
				<td><c:out value="${producto.precio}"></c:out></td>
				<td><c:out value="${producto.fechaCrear}"></c:out></td>
				<td><c:out value="${producto.fechaActualizar}"></c:out></td>
				<td><a
					href="productos?opcion=eliminar&id=<c:out value="${producto.id}"></c:out>">Eliminar</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>