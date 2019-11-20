/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Cliente;

/**
 *
 * @author nicho
 */
public interface IClienteController extends Remote {
    public void salvar(Integer id, String nome, String email,
            String celular, String cpf) throws RemoteException;
    public List listar() throws RemoteException;
    public Cliente buscarPorId(Integer id) throws RemoteException;
}
