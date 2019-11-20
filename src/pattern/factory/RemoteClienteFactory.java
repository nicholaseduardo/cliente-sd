/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.factory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import rmi.IRemoteController;
import rmi.RMICliente;

/**
 *
 * @author nicho
 */
public class RemoteClienteFactory implements RemoteObjectFactory {
    private static RemoteClienteFactory factory = null;
    
    private RemoteClienteFactory() {
        
    }
    
    public static synchronized RemoteClienteFactory getInstance() {
        if (factory == null) {
            factory = new RemoteClienteFactory();
        }
        return factory;
    }

    @Override
    public IRemoteController getRemoteObject() throws ClassNotFoundException, SQLException, RemoteException {
        RMICliente rmiCliente = new RMICliente();
        return (IRemoteController) UnicastRemoteObject.exportObject(rmiCliente, 0);
    }
    
}
