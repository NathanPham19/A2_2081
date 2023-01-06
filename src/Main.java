import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Are you an customer or admin?");
        System.out.println("1. Admin");
        System.out.println("2. Customer");

        int result = scan.nextInt();
        switch (result){
            case 1:
                Admin a1 = new Admin();
                a1.login();
            case 2:
                System.out.println("Welcome Customer. Do you want to login or register");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.print("Your choice: ");
                int r2 = scan.nextInt();
                switch (r2){
                    case 1:
                        Customer c1 = new Customer();
                        c1.login();
                    case 2:
                        Customer c2 = new Customer();
                        c2.registerUser();

                }

        }
    }
}