/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

import java.util.List;

/*
 * Clase de entidad para Aplicación
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */

public class Aplicacion {
	private String nombre;
	private String mnemotico;
	private List<Permiso> lPermiso;
	private List<Rol> lRol;
	
	public Aplicacion(String nombre, String mnemotico, List<Permiso> lPermiso, List<Rol> lRol) {
		this.nombre = nombre;
		this.mnemotico = mnemotico;
		this.lPermiso = lPermiso;
		this.lRol = lRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMnemotico() {
		return mnemotico;
	}

	public void setMnemotico(String mnemotico) {
		this.mnemotico = mnemotico;
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
		return "Aplicacion [nombre=" + nombre + ", mnemotico=" + mnemotico + ", lPermiso=" + lPermiso + ", lRol=" + lRol
				+ "]";
	}
	
}
