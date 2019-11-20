/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pessoa;

/**
 *
 * @author nicho
 */
public interface PessoaFactory {
    public Pessoa getPessoa();
    public Pessoa getPessoa(Integer id, String nome, String email, String celular);
    public Pessoa getPessoa(ResultSet rs, String[] params) throws SQLException;
    public Pessoa getPessoa(ResultSet rs) throws SQLException;
    
}
