package Projeto;

import java.util.Scanner;

public class Main {

	// ==================================
	// MAIN
	// ==================================
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[][] clientes = new String[0][8];
		String[][] contatos = new String[0][5];

		menuPrincipal(sc, clientes, contatos);
	}

	// ==================================
	// MENU PRINCIPAL
	// ==================================
	public static void menuPrincipal(Scanner sc, String[][] clientes, String[][] contatos) {

		String opcao = "";

		while (!opcao.equals("9")) {

			System.out.println("\n=== MENU ===");
			System.out.println("1 - Clientes");
			System.out.println("2 - Contatos");
			System.out.println("3 - Relatorio");
			System.out.println("9 - Sair");
			System.out.println("============");

			System.out.print("Opcao: ");
			opcao = sc.nextLine();

			if (opcao.equals("1")) {

				clientes = menuClientes(sc, clientes, contatos);

			} else if (opcao.equals("2")) {

				contatos = menuContatos(sc, clientes, contatos);

			} else if (opcao.equals("3")) {

				exibirRelatorio(clientes, contatos);

			} else if (opcao.equals("9")) {

				System.out.println("Programa encerrado!");

			} else {

				System.out.println("Opcao invalida!");

			}
		}
	}

	// ==================================
	// MENU CLIENTES
	// ==================================
	public static String[][] menuClientes(Scanner sc, String[][] clientes, String[][] contatos) {

		String opcao = "";

		while (!opcao.equals("9")) {

			System.out.println("\n--- CLIENTES ---");
			System.out.println("1 - Incluir");
			System.out.println("2 - Listar");
			System.out.println("3 - Consultar");
			System.out.println("4 - Alterar");
			System.out.println("5 - Excluir");
			System.out.println("6 - Ordenar por nome");
			System.out.println("9 - Voltar");

			System.out.print("Opcao: ");
			opcao = sc.nextLine();

			if (opcao.equals("1")) {

				clientes = incluirCliente(sc, clientes);

			} else if (opcao.equals("2")) {

				listarClientes(clientes);

			} else if (opcao.equals("3")) {

				consultarCliente(sc, clientes);

			} else if (opcao.equals("4")) {

				alterarCliente(sc, clientes);

			} else if (opcao.equals("5")) {

				clientes = excluirCliente(sc, clientes, contatos);

			} else if (opcao.equals("6")) {

				clientes = ordenarClientesPorNome(clientes);
				System.out.println("\nCLIENTES ORDENADOS!\n");
				listarClientes(clientes);

			}
		}

		return clientes;
	}

	// ==================================
	// VALIDAR CPF
	// ==================================
	public static boolean validarCPF(String cpf) {

		if (cpf.length() != 11) {
			return false;
		}

		for (int i = 0; i < cpf.length(); i++) {

			char caractere = cpf.charAt(i);

			if (caractere < '0' || caractere > '9') {
				return false;
			}
		}

		return true;
	}

	// ==================================
	// INCLUIR CLIENTE
	// ==================================
	public static String[][] incluirCliente(Scanner sc, String[][] clientes) {

		clientes = aumentarClientes(clientes);

		int pos = clientes.length - 1;

		clientes[pos][0] = String.valueOf(clientes.length);

		System.out.print("Nome: ");
		clientes[pos][1] = sc.nextLine();

		String cpf;

		do {

			System.out.print("CPF: ");
			cpf = sc.nextLine();

			if (!validarCPF(cpf)) {
				System.out.println("CPF invalido! Digite 11 numeros.");
			}

		} while (!validarCPF(cpf));

		clientes[pos][2] = cpf;

		System.out.print("Nascimento: ");
		clientes[pos][3] = sc.nextLine();

		System.out.print("Sexo: ");
		clientes[pos][4] = sc.nextLine();

		System.out.print("Cidade: ");
		clientes[pos][5] = sc.nextLine();

		System.out.print("Estado: ");
		clientes[pos][6] = sc.nextLine();

		clientes[pos][7] = "ATIVO";

		System.out.println("Cliente cadastrado!");

		return clientes;
	}

	// ==================================
	// CONSULTAR CLIENTE
	// ==================================
	public static void consultarCliente(Scanner sc, String[][] clientes) {

		System.out.print("Codigo: ");
		String codigo = sc.nextLine();

		int pos = buscarCliente(clientes, codigo);

		if (pos != -1) {

			System.out.println("\n--- CLIENTE ENCONTRADO ---");
			System.out.println("Codigo:     " + clientes[pos][0]);
			System.out.println("Nome:       " + clientes[pos][1]);
			System.out.println("CPF:        " + clientes[pos][2]);
			System.out.println("Nascimento: " + clientes[pos][3]);
			System.out.println("Sexo:       " + clientes[pos][4]);
			System.out.println("Cidade:     " + clientes[pos][5]);
			System.out.println("Estado:     " + clientes[pos][6]);
			System.out.println("Status:     " + clientes[pos][7]);

		} else {

			System.out.println("Cliente nao encontrado!");

		}
	}

	// ==================================
	// ALTERAR CLIENTE
	// ==================================
	public static void alterarCliente(Scanner sc, String[][] clientes) {

		listarClientes(clientes);

		System.out.print("Codigo: ");
		String codigo = sc.nextLine();

		int pos = buscarCliente(clientes, codigo);

		if (pos != -1) {

			System.out.println("\nDADOS ATUAIS");
			System.out.println("Codigo: " + clientes[pos][0]);
			System.out.println("Nome: " + clientes[pos][1]);
			System.out.println("CPF: " + clientes[pos][2]);
			System.out.println("Nascimento: " + clientes[pos][3]);
			System.out.println("Sexo: " + clientes[pos][4]);
			System.out.println("Cidade: " + clientes[pos][5]);
			System.out.println("Estado: " + clientes[pos][6]);
			System.out.println("Status: " + clientes[pos][7]);

			System.out.println("\nNOVOS DADOS (deixe em branco para manter o valor atual)");

			System.out.print("Novo nome: ");
			String entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][1] = entrada;
			}

			System.out.print("Novo CPF: ");
			entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][2] = entrada;
			}

			System.out.print("Novo nascimento: ");
			entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][3] = entrada;
			}

			System.out.print("Novo sexo: ");
			entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][4] = entrada;
			}

			System.out.print("Nova cidade: ");
			entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][5] = entrada;
			}

			System.out.print("Novo estado: ");
			entrada = sc.nextLine();
			if (!entrada.equals("")) {
				clientes[pos][6] = entrada;
			}

			System.out.println("Cliente alterado!");

		} else {

			System.out.println("Cliente nao encontrado!");

		}
	}

	// ==================================
	// EXCLUIR CLIENTE
	// ==================================
	public static String[][] excluirCliente(Scanner sc, String[][] clientes, String[][] contatos) {

		listarClientes(clientes);

		System.out.print("Codigo: ");
		String codigo = sc.nextLine();

		int pos = buscarCliente(clientes, codigo);

		if (pos != -1) {

			System.out.println("\nCLIENTE ENCONTRADO");
			System.out.println("Codigo: " + clientes[pos][0]);
			System.out.println("Nome: " + clientes[pos][1]);
			System.out.println("CPF: " + clientes[pos][2]);
			System.out.println("Cidade: " + clientes[pos][5]);

			System.out.print("\nConfirma exclusao? S/N: ");
			String resp = sc.nextLine();

			if (resp.equals("S") || resp.equals("s")) {

				excluirContatosDoCliente(contatos, codigo);

				String[][] novo = new String[clientes.length - 1][8];

				int x = 0;

				for (int i = 0; i < clientes.length; i++) {

					if (i != pos) {

						for (int j = 0; j < 8; j++) {
							novo[x][j] = clientes[i][j];
						}

						x++;
					}
				}

				clientes = novo;

				System.out.println("Cliente e seus contatos removidos!");

			} else {

				System.out.println("Cancelado!");

			}

		} else {

			System.out.println("Cliente nao encontrado!");

		}

		return clientes;
	}

	// ==================================
	// ORDENAR CLIENTES POR NOME
	// ==================================
	public static String[][] ordenarClientesPorNome(String[][] clientes) {

		for (int i = 0; i < clientes.length - 1; i++) {

			for (int j = 0; j < clientes.length - 1; j++) {

				if (compararNomes(clientes[j][1], clientes[j + 1][1]) > 0) {

					String[] temp = clientes[j];
					clientes[j] = clientes[j + 1];
					clientes[j + 1] = temp;

				}
			}
		}

		return clientes;
	}

	// ==================================
	// MENU CONTATOS
	// ==================================
	public static String[][] menuContatos(Scanner sc, String[][] clientes, String[][] contatos) {

		String opcao = "";

		while (!opcao.equals("9")) {

			System.out.println("\n--- CONTATOS ---");
			System.out.println("1 - Incluir");
			System.out.println("2 - Listar");
			System.out.println("3 - Alterar");
			System.out.println("4 - Excluir");
			System.out.println("5 - Ordenar por nome do cliente");
			System.out.println("9 - Voltar");
			System.out.println("----------------");

			System.out.print("Opcao: ");
			opcao = sc.nextLine();

			if (opcao.equals("1")) {

				contatos = incluirContato(sc, clientes, contatos);

			} else if (opcao.equals("2")) {

				listarContatos(clientes, contatos);

			} else if (opcao.equals("3")) {

				alterarContato(sc, clientes, contatos);

			} else if (opcao.equals("4")) {

				contatos = excluirContato(sc, clientes, contatos);

			} else if (opcao.equals("5")) {

				contatos = ordenarContatosPorNome(clientes, contatos);
				System.out.println("\nCONTATOS ORDENADOS!\n");
				listarContatos(clientes, contatos);

			}
		}

		return contatos;
	}

	// ==================================
	// INCLUIR CONTATO
	// ==================================
	public static String[][] incluirContato(Scanner sc, String[][] clientes, String[][] contatos) {

		listarClientes(clientes);

		System.out.print("Codigo do cliente: ");
		String codCliente = sc.nextLine();

		int posCliente = buscarCliente(clientes, codCliente);

		if (posCliente != -1) {

			contatos = aumentarContatos(contatos);

			int pos = contatos.length - 1;

			contatos[pos][0] = String.valueOf(contatos.length);
			contatos[pos][1] = codCliente;

			System.out.print("Tipo de contato (Telefone, Celular ou E-mail): ");
			contatos[pos][2] = sc.nextLine();

			System.out.print(contatos[pos][2] + ": ");
			contatos[pos][3] = sc.nextLine();

			contatos[pos][4] = "ATIVO";

			System.out.println("Contato cadastrado!");

		} else {

			System.out.println("Cliente nao encontrado!");

		}

		return contatos;
	}

	// ==================================
	// ALTERAR CONTATO
	// ==================================
	public static void alterarContato(Scanner sc, String[][] clientes, String[][] contatos) {

		listarContatos(clientes, contatos);

		System.out.print("Codigo do contato: ");
		String codigo = sc.nextLine();

		int pos = buscarContato(contatos, codigo);

		if (pos != -1) {

			System.out.println("\nDADOS ATUAIS");
			System.out.println("Codigo: " + contatos[pos][0]);
			System.out.println(contatos[pos][2] + ": " + contatos[pos][3]);
			System.out.println("Status: " + contatos[pos][4]);

			System.out.println("\nDeixe em branco para manter o valor atual.");

			System.out.print("Novo " + contatos[pos][2] + ": ");
			String novoContato = sc.nextLine();

			if (!novoContato.equals("")) {
				contatos[pos][3] = novoContato;
			}

			contatos[pos][4] = "ATIVO";

			System.out.println("Contato alterado!");

		} else {

			System.out.println("Contato nao encontrado!");

		}
	}

	// ==================================
	// EXCLUIR CONTATO
	// ==================================
	public static String[][] excluirContato(Scanner sc, String[][] clientes, String[][] contatos) {

		listarContatos(clientes, contatos);

		System.out.print("Codigo do contato: ");
		String codigo = sc.nextLine();

		int pos = buscarContato(contatos, codigo);

		if (pos != -1) {

			System.out.println("\nCONTATO ENCONTRADO");
			System.out.println("Codigo: " + contatos[pos][0]);
			System.out.println("Tipo: " + contatos[pos][2]);
			System.out.println("Valor: " + contatos[pos][3]);

			System.out.print("\nConfirma exclusao? S/N: ");
			String resp = sc.nextLine();

			if (resp.equals("S") || resp.equals("s")) {

				String[][] novo = new String[contatos.length - 1][5];

				int x = 0;

				for (int i = 0; i < contatos.length; i++) {

					if (i != pos) {

						for (int j = 0; j < 5; j++) {
							novo[x][j] = contatos[i][j];
						}

						x++;
					}
				}

				contatos = novo;

				System.out.println("Contato removido!");

			} else {

				System.out.println("Cancelado!");

			}

		} else {

			System.out.println("Contato nao encontrado!");

		}

		return contatos;
	}

	// ==================================
	// EXCLUIR CONTATOS DE UM CLIENTE
	// ==================================
	public static void excluirContatosDoCliente(String[][] contatos, String codCliente) {

		for (int i = 0; i < contatos.length; i++) {

			if (contatos[i][1] != null && contatos[i][1].equals(codCliente)) {

				for (int j = 0; j < 5; j++) {
					contatos[i][j] = null;
				}
			}
		}
	}

	// ==================================
	// ORDENAR CONTATOS POR NOME DO CLIENTE
	// ==================================
	public static String[][] ordenarContatosPorNome(String[][] clientes, String[][] contatos) {

		for (int i = 0; i < contatos.length - 1; i++) {

			for (int j = 0; j < contatos.length - 1; j++) {

				int pos1 = buscarCliente(clientes, contatos[j][1]);
				int pos2 = buscarCliente(clientes, contatos[j + 1][1]);

				String nome1 = "";
				String nome2 = "";

				if (pos1 != -1) {
					nome1 = clientes[pos1][1];
				}

				if (pos2 != -1) {
					nome2 = clientes[pos2][1];
				}

				if (compararNomes(nome1, nome2) > 0) {

					String[] temp = contatos[j];
					contatos[j] = contatos[j + 1];
					contatos[j + 1] = temp;

				}
			}
		}

		return contatos;
	}

	// ==================================
	// RELATORIO
	// ==================================
	public static void exibirRelatorio(String[][] clientes, String[][] contatos) {

		System.out.println("\n--- RELATORIO ---");
		System.out.println("Total de clientes: " + clientes.length);
		System.out.println("Total de contatos: " + contatos.length);

		System.out.println("\nContatos por cliente:");

		for (int i = 0; i < clientes.length; i++) {

			int total = 0;

			for (int j = 0; j < contatos.length; j++) {

				if (contatos[j][1] != null && contatos[j][1].equals(clientes[i][0])) {
					total++;
				}
			}

			System.out.println("  " + clientes[i][1] + ": " + total + " contato(s)");
		}
	}

	// ==================================
	// LISTAR CLIENTES
	// ==================================
	public static void listarClientes(String[][] clientes) {

		System.out.println("\n--- CLIENTES ---");

		if (clientes.length == 0) {

			System.out.println("Nenhum cliente cadastrado!");

		} else {

			for (int i = 0; i < clientes.length; i++) {

				System.out.println(
						"Codigo: " + clientes[i][0] + " | Nome: " + clientes[i][1] + " | CPF: " + clientes[i][2]
								+ " | Nascimento: " + clientes[i][3] + " | Sexo: " + clientes[i][4] + " | Cidade: "
								+ clientes[i][5] + " | Estado: " + clientes[i][6] + " | Status: " + clientes[i][7]);
			}
		}
	}

	// ==================================
	// LISTAR CONTATOS
	// ==================================
	public static void listarContatos(String[][] clientes, String[][] contatos) {

		System.out.println("\n--- CONTATOS ---");

		if (contatos.length == 0) {

			System.out.println("Nenhum contato cadastrado!");

		} else {

			for (int i = 0; i < clientes.length; i++) {

				System.out.println("\nCodigo Cliente: " + clientes[i][0] + " | Cliente: " + clientes[i][1]);

				boolean possuiContato = false;

				for (int j = 0; j < contatos.length; j++) {

					if (contatos[j][1] != null && contatos[j][1].equals(clientes[i][0])) {

						System.out.println("   Codigo Contato: " + contatos[j][0] + " | " + contatos[j][2] + ": "
								+ contatos[j][3] + " | Status: " + contatos[j][4]);

						possuiContato = true;
					}
				}

				if (!possuiContato) {
					System.out.println("   Nenhum contato cadastrado.");
				}
			}
		}
	}
	// ==================================
	// BUSCAR CLIENTE
	// ==================================
	public static int buscarCliente(String[][] clientes, String codigo) {

		for (int i = 0; i < clientes.length; i++) {

			if (clientes[i][0] != null && clientes[i][0].equals(codigo)) {
				return i;
			}
		}

		return -1;
	}

	// ==================================
	// BUSCAR CONTATO
	// ==================================
	public static int buscarContato(String[][] contatos, String codigo) {

		for (int i = 0; i < contatos.length; i++) {

			if (contatos[i][0] != null && contatos[i][0].equals(codigo)) {
				return i;
			}
		}

		return -1;
	}

	// ==================================
	// COMPARAR NOMES (caractere por caractere)
	// ==================================
	public static int compararNomes(String primeiroNome, String segundoNome) {

		int quantidadeCaracteres = primeiroNome.length();

		if (segundoNome.length() < quantidadeCaracteres) {
			quantidadeCaracteres = segundoNome.length();
		}

		for (int indice = 0; indice < quantidadeCaracteres; indice++) {

			char letraPrimeiroNome = primeiroNome.charAt(indice);
			char letraSegundoNome = segundoNome.charAt(indice);

			if (letraPrimeiroNome != letraSegundoNome) {
				return letraPrimeiroNome - letraSegundoNome;
			}
		}

		return primeiroNome.length() - segundoNome.length();
	}

	// ==================================
	// AUMENTAR CLIENTES
	// ==================================
	public static String[][] aumentarClientes(String[][] clientes) {

		String[][] novo = new String[clientes.length + 1][8];

		for (int i = 0; i < clientes.length; i++) {

			for (int j = 0; j < 8; j++) {
				novo[i][j] = clientes[i][j];
			}
		}

		return novo;
	}

	// ==================================
	// AUMENTAR CONTATOS
	// ==================================
	public static String[][] aumentarContatos(String[][] contatos) {

		String[][] novo = new String[contatos.length + 1][5];

		for (int i = 0; i < contatos.length; i++) {

			for (int j = 0; j < 5; j++) {
				novo[i][j] = contatos[i][j];
			}
		}

		return novo;
	}
}