package main;

import java.io.IOException;
import java.util.Scanner;

import model.Client;
import model.ClientRunnable;
import model.StringValidator;

public class ClientMain {
	private static final int DEFALUT_PORT = 1208;
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
        Boolean isValidUsername;
        String username;
        do {
            username = getMessage("Informe o nome do usuário: ");
            isValidUsername = StringValidator.validate(username);
            if (!isValidUsername) {
                System.out.println("Usuário inválido. ");
            }
        } while (!isValidUsername);

		Client client = new Client(username, HOST_NAME, DEFALUT_PORT);

		System.out.println("Informe uma mensagem: ");
		new Thread(new ClientRunnable(client)).start();

        String message;
		do {
			message = getMessage(null);
            if (StringValidator.validate(message)) {
                client.sendMessage(message);
            } else {
                System.out.println("Mensagem inválida.");
            }
		} while (message != "");

		client.closeConnection();
	}

	private static String getMessage(String label) {
		Scanner scanner = new Scanner(System.in);
		if (label != null) {
			System.out.println(label);
		}

		return scanner.nextLine();
	}
}
