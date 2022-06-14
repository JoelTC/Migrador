/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

import java.util.List;

/*
 * Clase de entidad para Empresa
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */
public class Empresa {
	private String nombre;
	private String mnemotico;
	private String cifrado;
	List<Aplicacion> lAplicación;
	List<Usuario> lUsuario;
	
	public Empresa(String nombre, String mnemotico, String cifrado, List<Aplicacion> lAplicación,
			List<Usuario> lUsuario) {
		this.nombre = nombre;
		this.mnemotico = mnemotico;
		this.cifrado = cifrado;
		this.lAplicación = lAplicación;
		this.lUsuario = lUsuario;
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

	public String getCifrado() {
		return cifrado;
	}

	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}

	public List<Aplicacion> getlAplicación() {
		return lAplicación;
	}

	public void setlAplicación(List<Aplicacion> lAplicación) {
		this.lAplicación = lAplicación;
	}

	public List<Usuario> getlUsuario() {
		return lUsuario;
	}

	public void setlUsuario(List<Usuario> lUsuario) {
		this.lUsuario = lUsuario;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", mnemotico=" + mnemotico + ", cifrado=" + cifrado + ", lAplicación="
				+ lAplicación + ", lUsuario=" + lUsuario + "]";
	}
	
}
