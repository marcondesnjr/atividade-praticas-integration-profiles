package ifpb.pp;

/**
 *
 * @author Ricardo Job
 */
public interface Validador<T> {

    // Pode acontecer uma ValidadorException()
    public boolean validar(T t);
}
