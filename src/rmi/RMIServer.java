/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import controller.ClienteController;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;

/**
 *
 * @author nicho
 */
public class RMIServer implements IClienteController {

    private ClienteController controller;
    
    public RMIServer() throws ClassNotFoundException, SQLException {
        super();
        controller = new ClienteController();
    }
    
    @Override
    public void salvar(Integer id, String nome, String email, 
            String celular, String cpf) throws RemoteException {
        try {
            controller.salvar(id, nome, email, celular, cpf);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List listar() throws RemoteException {
        return controller.listar();
    }

    @Override
    public Cliente buscarPorId(Integer id) throws RemoteException {
        return controller.buscarPorId(id);
    }
    
}
