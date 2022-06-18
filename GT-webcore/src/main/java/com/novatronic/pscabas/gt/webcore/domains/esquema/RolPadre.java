package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "ROLPADRE")
public class RolPadre {
	@Attribute(name = "mnemonico", required = false)
	private String mnemonico;
	
	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "tipo", required = false)
	private String tipo;
	
	@ElementList(name = "ROLPORROL", inline = true, required = false)
	private List<RolPorRol> lRolPorRol;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<RolPorRol> getlRolPorRol() {
		return lRolPorRol;
	}

	public void setlRolPorRol(List<RolPorRol> lRolPorRol) {
		this.lRolPorRol = lRolPorRol;
	}

	@Override
	public String toString() {
		return nombre + " - " + mnemonico;
	}
}
