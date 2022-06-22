package com.novatronic.pscabas.gt.webcore.domains.request;

public class CifradoRequest {
	private String cifradoOrigen;
	private String cifradoDestino;
	private String contrasena;

	public String getCifradoOrigen() {
		return cifradoOrigen;
	}

	public void setCifradoOrigen(String cifradoOrigen) {
		this.cifradoOrigen = cifradoOrigen;
	}

	public String getCifradoDestino() {
		return cifradoDestino;
	}

	public void setCifradoDestino(String cifradoDestino) {
		this.cifradoDestino = cifradoDestino;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
