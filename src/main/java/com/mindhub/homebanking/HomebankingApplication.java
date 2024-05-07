package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {SpringApplication.run(HomebankingApplication.class, args);}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
		return (args) -> {
			Client client1 = new Client("John", "Doe", "jhondoe13@hotmail.com");
			Client client2 = new Client("Melba", "Morel", "melmorel@hotmail.com");
			LocalDate ayer = LocalDate.now().minusDays(1);
			LocalDate hoy = LocalDate.now();
			Account account1 = new Account("VIN001", ayer, 5000);
			Account account2 = new Account("VIN002", hoy, 7500);
			Account account3 = new Account("JHD001", hoy, 10000);
			Transaction transaction1 = new Transaction("For the pizza party!", 5000.0,LocalDateTime.now(), TransactionType.DEBIT);
			Transaction transaction2 = new Transaction("We are going for trip!", -13000.0,LocalDateTime.now(), TransactionType.CREDIT);
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
			System.out.println(client1);
			System.out.println(client2);
		};
	}
}