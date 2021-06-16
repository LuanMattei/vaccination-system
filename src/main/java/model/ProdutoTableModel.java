/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Desktop
 */
public class ProdutoTableModel extends AbstractTableModel {
    private List<Produto> dados = new ArrayList<>();
    private String[] colunas = {"Nome paciente","Cpf","Nome vacina","Data da segunda"
    ,"Unidade"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return  dados.size();
    }

    @Override
    public int getColumnCount() {
        return  colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int colunas) {
        switch(colunas){
            case 0:
              return dados.get(linha).getNomePaciente();
            case 1:
                return  dados.get(linha).getCpf();
            case 2:
                return  dados.get(linha).getNomeVacina();
            case 3:
                return dados.get(linha).getDataSegunda();
            case 4:
                return dados.get(linha).getUnidade();
        }
        return null;
}
    public void addRow(Produto p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
}
