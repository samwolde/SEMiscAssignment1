package com.company;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static RemoteCall remoteCall;
    public static void main(String[] args) throws Exception{
        try{
            remoteCall = new RemoteCall();
        } catch (Exception e){
            System.out.println("Unable to create remote call object");
        }

        Scanner scanner =  new Scanner(System.in);
        String fun;
        Double a, b, c = 0.0;


        System.out.println("Enter operation to perform(add, sub,mul,div): ");
        fun = scanner.nextLine();

        System.out.println("Enter first number: ");
        a = scanner.nextDouble();

        System.out.println("Enter second number: ");
        b = scanner.nextDouble();

        switch (fun) {
            case "add":
                c = remoteCall.add(a, b);
                break;

            case "sub":
                c = remoteCall.sub(a, b);
                break;

            case "mul":
                c = remoteCall.mul(a, b);
                break;

            case "div":
                c = remoteCall.div(a, b);
                break;
        }

        System.out.println("The "+fun+" of " + a.toString() + " and " + b.toString() + " is " + c.toString());
    }

}
