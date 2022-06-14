package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "USUARIO")
public class Usuario {
	@Attribute(name = "nombre")
	private String nombre;

	@Attribute(name = "usuario")
	private String usuario;

	@Attribute(name = "apellidopaterno")
	private String apellidoPaterno;

	@Attribute(name = "apellidomaterno")
	private String apellidoMaterno;

	@Attribute(name = "fechanacimiento", required = false)
	private String fechanacimiento;

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	@Attribute(name = "telefono")
	private String telefono;

	@Attribute(name = "tipodocumento")
	private String tipoDocumento;

	@Attribute(name = "numerodocumento")
	private String numeroDocumento;

	@Attribute(name = "correo")
	private String correo;

	@Attribute(name = "contrasena")
	private String contrasena;

	@ElementList(name = "USUARIOROL", inline = true, required = false)
	private List<UsuarioRol> lUsuarioRol;

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

	public List<UsuarioRol> getlUsuarioRol() {
		return lUsuarioRol;
	}

	public void setlUsuarioRol(List<UsuarioRol> lUsuarioRol) {
		this.lUsuarioRol = lUsuarioRol;
	}

}
