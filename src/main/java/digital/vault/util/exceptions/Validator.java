package digital.vault.util.exceptions;

public interface Validator<T>
{
    void validate(T value) throws ValidationException;
}
