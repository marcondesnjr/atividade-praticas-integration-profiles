package ifpb.pp;

import java.util.List;

/**
 *
 * @author Ricardo Job
 * @param <K>
 * @param <T>
 */
public interface Repositorio<K extends Number, T> {

    public boolean salvar(T t);

    public boolean remover(T t);

    public boolean remover(K key);

    public T localizar(K key);

    public List<T> todos();
}
