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

        Assertions.assertEquals("PLATINUM", conta.getCategoria());
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



}