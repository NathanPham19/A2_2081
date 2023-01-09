import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Customer {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;

    private static String employeeID = "0";

    //Generate Unique Customer ID number
    public static String getNextUniqueID() {
        int id = Integer.parseInt(employeeID);
        Random rand = new Random();
        id = rand.nextInt((99999 - 10000) + 1) + 10000;
        return Integer.toString(id);

    }

    //Constructor without param
    public Customer() {

    }
    // Normal Constructor
    public Customer(String username, String password, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;

    }



    //List all the things the users can do
    public void CustomerAction() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Done. What do you want to do now, Customer?");
        System.out.println("1. View Info");
        System.out.println("2. Update Info");
        System.out.println("3. Check membership status");
        System.out.println("4. List all products");
        System.out.println("5. Find products");
        System.out.println("6. Create new Order");
        System.out.println("7. Tracking delivery status through Order ID");
        System.out.println("8. View Order History");

        System.out.print("Choose your option: ");
        int option = scan.nextInt();
        switch(option){
            case 1:
                viewInfo(getUsername());

        }
    }


    // Login system
    public void login() throws IOException{
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
                this.username = username;
                this.password = password;
                CustomerAction();
            }
        }

        if(!foundUser){
            System.out.println("Wrong username or password! Re-enter your info!");
            login();
        }
    }




    // Register User
    public void registerUser() throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Fill out info to register your account!");

        System.out.println("Username: ");
        String username = scan.nextLine();

        System.out.println("Password: ");
        String password = scan.nextLine();

        System.out.print("Email address: ");
        String email = scan.nextLine();

        System.out.print("Phone number: ");
        String phone = scan.nextLine();

        System.out.print("Home address: ");
        String address = scan.nextLine();

        String Unique_CustomerID = "C";

        // If clause to ensure that the user filled out all info
        if (username.isEmpty() || password.isEmpty() || phone.isEmpty()
            || email.isEmpty() || address.isEmpty())
            {
            System.out.println("One or more required information is missing. Please re-enter your info.");
            registerUser();
            }

        else {


            // run PrintWriter method to create new account into a text file;
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Customer_Info.txt"),true));
            pw.println(username + "," + password + "," + email + "," + phone + "," + address + ",C"+ getNextUniqueID() + ",member,");
            pw.flush();
            pw.close();


            PrintWriter pw1 = new PrintWriter(new FileOutputStream(new File("Member_Login_Info.txt"),true));
            pw1.println(username + " " + password);
            pw1.flush();
            pw1.close();

            System.out.println("You are now registered");
            login();

        }
    }

    //View info of customer
    public void viewInfo(String User) throws IOException {

        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            if (line.startsWith(User + ",")){
                System.out.println("user | pass | email | phone | address | CustomerID | Membership | Amount Spent");
                System.out.println(line);
                break;
            }
        }
    }


    // Update User info by matching the username and then let the user decide what to change
    public void updateInfo() throws IOException {
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));

        // New temp file that will replace the original file
        File tempFile = new File("Customer_Info_Temp.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line = null;
        // Read OG file and write to new temp file
        // unless content matches data to be removed and replaced

        while ((line = br.readLine()) != null){
            if (line.contains(username)){
                String strCurrentValue;
            }
        }


    }

    //View Membership status
    public void viewMembershipStatus(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
