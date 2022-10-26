package com.ecodeup.model;

import java.sql.Date;

public class Producto {

	private Integer id;

	private String nombre;

	private Double cantidad;

	private Double precio;

	private Date fechaCrear;

	private Date fechaActualizar;

	public Producto(Integer id, String nombre, Double cantidad, Double precio, Date fechaCrear, Date fechaActualizar) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fechaCrear = fechaCrear;
		this.fechaActualizar = fechaActualizar;
	}

	public Producto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFechaCrear() {
		return fechaCrear;
	}

	public void setFechaCrear(Date fechaCrear) {
		this.fechaCrear = fechaCrear;
	}

	public Date getFechaActualizar() {
		return fechaActualizar;
	}

	public void setFechaActualizar(Date fechaActualizar) {
		this.fechaActualizar = fechaActualizar;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fechaCrear=" + fechaCrear
				+ ", fechaActualizar=" + fechaActualizar + "]";
	}

}
