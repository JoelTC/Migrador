package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "USUARIOROL")
public class UsuarioRol {

	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "mnemonico", required = false)
	private String mnemonico;

	@Attribute(name = "aplicacion", required = false)
	private String aplicacion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMnemonico() {
		return mnemonico;
	}

	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	@Override
	public String toString() {
		return nombre + " - " + mnemonico + " - " + aplicacion;
	}
	
}