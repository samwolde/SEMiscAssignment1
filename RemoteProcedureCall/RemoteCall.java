package com.company;


import java.io.*;
import java.net.Socket;

public class RemoteCall {
    public Socket socket;
    public static BufferedReader keyRead, receiveRead;
    public static PrintWriter pWrite;

    public RemoteCall() throws Exception{
        socket = new Socket("127.0.0.1", 3000);
        keyRead = new BufferedReader(new InputStreamReader(System.in));

        OutputStream osStream = socket.getOutputStream();
        pWrite = new PrintWriter(osStream, true);

        InputStream inStream = socket.getInputStream();

        receiveRead = new BufferedReader(new InputStreamReader(inStream));
    }


    public Double performAction(double a, double b, String action) throws Exception{
        pWrite.println(action==null?"add":action);
        pWrite.println(a);
        pWrite.println(b);

        System.out.flush();

        String receiveMessage = receiveRead.readLine();
        if(receiveMessage != null){
            return Double.parseDouble(receiveMessage);
        }
        return 0.0;
    }

    public Double add(double a, double b) throws Exception{
        return performAction(a, b, "add");
    }

    public Double sub(double a, double b) throws Exception{
        return performAction(a, b, "sub");
    }

    public Double mul(double a, double b) throws Exception{
        return performAction(a, b, "mul");
    }

    public Double div(double a, double b) throws Exception{
        return performAction(a, b, "div");
    }

}
