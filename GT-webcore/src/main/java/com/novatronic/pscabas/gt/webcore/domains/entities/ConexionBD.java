package com.novatronic.pscabas.gt.webcore.domains.entities;

public class ConexionBD {
	private String url;
	private String usuario;
	private String contrasena;
	private String baseDatos;
	private String driver;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getBaseDatos() {
		return baseDatos;
	}
	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}

}
