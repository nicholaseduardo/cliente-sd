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
public class RMICliente implements IRemoteController {

    private ClienteController controller;

    public RMICliente() throws ClassNotFoundException, SQLException {
        super();
        controller = new ClienteController();
    }

    @Override
    public void salvar(Object object) throws RemoteException {
        try {
            if (object instanceof Cliente) {
                Cliente c = (Cliente) object;
                controller.salvar(c.getId(), c.getNome(),
                        c.getEmail(), c.getCelular(), c.getCpf());
            }
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
