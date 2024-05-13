package avinho;

import java.util.Objects;

public class Corretor {
    String nome;
    Double saldo;

    public Corretor() {
    }

    public Corretor(String nome, Double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public Corretor(String nomeCorretor) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corretor corretor = (Corretor) o;
        return Objects.equals(getNome(), corretor.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
