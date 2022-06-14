package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns2_nillable_Rol", propOrder = {
    "ROL"
})
public class ArrayOfTns2NillableRol {
	@XmlElement(name = "ROL", nillable = true)
    protected List<Rol> rol;

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

}
