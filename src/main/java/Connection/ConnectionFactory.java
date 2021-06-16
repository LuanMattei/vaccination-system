/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Desktop
 */
public class ConnectionFactory {
private String usuario = "covid";
private String senha = "Covid2021";
private String host = "localhost";
private String porta = "3306";
private String bd = "db_vac";

public Connection obtemConexao (){
try{
Class.forName("com.mysql.jdbc.Driver");
Connection c = DriverManager.getConnection(
"jdbc:mysql://" + host + ":" + porta + "/" + bd,
usuario,
senha
);
return c;
}
catch (Exception e){
e.printStackTrace();
return null;
}
}
}
