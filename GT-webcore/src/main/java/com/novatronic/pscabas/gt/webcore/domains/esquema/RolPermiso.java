package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "ROLPERMISO")
public class RolPermiso {

	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "mnemonico", required = false)
	private String mnemonico;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getmnemonico() {
		return mnemonico;
	}

	public void setmnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	
	@Override
	public String toString() {
		return nombre + " - " + mnemonico;
	}
}