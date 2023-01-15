package Product;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProductControl {
    Management pm = new Management();
    Scanner sc = new Scanner(System.in);

    public ProductControl() {
        this.pm = pm;
        this.sc = sc;
    }

    //Setting button
    private int ManagingProduct(){
        System.out.println("______________");
        System.out.println("1. AddProduct");
        System.out.println("2. RemoveProduct");
        System.out.println("3. ShowProduct");
        System.out.println("0. Exit");
        int choice = sc.nextInt();
        return choice;
    }
    //Read User input
    public int readInt(int min, int max){
        int choice;
        while(true){
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 0 && choice <= 3){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return choice;
    }
    //Setting the cases
    public void start() throws IOException {
        while(true){
            int choice = ManagingProduct();
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addProduct();
                    break;
                case 2:
                    RemoveProduct();
                    break;
                case 3:
                    showProduct();
                    break;
                default: throw new AssertionError();


            }
        }

    }
    //Making Add function too product
    public void addProduct(){
        System.out.println("Enter product ID: ");
        int id = sc.nextInt();
        System.out.println("Enter product name:");
        String name = sc.next();
        System.out.println("Enter product category: ");
        String category = sc.next();
        System.out.println("Enter product price: ");
        double price = sc.nextDouble();
        try(FileWriter fw = new FileWriter("src/product.txt", true);
            BufferedWriter bw1 = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw1))
        {
            out.println(id + "," + name + "," + price + "," + category);
            out.close();

            bw1.close();
            fw.close();
        } catch (IOException e) {
        }

        new Product(id, name, price, category);

    }

    //Making Remove function too product
    public void RemoveProduct() throws IOException {

        System.out.println("Enter Id of Product: ");
        String id = sc.next();
        pm.RemoveProduct("src/product.txt", id);
    }


    //Making Show function too product
    public void showProduct() throws IOException {
        Scanner sc = new Scanner(System.in);
        String line, name = null, category = null, price = null, id = null;
        Scanner scan = new Scanner(new File("src/product.txt"));
        System.out.println("****************");
        System.out.println("All Product");
        System.out.println("****************");
        System.out.println("ID\t Name\tPrice\tCategory");
        while (scan.hasNext()){
            line = scan.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            id = st.nextToken();
            name = st.nextToken();
            price = st.nextToken();
            category = st.nextToken();
            System.out.println(id + "\t" + name +  "\t" + price + "\t" + "\t"  + category);
        }

        scan.close();




    }



}

