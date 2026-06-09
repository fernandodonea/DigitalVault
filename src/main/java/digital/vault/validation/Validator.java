package digital.vault.validation;

public interface Validator<T>
{
    void validate(T value) throws ValidationException;
}
