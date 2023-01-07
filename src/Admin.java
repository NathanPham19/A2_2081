import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    String user;
    String pass;
    public Admin (String user, String pass){
       this.user = user;
       this.pass = pass;

    }
    public Admin() {

    }

    public void viewCustomer_Info(){

    }

    public void login() throws FileNotFoundException {
        Scanner FileScan = new Scanner(new File("Member_Login_Info.txt"));
        Scanner scan = new Scanner(System.in);
        boolean foundUser = false;


        System.out.println("Username: ");
        String username = scan.nextLine();
        System.out.println("Password: ");
        String password = scan.nextLine();

        while (FileScan.hasNextLine()){
            String input = FileScan.nextLine();
            String user = input.substring(0, input.indexOf(' '));
            String pass = input.substring(input.indexOf(' ')+1 , input.length());


            if( user.equals(username) && (pass.equals(password)) ){
                foundUser = true;
                System.out.println("You are now logged in \n");
                break;
            }
        }

        if(!foundUser){
            System.out.println("Wrong username or password! Re-enter your info!");
            login();
        }
    }
}
