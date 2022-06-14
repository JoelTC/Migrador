package com.novatronic.pscabas.gt.webcore.domains.esquema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Permiso {
	
	@XmlAttribute
	protected String nombre;
	
	@XmlAttribute
	protected String mnemotico;

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

}
