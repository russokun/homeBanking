//package com.mindhub.homebanking;
//
//import com.mindhub.homebanking.utils.AccountNumberGenerator;
//import com.mindhub.homebanking.utils.CardNumberGenerator;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class UtilsTest {
//    //Test para cardNumberGenerator
//    @Test
//    public void cardNumberGeneratorTest(){
//        String cardNumber = CardNumberGenerator.generate();
//        assertThat(cardNumber, matchesPattern("\\d{4}-\\d{4}-\\d{4}-\\d{4}"));
//    }
//
//    //Test para AccountNumberGenerator
//    @Test
//    public void accountNumberGeneratorTest(){
//        String accountNumber = AccountNumberGenerator.generate();
//        assertThat(accountNumber, matchesPattern("VIN-\\d{8}"));
//    }
//}
