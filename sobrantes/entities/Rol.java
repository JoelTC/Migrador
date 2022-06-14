/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

import java.util.List;

/*
 * Clase de entidad para Rol
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */
public class Rol {
	private String nombre;
	private String mnemotico;
	private String tipo;
	private List<RolPermiso> lRolPermiso;
	
	public Rol(String nombre, String mnemotico, String tipo, List<RolPermiso> lRolPermiso) {
		this.nombre = nombre;
		this.mnemotico = mnemotico;
		this.tipo = tipo;
		this.lRolPermiso = lRolPermiso;
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

	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + ", mnemotico=" + mnemotico + ", tipo=" + tipo + ", lRolPermiso=" + lRolPermiso
				+ "]";
	}
		
}
