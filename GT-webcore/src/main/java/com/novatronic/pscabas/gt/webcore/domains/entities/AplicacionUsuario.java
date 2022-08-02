package com.novatronic.pscabas.gt.webcore.domains.entities;

public class AplicacionUsuario {
	private String usuario;
	private String rol;
	private String aplicacion;
	
	public AplicacionUsuario(String usuario, String rol, String aplicacion) {
		this.usuario = usuario;
		this.rol = rol;
		this.aplicacion = aplicacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	@Override
	public String toString() {
		return usuario + " - " + rol + " - " + aplicacion;
	}
}
