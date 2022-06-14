package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns2_nillable_RolPermiso", propOrder = {
    "ROLPERMISO"
})
public class ArrayOfTns2NillableRolPermiso {
	@XmlElement(name = "ROLPERMISO", nillable = true)
    protected List<RolPermiso> rolPermiso;

	public List<RolPermiso> getRolPermiso() {
		return rolPermiso;
	}

	public void setRolPermiso(List<RolPermiso> rolPermiso) {
		this.rolPermiso = rolPermiso;
	}

}
