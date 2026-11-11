public class Item {
    public int id;
    public String nome;
    public double valor;
    public int quantidade;
    public boolean comprado;

    public Item(int id, String nome, double valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.comprado = false;
    }

    public double getSubtotal() {
        return valor * quantidade;
    }

    public void exibirItem() {
        String status = comprado ? "[X]" : "[ ]";
        System.out.println(id + ". " + status + " " + nome + " - Qtd: " + quantidade + " - R$ " + valor + " (Subtotal: R$ " + getSubtotal() + ")");
    }
}