/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import rmi.IClienteController;

/**
 *
 * @author nicho
 */
public class RMIClient {

    private Scanner s;
    private IClienteController stub;

    public RMIClient(String hostname) throws RemoteException, NotBoundException {
        s = new Scanner(System.in);
        Registry registro = LocateRegistry.getRegistry(hostname);
        stub = (IClienteController) registro.lookup("RMIServer");
    }

    public void iniciar() throws RemoteException {
        int opcao = 0;
        do {
            System.out.println("\n1 - Listar clientes");
            System.out.println("2 - Cadastrar cliente");
            System.out.println("3 - Alterar cliente");
            System.out.println("4 - Buscar cliente");
            System.out.print("\nDigite 0 (zero) para sair.");
            System.out.print("Informe a opcao: ");
            opcao = s.nextInt();
            switch (opcao) {
                case 1: listar(); break;
                case 2: cadastrar(null); break;
                case 3: alterar(); break;
                case 4: buscar(); break;
            }
        } while (opcao != 0);
    }

    public void listar() throws RemoteException {
        System.out.println("\n**** Listagem de clientes ****");
        List lista = stub.listar();
        for (int i = 0; i < lista.size(); i++) {
            Cliente cliente = (Cliente) lista.get(i);
            System.out.printf("%d\t%s\t%s\t%s\t%s\n",
                    cliente.getId(), cliente.getNome(),
                    cliente.getEmail(), cliente.getCelular(),
                    cliente.getCpf());
        };
    }

    public void cadastrar(Integer id) throws RemoteException {
        if (id == null) {
            System.out.println("\n**** Cadastrar cliente ****");
        }
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("E-mail: ");
        String email = s.nextLine();
        System.out.print("Celular: ");
        String celular = s.nextLine();
        System.out.print("Cpf: ");
        String cpf = s.nextLine();
        stub.salvar(id, nome, email, celular, cpf);
    }

    public void alterar() throws RemoteException {
        System.out.println("\n**** Alterar dados do cliente ****");
        System.out.print("Informe o ID do cliente a ser alterado: ");
        int id = s.nextInt();
        Cliente cliente = null;
        do {
            cliente = stub.buscarPorId(id);
            if (cliente != null) {
                System.out.println("--- Dados atuais ---");
                System.out.printf("ID: %d\nNome: %s\nE-mail: %s\n"
                        + "Celular: %s\n",
                        cliente.getId(), cliente.getNome(),
                        cliente.getEmail(), cliente.getCelular());
                System.out.println("--- Digite os novos dados ---");
                // linha adicionada para
                s.nextLine();
                cadastrar(cliente.getId());
            } else {
                System.out.print("Deseja continuar (S/N)?");
                String op = s.nextLine();
                if ("S".equalsIgnoreCase(op)) {
                    break;
                }
            }
        } while (cliente == null);
    }
    
    public void buscar() throws RemoteException {
        System.out.println("\n**** Bucar cliente por ID ****");
        System.out.print("Informe o ID do cliente a ser alterado: ");
        int id = s.nextInt();
        
        Cliente cliente = null;
        do {
            cliente = stub.buscarPorId(id);
            if (cliente != null) {
                System.out.println("--- Dados atuais ---");
                System.out.printf("ID: %d\nNome: %s\nE-mail: %s\n"
                        + "Celular: %s\n",
                        cliente.getId(), cliente.getNome(),
                        cliente.getEmail(), cliente.getCelular());
            } else {
                System.out.print("Deseja continuar (S/N)?");
                String op = s.nextLine();
                if ("S".equalsIgnoreCase(op)) {
                    break;
                }
            }
        } while (cliente == null);
    }

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Infome o host: ");
            RMIClient rmiClient = new RMIClient(s.nextLine());
            rmiClient.iniciar();
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
