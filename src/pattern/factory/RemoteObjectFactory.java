/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.factory;

import java.rmi.RemoteException;
import java.sql.SQLException;
import rmi.IRemoteController;

/**
 *
 * @author nicho
 */
public interface RemoteObjectFactory {
    public IRemoteController getRemoteObject() throws ClassNotFoundException, SQLException, RemoteException;
}
