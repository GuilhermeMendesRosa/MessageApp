package main;

import java.io.IOException;

import model.*;

public class ClientMain {
	private static final int DEFAULT_PORT = 1208;
	private static final String DEFAULT_HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
        boolean isValidFullAddress = false;
        String host = null;
        Integer port = null;
        do {
            System.out.print(MessageBuilder.defaultColor(""));
            String serverAddress = ScannerMessage.get(MessageBuilder.defaultColor("Informe o endereço do servidor e a porta, separados por ':', ex: 127.0.0.1:1208. Se nada for informado, será utilizado " + DEFAULT_HOST_NAME + "."));

            if (StringValidator.isNotBlank(serverAddress)) {
                isValidFullAddress = StringValidator.isValidServerAddress(serverAddress);
                if (!isValidFullAddress) {
                    System.out.println(MessageBuilder.error("Endereço do servidor inválido. "));
                    continue;
                }
                host = serverAddress.split(":")[0];
                port = Integer.parseInt(serverAddress.split(":")[1]);
            } else {
                host = DEFAULT_HOST_NAME;
                port = DEFAULT_PORT;
                isValidFullAddress = true;
            }
        } while (!isValidFullAddress);

		boolean isValidUsername = false;
		String username;
		do {
            System.out.print(MessageBuilder.defaultColor(""));
			username = ScannerMessage.get(MessageBuilder.defaultColor("Informe o nome do usuário: "));
			isValidUsername = StringValidator.isNotBlank(username);
			if (!isValidUsername) {
				System.out.println(MessageBuilder.error("Usuário inválido. "));
			}
		} while (!isValidUsername);

        Client client;
		try {
            client = new Client(username, host, port);
        } catch (Exception exception) {
            System.out.println("Conexão recusada. Tente novamente");
            return;
        }

		System.out.println("Informe uma mensagem: ");
		new Thread(new ReceiveMessageRunnable(client)).start();

		String message;
		do {
            System.out.print(MessageBuilder.defaultColor(""));

            message = ScannerMessage.get(null);
            if (StringValidator.isNotBlank(message)) {
                client.sendMessage(message);
            } else {
				System.out.println(MessageBuilder.error("Mensagem inválida."));
			}
		} while (!message.equals("#close"));

		client.closeConnection();
	}
}
