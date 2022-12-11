package main;

import java.io.IOException;
import java.util.Scanner;

import model.Client;
import model.ClientRunnable;

public class ClientMain {
	private static final int DEFALUT_PORT = 1208;
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
		String message;
		String userName = getMessage("Nome do usuário: ");

		Client client = new Client(userName, HOST_NAME, DEFALUT_PORT);

		System.out.println("Diga olá...");
		new Thread(new ClientRunnable(client)).start();

		do {
			message = getMessage(null);
			client.sendMessage(message);
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
