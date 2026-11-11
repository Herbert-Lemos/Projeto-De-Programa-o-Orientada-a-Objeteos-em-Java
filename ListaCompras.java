import java.util.List;

public class ListaCompras implements Interface<Item> {
    private Pessoa usuario;

    public ListaCompras(Pessoa usuario) {
        this.usuario = usuario;
    }

    @Override
    public void cadastrar(Item item) {
        item.id = usuario.proximoIdItem++;
        usuario.itens.add(item);
        System.out.println("Item cadastrado com sucesso!");
    }

    @Override
    public List<Item> listar() {
        return usuario.itens;
    }

    @Override
    public void editar(int id, double novoValor, int novaQuantidade) {
        for (Item item : usuario.itens) {
            if (item.id == id) {
                item.valor = novoValor;
                item.quantidade = novaQuantidade;
                System.out.println("Item editado com sucesso!");
                return;
            }
        }
        System.out.println("Item nao encontrado!");
    }

    @Override
    public void excluir(int id) {
        for (int i = 0; i < usuario.itens.size(); i++) {
            if (usuario.itens.get(i).id == id) {
                usuario.itens.remove(i);
                System.out.println("Item removido com sucesso!");
                return;
            }
        }
        System.out.println("Item nao encontrado!");
    }

    public void marcarComprado(int id) {
        for (Item item : usuario.itens) {
            if (item.id == id) {
                item.comprado = !item.comprado;
                System.out.println("Status do item alterado!");
                return;
            }
        }
        System.out.println("Item nao encontrado!");
    }

    public void limparLista() {
        usuario.itens.clear();
        usuario.proximoIdItem = 1;
        System.out.println("Lista limpa com sucesso!");
    }

    public void exibirResumoFinanceiro(int parcelas) {
        double totalGeral = 0;
        double totalPendente = 0;

        for (Item item : usuario.itens) {
            double subtotal = item.getSubtotal();
            totalGeral += subtotal;
            if (!item.comprado) {
                totalPendente += subtotal;
            }
        }

        System.out.println("\n--- RESUMO FINANCEIRO ---");
        System.out.println("Valor Total dos Itens: R$ " + totalGeral);
        System.out.println("Valor Restante (Nao Comprado): R$ " + totalPendente);
        
        if (parcelas > 1) {
            System.out.println("Parcelamento do Restante em " + parcelas + "x: R$ " + (totalPendente / parcelas));
        }
    }
}