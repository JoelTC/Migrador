package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "APLICACION")
public class DocAplicacion {
	@Element(name = "APLICACION", required = false)
	private Aplicacion aplicacion;

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

}
