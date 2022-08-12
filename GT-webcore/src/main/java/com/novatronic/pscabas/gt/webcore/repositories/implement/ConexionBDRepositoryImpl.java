package com.novatronic.pscabas.gt.webcore.repositories.implement;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.novatronic.pscabas.gt.webcore.config.Datasource;
import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.repositories.ConexionBDRepository;

@Component
public class ConexionBDRepositoryImpl implements ConexionBDRepository {
	protected static final Logger logger = LogManager.getLogger(ConexionBDRepositoryImpl.class);

	@Override
	public boolean conexionBD(ConexionBD conexion) throws SQLException {
		try {
			boolean prueba = false;
			Datasource ds = new Datasource(conexion);
			Connection con = ds.getDataSource().getConnection();
			if (con != null) {
				prueba = true;
				con.close();
			}

			return prueba;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
