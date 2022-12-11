package main;

import java.io.IOException;

import model.Client;
import model.ReceiveMessageRunnable;
import model.ScannerMessage;
import model.StringValidator;

public class ClientMain {
	private static final int DEFAULT_PORT = 1208;
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
		Boolean isValidUsername = false;
		String username;
		do {
			username = ScannerMessage.get("Informe o nome do usuário: ");
			isValidUsername = StringValidator.isNotBlank(username);
			if (!isValidUsername) {
				System.out.println("Usuário inválido. ");
			}
		} while (!isValidUsername);

		Client client = new Client(username, HOST_NAME, DEFAULT_PORT);

		System.out.println("Informe uma mensagem: ");
		new Thread(new ReceiveMessageRunnable(client)).start();

		String message;
		do {
			message = ScannerMessage.get(null);
			if (StringValidator.isNotBlank(message)) {
				client.sendMessage(message);
			} else {
				System.out.println("Mensagem inválida.");
			}
		} while (!message.equals("#close"));

		client.closeConnection();
	}
}
