package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread implements ClientListener {
    private ServerSocket theSocket;
    private ServerListener listener;
    private boolean isON;
    private ArrayList<Client> clients;

    public Server(ServerListener l) {
        listener = l;
        isON = false;
        clients = new ArrayList<Client>(1);
        l.onMessage("server iniciado...");
    }
    public void turnOff() {
        isON = false;
    }
    public void run() {
        isON = true;
        try{
            listener.onMessage("Criando Socket");
            createServerSocket();
            listener.onMessage("Waiting...");
            waitForClients();
            theSocket.close();
        } catch(IOException e) {
            listener.onMessage("Erro no Run." + e.getMessage());
        }
    }

    private void waitForClients() throws IOException {
        do {
            Socket socketDoCliente = theSocket.accept();
            clients.add(new Client(socketDoCliente, this));
        }while(isON);
    }

    private void createServerSocket() throws IOException{
        theSocket = new ServerSocket(1208);
    }

    @Override
    public void onMessage(String message) {
        listener.onMessage(message);
        for(Client c : clients) {
            c.postMessage(message);
        }
    }
}
