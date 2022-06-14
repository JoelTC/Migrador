package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "PERMISO")
public class Permiso {

	@Attribute(name = "nombre")
	private String nombre;

	@Attribute(name = "mnemonico")
	private String mnemonico;

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

}
