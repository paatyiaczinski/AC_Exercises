package org.academiadecodigo.shellmurais.multichat;

import org.academiadecodigo.shellmurais.multichat.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {


    public static void main(String[] args) {

        Server server = new Server();

        while(server.isBound()) {
        try {
            server.listen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
}