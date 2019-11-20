/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.Pessoa;

/**
 *
 * @author nicho
 */
public class ClienteFactory implements PessoaFactory {
    private Cliente pessoa;
    private static ClienteFactory factory = null;
    
    private ClienteFactory() {
        
    }

    public static ClienteFactory getInstance() {
        if (factory == null)
            factory = new ClienteFactory();
        return factory;
    }
    
    @Override
    public Cliente getPessoa() {
        return new Cliente();
    }

    @Override
    public Cliente getPessoa(Integer id, String nome, String email, String celular) {
        return new Cliente(id, nome, email, celular);
    }

    @Override
    public Cliente getPessoa(ResultSet rs, String[] params) throws SQLException {
        Integer id = rs.getInt(params[0]);
        String nome = rs.getString(params[1]),
                email = rs.getString(params[2]),
                celular = rs.getString(params[3]),
                cpf = rs.getString(params[4]);
        Cliente cliente = getPessoa(id, nome, email, celular);
        cliente.setCpf(cpf);
        return cliente;
    }

    @Override
    public Pessoa getPessoa(ResultSet rs) throws SQLException {
        String params[] = {"r_id", "r_nome", 
            "r_email", "r_celular", "r_cpf"};
        return getPessoa(rs, params);
    }
    
}
