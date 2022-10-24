package br.tiagopedroso;

public class Main {

    public static void main(String[] args) {
        var banco = new Banco("Banco Brasileiro", 574);

        Cliente pedro = new Cliente("Pedro");
        Cliente tiago = new Cliente("Tiago");
        Cliente joao = new Cliente("João");

        var ccPedro = new ContaCorrente(banco, pedro);
        ccPedro.ativar();
        ccPedro.ativarLimite(50);

        var ccTiago = new ContaCorrente(banco, tiago);
        ccTiago.ativar();

        var cpJoao = new ContaPoupanca(banco, joao);

        ccPedro.depositar(100);
        ccPedro.transferir(150, ccTiago);
        ccTiago.transferir(50, cpJoao);
        ccTiago.sacar(100);

        ccPedro.imprimirExtrato();
        ccTiago.imprimirExtrato();
        cpJoao.imprimirExtrato();
    }
}