/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vacina;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Desktop
 */
public class Vacina {
    private String nomeVacina;
    private int qtdeDose;
    private int entreDose;
    private String fornecedor;
    private  int id;

    public Vacina(String nomeVacina, int qtdeDose, int entreDose, String fornecedor) {
        this.nomeVacina = nomeVacina;
        this.qtdeDose = qtdeDose;
        this.entreDose = entreDose;
        this.fornecedor = fornecedor;
    }

    public Vacina() {
        
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public int getQtdeDose() {
        return qtdeDose;
    }

    public void setQtdeDose(int qtdeDose) {
        this.qtdeDose = qtdeDose;
    }

    public int getEntreDose() {
        return entreDose;
    }

    public void setEntreDose(int entreDose) {
        this.entreDose = entreDose;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void CadastrarVacina(){
        //Definir comando sql
        String sql = "Insert into tb_vacina(nomeVacina,qtdeDose,prazo,fornecedor) Values (?,?,?,?)";
        //Abrir conexão
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,getNomeVacina());
            ps.setInt(2,getQtdeDose());
            ps.setInt(3,getEntreDose());
            ps.setString(4,getFornecedor());
            ps.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public void Apagar(String nomeVacina){
         setNomeVacina(nomeVacina);
        //1: Definir o comando SQL
        
        String sql = "delete from tb_vacina where nomeVacina = ?";
        //2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory(); // cria um objeto para fazer a conexão
        try(Connection c = factory.obtemConexao()){
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: manda os dados para o banco de dados
            ps.setString(1,getNomeVacina());   
            //5: Executa o comando
            ps.execute();
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
        
    }
     public void obter(String nomeVacina){
        String sql = "Select * from tb_vacina where nomeVacina = ?";
        //abri conexão 
         setNomeVacina(nomeVacina);
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, getNomeVacina()); 
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setId(rs.getInt("id_vacina"));
                  setNomeVacina(rs.getString("nomeVacina"));
                  setQtdeDose(rs.getInt("qtdeDose"));
                  setEntreDose(rs.getInt("prazo"));
                  setFornecedor(rs.getString("fornecedor"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public void atualizar(){
         String sql = "UPDATE tb_vacina set nomeVacina =?, qtdeDose = ?, prazo = ?, fornecedor = ? where id_vacina = ?";
         ConnectionFactory factory = new ConnectionFactory();
         
         try(Connection c = factory.obtemConexao()){
             PreparedStatement ps = c.prepareStatement(sql);
             ps.setInt(5,getId() ); 
             ps.setString(1, getNomeVacina());
             ps.setInt(2, getQtdeDose());
             ps.setInt(3, getEntreDose());
             ps.setString(4,getFornecedor());
             ps.execute();
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }
     public String[] listarVacina(){
         ArrayList<String> vacinas = new ArrayList<String>();
         //1: Definir comando SQL
         String sql = "Select nomeVacina FROM tb_vacina";
         //2: Abrir uma conexão
         ConnectionFactory factory = new  ConnectionFactory();
         try(Connection c = factory.obtemConexao()) {
             //3: pré compila
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()) {
                 vacinas.add(rs.getString("nomeVacina"));
             }
         }
         catch( Exception e){
             e.printStackTrace();
         }
         return  vacinas.toArray(new String[vacinas.size()]);
     }
     public void obter(int id){
         setId(id);
        String sql = "Select * from tb_vacina where id_vacina = ?";
        //abri conexão 
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, getId()); 
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setId(rs.getInt("id_vacina"));
                  setNomeVacina(rs.getString("nomeVacina"));
                  setQtdeDose(rs.getInt("qtdeDose"));
                  setEntreDose(rs.getInt("prazo"));
                  setFornecedor(rs.getString("fornecedor"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public void resetarAtributos(){
        setNomeVacina(null);
        setEntreDose(-2);
        setQtdeDose(-2);
        setFornecedor(null);
     }
     //JLabel l3,JLabel l4,JLabel l5,JLabel l6
     public void limparCampos(JTextField l1,JTextField l2,JSpinner j1,JSpinner j2){
         l1.setText(null);
         l2.setText(null);
         j1.setValue(1);
         j2.setValue(1);
}
    public void limparCampos(JTextField txtNomeSegunda,JTextField txtNomeVacinaSegunda,
            JTextField txtPrimeiraDoseSegunda,JTextField  txtUnidadeSegunda,
        JTextField quantidade,JTextField  txtCpf8){
        txtNomeSegunda.setText(null);
        txtNomeVacinaSegunda.setText(null);
        txtPrimeiraDoseSegunda.setText(null);
        txtUnidadeSegunda.setText(null);          
        quantidade.setText(null);
        txtCpf8.setText(null);
}
     public void listarVacina(JComboBox combo){
          combo.setModel(new javax.swing.DefaultComboBoxModel<>(new Vacina().listarVacina()));
     }
    public void AtribuirNosCampos(JTextField txtNomeVacina1,
            JTextField txtFornecedor1,JSpinner spinnerprazo1,
            JSpinner  spinnerDoses1) {
        
        txtNomeVacina1.setText(getNomeVacina());
        txtFornecedor1.setText(getFornecedor());
        spinnerprazo1.setValue(getEntreDose());
        spinnerDoses1.setValue(getQtdeDose());
     }

}
