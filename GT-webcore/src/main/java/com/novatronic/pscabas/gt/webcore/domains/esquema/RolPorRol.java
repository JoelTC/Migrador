package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "ROLPORROL")
public class RolPorRol {

	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "mnemonico", required = false)
	private String mnemonico;

	@Attribute(name = "mnemonicoaplicacion", required = false)
	private String mnemonicoaplicacion;

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

	public String getMnemonicoaplicacion() {
		return mnemonicoaplicacion;
	}

	public void setMnemonicoaplicacion(String mnemonicoaplicacion) {
		this.mnemonicoaplicacion = mnemonicoaplicacion;
	}

	@Override
	public String toString() {
		return nombre + " - " + mnemonico + " - " + mnemonicoaplicacion;
	}
}