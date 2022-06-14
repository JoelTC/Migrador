package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns2_nillable_UsuarioRol", propOrder = {
    "USUARIOROL"
})
public class ArrayOfTns2NillableUsuarioRol {
	
	@XmlElement(name = "USUARIOROL", nillable = true)
    protected List<UsuarioRol> usuarioRol;

	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	
}
