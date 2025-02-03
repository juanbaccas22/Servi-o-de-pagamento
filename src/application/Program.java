package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // formatar em data padrão

		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt); // ler a data
		System.out.print("Valor do contrato: ");
		double totalvalue = sc.nextDouble();

		Contract obj = new Contract(number, date, totalvalue);

		System.out.print("Entre com o numero de parcelas:");
		int n = sc.nextInt();

		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(obj, n);

		System.out.println("Parcelas:");
		for (Installment installment : obj.getInstallments()) { //para cada obj na lista
			System.out.println(installment);
		}

		sc.close();
	}
}
