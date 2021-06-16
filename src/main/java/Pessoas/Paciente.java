/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoas;

import Vacina.Vacinacao;
import Connection.ConnectionFactory;
import com.iot.systemvac.TelaVacinacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Desktop
 */
public class Paciente extends Pessoa{
    Date x1 = new Date();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private int id;
    public Paciente() {
    }

    public Paciente(String nome, String cpf, String cep, String telefone, String dataNascimento) {
        super(nome, cpf, cep, telefone, dataNascimento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public void CadastrarPaciente(){
        //1: Definir o comando SQL
        String sql = "INSERT INTO tb_paciente(NomePaciente,Cpf,DataNascimento,Telefone,Cep) VALUES(?,?,?,?,?)";
        //2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory(); // cria um objeto para fazer a conexão
        try(Connection c = factory.obtemConexao()){
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: manda os dados para o banco de dados
            ps.setString(1, getNome());
            ps.setString(2, getCpf());
            ps.setString(3, getDataNascimento());
            ps.setString(4, getTelefone());
            ps.setString(5, getCep());     
            //5: Executa o comando
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Apagar(String cpf){
         Vacinacao v1 = new Vacinacao();
         v1.setId(getId());
         v1.apagar();
        //1: Definir o comando SQL
        String sql = "delete from tb_paciente where  cpf = ?";
        //2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory(); // cria um objeto para fazer a conexão
        try(Connection c = factory.obtemConexao()){
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: manda os dados para o banco de dados
            ps.setString(1, getCpf());   
            //5: Executa o comando
            ps.execute();
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
        
    }
    public void obter(String cpf){
        setCpf(cpf);
        String sql = "Select * from tb_paciente where cpf = ?";
        //abri conexão 
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, getCpf()); 
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setId(rs.getInt("id_paciente"));
                  setNome(rs.getString("nomePaciente"));
                  setCpf(rs.getString("Cpf"));
                  setDataNascimento(rs.getString("DataNascimento"));
                  setTelefone( rs.getString("Telefone"));
                  setCep(rs.getString("Cep"));
            
            }
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
    }
    public void Atualizarpaciente(int id){
        setId(id);
        String sql = " Update tb_paciente set nomePaciente = ?,Cpf =? ,DataNascimento= ?,Telefone = ?,Cep = ? where id_paciente = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection  c = factory.obtemConexao()){
             PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(6,getId()); 
             // sla 
             ps.setString(1, getNome());
             ps.setString(2, getCpf());
             ps.setString(3, getDataNascimento());
             ps.setString(4, getTelefone());
             ps.setString(5, getCep());
             ps.execute();
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void zerar(){
        setCep(null);
        setCpf(null);
        setDataNascimento(null);
        setId(-1);
        setNome(null);
        setTelefone(null);
    }
    public void atribuirValores(JTextField txtNome5,JTextField txtCpf5,
    JTextField txtNascimento6,JTextField txtTelefone4,
    JTextField txtCep4){
        txtNome5.setText(getNome());
        txtCpf5.setText(getCpf());
        txtNascimento6.setText(getDataNascimento());
        txtTelefone4.setText(getTelefone());
        txtCep4.setText(getCep());
    }
}
