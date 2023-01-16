package Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//Resources use grom Lecture and Turorial slide and Stackoverflow
public class Management {
    private List<Product> ListOfProduct;

    private BufferedWriter writer;
    private BufferedReader reader;
    private File ProductFile = new File("src/product.txt");


    public Management() {
        this.ListOfProduct = new ArrayList<>();
    }

    public int addProduct(Product p) {
        this.ListOfProduct.add(p);
        return count();
    }

    public static void RemoveProduct(String filepath, String deleteLine) {
        String tempFile = "temp.txt";
        File oldFile = new File("src/product.txt");
        File newFile = new File(tempFile);
        int line = 0;
        String currentLine;
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                line++;
                if (!currentLine.startsWith(deleteLine)) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

        } catch (Exception e) {

        }
    }

    public int count() {
        return this.ListOfProduct.size();
    }

    public Product getProduct(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }
        return this.ListOfProduct.get(index);
    }

    public Product getProductIndex(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }
        return this.ListOfProduct.get(index);

    }


    public void updateToProductFile(int id, String name, double price, String category) {
        try {
            writer = new BufferedWriter(new FileWriter(ProductFile, true));
            writer.write(id + "," + name + "," + price + "," + category );
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

