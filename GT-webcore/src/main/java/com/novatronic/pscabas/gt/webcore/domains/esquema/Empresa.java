package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "EMPRESA")
public class Empresa {

	@Attribute(name = "nombre")
	private String nombre;

	@Attribute(name = "mnemonico")
	private String mnemonico;

	@Attribute(name = "cifrado")
	private String cifrado;

	@ElementList(name = "APLICACION", inline = true, required = false)
	private List<Aplicacion> lAplicacion;

	@ElementList(name = "USUARIO", inline = true, required = false)
	private List<Usuario> lUsuario;

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

	public String getCifrado() {
		return cifrado;
	}

	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}

	public List<Aplicacion> getlAplicacion() {
		return lAplicacion;
	}

	public void setlAplicacion(List<Aplicacion> lAplicacion) {
		this.lAplicacion = lAplicacion;
	}

	public List<Usuario> getlUsuario() {
		return lUsuario;
	}

	public void setlUsuario(List<Usuario> lUsuario) {
		this.lUsuario = lUsuario;
	}

}
