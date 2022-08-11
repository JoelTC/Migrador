package com.novatronic.pscabas.gt.webcore.services;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.repositories.ConexionBDRepository;
import com.novatronic.pscabas.gt.webcore.services.implement.ConexionBDServiceImpl;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

public class ConexionBDServiceTest {
	private static final String URL = "jdbc:oracle:thin:@172.29.43.133:1521:rh61prod";
	private static final String USUARIO = "scapcipr";
	private static final String CONTRASENA = "scapcipr";
	private static final String BASEDATOS="oracle";
	private static final String DRIVER=Constantes.ORACLE_DRIVER;
	
	private static final boolean CONEXION=true;
	
	public static final ConexionBD conexionBD = new ConexionBD();
	
	@Mock
	ConexionBDRepository repository;
	
	@InjectMocks
	ConexionBDServiceImpl serviceImp;
	
	@Before
	public void init() throws SQLException {
		MockitoAnnotations.initMocks(this);

		conexionBD.setBaseDatos(BASEDATOS);
		conexionBD.setUrl(URL);
		conexionBD.setUsuario(USUARIO);
		conexionBD.setContrasena(CONTRASENA);
		conexionBD.setDriver(DRIVER);
	}
	
	@Test
	public void conexionBDTest() throws SQLException {
		Mockito.when(repository.conexionBD(conexionBD)).thenReturn(CONEXION);
		serviceImp.conexionBD(conexionBD);
	}

}
