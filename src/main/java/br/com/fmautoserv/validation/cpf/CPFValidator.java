package br.com.fmautoserv.validation.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValido, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {

        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++)
                soma += (cpf.charAt(i) - '0') * (10 - i);

            int resto = 11 - (soma % 11);
            char dig1 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += (cpf.charAt(i) - '0') * (11 - i);

            resto = 11 - (soma % 11);
            char dig2 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            return dig1 == cpf.charAt(9) && dig2 == cpf.charAt(10);

        } catch (Exception e) {
            return false;
        }
    }
}
