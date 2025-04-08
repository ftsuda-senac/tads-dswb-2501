package br.senac.tads.dsw.exemplo.rest.validation;

import br.senac.tads.dsw.exemplo.rest.DadosDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhasIguaisValidator implements
        ConstraintValidator<SenhasIguais, DadosDto> {

    @Override
    public boolean isValid(DadosDto dados, ConstraintValidatorContext context) {
        return dados.getSenha().equals(dados.getSenhaConfirmacao());
    }
    
}
