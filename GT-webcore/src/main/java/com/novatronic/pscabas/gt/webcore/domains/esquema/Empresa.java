package com.novatronic.pscabas.gt.webcore.domains.esquema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EMPRESA", propOrder = {
    "APLICACION", "USUARIO"
})
@XmlRootElement
public class Empresa {

	@XmlAttribute
	protected String nombre;
	
	@XmlAttribute
	protected String mnemotico;
	
	@XmlAttribute
	protected String cifrado;
	
	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillableAplicacion aplicacion;
	
	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillableUsuario usuario;

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

	public ArrayOfTns2NillableAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(ArrayOfTns2NillableAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public ArrayOfTns2NillableUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(ArrayOfTns2NillableUsuario usuario) {
		this.usuario = usuario;
	}

}
