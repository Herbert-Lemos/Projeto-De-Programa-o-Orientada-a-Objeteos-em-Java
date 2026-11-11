import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    public String nome;
    public String email;
    public List<Item> itens;
    public int proximoIdItem;

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.itens = new ArrayList<>();
        this.proximoIdItem = 1;
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public void exibirCadastro() {
        System.out.println("Nome: " + this.nome);
        System.out.println("E-mail: " + this.email);
    }
}