package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "USUARIO")
public class Usuario {
	@Attribute(name = "nombre", required = false)
	private String nombre;

	@Attribute(name = "usuario", required = false)
	private String usuario;

	@Attribute(name = "apellidopaterno", required = false)
	private String apellidoPaterno;

	@Attribute(name = "apellidomaterno", required = false)
	private String apellidoMaterno;

	@Attribute(name = "fechanacimiento", required = false)
	private String fechaNacimiento;

	@Attribute(name = "telefono", required = false)
	private String telefono;

	@Attribute(name = "tipodocumento", required = false)
	private String tipoDocumento;

	@Attribute(name = "numerodocumento", required = false)
	private String numeroDocumento;

	@Attribute(name = "correo", required = false)
	private String correo;

	@Attribute(name = "contrasena", required = false)
	private String contrasena;

	@Attribute(name = "rolmnemonico", required = false)
	private String rolMnemonico;

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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRolMnemonico() {
		return rolMnemonico;
	}

	public void setRolMnemonico(String rolMnemonico) {
		this.rolMnemonico = rolMnemonico;
	}

	public List<UsuarioRol> getlUsuarioRol() {
		return lUsuarioRol;
	}

	public void setlUsuarioRol(List<UsuarioRol> lUsuarioRol) {
		this.lUsuarioRol = lUsuarioRol;
	}

}
