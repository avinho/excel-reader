package avinho;

import java.util.Objects;

public class Seguradora {
    private String nome;
    private Integer quantidade = 0;
    private double saldo = 0;

    public Seguradora() {
    }

    public Seguradora(String nome) {
        this.nome = nome;
    }

    public Seguradora(String nome, Integer quantidade, Double saldo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.saldo = saldo;
    }

    public void addSaldo(Double valor) {
        this.saldo += valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void addQuantidade() {
        this.quantidade++;
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
        Seguradora that = (Seguradora) o;
        return Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getQuantidade(), getSaldo());
    }

    @Override
    public String toString() {
        return "Seguradora{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", saldo=" + saldo +
                '}';
    }
}
