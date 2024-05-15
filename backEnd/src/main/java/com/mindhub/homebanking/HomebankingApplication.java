package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {SpringApplication.run(HomebankingApplication.class, args);}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoansRepository loansRepository, ClientLoansRepository clientLoansRepository, CardRepository cardRepository) {
		return (args) -> {
			Client client1 = new Client("John", "Doe", "jhondoe13@hotmail.com");
			Client client2 = new Client("Melba", "Morel", "melmorel@hotmail.com");

			LocalDate ayer = LocalDate.now().minusDays(1);
			LocalDate hoy = LocalDate.now();
			Account account1 = new Account("VIN001", ayer, 5000);
			Account account2 = new Account("VIN002", hoy, 7500);
			Account account3 = new Account("JHD001", hoy, 10000);

			Loans loan1 = new Loans(Set.of(6, 12, 18, 24, 36),"Personal Loan", 100000);
			Loans loan2 = new Loans(Set.of(6, 10, 24),"Car Loan", 500000);
			Loans loan3 = new Loans(Set.of(12, 24, 36),"Home Loan", 1000000);

			Transaction transaction1 = new Transaction("For the pizza party!", 5000.0,LocalDateTime.now(), TransactionType.DEBIT);
			Transaction transaction2 = new Transaction("We are going for trip!", -13000.0,LocalDateTime.now(), TransactionType.CREDIT);

			loansRepository.save(loan1);
			loansRepository.save(loan2);
			loansRepository.save(loan3);

			ClientLoans clientLoans1 = new ClientLoans(12,500000 );
			ClientLoans clientLoans2 = new ClientLoans(24,1000000 );
			ClientLoans clientLoans3 = new ClientLoans(36,1500000 );

			Card card1 = new Card(CardColor.TITANIUM, CardType.DEBIT, "4212-9050-5040-1898", LocalDate.now(), LocalDate.now().plusYears(5), 898);
			Card card2 = new Card(CardColor.SILVER, CardType.CREDIT, "4212-9050-5040-1899", LocalDate.now().plusDays(1), LocalDate.now().plusYears(5), 899);
			Card card3 = new Card(CardColor.GOLD, CardType.DEBIT, "4212-9050-5040-1900", LocalDate.now(), LocalDate.now().plusYears(5), 900);

			client2.addCard(card1);
			client2.addCard(card2);
			client1.addCard(card3);

			loan1.addClientLoans(clientLoans1);
			client1.addClientLoans(clientLoans1);

			loan2.addClientLoans(clientLoans2);
			client1.addClientLoans(clientLoans2);

			loan3.addClientLoans(clientLoans3);
			client2.addClientLoans(clientLoans3);

			account3.addTransaction(transaction1);
			account2.addTransaction(transaction2);

			client2.addAccount(account1);
			client2.addAccount(account2);
			client1.addAccount(account3);

			clientRepository.save(client1);
			clientRepository.save(client2);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			clientLoansRepository.save(clientLoans1);
			clientLoansRepository.save(clientLoans2);
			clientLoansRepository.save(clientLoans3);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

			System.out.println(client1);
			System.out.println(client2);
			System.out.println(loan1);
		};
	}
}