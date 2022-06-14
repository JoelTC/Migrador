package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns2_nillable_Aplicacion", propOrder = { "APLICACION" })
public class ArrayOfTns2NillableAplicacion {

	@XmlElement(name = "APLICACION", nillable = true)
	protected List<Permiso> Aplicacion;

	public List<Permiso> getAplicacion() {
		return Aplicacion;
	}

	public void setAplicacion(List<Permiso> aplicacion) {
		Aplicacion = aplicacion;
	}

}
