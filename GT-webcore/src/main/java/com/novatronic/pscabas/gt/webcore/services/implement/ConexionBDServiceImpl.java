package com.novatronic.pscabas.gt.webcore.services.implement;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.config.Datasource;
import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.services.interfaces.ConexionBDService;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

@Service
public class ConexionBDServiceImpl implements ConexionBDService {
	public static ConexionBD conexionBD;

	@Override
	public boolean conexionBD(ConexionBD conexion) throws SQLException {
		try {
			boolean prueba = false;

			if (conexion.getBaseDatos().equals("oracle")) {
				conexion.setDriver(Constantes.ORACLE_DRIVER);
			} else if (conexion.getBaseDatos().equals("postgres")) {
				conexion.setDriver(Constantes.POSTGRES_DRIVER);
			}

			conexionBD = conexion;

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
			return false;
		}
	}
}
