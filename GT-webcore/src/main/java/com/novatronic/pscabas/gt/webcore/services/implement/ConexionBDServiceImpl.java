package com.novatronic.pscabas.gt.webcore.services.implement;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.repositories.ConexionBDRepository;
import com.novatronic.pscabas.gt.webcore.services.ConexionBDService;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

@Service
public class ConexionBDServiceImpl implements ConexionBDService {
	
	@Autowired
	private ConexionBDRepository repository;
	
	public static ConexionBD conexionBD;

	@Override
	public boolean conexionBD(ConexionBD conexion) throws SQLException {
		try {

			if (conexion.getBaseDatos().equals("oracle")) {
				conexion.setDriver(Constantes.ORACLE_DRIVER);
			} else if (conexion.getBaseDatos().equals("postgres")) {
				conexion.setDriver(Constantes.POSTGRES_DRIVER);
			}

			conexionBD = conexion;

			return this.repository.conexionBD(conexion);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return false;
		}
	}
}
