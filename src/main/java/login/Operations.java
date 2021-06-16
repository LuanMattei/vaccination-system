/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Desktop
 */
public class Operations {
    public static boolean isLogin(String cpf, String cep, JFrame frame){
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection  c = factory.obtemConexao()) {  
            String sql = "SELECT cpf, cep FROM tb_funcionario where cpf = '"+cpf
                    +"'and cep = '"+ cep + "'";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                LoginSession.cpf = rs.getString("cpf");
                LoginSession.cep = rs.getString("cep");
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,"Desconhecido" + e.getMessage());
        }
        return false;
        }
    }
