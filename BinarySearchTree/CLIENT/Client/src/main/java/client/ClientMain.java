package client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientMain {

    private ClientMain() {
        System.out.println("Jaki typ drzewa chcesz stworzyc? (wybierz 1, 2 lub 3)");
        System.out.println("1.Integer\n2.Double\n3.String");
        Scanner input = new Scanner(System.in);
        int type;

        while (true) {
            try {
                type = input.nextInt();
                if (type == 1 || type == 2 || type == 3)
                    break;
                else
                    System.out.println("Proszę wpisać 1,2 lub 3");
            } catch (InputMismatchException e) {
                System.out.println("zły typ danych, proszę wpisać 1,2 lub 3");
                input.next();
            }
        }
        new SocketClient(type);
    }

    public static void main(String[] args) {
        new ClientMain();
    }
}
