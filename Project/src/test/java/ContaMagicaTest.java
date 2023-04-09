import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaMagicaTest {

    @org.junit.jupiter.api.Test
    void getNumeroConta() {
    }


    @Test
    public void verificacaoSaldo(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mundstock");
        conta.deposito(90);

        Assertions.assertEquals(conta.retirada(100), false);
    }

    @Test
    public void verificaPlatina(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mundstock");
        conta.deposito(50000);
        conta.deposito(1000);
        conta.deposito(20000000);

        Assertions.assertEquals(Categoria.PLATINUM, conta.getCategoria());
    }

    @Test
    public void verificaValorizacaoPlatinum(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mundstock");
        conta.deposito(50000);
        conta.deposito(200000);
        conta.deposito(100);
        conta.deposito(100);

        Assertions.assertEquals(250203.5, conta.getSaldo());
    }

    @Test
    public void verificaGold(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mello");
        conta.deposito(40000);
        conta.deposito(10000);
        conta.deposito(1000);


        Assertions.assertEquals(Categoria.GOLD, conta.getCategoria());
    }
    @Test
    public void verificaValorizacaoGold(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mello");
        conta.deposito(40000);
        conta.deposito(10000);
        conta.deposito(1000);

        Assertions.assertEquals(51010, conta.getSaldo());
    }

    @Test
    public void verificaEstadoSaqueGold(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mello");
        conta.deposito(40000);
        conta.deposito(10000);
        conta.deposito(1000);
        conta.retirada(500);

        Assertions.assertEquals(Categoria.GOLD, conta.getCategoria());
    }
    @Test
    public void verificaSaqueGold(){
        ContaMagica conta = new ContaMagica("100445-14", "Thomas Mello");
        conta.deposito(40000);
        conta.deposito(10000);
        conta.deposito(1000);
        conta.retirada(500);

        Assertions.assertEquals(50510, conta.getSaldo());
    }

    @Test
    public void verificaDepositoSilver(){
        // Cria conta
        ContaMagica conta = new ContaMagica("100445-14", "Thales Veiga");
        // Deposita valor da conta gold
        conta.deposito(100);
        conta.deposito(200);
        conta.deposito(300);
        conta.deposito(400);

        Assertions.assertEquals(1000, conta.getSaldo());

    }

    @Test
    public void verificaSaqueSilver(){
        ContaMagica conta = new ContaMagica("100445-14", "Thales Veiga");

        conta.deposito(500);
        conta.retirada(400);

        Assertions.assertEquals(100, conta.getSaldo());
    }

    @Test
    public void verificaEstadoDepositoSilver(){
        // Cria conta
        ContaMagica conta = new ContaMagica("100445-14", "Thales Veiga");
        // Deposita valor da conta gold
        conta.deposito(100);
        conta.deposito(200);
        conta.deposito(300);
        conta.deposito(400);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());

    }

    @Test
    public void verificaEstadoSaqueSilver(){
        ContaMagica conta = new ContaMagica("100445-14", "Thales Veiga");

        conta.deposito(500);
        conta.retirada(400);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());
    }
}

