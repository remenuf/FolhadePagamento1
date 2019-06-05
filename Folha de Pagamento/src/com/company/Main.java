package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[][]funcionario = new String[10][11];
        int i;
        int h;
        for (i = 0; i < 10; i++) {
            for (h = 0; h < 11; h++) {
                funcionario[i][h] = "-1";
            }
        }
        Scanner input = new Scanner(System.in);
        String[][]agenda = new String[30][5];
        int indiceAgenda = 3;

        System.out.println("Digite o dia do mes");
        String dMes = input.nextLine();
        System.out.println("Digite o dia da semana");
        String dSem = input.nextLine();
        agenda[0][0] = dMes;
        agenda[0][3] = "-1";
        agenda[1][0] = dSem;
        agenda[1][3] = "-1";
        agenda[2][0] = "semanal";
        agenda[2][1] = "2";
        agenda[2][2] = "sex";
        agenda[2][3] = "2";
        agenda[2][4] = "semanal 2 sex";

        String[][] u0 = new String[10][11];
        String[][] u1 = new String[10][11];
        String[][] u2 = new String[10][11];
        String[][] u3 = new String[10][11];
        String[][] u4 = new String[10][11];
        String[][] r0 = new String[10][11];
        String[][] r1 = new String[10][11];
        String[][] r2 = new String[10][11];
        String[][] r3 = new String[10][11];
        String[][] r4 = new String[10][11];

        int undo = 0;
        int redo = 0;

        while (true) {
            while (true) {
                System.out.println("SELECIONE A OPERAÇÃO DESEJADA\n\n" +
                        "[1]  Adicionar funcionário\n" +
                        "[2]  Remover funcionário\n" +
                        "[3]  Lançar cartão de ponto\n" +
                        "[4]  Lançar resultado de venda\n" +
                        "[5]  Lançar taxa de serviço\n" +
                        "[6]  Modificar dados de um funcionário\n" +
                        "[7]  Rodar folha de pagamento do dia\n" +
                        "[8]  Desfazer\n" +
                        "[9]  Refazer\n" +
                        "[10] Alterar agenda de pagamento\n" +
                        "[11] Criar nova agenda de pagamento\n" +
                        "[12] Encerrar o dia\n" +
                        "[13] Encerrar o programa");
                int index = Integer.parseInt(input.nextLine());
                if (index < 8) {
                    undoRedo(funcionario, u0, u1, u2, u3, u4, undo, 1);
                    undo += 1;
                    undo = check(undo);
                }
                switch (index) {
                    case 1:
                        int aux = searchFunc(funcionario);
                        addFunc(funcionario, input, aux);
                        //System.out.println(aux);
                        break;
                    case 2:
                        remove(funcionario, input);
                        break;
                    case 3:
                        ponto(funcionario, input);
                        break;
                    case 4:
                        venda(funcionario, input);
                        break;
                    case 5:
                        servico(funcionario, input);
                        break;
                    case 6:
                        modFunc(funcionario, input);
                        break;
                    case 7:
                        folha(funcionario, agenda, indiceAgenda);
                        break;
                    case 8:
                        undoRedo(funcionario, r0, r1, r2, r3, r4, redo, 1);
                        redo += 1;
                        redo = check(redo);
                        undo -= 1;
                        undo = check(undo);
                        undoRedo(funcionario, u0, u1, u2, u3, u4, undo, 2);
                        System.out.println(">>>OPERAÇÃO DESFEITA<<<");
                        break;
                    case 9:
                        undoRedo(funcionario, u0, u1, u2, u3, u4, undo, 1);
                        undo += 1;
                        undo = check(undo);
                        redo -= 1;
                        redo = check(redo);
                        undoRedo(funcionario, r0, r1, r2, r3, r4, redo, 2);
                        System.out.println(">>>OPERAÇÃO REFEITA<<<");
                        break;
                    case 10:
                        modAgenda(funcionario, agenda, indiceAgenda, input);
                        break;
                    case 11:
                        indiceAgenda = addAgenda(agenda, input, indiceAgenda);
                        break;
                    case 12:
                        System.out.println("Digite o dia do mês");
                        dMes = input.nextLine();
                        System.out.println("Digite o dia da semana");
                        dSem = input.nextLine();

                        agenda[0][0] = dMes;
                        agenda[1][0] = dSem;
                        break;
                    case 13:
                        return;
                }
            }
        }
    }

    private static int check (int v){
        if (v < 0) v = 4;
        if (v > 4) v = 0;
        return v;
    }

    private static void undoRedo(String[][] funcionario, String[][] m0, String[][] m1, String[][] m2, String[][] m3, String[][] m4, int i, int condition){
        if (condition == 1){
            switch (i){
                case 0:
                    backup(funcionario, m0);
                    break;
                case 1:
                    backup(funcionario, m1);
                    break;
                case 2:
                    backup(funcionario, m2);
                    break;
                case 3:
                    backup(funcionario, m3);
                    break;
                case 4:
                    backup(funcionario, m4);
            }
        }
        else{
            switch (i){
                case 0:
                    backup(m0, funcionario);
                    break;
                case 1:
                    backup(m1, funcionario);
                    break;
                case 2:
                    backup(m2, funcionario);
                    break;
                case 3:
                    backup(m2, funcionario);
                    break;
                case 4:
                    backup(m2, funcionario);
            }
        }
        return;
    }

    private static void backup(String[][] funcionario, String[][] copy) {
        int i;
        int j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 11; j++) {
                copy[i][j] = funcionario[i][j];
            }
        }
        return;
    }

    private static void addFunc(String[][] funcionario, Scanner input, int i) {
        System.out.println(">>>ADICIONAR EMPREGADO<<<\n");
        System.out.println("Digite o nome");
        funcionario[i][0] = input.nextLine();

        System.out.println("Digite o endereço");
        funcionario[i][1] = input.nextLine();

        System.out.println("Digite o tipo de funcionário, 1 para horário, 2 para assalariado e 3 para comissionado");
        funcionario[i][4] = input.nextLine();

        if (funcionario[i][4].equals("1")) {
            System.out.println("Digite o pagamento por hora");
            funcionario[i][9] = input.nextLine();
            funcionario[i][10] = null;
            funcionario[i][5] = "sex";
        }
        else if (funcionario[i][4].equals("2")) {
            System.out.println("Digite o dia de pagamento");
            funcionario[i][5] = input.nextLine();
            System.out.println("Digite o salário do funcionário");
            funcionario[i][9] = input.nextLine();
            funcionario[i][10] = null;
        }
        else if (funcionario[i][4].equals("3")) {
            System.out.println("Digite o salário do funcionário");
            funcionario[i][9] = input.nextLine();
            System.out.println("Informe a comissão por venda (em porcentagem)");
            funcionario[i][10] = input.nextLine();
            funcionario[i][5] = "semanal 2 sex";
        }

        funcionario[i][2] = Integer.toString(i);
        funcionario[i][3] = "0";
        funcionario[i][6] = "1";
        funcionario[i][7] = "0";
        funcionario[i][8] = null;
        System.out.println(">>>FUNCIONÁRIO CADASTRADO<<<\n");

        return;
    }

    private static void remove(String[][] funcionario, Scanner input) {
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        funcionario[i][2] = "-1";
        System.out.println(">>>FUNCIONÁRIO REMOVIDO<<<\n");
        return;
    }

    private static void printfunc(String[][] funcionario, int i) {
        int j;
        for (j = 0; j < 11; j++) {
            System.out.println(funcionario[i][j]);
        }
        return;
    }

    private static void ponto(String[][] funcionario, Scanner input) {
        System.out.println(">>>REGISTRO DE PONTO<<<");
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        double total = Double.parseDouble(funcionario[i][3]);
        double salario = Double.parseDouble(funcionario[i][9]);
        System.out.println("Insira o número de horas trabalhadas");
        int horas = Integer.parseInt(input.nextLine());

        if (horas > 8){
            total += (salario*8) + (1.5*salario*(horas - 8));
            funcionario[i][3] = Double.toString(total);
        }
        else {
            total += salario * horas;
            funcionario[i][3] = Double.toString(total);
        }

        System.out.println(">>>PONTO REGISTRADO<<<\n");
        return;
    }

    private static void venda(String[][] funcionario, Scanner input) {
        System.out.println(">>>REGISTRO DE VENDA<<<");
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        double total = Double.parseDouble(funcionario[i][3]);
        double comissao = Double.parseDouble(funcionario[i][10]);
        System.out.println("Insira o preço da venda");
        double venda = Double.parseDouble(input.nextLine());

        total += (comissao / 100) * venda;
        funcionario[i][3] = Double.toString(total);
        System.out.println(">>>VENDA REGISTRADA<<<\n");
        return;
    }

    private static void servico (String[][] funcionario, Scanner input) {
        System.out.println(">>>LANÇAR TAXA DE SERVIÇO<<<");
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        double total = Double.parseDouble(funcionario[i][3]);
        System.out.println("Insira o valor a ser descontado");
        double servico = Double.parseDouble(input.nextLine());
        total -= servico;
        funcionario[i][3] = Double.toString(total);
        System.out.println(">>>ATUALIZAÇÃO CONCLUÍDA\n");
        return;
    }

    private static void modFunc (String[][] funcionario, Scanner input) {
        System.out.println(">>>MODIFICAR FUNCIONÁRIO<<<");
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        while (true) {
            System.out.println("Insira o número da operação desejada\n" +
                    "[1] Alterar nome\n" +
                    "[2] Alterar endereço\n" +
                    "[3] Alterar tipo de pagamento\n" +
                    "[4] Alterar método de pagamento\n" +
                    "[5] Modificar registro sindical\n" +
                    "[6] Encerrar alterações");
            String indice = input.nextLine();

            if (indice.equals("1")){
                System.out.println("Insira o novo nome");
                funcionario[i][0] = input.nextLine();
            }
            else if (indice.equals("2")){
                System.out.println("Insira o novo endereço");
                funcionario[i][1] = input.nextLine();
            }
            else if (indice.equals("3")){
                System.out.println("Insira o novo tipo de pagamento, 1 para horário, 2 para assalariado e 3 para comissionado");
                funcionario[i][4] = input.nextLine();
                if (funcionario[i][4].equals("1")) {
                    System.out.println("Digite o pagamento por hora");
                    funcionario[i][9] = input.nextLine();
                    funcionario[i][10] = null;
                }
                else if (funcionario[i][4].equals("2")) {
                    System.out.println("Digite o salário do funcionário");
                    funcionario[i][9] = input.nextLine();
                    funcionario[i][10] = null;
                }
                else if (funcionario[i][4].equals("3")) {
                    System.out.println("Digite o salário do funcionário");
                    funcionario[i][9] = input.nextLine();
                    System.out.println("Informe a comissão por venda (em porcentagem)");
                    funcionario[i][10] = input.nextLine();
                }
            }
            else if (indice.equals("4")){
                System.out.println("Insira o novo método de pagamento, 1 para depósito, 2 para cheque via correio e 3 para cheque em mãos");
                funcionario[i][6] = input.nextLine();
            }
            else if (indice.equals("5")){
                System.out.println("Insira a nova identificação dentro do sindicato");
                funcionario[i][8] = input.nextLine();
                System.out.println("insira a nova taxa sindical");
                funcionario[i][7] = input.nextLine();
            }
            else {
                System.out.println(">>>MODIFICAÇÃO CONCLUÍDA<<<");
                return;
            }
        }

    }

    private static void folha (String[][] funcionario, String[][] agenda, int indiceAgenda) {
        int i;
        double total;
        double salario;
        double imposto;
        System.out.println("--------------------------------------------------");
        for (i = 0; i < 10; i++){
            if (searchAgenda(funcionario[i][5], agenda, indiceAgenda)){
                salario = Double.parseDouble(funcionario[i][9]);
                imposto = Double.parseDouble(funcionario[i][7]);
                total = Double.parseDouble(funcionario[i][3]);
                if (funcionario[i][4].equals("2") || funcionario[i][4].equals("3")){
                    total += salario - ((imposto/100)*salario);
                }
                funcionario[i][3] = "0";

                System.out.printf("Funcionário: %s\nID: %s\nSalário: %.2f\nStatus", funcionario[i][0], funcionario[i][2], total);
                if (funcionario[i][6].equals("1")) System.out.println("Status: Depósito efetuado");
                else if (funcionario[i][6].equals("2")) System.out.println("Status: Cheque enviado para " + funcionario[i][1]);
                else System.out.println("Status: Cheque entregue");
                System.out.println("--------------------------------------------------");
            }
        }
        int j;
        for (j = 2; j < indiceAgenda; j++){
            if (agenda[j][2].equals(agenda[1][0])) {
                if (agenda[j][3].equals("2")) agenda[j][3] = "1";
                else if (agenda[j][3].equals("1")) agenda[j][3] = "0";
                else if (agenda[j][3].equals("0")) agenda[j][3] = "3";
                else if (agenda[j][3].equals("3")) agenda[j][3] = "2";
            }
        }
        System.out.println(">>>PAGAMENTOS EFETUADOS COM SUCESSO<<<");
        return;
    }

    private static int addAgenda (String[][] agenda, Scanner input, int i){
        System.out.println(">>>CRIAR NOVA AGENDA<<<");
        System.out.println("Digite o tipo de agenda (mensal ou semanal)");
        agenda[i][0] = input.nextLine();

        if (agenda[i][0].equals("mensal")){
            System.out.println("Digite o dia do mês");
            agenda[i][1] = input.nextLine();
            agenda[i][4] = agenda[i][0] + " " + agenda[i][1];
            agenda[i][2] = "-1";
            agenda[i][3] = "-1";
        }
        else {
            System.out.println("Digite quantas semanas no mês (1 ou 2)");
            agenda[i][1] = input.nextLine();
            System.out.println("Digite o dia da semana");
            agenda[i][2] = input.nextLine();
            if (agenda[i][1].equals("2")) agenda[i][3] = "2";
            else agenda[i][3] = "-1";
            agenda[i][4] = agenda[i][0] + " " + agenda[i][1] + " " + agenda[i][2];
        }
        System.out.println(agenda[i][4]);
        System.out.println(">>>NOVA AGENDA ADICIONADA<<<\n");
        i += 1;
        return i;
    }

    private static boolean searchAgenda (String funcionario, String[][] agenda, int i) {
        if (funcionario.equals(agenda[0][0])) return true;
        else if (funcionario.equals(agenda[1][0])) return true;
        else{
            int j;
            for (j = 2; j < i; j++) {
                if (funcionario.equals(agenda[j][4])) {
                    if (agenda[j][2].equals(agenda[1][0])) {
                        if (agenda[j][3].equals("2") || agenda[j][3].equals("0") || agenda[j][3].equals("-1")) {
                            return true;
                        }
                    }
                    else if (agenda[j][0].equals("mensal") && agenda[j][1].equals(agenda[0][0])) return true;
                }
            }
            return false;
        }
    }

    private static void printAgenda (String[][] agenda, int i){
        int j;
        for (j = 2; j < i; j++){
            System.out.printf("[%d] %s\n", j, agenda[j][4]);
        }
    }

    private static void modAgenda (String[][] funcionario, String[][] agenda, int indiceAgenda, Scanner input){
        System.out.println(">>>ALTERAR AGENDA<<<");
        System.out.println("Digite o ID do funcionário");
        int i = Integer.parseInt(input.nextLine());
        printAgenda(agenda, indiceAgenda);
        System.out.println("Escolha o número da opção que deseja inserir");
        int j = Integer.parseInt(input.nextLine());
        funcionario[i][5] = agenda[j][4];
        System.out.println(">>>ALTERAÇÃO CONCLUÍDA\n");
        return;
    }

    private static int searchFunc (String[][] funcionario){
        int j;
        for (j = 0; j < 10; j++){
            if (funcionario[j][2].equals("-1")) return j;
        }
        return j;
    }
}
