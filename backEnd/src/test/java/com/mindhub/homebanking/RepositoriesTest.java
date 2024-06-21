//package com.mindhub.homebanking;
//import com.mindhub.homebanking.models.*;
//import com.mindhub.homebanking.repositories.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import com.mindhub.homebanking.configurations.WebConfig;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
//public class RepositoriesTest {
//  @Autowired
//  LoansRepository loanRepository;
//  @Autowired
//  ClientRepository clientRepository;
//  @Autowired
//  AccountRepository accountRepository;
//  @Autowired
//  CardRepository cardRepository;
//  @Autowired
//  TransactionRepository transactionRepository;
//  @Autowired
//  ClientLoansRepository clientLoansRepository;
//
////Test para loans(los de el plan de ataque)
//  @Test
//  public void existLoans(){
//
//    List<Loans> loans = loanRepository.findAll();
//
//    assertThat(loans,is(not(empty())));
//
//  }
//
//  @Test
//  public void existPersonalLoan(){
//
//    List<Loans> loans = loanRepository.findAll();
//
//    assertThat(loans, hasItem(hasProperty("name", is("Personal Loan"))));
//
//  }
////Test para client
//  @Test
//  public void existClients(){
//    List<Client> clients = clientRepository.findAll();
//    assertThat(clients, is(not(empty())));
//  }
//
//  @Test
//  public void existSpecificClient(){
//    Optional<Client> client = Optional.ofNullable(clientRepository.findByEmail("jhondoe13@hotmail.com"));
//    assertThat(client.isPresent(), is(true));
//  }
////Test para account
//  @Test
//  public void accountHasTransactions(){
//    Optional<Account> account = Optional.ofNullable(accountRepository.findByNumber("VIN002"));
//    assertThat(account.isPresent(), is(true));
//    assertThat(account.get().getTransactions(), is(not(empty())));
//  }
//
//  @Test
//  public void accountHasPositiveBalance(){
//    Optional<Account> account = Optional.ofNullable(accountRepository.findByNumber("VIN001"));
//    assertThat(account.isPresent(), is(true));
//    assertThat(account.get().getBalance(), is(greaterThan(0.0)));
//  }
////Test para card
//  @Test
//  public void cardIsActive(){
//    Optional<Card> card = cardRepository.findByNumber("4212-9050-5040-1898");
//    assertThat(card.isPresent(), is(true));
//    assertThat(card.get().getDueDate().isAfter(LocalDate.now()), is(true));
//  }
//
//  @Test
//  public void cardIsFromAuthClient(){
//    Optional<Card> card = cardRepository.findByNumber("4212-9050-5040-1898");
//    assertThat(card.isPresent(), is(true));
//    assertThat(card.get().getCardHolder(), is("Melba Morel"));
//  }
////Test para transaction
//  @Test
//  public void transactionIsDebit(){
//    Optional<Transaction> transaction = transactionRepository.findById(1L);
//    assertThat(transaction.isPresent(), is(true));
//    assertThat(transaction.get().getType(), is(TransactionType.DEBIT));
//  }
//
//  @Test
//  public void transactionHasPositiveAmount(){
//    Optional<Transaction> transaction = transactionRepository.findById(1L);
//    assertThat(transaction.isPresent(), is(true));
//    assertThat(transaction.get().getAmount(), is(greaterThan(0.0)));
//  }
////Test para ClientLoan
//  @Test
//  public void clientLoanHasAssociatedClient(){
//    Optional<ClientLoans> clientLoan = clientLoansRepository.findById(1L);
//    assertThat(clientLoan.isPresent(), is(true));
//    assertThat(clientLoan.get().getClient(), is(notNullValue()));
//  }
//
//  @Test
//  public void clientLoanHasPositiveAmount(){
//    Optional<ClientLoans> clientLoan = clientLoansRepository.findById(1L);
//    assertThat(clientLoan.isPresent(), is(true));
//    assertThat(clientLoan.get().getAmount(), is(greaterThan(0.0)));
//  }
//}