package com.novatronic.pscabas.gt.webcore.domains.request;

import java.util.List;

public class MigradorEmpresaRequest {

	private String version;
	private String rolPadre;
	private String tipo;
	private List<AplicacionRequest> lAplicacion;
	private String fechaNacimiento;
	private String cifradoOrigen;
	private String cifradoDestino;
	private String contrasena;
	private List<RolPadreRequest> lRolPadre;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRolPadre() {
		return rolPadre;
	}

	public void setRolPadre(String rolPadre) {
		this.rolPadre = rolPadre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<AplicacionRequest> getlAplicacion() {
		return lAplicacion;
	}

	public void setlAplicacion(List<AplicacionRequest> lAplicacion) {
		this.lAplicacion = lAplicacion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCifradoOrigen() {
		return cifradoOrigen;
	}

	public void setCifradoOrigen(String cifradoOrigen) {
		this.cifradoOrigen = cifradoOrigen;
	}

	public String getCifradoDestino() {
		return cifradoDestino;
	}

	public void setCifradoDestino(String cifradoDestino) {
		this.cifradoDestino = cifradoDestino;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<RolPadreRequest> getlRolPadre() {
		return lRolPadre;
	}

	public void setlRolPadre(List<RolPadreRequest> lRolPadre) {
		this.lRolPadre = lRolPadre;
	}

}