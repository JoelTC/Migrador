/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

/*
 * Clase de entidad para UsuarioRol
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */

public class UsuarioRol {
	private String nombre;
	private String mnemotico;
	
	public UsuarioRol(String nombre, String mnemotico) {
		this.nombre = nombre;
		this.mnemotico = mnemotico;
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

	@Override
	public String toString() {
		return "UsuarioRol [nombre=" + nombre + ", mnemotico=" + mnemotico + "]";
	}
	
}
