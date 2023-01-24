package main;

import model.Server;
import model.ServerListener;

import java.io.PrintStream;

public class Main {
    static Server server;
    public static void main(String[] args) {
        server = new Server((message)-> System.out.println(message));
        server.start();
    }
}
