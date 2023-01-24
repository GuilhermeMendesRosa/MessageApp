package model;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private ClientListener listener;
    private final String nomeIP;
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintStream printer;

    public Client(Socket socketDoCliente, ClientListener listener) throws IOException{
        this.listener = listener;
        this.socket = socketDoCliente;
        InputStream is = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(is));
        printer = new PrintStream(socket.getOutputStream());
        nomeIP = lerNomeEIP();
        listener.onMessage(nomeIP + " conectou!");
        start();
    }

    @Override
    public void run() {
        String message = "";
        do{
            try {
                message = reader.readLine();
                if(message != null)
                    listener.onMessage(message);
            }catch (IOException e) {}
        }while(!message.equalsIgnoreCase("close"));
    }

    private String lerNomeEIP() throws IOException {
        String response = "";
        String clientId = reader.readLine();
        String ip = socket.getInetAddress().getHostAddress();
        response = clientId +":" + ip;
        return response;
    }

    /** para enviar uma mensagem para o cliente.*/
    public void postMessage(String message) {
        printer.println(message);
    }
}
