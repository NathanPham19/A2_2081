import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Customer {
    private static final String CUSTOMER_FILE = "Customer_Info.txt";
    private static final String DELIMITER = ",";

    private String username;
    private String password;
    private String full_name;
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
    public Customer(String username, String password, String full_name,String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
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
            case 2:
                updateInfo(getUsername(),getPassword());

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


            if(user.equals(username) && (pass.equals(password)) ){
                foundUser = true;
                this.username = username;
                this.password = password;
                CustomerAction();
                break;
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

        System.out.println("Your full name:");
        String full_name = scan.nextLine();

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
            pw.println(username + "," + password + "," + full_name + ","  + email + "," + phone + "," + address + ",C"+ getNextUniqueID() + ",member,0");
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
                System.out.println("user | pass | full name | email | phone | address | CustomerID | Membership | Amount Spent");
                System.out.println(line);
                break;
            }
        }
    }




    // Update User info by matching the username and then let the user decide what to change

    public void updateInfo(String username, String password) throws IOException {
        String CUSTOMER_FILE = "Customer_Info.txt";
        String DELIMITER = ",";

        Scanner scanner = new Scanner(System.in);

        // Read the list of customers from the file
        List<Customer> customers = readCustomersFromFile();

        // Find the customer with the given username and password
        Customer customer = null;
        for (Customer c : customers) {
            if (c.username.equals(username) && c.password.equals(password)) {
                customer = c;
                break;
            }
        }

        // If the customer was not found, display an error message
        if (customer == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        // Edit the customer information
        System.out.print("Enter new password (blank to keep current value): ");
        password = scanner.nextLine().trim();
        if (!password.isEmpty()) {
            customer.password = password;
        }
        System.out.print("Enter new full name (blank to keep current value): ");
        String fullName = scanner.nextLine().trim();
        if (!fullName.isEmpty()) {
            customer.full_name = fullName;
        }
        System.out.print("Enter new phone number (blank to keep current value): ");
        String phoneNumber = scanner.nextLine().trim();
        if (!phoneNumber.isEmpty()) {
            customer.phone = phoneNumber;
        }
        System.out.print("Enter new email (blank to keep current value): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            customer.email = email;
        }
        System.out.print("Enter new address (blank to keep current value): ");
        String address = scanner.nextLine().trim();
        if (!address.isEmpty()) {
            customer.address = address;
        }

        // Write the updated list of customers to the file
        writeCustomersToFile(customers);

        scanner.close();
    }

    public void updateInfo_2(String LineToDelete, String User) throws IOException {

        //Delete the file
        File inputFile = new File("Customer_Info.txt");
        File tempFile = new File("temp_file.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();

            if(trimmedLine.equals(LineToDelete)) continue;

            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();


        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void updateInfo_3(String User) throws IOException {
        Scanner scan = new Scanner(System.in);

        //Extract the line the program want to edit
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            if (line.startsWith(User + ",")){
                String result = line;
                System.out.println("Membership tier:");
                String[] parts = result.split(",");
                String user = parts[0];
                String pass = parts[1];
                String full_name = parts[2];
                String email = parts[3];
                String phone = parts[4];
                String address = parts[5];
                String cID = parts[6];
                String membership = parts[7];
                String amount_spent = parts[8];


                System.out.println("Do you want to change password (blank to keep old value | Y to change new pass): ");
                String ans = scan.nextLine();
                if (!ans.isEmpty()){
                    System.out.println("Enter your new password");
                    pass = scan.nextLine();
                }

                System.out.println("Do you want to change password (blank to keep old value | Y to change new pass): ");
                String ans1 = scan.nextLine();
                if (!ans1.isEmpty()){
                    System.out.println("Enter your new full name");
                    full_name = scan.nextLine();
                }

                System.out.println("Do you want to change password (blank to keep old value | Y to change new pass): ");
                String ans2 = scan.nextLine();
                if (!ans2.isEmpty()){
                    System.out.println("Enter your new email address");
                    email = scan.nextLine();
                }

                System.out.println("Do you want to change password (blank to keep old value | Y to change new pass): ");
                String ans3 = scan.nextLine();
                if (!ans3.isEmpty()){
                    System.out.println("Enter your new phone number");
                    phone = scan.nextLine();
                }

                System.out.println("Do you want to change password (blank to keep old value | Y to change new pass): ");
                String ans4 = scan.nextLine();
                if (!ans4.isEmpty()){
                    System.out.println("Enter your new phone number");
                    address = scan.nextLine();
                }



                System.out.println(user+","+pass+","+full_name+","+email+","+phone+","+address+","+cID+","+membership+","+amount_spent);


            }
        }
    }




    private List<Customer> readCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(CUSTOMER_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(DELIMITER);
                    Customer customer = new Customer();
                    customer.username = parts[0];
                    customer.password = parts[1];
                    customer.full_name = parts[2];
                    customer.phone = parts[3];
                    customer.email = parts[4];
                    customer.address = parts[5];
                    customers.add(customer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    private static void writeCustomersToFile(List<Customer> customers) {
        File file = new File(CUSTOMER_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Customer customer : customers) {
                writer.write(customer.username + DELIMITER + customer.password + DELIMITER + customer.full_name + DELIMITER + customer.phone + DELIMITER + customer.email + DELIMITER + customer.address);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //View Membership status
    public void viewMembershipStatus(String User) throws IOException {
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            if (line.startsWith(User + ",")){
                String result = line;
                System.out.println("Membership tier:");
                String[] parts = result.split(",");
                    String user = parts[0];
                    String pass = parts[1];
                    String full_name = parts [2];
                    String email = parts[3];
                    String phone = parts[4];
                    String address = parts[5];
                    String cID = parts[6];
                    String membership = parts[7];

                    System.out.println(user+ "| " +membership);

                break;
            }
        }
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
