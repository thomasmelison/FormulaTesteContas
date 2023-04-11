import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ContaMagicaTest {

    public ContaMagica conta = null;

    @BeforeEach
    public void setup(){
        conta = new ContaMagica("100445-14", "Thomas Mundstock");
    }

    @org.junit.jupiter.api.Test
    void getNumeroConta() {
    }

    // VERIFICAÇÃO DO SALDO DA CONTA
    @ParameterizedTest
    @CsvSource({
            "89, true",
            "90, true",
            "100, false"
    })
    public void verificacaoSaldo(int valor_retirada, boolean esperado){
        conta.deposito(90);

        Assertions.assertEquals(conta.retirada(valor_retirada), esperado);
    }

    // VERIFICAÇÃO DA CATEGORIA PLATINA
    @ParameterizedTest
    @CsvSource({
            "50000, 50000, 99000, GOLD",
            "50000, 50000, 100000, PLATINUM",
            "50000, 50000, 300000, PLATINUM"
    })
    public void verificaCategoriaPlatina(int valor_a, int valor_b, int valor_c, String nivel_conta){
        conta.deposito(valor_a);
        conta.deposito(valor_b);
        conta.deposito(valor_c);
        System.out.println("saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    // VERIFICAÇÃO DA CATEGORIA GOLD
    @ParameterizedTest
    @CsvSource({
            "200, 1000, 0, SILVER",
            "1000, 20000, 29000, GOLD",
            "1000, 50000, 148000, GOLD"
    })
    public void verificaCategoriaGold(int valor_a, int valor_b, int valor_c, String nivel_conta){
        conta.deposito(valor_a);
        conta.deposito(valor_b);
        conta.deposito(valor_c);
        System.out.println("saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    // VERIFICAÇÃO DA CATEGORIA SILVER
    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, SILVER",
            "1000, 20000, 28000, SILVER",
            "1000, 50000, 0, GOLD"
    })
    public void verificaCategoriaSilver(int valor_a, int valor_b, int valor_c, String nivel_conta){
        conta.deposito(valor_a);
        conta.deposito(valor_b);
        conta.deposito(valor_c);
        System.out.println("saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());

    }

    // VERIFICAÇÃO DA VALORIZAÇÃO DOS VALORES DE DEPÓSITO - PLATINUM
    @Test
    public void verificaValorizacaoPlatinum(){
        conta.deposito(50000);
        conta.deposito(200000);
        conta.deposito(100);
        conta.deposito(100);

        Assertions.assertEquals(250203.5, conta.getSaldo());
    }

    // VERIFICAÇÃO DA VALORIZAÇÃO DOS VALORES DE DEPÓSITO - GOLD
    @Test
    public void verificaValorizacaoGold(){
        conta.deposito(40000);
        conta.deposito(10000);
        conta.deposito(1000);

        Assertions.assertEquals(51010, conta.getSaldo());
    }

    // VERIFICA O ESTADO DA CONTA APÓS UM SAQUE FEITO EM UMA CONTA PLATINUM
    @ParameterizedTest
    @CsvSource({
            "50000, 200000, 50000, 100000, PLATINUM",
            "50000, 200000, 100000, 300000, PLATINUM"
    })
    public void verificaEstadoSaquePlatinum(int deposito_a, int deposito_b, int deposito_c, int retirada, String nivel_conta){
        conta.deposito(deposito_a);
        conta.deposito(deposito_b);
        conta.deposito(deposito_c);
        conta.retirada(retirada);
        System.out.println("Saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    // VERIFICA O ESTADO DA CONTA APÓS UM SAQUE FEITO EM UMA CONTA GOLD
    @ParameterizedTest
    @CsvSource({
            "50000, 200000, 1000, 52000, GOLD",
            "50000, 10000, 1000, 10000, GOLD"
    })
    public void verificaEstadoSaqueGold(int deposito_a, int deposito_b, int deposito_c, int retirada, String nivel_conta){
        conta.deposito(deposito_a);
        conta.deposito(deposito_b);
        conta.deposito(deposito_c);
        conta.retirada(retirada);
        System.out.println("Saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    // VERIFICA O ESTADO DA CONTA APÓS UM SAQUE FEITO EM UMA CONTA SILVER
    @ParameterizedTest
    @CsvSource({
            "0, 0, 100, 100, 0, SILVER",
            "50000, 10000, 0, 30000, 0, SILVER",
            "50000, 200000, 0, 220000, 10, SILVER"
    })
    public void verificaEstadoSaqueSilver(int deposito_a, int deposito_b, int deposito_c, int retirada_a, int retirada_b, String nivel_conta){
        conta.deposito(deposito_a);
        conta.deposito(deposito_b);
        conta.deposito(deposito_c);
        conta.retirada(retirada_a);
        conta.retirada(retirada_b);
        System.out.println("Saldo: " + conta.getSaldo());

        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    @ParameterizedTest
    @CsvSource({
            "50000, 10000, GOLD",
            "500, 100, SILVER"
    })
    public void verificaTransicaoSilverParaGold(int deposito_a, int deposito_b, String nivel_conta){
        conta.deposito(deposito_a);
        conta.deposito(deposito_b);
        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }

    @ParameterizedTest
    @CsvSource({
            "50000, 200000, 10000, PLATINUM",
            "50000, 10000, 10000, GOLD"
    })
    public void verificaTransicaoGoldParaPlatinum(int deposito_a, int deposito_b, int deposito_c, String nivel_conta){
        conta.deposito(deposito_a);
        conta.deposito(deposito_b);
        conta.deposito(deposito_c);
        Assertions.assertEquals(Categoria.valueOf(nivel_conta), conta.getCategoria());
    }
}

