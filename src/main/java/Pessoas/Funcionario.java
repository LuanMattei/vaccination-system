/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoas;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;

/**
 *
 * @author Desktop
 */
public final class Funcionario extends Pessoa{
private String cargo;
private String unidade;
private int id;
    public Funcionario() {
    }

    public Funcionario(String nome,String cargo,String cpf, String cep, String dataNascimento,String telefone,String unidade ) {
        super(nome, cpf, cep, telefone, dataNascimento);
        setCargo(cargo);
        setUnidade(unidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    

    public void CadastrarFuncionario(){
        //1: Definir o comando SQL
        String sql = "INSERT INTO tb_funcionario(nomeFuncionario,cargo,cpf,cep,dataDeNascimento,telefone,unidade) VALUES(?,?,?,?,?,?,?)";
        //2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory(); // cria um objeto para fazer a conexão
        try(Connection c = factory.obtemConexao()){
            //3: Pré compila o comando
            PreparedStatement ps = c.prepareStatement(sql);
            //4: manda os dados para o banco de dados
            ps.setString(1, getNome());
            ps.setString(2, getCargo());
            ps.setString(3, getCpf());
            ps.setString(4, getCep());
            ps.setString(5, getDataNascimento());
            ps.setString(6, getTelefone());
            ps.setString(7, getUnidade());
            
            //5: Executa o comando
            ps.execute();
        }
        catch(Exception e){
            e.printStackTrace();      
        }
    }
       public void Apagar(){
        //1: Definir o comando SQL
        String sql = "delete from tb_funcionario where  cpf = ?";
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
        String sql = "Select * from tb_funcionario where cpf = ?";
        //abri conexão 
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection c = factory.obtemConexao()){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, getCpf()); 
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                  setId(rs.getInt("id"));
                  setNome(rs.getString("nomeFuncionario"));
                  setCargo(rs.getString("cargo"));
                  setCpf(rs.getString("cpf"));
                  setDataNascimento(rs.getString("dataDeNascimento"));
                  setTelefone( rs.getString("telefone"));
                  setCep(rs.getString("cep"));      
                  setUnidade(rs.getString("unidade"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
 public void Atualizarfuncionario(int id ){
     setId(id);
        String sql = " Update tb_funcionario set nomeFuncionario = ?,cargo=?,cpf =? ,dataDeNascimento= ?,telefone = ?,cep = ?,unidade = ? where id = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection  c = factory.obtemConexao()){
             PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(8, getId()); 
             // sla 
             ps.setString(1, getNome());
             ps.setString(2, getCargo());
             ps.setString(3, getCpf());
             ps.setString(4, getDataNascimento());
             ps.setString(5, getTelefone());
             ps.setString(6, getCep());
             ps.setString(7,getUnidade());
             ps.execute();
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }
    public void login(){
        String sql = "Select cpf ";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection  c = factory.obtemConexao()){
             PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(8, getCpf()); 
             // sla 
             ps.setString(1, getNome());
             ps.setString(2, getCargo());
             ps.setString(3, getCpf());
             ps.setString(4, getDataNascimento());
             ps.setString(5, getTelefone());
             ps.setString(6, getCep());
             ps.setString(7,getUnidade());
             ps.execute();
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }
        public void limparCampos(JTextField txtNomeSegunda,JTextField txtNomeVacinaSegunda,
            JTextField txtPrimeiraDoseSegunda,JTextField  txtUnidadeSegunda,
        JTextField quantidade,JTextField  txtCpf8,JTextField  OTHER){
        txtNomeSegunda.setText(null);
        txtNomeVacinaSegunda.setText(null);
        txtPrimeiraDoseSegunda.setText(null);
        txtUnidadeSegunda.setText(null);          
        quantidade.setText(null);
        txtCpf8.setText(null);
        OTHER.setText(null);
}
        public void  AtribuirNosCampos(JTextField txtNomeSegunda,JTextField txtNomeVacinaSegunda,
            JTextField txtPrimeiraDoseSegunda,JTextField  txtUnidadeSegunda,
        JTextField quantidade,JTextField  txtCpf8,JTextField  OTHER){
        txtNomeSegunda.setText(getNome());
        txtNomeVacinaSegunda.setText(getCpf());
        txtPrimeiraDoseSegunda.setText(getDataNascimento());
        txtUnidadeSegunda.setText(getTelefone());          
        quantidade.setText(getCep());
        txtCpf8.setText(getUnidade());
        OTHER.setText(getCargo());
}
}
