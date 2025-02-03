package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}



	public void processContract(Contract contract, int months) {
		
		double basicQuota = contract.getTotalValue() / months; // valor total dividido por meses
		
		for (int i = 1; i<=months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);//pega a data e adiciona um mes depois, o i é responsavel pela contagem dos meses
			
			double interest = onlinePaymentService.interest(basicQuota, i); // juros 
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);//taxa
			double quota = basicQuota + interest + fee; //valor total que seria valor+juros+taxa
			
			contract.getInstallments().add(new Installment(dueDate, quota));// add a lista a data e o  valorTotal
			
		}
	}
	
}
