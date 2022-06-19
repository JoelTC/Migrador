package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "APLICACION")
public class Aplicacion {

	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "mnemonico", required = false)
	private String mnemonico;

	@ElementList(name = "PERMISO", inline = true, required = false)
	private List<Permiso> lPermiso;

	@ElementList(name = "ROL", inline = true, required = false)
	private List<Rol> lRol;

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

	public List<Permiso> getlPermiso() {
		return lPermiso;
	}

	public void setlPermiso(List<Permiso> lPermiso) {
		this.lPermiso = lPermiso;
	}

	public List<Rol> getlRol() {
		return lRol;
	}

	public void setlRol(List<Rol> lRol) {
		this.lRol = lRol;
	}

	@Override
	public String toString() {
		return nombre + " - " + mnemonico;
	}
}