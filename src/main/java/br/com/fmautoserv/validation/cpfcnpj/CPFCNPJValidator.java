package br.com.fmautoserv.validation.cpfcnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFCNPJValidator implements ConstraintValidator<CPFCNPJValido, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {

        if (valor == null) return false;

        String documento = valor.replaceAll("[^0-9]", "");

        if (documento.length() == 11) {
            return validarCPF(documento);
        }

        if (documento.length() == 14) {
            return validarCNPJ(documento);
        }

        return false;
    }

    private boolean validarCPF(String cpf) {

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++)
                soma += (cpf.charAt(i) - '0') * (10 - i);

            int resto = 11 - (soma % 11);
            char dig1 = (resto >= 10) ? '0' : (char) (resto + '0');

            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += (cpf.charAt(i) - '0') * (11 - i);

            resto = 11 - (soma % 11);
            char dig2 = (resto >= 10) ? '0' : (char) (resto + '0');

            return dig1 == cpf.charAt(9) && dig2 == cpf.charAt(10);

        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarCNPJ(String cnpj) {

        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] peso1 = {5,4,3,2,9,8,7,6,5,4,3,2};
            int[] peso2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

            int soma = 0;
            for (int i = 0; i < 12; i++)
                soma += (cnpj.charAt(i) - '0') * peso1[i];

            int resto = soma % 11;
            char dig1 = (resto < 2) ? '0' : (char)((11 - resto) + '0');

            soma = 0;
            for (int i = 0; i < 13; i++)
                soma += (cnpj.charAt(i) - '0') * peso2[i];

            resto = soma % 11;
            char dig2 = (resto < 2) ? '0' : (char)((11 - resto) + '0');

            return dig1 == cnpj.charAt(12) && dig2 == cnpj.charAt(13);

        } catch (Exception e) {
            return false;
        }
    }
}