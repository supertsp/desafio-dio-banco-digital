package br.tiagopedroso;

import lombok.Getter;
import lombok.Setter;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 0;

    @Getter
    @Setter
    private TipoConta tipoConta;

    @Getter
    protected Banco banco;

    @Getter
    protected int agencia;

    @Getter
    protected int numero;

    @Getter
    protected double saldo;

    @Getter
    protected Cliente cliente;

    @Getter
    private boolean ativa;

    protected Conta(Banco banco, TipoConta tipoConta, Cliente cliente) {
        this.banco = banco;
        if (this.banco != null) {
            this.banco.incluirConta(this);
        }

        this.tipoConta = tipoConta;
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public static final Conta INEXISTENTE = new Conta(null, TipoConta.INEXISTENTE, null) {
        @Override
        public void imprimirExtrato() {
            System.out.println("Conta Inexistente");
        }
    };

    public void ativar() {
        ativa = true;
    }

    public void desativar() {
        ativa = false;
    }

    @Override
    public boolean sacar(double valor) {
        if (isAtiva() && saldo >= valor) {
            saldo -= valor;
            return true;
        }

        return false;
    }

    @Override
    public boolean depositar(double valor) {
        if (isAtiva()) {
            saldo += valor;
            return true;
        }

        return false;
    }

    @Override
    public boolean transferir(double valor, IConta contaDestino) {
        if (sacar(valor)) {
            contaDestino.depositar(valor);
            return true;
        }

        return false;
    }

    protected String getExtratoBase() {
        return String.format("""
                           === %s - %s ===
                           Ag.: %d - Num.: %d - Status: %s
                           Titular: %s	
                           Saldo: R$ %.2f
                        """,
                banco.getNome(),
                tipoConta,
                agencia,
                numero,
                ativa ? "ativa" : "desativa",
                cliente.getNome(),
                saldo
        );
    }

    @Override
    public void imprimirExtrato() {
        System.out.println(getExtratoBase());
    }
}
