package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns2_nillable_Permiso", propOrder = {
    "PERMISO"
})
public class ArrayOfTns2NillablePermiso {
	@XmlElement(name = "PERMISO", nillable = true)
    protected List<Permiso> permiso;

	public List<Permiso> getPermiso() {
		return permiso;
	}

	public void setPermiso(List<Permiso> permiso) {
		this.permiso = permiso;
	}

}
