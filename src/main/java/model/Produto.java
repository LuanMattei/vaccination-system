/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Connection.ConnectionFactory;
import Pessoas.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Desktop
 */
public class Produto {
    private String nomePaciente;
    private String cpf;
    private String nomeVacina;
    private Date dataSegunda;
    private String Unidade;

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public Date getDataSegunda() {
        return dataSegunda;
    }

    public void setDataSegunda(Date dataSegunda) {
        this.dataSegunda = dataSegunda;
    }

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }
     public void listarPaciente(){
        ProdutoTableModel tableModel = new ProdutoTableModel();
        Produto p = new Produto();
        String sql = "Select c.nomePaciente ,c.Cpf,v.nomeVacina,(dataPrimeiraDose InTerval v.prazo Day)  as dataSegundaDose, cv.* from  tb_paciente c , tb_infovacinacao cv , tb_vacina v"
                + " where c.id_paciente = cv.id_paciente "
                + "v.id_vacina = v.id_vacina"
                + " ORDER BY nomePaciente";
                ConnectionFactory factory = new ConnectionFactory();
                 try (Connection  c = factory.obtemConexao()){
                     PreparedStatement ps = c.prepareStatement(sql);
                     
                     ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                    p.setNomePaciente(rs.getString("nomePaciente"));
                    p.setCpf(rs.getString("Cpf"));               
                    p.setNomeVacina(rs.getString("nomeVacina"));
                    p.setDataSegunda(rs.getDate("dataSegundaDose"));
                    p.setUnidade(rs.getString("unidade"));
                    
                    tableModel.addRow(p);
                     }
                 }
                 catch(Exception e ){
                     JOptionPane.showMessageDialog(null,"Não foi encontrado pacientes entre essas datas", 
                             "Erro",JOptionPane.ERROR_MESSAGE);
                 }
        }

    @Override
    public String toString() {
        return "Produto{" + "nomePaciente=" + nomePaciente + ", cpf=" + cpf + ", nomeVacina=" + nomeVacina + ", dataSegunda=" + dataSegunda + ", Unidade=" + Unidade + '}';
    }
     
}
