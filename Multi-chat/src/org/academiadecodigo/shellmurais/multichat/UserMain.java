package org.academiadecodigo.shellmurais.multichat;

import org.academiadecodigo.shellmurais.multichat.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserMain {


    public static void main(String[] args) {

       User user = new User();
        ExecutorService thread = Executors.newSingleThreadExecutor();

        while (user.isBound()) {
       thread.submit(user);

       user.receiveInfo();

        }

    }
}
