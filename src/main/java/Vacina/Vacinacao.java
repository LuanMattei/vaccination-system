/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vacina;

import Connection.ConnectionFactory;
import Pessoas.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoTableModel;


/**
 *
 * @author Desktop
 */
public class Vacinacao {
    private int id;
    private int id_vacina;
    private String nome_Vacina;
    private String unidade;
    private LocalDate primeiraDoseData;
     private LocalDate segundaDoseData;
    private int quantidade ;
    private int id_carteira;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Vacinacao() {
    }

    public Vacinacao( String unidade,  String primeiraDoseData,String segundaDoseData ,int id_paciente,int id_vacina) {
        setUnidade(unidade);
        setPrimeiraDoseData(primeiraDoseData);
        setId(id_paciente);
        setSegundaDoseData(segundaDoseData);
        setId_vacina(id_vacina);
    }
    public int getId_vacina() {
        return id_vacina;
    }
    public void setId_vacina(int id_vacina) {
        this.id_vacina = id_vacina;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_carteira() {
        return id_carteira;
    }

    public void setId_carteira(int id_carteira) {
        this.id_carteira = id_carteira;
    }
    
    public String getNome_Vacina() {
        return nome_Vacina;
    }
    public String getUnidade() {
        return unidade;
    }
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
     public void setNome_Vacina(String nome_Vacina) {
        this.nome_Vacina = nome_Vacina;
    }  

    public String getPrimeiraDoseData() {
        return formato.format(primeiraDoseData);
    }

    public void setPrimeiraDoseData(String primeiraDoseData) {
        this.primeiraDoseData = LocalDate.parse(primeiraDoseData, formato);
    }

    public String getSegundaDoseData() {
        return formato.format(segundaDoseData);
    }

    public void setSegundaDoseData(String dataSegundaDose) {
        this.segundaDoseData = LocalDate.parse(dataSegundaDose, formato);
    }
        public void setPrimeiraDoseData(java.sql.Date d ) {
        primeiraDoseData = d.toLocalDate();
    }
    public void setSegundaDoseData(java.sql.Date d ) {
        segundaDoseData = d.toLocalDate();
    }

     
    public String showSecondDose(int prazo){
         GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String d1 ;
        gc.add(GregorianCalendar.DAY_OF_MONTH,prazo);
        d1 = sdf1.format(gc.getTime());
        return d1;
    }
       public String day_of_today(){
         LocalDate hoje = LocalDate.now();
        String hojeFormatado = hoje.format(formato);
        return  hojeFormatado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
       
       public void CadastrarCidadaoVacinado(){
        //1: Definir o comando SQL
        String sql = "INSERT INTO tb_infovacinacao(unidade,dataPrimeiraDose,id_paciente,id_vacina)"
                + " VALUES(?,?,?,?)";
        //2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory(); // cria um objeto para fazer a conexão
        try(Connection c = factory.obtemConexao()){
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: manda os dados para o banco de dados
            ps.setString(1,getUnidade());
            ps.setDate(2, java.sql.Date.valueOf(primeiraDoseData));
            ps.setInt(3,getId());
            ps.setInt(4, getId_vacina());
            
             //5: Executa o comando
            ps.execute();
        }
        catch(Exception e){
             e.printStackTrace();      
        }
    }
    
       public void obter(){
        String sql = "Select c.nomePaciente ,c.Cpf, cv.* , v.nomeVacina ,"
        + " (dataPrimeiraDose + InTerval v.prazo Day) as dataSegundaDose"
        +" from  tb_paciente c , tb_infovacinacao cv , tb_vacina v"
        +" where c.id_paciente = ?"
        +" and cv.id_vacina = v.id_vacina"
        +" ORDER BY nomePaciente" ;
        //abri conexão 
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, getId()); 
        ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setPrimeiraDoseData(rs.getDate("dataPrimeiraDose"));
                  setSegundaDoseData(rs.getDate("dataSegundaDose"));
                  setUnidade(rs.getString("unidade"));
                  setId_vacina(rs.getInt("id_vacina"));
                  setId_carteira(rs.getInt("id_carteira"));
            }
        }
        catch(Exception e){
            setNome_Vacina("Não foi vacinado");
            e.printStackTrace();
        }
    }
      public void apagar(){
          String sql = "Delete from tb_infovacinacao where id_paciente = ?";
          //abri conexão
          ConnectionFactory factory   = new ConnectionFactory();
          try ( Connection c = factory.obtemConexao()){
              PreparedStatement ps = c.prepareStatement(sql);
              ps.setInt(1, getId());
              ps.execute();
          }
         catch(Exception e ){
             e.printStackTrace();
         }
      }
      // count(*)
      // conta quantas doses cada pessoa já tomou  
       public void contar(int id){
           String sql = "Select count(*) as quantidade  from  tb_infovacinacao where id_paciente = ? ";
           //abrir conexão
           ConnectionFactory factory  = new ConnectionFactory();
           try(Connection c = factory.obtemConexao()) {
               PreparedStatement ps  = c.prepareStatement(sql);
               ps.setInt(1,id); 
               ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setQuantidade(rs.getInt("quantidade"));
              }
           }
           catch(Exception e ){
               e.printStackTrace();
           }
       }
       public String  verificar(int qtdeDose){
           quantidade = getQuantidade();
           String mensagem  = null;
           if (quantidade/qtdeDose < 1){
               mensagem = "Paciente tomou "+quantidade+" doses "
                       +"das " +qtdeDose + " disponiveis";
           }
           else{
               mensagem = "Todas as doses foram aplicadas";
           }
           return mensagem;
       }
        public String verificar(String data,int prazo){
            String mensagem = null;
            if (getSegundaDoseData().equals(data)){
                mensagem = "Paciente não esperou o prazo de: "+prazo;
            }
            else {
                mensagem = "Paciente pode ser vacinado";
            }
            return   mensagem; 
    }
    public ProdutoTableModel listarPaciente(){
        ProdutoTableModel tableModel = new ProdutoTableModel();
        
        String sql = "Select c.nomePaciente ,c.Cpf,v.nomeVacina,(dataPrimeiraDose + InTerval v.prazo Day)"
                + "  as dataSegundaDose, cv.*\n" +
                "from  tb_paciente c , tb_infovacinacao cv , tb_vacina v\n" +
                "where c.id_paciente = cv.id_paciente\n" +
                "and cv.id_vacina = v.id_vacina\n" +
                "and  dataPrimeiraDose + InTerval v.prazo Day BETWEEN ? AND ? \n"+
                "and cv.status = 'ABERTO'"+
                "ORDER BY nomePaciente ";
                ConnectionFactory factory = new ConnectionFactory();
                try (Connection  c = factory.obtemConexao()){
                     PreparedStatement ps = c.prepareStatement(sql);
                      ps.setDate(1, java.sql.Date.valueOf(primeiraDoseData));
                      ps.setDate(2,java.sql.Date.valueOf(segundaDoseData));
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                    Produto prod = new Produto();
                    prod.setNomePaciente(rs.getString("nomePaciente"));
                    prod.setCpf(rs.getString("Cpf"));               
                    prod.setNomeVacina(rs.getString("nomeVacina"));
                    prod.setDataSegunda(rs.getDate("dataSegundaDose"));
                    prod.setUnidade(rs.getString("unidade"));
                    tableModel.addRow(prod);
                     }
                 }
                 catch(SQLException e ){
                     JOptionPane.showMessageDialog(null,"Não foi encontrado pacientes entre essas datas", 
                             "Erro",JOptionPane.ERROR_MESSAGE);
                 }
                return tableModel;
        }
 public void segundaDose(int id){
           setId(id);
           //definir comando sql
           String sql = "UPDATE tb_infovacinacao SET status = 'VACINADO' WHERE id_paciente = ? ";
           // Abrir uma conexão
           ConnectionFactory factory = new ConnectionFactory();
           try (Connection c = factory.obtemConexao()){
               //Pré compila 
               PreparedStatement ps = c.prepareStatement(sql);
               ps.setInt(1,getId());
               ps.execute();
           }
           catch(Exception e){
               e.printStackTrace();
           }

    }
  public void obterId(int id){
        String sql = "Select c.nomePaciente ,c.Cpf, cv.* , v.nomeVacina ,"
        + " (dataPrimeiraDose + InTerval v.prazo Day) as dataSegundaDose"
        +" from  tb_paciente c , tb_infovacinacao cv , tb_vacina v"
        +" where c.id_paciente = ?"
        +" and cv.id_vacina = v.id_vacina"
        +" ORDER BY nomePaciente" ;
        //abri conexão 
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id); 
        ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setId_carteira(rs.getInt("id_carteira"));
                  setPrimeiraDoseData(rs.getDate("dataPrimeiraDose"));
                  setSegundaDoseData(rs.getDate("dataSegundaDose"));
                  setUnidade(rs.getString("unidade"));
                  setId_vacina(rs.getInt("id_vacina"));
                  
            }
        }
        catch(Exception e){
            setNome_Vacina("Não foi vacinado");
            e.printStackTrace();
        }
    }
    
}
        
