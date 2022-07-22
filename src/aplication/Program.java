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
		// Para colocar o separador de decimais do tipo de moeda de um pais 
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.print("How many employee will be registered? ");
		int N =sc.nextInt();
		
		// Dê preferência começar o for com i=o 
		for (int i=0; i<N; i++) {
			
			System.out.println();
			System.out.println("############## **Identity Employees** ################");
			System.out.println("Employee #" + (i + 1) + ": ");
			System.out.print("Id: ");
			// 1- O nextInt gero uma quebra de linha no Buffer de entrada padrão
			Integer id = sc.nextInt();
			
			// Para testar se o id já existe, 
			while (hasId(list, id)) {
				System.out.println("ID already taken! Try again: ");
				// se existe tente denovo
				id = sc.nextInt();
			}
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
		System.out.println();
		System.out.println("############### **Employees Incresed** ###############");
		System.out.println("Enter the employee id that will have salary increase: ");
		int idSalary = sc.nextInt(); 
		Integer pos = positionId(list, idSalary);
		
		// *****Função Lambda para encontar o id***** 
		// Employee emp = list.stream().filter(x -> x.getId() == idsalary).findFirst().orElse(null);
		// if (emp == null) {
		
		
		if (pos == null) {
			System.out.println();
			System.out.println("This id does not exist!");
		}
		else {
			System.out.print("Enter the porcentage: ");
			double percent = sc.nextDouble();
			// Pega acessar o funcionario que esta na posição (pos) 
			// Então incrementa o salario com a porcentage
			list.get(pos).increaseSalary(percent);
			// Usando o Lambda
			// emp.increaseSalary(percent);
		}
		
		System.out.println();
		System.out.println("############### **Result Final** ###############");
		System.out.println("List of employees: ");
		// for each serve para pegar cada empregado em emp, na List<Employee> list
		for (Employee emp : list) {
			// Imprima o emp
			System.out.println(emp);
			
			// Usando Lambda
			// for (Employee e : list) {
				// Imprima o e
				//System.out.println(e);
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
	
	public static boolean hasId(List<Employee> list, int id) {
		// Função Lambda 
		// Lógica: A função Lambda transforma sua lista num stream
		// 1- O stream é uma função do java que aceita funções de auta ordem(Lambda)
		// 2- O filter filtra da lista só aqueles elementos um predicado que 
		// for colocado(x ->(tal que) x.getId == id). ->(tal que)
		// O findFirst tenta buscar o primeiro e não existir
		// o orElse se não existir reorna nulo
		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null;
	}
}
