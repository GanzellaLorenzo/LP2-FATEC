package repository;
import java.util.ArrayList;

public interface CRUD<T> {
    public boolean incluir(T entidade) throws Exception;
 
    public boolean editar(T entidade) throws Exception;
 
    public boolean excluir(int id) throws Exception;
 
    public T obter(int id) throws Exception;
 
    public ArrayList<T> listar() throws Exception;
 
    public ArrayList<T> pesquisar(String pesquisa) throws Exception;
}
