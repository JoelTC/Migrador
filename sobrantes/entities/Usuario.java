/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

import java.util.List;

/*
 * Clase de entidad para Usuario
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */

public class Usuario {
	private String nombre;
	private String usuario;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaNacimiento;
	private String telefono;
	private String tipoDocumento;
	private String numeroDocumento;
	private String correo;
	private String contraseña;
	private List<UsuarioRol> lUsuarioRol;
	
	public Usuario(String nombre, String usuario, String apellidoPaterno, String apellidoMaterno,
			String fechaNacimiento, String telefono, String tipoDocumento, String numeroDocumento, String correo,
			String contraseña, List<UsuarioRol> lUsuarioRol) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.correo = correo;
		this.contraseña = contraseña;
		this.lUsuarioRol = lUsuarioRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<UsuarioRol> getlUsuarioRol() {
		return lUsuarioRol;
	}

	public void setlUsuarioRol(List<UsuarioRol> lUsuarioRol) {
		this.lUsuarioRol = lUsuarioRol;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", usuario=" + usuario + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", telefono="
				+ telefono + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", correo="
				+ correo + ", contraseña=" + contraseña + ", lUsuarioRol=" + lUsuarioRol + "]";
	}
	
}
