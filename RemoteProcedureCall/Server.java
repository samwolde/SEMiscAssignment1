package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static void main(String[] args) throws Exception{
        serverSocket = new ServerSocket(3000);

        System.out.println("Server ready");

        while(true){
            try{
                createServer();
            } catch (SocketException e){
                System.out.println("Connection reseted");
            }
        }
    }

    public static void createServer() throws Exception{
        socket = serverSocket.accept();

        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        OutputStream osStream = socket.getOutputStream();
        PrintWriter pWrite = new PrintWriter(osStream, true);

        InputStream inStream = socket.getInputStream();

        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inStream));

        String receiveMessage, sendMessage, fun;
        Double a,b,c=0.0;

        while(true){
            fun = receiveRead.readLine();
            if(fun != null){
                a = Double.parseDouble(receiveRead.readLine());

                b = Double.parseDouble(receiveRead.readLine());

                switch (fun){
                    case "add":
                        c = add(a,b);
                        break;

                    case "sub":
                        c = sub(a,b);
                        break;

                    case "mul":
                        c = mul(a,b);
                        break;

                    case "div":
                        c = div(a,b);
                        break;

                }

                pWrite.println(c);
                System.out.flush();
            }
        }
    }

    public static Double add(double a, double b){
        return a+b;
    }

    public static Double sub(double a, double b){
        return a-b;
    }

    public static Double mul(double a, double b){
        return a*b;
    }

    public static Double div(double a, double b){
        return a/b;
    }

}
