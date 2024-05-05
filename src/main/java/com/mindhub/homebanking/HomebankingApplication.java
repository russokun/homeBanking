package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {SpringApplication.run(HomebankingApplication.class, args);}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return (args) -> {
			Client client1 = new Client("John", "Doe", "jhondoe13@hotmail.com");
			Client client2 = new Client("Melba", "Morel", "melmorel@hotmail.com");
			Client client3 = new Client("Alice", "Smith", "aliceSmithh4@gmail.com");
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			LocalDate ayer = LocalDate.now().minusDays(1);
			LocalDate hoy = LocalDate.now();
			Account account1 = new Account("VIN001", ayer, 5000);
			Account account2 = new Account("VIN002", hoy, 7500);
			accountRepository.save(account1);
			accountRepository.save(account2);
			System.out.println(account1);
			System.out.println(account2);
		};
	}
}