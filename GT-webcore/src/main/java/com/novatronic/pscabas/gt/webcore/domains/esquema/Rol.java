package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "ROL")
public class Rol {
	@Attribute(name = "nombre")
	private String nombre;

	@Attribute(name = "mnemonico")
	private String mnemonico;
	
	@Attribute(name = "tipo", required = false)
	private String tipo;

	@ElementList(name = "ROLPERMISO", inline = true, required = false)
	private List<RolPermiso> lRolPermiso;

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

	public List<RolPermiso> getlRolPermiso() {
		return lRolPermiso;
	}

	public void setlRolPermiso(List<RolPermiso> lRolPermiso) {
		this.lRolPermiso = lRolPermiso;
	}
}
