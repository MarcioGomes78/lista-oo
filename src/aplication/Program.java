package aplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

/*Fazer um programa para ler um número inteiro N e depois os 
dados (id, nome e salario) de N funcionários. Não deve haver 
repetição de id. 
 
Em seguida, efetuar o aumento de X por cento no salário de 
um determinado funcionário. Para isso, o programa deve ler 
um id e o valor X. Se o id informado não existir, mostrar 
uma mensagem e abortar a operação. Ao final, mostrar a 
listagem atualizada dos funcionários, conforme exemplos.
 
Lembre-se de aplicar a técnica de encapsulamento para não 
permitir que o salário possa ser mudado livremente. Um 
salário só pode ser aumentado com base em uma operação 
de aumento por porcentagem dada.*/
public class Program {

	public static void main(String[] args) {
		//Para colocar o separador de decimais do tipo de moeda de um pais 
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.print("How many employee will be registered? ");
		int N =sc.nextInt();
		
		// Dê preferência começar o for com i=o 
		for (int i=0; i<N; i++) {
			
			System.out.println("***************************");
			System.out.println("Employee #" + (i + 1) + ": ");
			System.out.print("Id: ");
			// 1- O nextInt gero uma quebra de linha no Buffer de entrada padrão
			Integer id = sc.nextInt();
			
			System.out.print("Name: ");
			// 3- Serve consumir o enter na entrada do nextInt que ficou pendente na quebra de linha
			sc.nextLine();
			// 2- Se dessemos só este nextLine não iria ler o nome
			String name = sc.nextLine();
			
			System.out.print("Salary: ");
			Double salary = sc.nextDouble();
			
			Employee emp = new Employee(id, name, salary);
			
			// para adicionar um elemento na lista 
			// por padrão adiciona no fim da lista
			list.add(emp);
		}
		System.out.println("########################");
		System.out.println("Enter the employee id that will have salary increase: ");
		int idSalary = sc.nextInt(); 
		Integer pos = positionId(list, idSalary);
		if (pos == null) {
			System.out.println("This id does not exist!");
		}
		else {
			System.out.print("Enter the porcentage: ");
			double percent = sc.nextDouble();
			//Pega acessar o funcionario que esta na posição (pos) 
			// Então incrementa o salario com a porcentage
			list.get(pos).increaseSalary(percent);
		}
		
		sc.close();
		
	}

	public static Integer positionId(List<Employee> list, int id) {
		// size indica o tamanho da lista
		for (int i = 0; i < list.size(); i++) {
			// O get pega o elemento na posição
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
}
