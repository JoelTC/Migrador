package com.novatronic.pscabas.gt.webcore.domains.esquema;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "APLICACION")
public class DocAplicacion {

	@ElementList(name = "APLICACION", inline = true, required = false)
	private List<Aplicacion> lAplicacion;

	public List<Aplicacion> getlAplicacion() {
		return lAplicacion;
	}

	public void setlAplicacion(List<Aplicacion> lAplicacion) {
		this.lAplicacion = lAplicacion;
	}
}