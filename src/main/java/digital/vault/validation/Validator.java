package digital.vault.validation;

import digital.vault.exception.ValidationException;

public interface Validator<T>
{
    void validate(T value) throws ValidationException;
}
