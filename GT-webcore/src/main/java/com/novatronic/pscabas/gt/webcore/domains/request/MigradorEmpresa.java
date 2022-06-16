package com.novatronic.pscabas.gt.webcore.domains.request;

import java.util.List;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Aplicacion;

public class MigradorEmpresa {
	private String version;
	private String rolPadre;
	private String tipo;
	private List<Aplicacion> lAplicacion;
	private String fechaNacimiento;
	private String contrasena;
	
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
	public List<Aplicacion> getlAplicacion() {
		return lAplicacion;
	}
	public void setlAplicacion(List<Aplicacion> lAplicacion) {
		this.lAplicacion = lAplicacion;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
