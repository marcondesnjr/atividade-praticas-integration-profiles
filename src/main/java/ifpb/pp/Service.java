package ifpb.pp;

import java.util.List;

/**
 *
 * @author Ricardo Job
 */
public interface Service<K, T> {

    /*Pode acontecer uma ValidadorException()
     Pode acontecer uma ServiceException() */
    public boolean salvar(T t);

    public boolean remover(T t);

    public T localizar(K key);

    public List<T> todos();
}
