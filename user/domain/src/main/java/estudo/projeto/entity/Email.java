package estudo.projeto.entity;

import estudo.projeto.exception.EmailInvalidoException;

public record Email(String valor) {

    public Email {
        if (valor == null || !valor.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$")) {
            throw new EmailInvalidoException();
        }
    }

    public String obterDominio() {
        return this.valor.substring(this.valor.indexOf("@") + 1);
    }

    public boolean isDaMesmaEmpresa(Email outroEmail) {
        return this.obterDominio().equalsIgnoreCase(outroEmail.obterDominio());
    }
}
