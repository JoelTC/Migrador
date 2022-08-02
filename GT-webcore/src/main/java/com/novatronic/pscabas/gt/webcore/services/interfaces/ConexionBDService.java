package com.novatronic.pscabas.gt.webcore.services.interfaces;

import java.sql.SQLException;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;

public interface ConexionBDService {

	public boolean conexionBD(ConexionBD conexion) throws SQLException;
}
