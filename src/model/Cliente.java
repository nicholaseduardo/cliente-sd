/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nicho
 */
public class Cliente extends Pessoa {
    private String cpf;
    
    public Cliente() {
        super();
    }
    
    public Cliente(Integer id, String nome, String email,
            String celular) {
        super(id, nome, email, celular);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
