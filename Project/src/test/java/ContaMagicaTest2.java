import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ContaMagicaTest2 {
    ContaMagica conta;

    @BeforeEach
    public void criaConta() {
        conta = new ContaMagica("100445-14", "Bob Test");
    }

    //============================================================================================
    // Testes gerais

    // Retorna false quando não deveria
    @Test
    public void verificaSaqueValido() {
        conta.deposito(1000);
        Boolean response = conta.retirada(100);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void verificaValorSaque() {
        conta.deposito(1000);
        conta.retirada(100);

        Assertions.assertEquals(900, conta.getSaldo());
    }

    @Test
    public void verificaSaqueMaiorQueSaldo() {
        conta.deposito(1000);
        Boolean response = conta.retirada(2000);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void verificaValorSaqueMaiorQueSaldo() {
        conta.deposito(1000);
        conta.retirada(2000);

        Assertions.assertEquals(1000, conta.getSaldo());
    }

    // Não permite que o saldo da conta fique igual a 0
    @Test
    public void verificaValorSaqueTodoDinheiro() {
        conta.deposito(1000);
        conta.retirada(1000);

        Assertions.assertEquals(0, conta.getSaldo());
    }

    //============================================================================================
    // Testes Silver

    @Test
    public void verificaEstadoDepositoSilver() {
        conta.deposito(1000);
        conta.deposito(2000);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());
    }

    @Test
    public void verificaValorDepositoSilver() {
        conta.deposito(1000);
        conta.deposito(2000);

        Assertions.assertEquals(3000, conta.getSaldo());
    }

    @Test
    public void verificaEstadoSaqueSilver() {
        conta.deposito(1000);
        conta.retirada(10);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());
    }

    @Test
    public void verificaValorSaqueSilver() {
        conta.deposito(1000);
        conta.retirada(10);

        Assertions.assertEquals(990, conta.getSaldo());
    }

    //============================================================================================
    // Testes Silver-Gold

    // Ao atingir o valor que deveria trocar a categoria para gold nada ocorre
    @Test
    public void verificaEstadoDepositoLimite() {
        conta.deposito(50000);

        Assertions.assertEquals(Categoria.GOLD, conta.getCategoria());
    }

    // Ao passar o valor que deveria subir a categoria para gold nada ocorre
    @Test
    public void verificaEstadoDepositoMaiorQueLimite() {
        conta.deposito(51000);

        Assertions.assertEquals(Categoria.GOLD, conta.getCategoria());
    }

    @Test
    public void verificaValorDepositoMaiorQueLimite() {
        conta.deposito(51000);

        Assertions.assertEquals(51000, conta.getSaldo());
    }

    // Ao atingir o valor que deverua descer a categoria para silver nada ocorre
    @Test
    public void verificaEstadoSaqueLimite() {
        conta.deposito(55000);
        //validar conta no gold
        conta.deposito(1);
        conta.retirada(5002);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());
    }

    // Ao passar o valor que deveria descer a categoria para silver nada ocorre
    @Test
    public void verificaEstadoSaqueMaiorQueLimite() {
        conta.deposito(55000);
        //validar conta no gold
        conta.deposito(1);
        conta.retirada(50000);

        Assertions.assertEquals(Categoria.SILVER, conta.getCategoria());
    }

    @Test
    public void verificaValorSaqueSaqueMaiorQueLimite() {
        conta.deposito(55000);
        //validar conta no gold
        conta.deposito(1);
        conta.retirada(50000);

        Assertions.assertEquals(5001, conta.getSaldo());
    }


}
