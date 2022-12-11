package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	PrintStream printStream;
	Socket socket;
	String userId;
	String localhost;
	Integer port;

	public Client(String userId, String localhost, Integer port) throws IOException {
		this.userId = userId;
		this.localhost = localhost;
		this.port = port;

		this.socket = new Socket(localhost, port);

		printStream = new PrintStream(socket.getOutputStream());
		printStream.println(userId);
	}

	public void sendMessage(String message) {
		printStream.println(userId + ": " + message);
	}

	public void receiveMessage() throws IOException {
		InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(inputReader);

		String serverMessage;
		while ((serverMessage = reader.readLine()) != null) {
			String messageOwner = serverMessage.split(":")[0];

			if (messageOwner != null && !messageOwner.equals(userId)) {
				System.out.println(serverMessage);
			}
		}
	}

	public void closeConnection() throws IOException {
		printStream.close();
		socket.close();
	}
}
