package com.novatronic.pscabas.gt.webcore.domains.esquema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "USUARIO", propOrder = {
    "USUARIOROL"
})
@XmlRootElement
public class Usuario {
	@XmlAttribute
	protected String nombre;
	
	@XmlAttribute
	protected String usuario;
	
	@XmlAttribute
	protected String apellidoPaterno;
	
	@XmlAttribute
	protected String apellidoMaterno;
	
	@XmlAttribute
	protected String telefono;
	
	@XmlAttribute
	protected String tipoDocumento;
	
	@XmlAttribute
	protected String numeroDocumento;
	
	@XmlAttribute
	protected String correo;
	
	@XmlAttribute
	protected String contrasena;
	
	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillableUsuarioRol usuarioRol;

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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public ArrayOfTns2NillableUsuarioRol getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(ArrayOfTns2NillableUsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	
}
