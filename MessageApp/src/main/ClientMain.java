package main;

import java.io.IOException;

import model.*;

public class ClientMain {
	private static final int DEFAULT_PORT = 1208;
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
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
            client = new Client(username, HOST_NAME, DEFAULT_PORT);
        } catch (Exception exception) {
            System.out.println("Conexão recusada. Tente novamente");
            return;
        }

		System.out.println("Informe uma mensagem: ");
		new Thread(new ReceiveMessageRunnable(client)).start();

		String message;
		do {
            System.out.println(MessageBuilder.defaultColor(""));

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
