package com.novatronic.pscabas.gt.webcore.domains.esquema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ROL", propOrder = {
    "ROLPERMISO"
})
@XmlRootElement
public class Rol {
	@XmlAttribute
	protected String nombre;
	
	@XmlAttribute
	protected String mnemotico;
	
	@XmlElement(required = true, nillable = true)
    protected ArrayOfTns2NillableRolPermiso rolPermiso;

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

	public ArrayOfTns2NillableRolPermiso getRolPermiso() {
		return rolPermiso;
	}

	public void setRolPermiso(ArrayOfTns2NillableRolPermiso rolPermiso) {
		this.rolPermiso = rolPermiso;
	}

}
