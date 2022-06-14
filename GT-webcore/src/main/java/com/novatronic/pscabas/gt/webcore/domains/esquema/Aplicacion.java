package com.novatronic.pscabas.gt.webcore.domains.esquema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APLICACION", propOrder = {
    "PERMISO", "ROL"
})
@XmlRootElement
public class Aplicacion {
	
	@XmlAttribute
	protected String nombre;
	
	@XmlAttribute
	protected String mnemotico;

	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillablePermiso permiso;
	
	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillableRol rol;

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

	public ArrayOfTns2NillablePermiso getPermiso() {
		return permiso;
	}

	public void setPermiso(ArrayOfTns2NillablePermiso permiso) {
		this.permiso = permiso;
	}

	public ArrayOfTns2NillableRol getRol() {
		return rol;
	}

	public void setRol(ArrayOfTns2NillableRol rol) {
		this.rol = rol;
	}
	
}
