package com.novatronic.pscabas.gt.webcore.repositories;

import java.sql.SQLException;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;

public interface ConexionBDRepository {
	public boolean conexionBD(ConexionBD conexion) throws SQLException;
}
