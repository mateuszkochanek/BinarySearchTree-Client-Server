package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

import binarytree.BinaryTree;

public class ServerMain {
    private ServerSocket server = null;
    private Socket client = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private CharBuffer outputString;
    private String type = "";
    private BinaryTree tree;
    private String option = "";
    private String value = "";
    private String treeDraw = "";
    private String lineToPrint = "";

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
            type = in.readLine();
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
                if (option.compareTo("1") == 0) {//Input option
                    value = in.readLine();
                    if (type.compareTo("1") == 0){//Integer
                        try {
                            lineToPrint = tree.insert(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during integer conversion in input");
                        }
                    } else if (type.compareTo("2") == 0){//Double
                        try {
                            lineToPrint = tree.insert(Double.parseDouble(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during double conversion in input");
                        }
                    } else if (type.compareTo("3") == 0){//String
                        lineToPrint = tree.insert(value);
                    }
                    out.println(lineToPrint);
                } else if (option.compareTo("2") == 0) {//Delete option
                    value = in.readLine();
                    if (type.compareTo("1") == 0){//Integer
                        try {
                            tree.delete(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during integer conversion in delete");
                        }
                    } else if (type.compareTo("2") == 0){//Double
                        try {
                            tree.delete(Double.parseDouble(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during double conversion in delete");
                        }
                    } else if (type.compareTo("3") == 0){//String
                        tree.delete(value);
                    }
                    treeDraw = tree.getTree();
                    System.out.println(treeDraw);
                } else if (option.compareTo("3") == 0) {//Draw option
                    System.out.println(tree.getTree());
                    out.println(tree.getTree());
                } else if (option.compareTo("4") == 0) {//Search option
                    if (type.compareTo("1") == 0){//Integer
                        try {
                            tree.search(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during integer conversion in delete");
                        }
                    } else if (type.compareTo("2") == 0){//Double
                        try {
                            tree.search(Double.parseDouble(value));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong type during double conversion in delete");
                        }
                    } else if (type.compareTo("3") == 0){//String
                        tree.search(value);
                    }
                } else if (option.compareTo("5") == 0) {
                    System.out.print("wybrano wyjscie");
                }
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }

        } while (option.compareTo("5") != 0);

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
