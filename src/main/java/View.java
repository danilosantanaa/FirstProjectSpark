import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class View {

    public String index() {
        return this.lerConteudoHtml("view/index.html");
    }

    public String response() {
        return this.lerConteudoHtml("view/resultado.html");
    }

    private String lerConteudoHtml(String page) {
        String manual = "";
        try {
            File file = new File(new File("").getCanonicalFile() + "\\" + page);
            Scanner sc = new Scanner(file);
            String st;
            while(sc.hasNextLine()) {
                manual += new String(sc.nextLine().getBytes(), "UTF-8");
            }
        } catch (FileNotFoundException e) {
            manual = "<<< ERRO ao tentar carregar o arquivo. Arquivo não encontrado!";
            System.err.println(manual);
        } catch (IOException e) {
            manual = e.getMessage();
            e.printStackTrace();
        }

        return manual;
    }
}
