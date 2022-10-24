package br.tiagopedroso;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Banco banco, Cliente cliente) {
		super(banco, TipoConta.POUPANCA, cliente);
	}

}
