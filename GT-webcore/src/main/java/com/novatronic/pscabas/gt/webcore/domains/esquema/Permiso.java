package com.novatronic.pscabas.gt.webcore.domains.esquema;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "PERMISO")
public class Permiso {

	@Attribute(name = "mnemonico")
	private String mnemonico;

	@Attribute(name = "nombre")
	private String nombre;
	
	@Attribute(name = "estado", required = false)
	private String estado;
	
	@Attribute(name = "tipo", required = false)
	private String tipo;
	
	@Attribute(name = "tipoOpc", required = false)
	private String tipoOpc;
	
	@Attribute(name = "tipoOpcion", required = false)
	private String tipoOpcion;
	
	@Attribute(name = "dataSeg", required = false)
	private String dataSeg;
	
	@Attribute(name = "configuracion", required = false)
	private String configuracion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMnemonico() {
		return mnemonico;
	}

	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoOpc() {
		return tipoOpc;
	}

	public void setTipoOpc(String tipoOpc) {
		this.tipoOpc = tipoOpc;
	}

	public String getTipoOpcion() {
		return tipoOpcion;
	}

	public void setTipoOpcion(String tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}

	public String getDataSeg() {
		return dataSeg;
	}

	public void setDataSeg(String dataSeg) {
		this.dataSeg = dataSeg;
	}

	public String getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(String configuracion) {
		this.configuracion = configuracion;
	}

}
