/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Desktop
 */
public class loadingBar {
    private int primeira;
    private int segunda;

    public int getPrimeira() {
        return primeira;
    }

    public void setPrimeira(int primeira) {
        this.primeira = primeira;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public loadingBar() {
        semSegundaDose();
        segundaDoseAplicada();
    }
    
    
        public void semSegundaDose(){
           String sql = "Select count(*) as quantidade  from  tb_infovacinacao ";
           //abrir conexão
           ConnectionFactory factory  = new ConnectionFactory();
           try(Connection c = factory.obtemConexao()) {
               PreparedStatement ps  = c.prepareStatement(sql);
               ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setPrimeira(rs.getInt("quantidade"));
              }
           }
           catch(Exception e ){
               e.printStackTrace();
           }
       }
        public void segundaDoseAplicada(){
           String sql = "Select count(*) as quantidade  from  tb_infovacinacao where status = 'VACINADO'";
           //abrir conexão
           ConnectionFactory factory  = new ConnectionFactory();
           try(Connection c = factory.obtemConexao()) {
               PreparedStatement ps  = c.prepareStatement(sql);
               ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setSegunda(rs.getInt("quantidade"));
                 }
           }
           catch(Exception e ){
               e.printStackTrace();
           }
       }
}
