package com.novatronic.pscabas.gt.webcore.config;

import org.apache.commons.dbcp.BasicDataSource;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;

public class Datasource {
	private static BasicDataSource basicDataSource=null;
	
	 public Datasource(ConexionBD conexion){
         if (null==basicDataSource){
             basicDataSource = new BasicDataSource();
             basicDataSource.setDriverClassName(conexion.getDriver());
             basicDataSource.setUsername(conexion.getUsuario());
             basicDataSource.setPassword(conexion.getContrasena());
             basicDataSource.setUrl(conexion.getUrl());
             basicDataSource.setMaxActive(200);
             basicDataSource.setMinIdle(50);
             basicDataSource.setMaxIdle(100);
         }
     }

     public BasicDataSource getDataSource(){
         return basicDataSource;
     }
}
