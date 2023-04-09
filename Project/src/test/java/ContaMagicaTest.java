import static org.junit.jupiter.api.Assertions.*;

class ContaMagicaTest {

    @org.junit.jupiter.api.Test
    void getNumeroConta() {
    }

    @Test
    void test_silver_to_gold() {
        ContaMagica contaMagica = new ContaMagica("102030-40","Filipe Serafini");
        contaMagica.deposita(45000);
        Assertations.assertEquals(Categoria.SILVER, contaMagica.getCategoria());
        contaMagica.deposita(6000);
        Assertations.assertEquals(Categoria.GOLD, contaMagica.getCatergoria());
    }


}