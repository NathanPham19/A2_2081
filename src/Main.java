/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2022C
  Assessment: Assignment 3
  Author:
  Pham Quang Huy
  Bui Minh Khoi
  Tran Vinh Tuong
  ID:  (3940676)
       (3929015)
       (3878734)
  Acknowledgement: Acknowledge the resources that you use here.
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
        System.out.println("Group: Random 3");
        System.out.println("s3940676, Pham Quang Huy");
        System.out.println("s3929015, Bui Minh Khoi");
        System.out.println("s3878734, Tran Vinh Tuong\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome");
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
