import java.util.List;

public interface Interface<T> {
    void cadastrar(T objeto);
    List<T> listar();
    void editar(int id, double novoValor, int novaQuantidade);
    void excluir(int id);
}