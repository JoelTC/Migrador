package com.novatronic.pscabas.gt.webcore.repositories.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.novatronic.pscabas.gt.webcore.config.Datasource;
import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionUsuario;
import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.repositories.interfaces.AplicacionUsuarioRepository;

@Component
public class AplicacionUsuarioRepositoryImpl implements AplicacionUsuarioRepository {

	@Override
	public List<AplicacionUsuario> listarAplicacionUsuario(ConexionBD conexion, String empresa) {
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT  tu.USUARIO AS USUARIO, tr.MNEMONICO AS ROL, ta.MNEMONICO AS APLICACION");
			query.append("	FROM TP_USUARIO tu ");
			query.append("	LEFT JOIN TR_ROL_X_USUARIO trxu ON tu.ID_USUARIO = trxu.ID_USUARIO ");
			query.append("	LEFT JOIN TP_ROL tr ON trxu.ID_ROL = tr.ID_ROL ");
			query.append("	LEFT JOIN TP_APLICACION ta ON tr.ID_APLICACION = ta.ID_APLICACION ");
			query.append("	LEFT JOIN TP_EMPRESA te ON tu.ID_EMPRESA = te.ID_EMPRESA ");
			query.append("	WHERE te.MNEMONICO =  '" + empresa + "' ");
			query.append("	ORDER BY tu.USUARIO, tr.MNEMONICO");

			Datasource ds = new Datasource(conexion);

			Connection con = ds.getDataSource().getConnection();

			PreparedStatement stmt = con.prepareStatement(query.toString());

			ResultSet rs = stmt.executeQuery();

			List<AplicacionUsuario> lAplicacionUsuario = new ArrayList<AplicacionUsuario>();

			while (rs.next()) {
				AplicacionUsuario aplicacion = new AplicacionUsuario(rs.getString("USUARIO"), rs.getString("ROL"),
						rs.getString("APLICACION"));
				lAplicacionUsuario.add(aplicacion);
			}

			if (con != null) {
				con.close();
			}
			return lAplicacionUsuario;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

}
