package Main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int option = 0;
		System.out.println("Welcome to the banking app!");
		input.nextLine();
		String mydecision = "n";

		System.out.println("Choose your options:");
		System.out.println("1)Login as superuser \n2)Login account \n3)Creat account \n4)Exit");
		option = input.nextInt();

		switch (option) {
		case 1:
			SuperUser user = new SuperUser();
			user.login();

			break;

		case 2:
			RegularUser reg = new RegularUser();
			reg.login();

			break;

		case 3:
			SuperUser sup = new SuperUser();
			User user1 = new User();
			sup.creatBankAccount(user1);

			break;

		case 4:

			break;

		default:
			break;

		}

	}

}
