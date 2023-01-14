
import javax.sound.sampled.Line;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String username;
    private String password;
    private String full_name;
    private String phone;
    private String email;
    private String address;




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



    //List all the things the users can do and let the user choose what to do
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
                UpdateInfoCustomer();
            case 3:
                viewMembershipStatus();
            case 6:
                CreateOrder();
            case 7:
                TrackOrder();
            case 8:
                viewOrderHistory();
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
            String pass = input.substring(input.indexOf(' ')+1, input.length());


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
        int id;
        Random rand = new Random();
        id = rand.nextInt(999999);

        String CustomerID = Integer.toString(id);



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
            PrintWriter pw = new PrintWriter(new FileOutputStream("Customer_Info.txt",true));
            pw.println(username + "," + password + "," + full_name + ","  + email + "," + phone + "," + address + ","+ CustomerID + ",member,0");
            pw.flush();
            pw.close();


            PrintWriter pw1 = new PrintWriter(new FileOutputStream("Member_Login_Info.txt",true));
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



    public void UpdateInfoCustomer() throws IOException {
        // Extract and Update the String
        //Extract the Line and Ask the User which line to edit
        Scanner scan = new Scanner(System.in);
        String User = getUsername();
        //Extract the line the program want to edit
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";
        String changed_acc="";
        String new_credentials = "";
        String LineToDelete = "";


        while ((line = br.readLine()) != null){
            if (line.startsWith(User + ",")){
                String result = line;
                LineToDelete = line;
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

                System.out.println("Do you want to change full name (blank to keep old value | Y to change new pass): ");
                String ans1 = scan.nextLine();
                if (!ans1.isEmpty()){
                    System.out.println("Enter your new full name");
                    full_name = scan.nextLine();
                }

                System.out.println("Do you want to change email address (blank to keep old value | Y to change new pass): ");
                String ans2 = scan.nextLine();
                if (!ans2.isEmpty()){
                    System.out.println("Enter your new email address");
                    email = scan.nextLine();
                }

                System.out.println("Do you want to change phone number (blank to keep old value | Y to change new pass): ");
                String ans3 = scan.nextLine();
                if (!ans3.isEmpty()){
                    System.out.println("Enter your new phone number");
                    phone = scan.nextLine();
                }

                System.out.println("Do you want to change home address (blank to keep old value | Y to change new pass): ");
                String ans4 = scan.nextLine();
                if (!ans4.isEmpty()){
                    System.out.println("Enter your new phone number");
                    address = scan.nextLine();
                }

                new_credentials = user +" "+pass;
                changed_acc = user +","+pass+","+full_name+","+email+","+phone+","+address+","+cID+","+membership+","+amount_spent;
                System.out.println("Previous Info: "+ LineToDelete);
                System.out.println("Here's your updated information:");
                System.out.println("user | pass | full name | email | phone | address | CustomerID | Membership | Amount Spent");
                System.out.println(changed_acc);
                break;
            }
        }

        //Create new temp file with all the content but deleted line. Then delete the Original file. Rename temp file to Original file name
        deleteLine(LineToDelete);

        //Write the new credentials to the Original file
        PrintWriter pw = new PrintWriter(new FileOutputStream("Customer_Info.txt",true));
        pw.println(changed_acc);
        pw.flush();
        pw.close();

        PrintWriter pw1 = new PrintWriter(new FileOutputStream("Member_Login_Info.txt",true));
        pw1.println(new_credentials);
        pw1.flush();
        pw1.close();


    }

    //Function to find and delete a record to edit a new one in.
    //Bug: The problem do create a new file without the "Line that needs to be deleted"
    //     but it does not delete the Original File. So the new User Info is added on top of the old one

    public void deleteLine(String LineToDelete) throws IOException {


        File inputFile = new File("Customer_Info.txt");
        File tempFile = new File("temp_file_Customer.txt");

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

    //View Membership status
    public void viewMembershipStatus() throws IOException {
        File originalFile = new File("Customer_Info.txt");
        String User = getUsername();
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line;

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

                    System.out.println(user+ "| " +membership);

                break;
            }
        }
    }


    //View membership status to apply discount
    public String getMembershipLevel() throws IOException {
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line;
        String membership ="";

        String User = getUsername();

        while ((line = br.readLine()) != null) {
            if (line.startsWith(User + ",")) {
                String[] parts = line.split(",");
                String user = parts[0];
                String pass = parts[1];
                String full_name = parts[2];
                String email = parts[3];
                String phone = parts[4];
                String address = parts[5];
                String cID = parts[6];
                membership = parts[7];
            }
        }
        return membership;
    }

    // Check for discount based on membership tier
    public double ApplyDiscount() throws IOException {
        double SILVER_DISCOUNT = 0.05;
        double GOLD_DISCOUNT = 0.1;
        double PLATINUM_DISCOUNT = 0.15;

        String membershipLevel = getMembershipLevel();

        if (membershipLevel.equals("Silver")) {
            return SILVER_DISCOUNT;
        } else if (membershipLevel.equals("Gold")) {
            return GOLD_DISCOUNT;
        } else if (membershipLevel.equals("Platinum")) {
            return PLATINUM_DISCOUNT;
        } else {
            return 0;
        }
    }

    public String DiscountAmount() throws IOException{
        String membershipLevel = getMembershipLevel();

        if (membershipLevel.equals("Silver")) {
            return "5%";
        } else if (membershipLevel.equals("Gold")) {
            return "10%";
        } else if (membershipLevel.equals("Platinum")) {
            return "15%";
        } else {
            return "0";
        }
    }

    public void CreateOrder() throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String CurrentDate = dateFormat.format(date);

        int id;
        Random rand = new Random();
        id = rand.nextInt(999999);
        String OrderID= Integer.toString(id);

        System.out.println("Thank you ordering");
        System.out.println("Your ORDED Number is: ID" + OrderID);
        System.out.println("Your Discount for your Order is: "+ DiscountAmount());

        PrintWriter pw = new PrintWriter(new FileOutputStream("Order_Info.txt",true));
        pw.println(getCustomerID() + "," + getUsername()+ "," + CurrentDate+",Delivered");
        pw.flush();
        pw.close();


    }



    public void TrackOrder() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your orderID: ");
        int ID = scan.nextInt();

        String CustomerID = getUsername();
        String orderID = String.valueOf(ID);

        File originalFile = new File("Order_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            if (line.startsWith(CustomerID+ ","+ orderID)){
                System.out.println("CustomerID,OrderID,Date_Ordered,Order_Status");
                System.out.println(line);
                System.out.println();
            }
        }
    }

    public void viewOrderHistory() throws IOException {
        File originalFile = new File("Order_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            if (line.startsWith(getCustomerID())){
                System.out.println("CustomerID,OrderID,Date_Ordered,Order_Status");
                System.out.println(line);
                System.out.println();
            }
        }
    }


    public String getCustomerID() throws IOException {
        File originalFile = new File("Customer_Info.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));
        StringBuilder sb = new StringBuilder();
        String line ="";
        String cID ="";

        String User = getUsername();

        while ((line = br.readLine()) != null) {
            if (line.startsWith(User + ",")) {
                String result = line;
                String[] parts = result.split(",");
                String user = parts[0];
                String pass = parts[1];
                String full_name = parts[2];
                String email = parts[3];
                String phone = parts[4];
                String address = parts[5];
                cID = parts[6];
                String membership = parts[7];
            }
        }
        return cID;
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
