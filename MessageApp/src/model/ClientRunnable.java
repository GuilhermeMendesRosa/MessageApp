package model;

import java.io.IOException;

public class ClientRunnable implements Runnable {
	Client client;

	public ClientRunnable(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			client.receiveMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
