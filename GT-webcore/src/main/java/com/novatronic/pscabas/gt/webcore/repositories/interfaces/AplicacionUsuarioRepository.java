package com.novatronic.pscabas.gt.webcore.repositories.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionUsuario;
import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;

@Repository
public interface AplicacionUsuarioRepository {
	public List<AplicacionUsuario> listarAplicacionUsuario(ConexionBD conexion, String empresa);

}
