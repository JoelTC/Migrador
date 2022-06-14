/*
 * Novatronic S.A.C todos los derechos reservados
 * www.novatronic.com
 */
package com.novatronic.pscabas.gt.webcore.domains.entities;

/*
 * Clase de entidad para Permiso
 * 
 * @author jtrujillo
 * since 1.0
 * version 1.0, 23/05/2022
 */

public class Permiso {
	private String nombre;
	private String mnemotico;
	private String estado;
	private String tipo;
	private String tipoOpc;
	private String dataSec;
	
	public Permiso(String nombre, String mnemotico, String estado, String tipo, String tipoOpc, String dataSec) {
		this.nombre = nombre;
		this.mnemotico = mnemotico;
		this.estado = estado;
		this.tipo = tipo;
		this.tipoOpc = tipoOpc;
		this.dataSec = dataSec;
	}

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

	public String getDataSec() {
		return dataSec;
	}

	public void setDataSec(String dataSec) {
		this.dataSec = dataSec;
	}

	@Override
	public String toString() {
		return "Permiso [nombre=" + nombre + ", mnemotico=" + mnemotico + ", estado=" + estado + ", tipo=" + tipo
				+ ", tipoOpc=" + tipoOpc + ", dataSec=" + dataSec + "]";
	}

}
