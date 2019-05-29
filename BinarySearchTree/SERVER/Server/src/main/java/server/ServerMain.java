package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import binarytree.BinaryTree;

public class ServerMain {
    private ServerSocket server = null;
    private Socket client = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String line = "";
    private BinaryTree tree;
    private String option = "";
    private String value = "";
    private String treeDraw = null;

    ServerMain() {
        try {
            server = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
        listenSocket();
        createTree();
        getInstructions();
    }

    public void listenSocket() {
        try {
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("in and out failed");
            System.exit(-1);
        }
    }

    private void createTree() {
        try {
            String type = in.readLine();
            if (type.compareTo("1") == 0)
                tree = new BinaryTree<Integer>();
            else if (type.compareTo("2") == 0)
                tree = new BinaryTree<Double>();
            else if (type.compareTo("3") == 0)
                tree = new BinaryTree<String>();
            out.println("Stworzono drzewo");
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }

    private void getInstructions() {
        do {
            try {
                option = in.readLine();
                if (option.compareTo("1") == 0) {
                    value = in.readLine();
                } else if (option.compareTo("2") == 0) {
                    System.out.print("wybrano delete");
                } else if (option.compareTo("3") == 0) {
                    treeDraw = tree.getTree();
                    System.out.println(treeDraw);
                } else if (option.compareTo("4") == 0) {
                    System.out.print("wybrano wyjscie");
                }
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }

        } while (option.compareTo("4") != 0);

    }

    protected void finalize() {
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        ServerMain server = new ServerMain();
    }

}
/*
 * if (option == 1){
 * 
 * } else if(option == 2){
 * 
 * } else if(option == 3){
 * 
 * } else if(option == 4){
 * 
 * } else System.out.println("Proszę wpisać 1,2,3 lub 4");
 */