/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.meu_java.chalenge_conversor;

//import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
//import java.util.Map;

/**
 *
 * @author mathe
 */
public class Chalenge_conversor {

    public static void main(String[] args) throws IOException, InterruptedException {
        String opcao;
        String ret = "";
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        double valor = 0;
        String exc1 = "";
        String exc2 = "";
        double valFi = 0;
        boolean erro = false;
        boolean rep = false;
        String opc = "sim";

        do {
            do {
                rep = false;
                System.out.println("Escolha o tipo da moeda a ser convertido: ");
                System.out.println("""
                               1 - Dolar
                               2 - Real
                               3 - Peso Argentino
                               4 - Peso Colombiano
                               5 - Peso Chileno
                               6 - Boliviano
                               7 - Sair
                               """);
                opcao = scan.nextLine();

                switch (opcao) {

                    case "Dolar", "1", "dolar" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "USD";
                        exc1 = "Dolar";
                    }

                    case "Real", "2", "real" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "BRL";
                        exc1 = "Real";
                    }

                    case "Peso Argentino", "3", "peso argentino" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "ARS";
                        exc1 = "Peso Argentino";
                    }

                    case "Peso Colombiano", "4", "peso colombiano" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "COP";
                        exc1 = "Peso Colombiano";
                    }

                    case "Peso Chileno", "5", "peso chileno" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "CLP";
                        exc1 = "Peso Chileno";
                    }

                    case "Boliviano", "6", "boliviano" -> {
                        ret = "https://v6.exchangerate-api.com/v6/4601e91ff874aabe43a269d6/latest/" + "BOB";
                        exc1 = "Boliviano";
                    }

                    case "Sair", "7", "sair" -> {
                        System.out.println("Encerrando Programa ...");
                        break;
                    }
                    default -> {
                        System.out.println("Opçao Invalida");
                        rep = true;
                    }
                }
            } while (rep);
            if (!opcao.equals("7") && !opcao.equals("Sair") && !opcao.equals("sair")) {

                HttpClient cotas = HttpClient.newHttpClient();
                HttpRequest pedido = HttpRequest.newBuilder()
                        .uri(URI.create(ret))
                        .build();
                HttpResponse<String> resposta = cotas
                        .send(pedido, HttpResponse.BodyHandlers.ofString());

                Gson sonn = new GsonBuilder().create();

                Moeda moedaRec = sonn.fromJson(resposta.body(), Moeda.class);

                moedaRec.sep();

                do {
                    erro = false;
                    try {
                        System.out.println("""
                               Digite o valor a ser convertido
                               
                               Ps: digite APENAS o valor 
                               """);
                        valor = scan1.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Nao foi possivel identificar um valor");
                        erro = true;
                        System.out.println("Tente inserir novamente o valor:");
                        scan1.next();
                    }
                } while (erro);

                Calculator calcu = new Calculator();

                do {
                    rep = false;
                    System.out.println("Escolha o tipo da moeda para qual sera convertido: ");
                    System.out.println("""
                               1 - Dolar
                               2 - Real
                               3 - Peso Argentino
                               4 - Peso Colombiano
                               5 - Peso Chileno
                               6 - Boliviano                               
                               """);
                    opcao = scan.nextLine();

                    switch (opcao) {
                        case "Dolar", "1", "dolar" -> {
                            calcu.Calculator(valor, moedaRec.getDol());
                            exc2 = "Dolar";
                        }
                        case "Real", "2", "real" -> {
                            calcu.Calculator(valor, moedaRec.getRea());
                            exc2 = "Real";
                        }
                        case "Peso Argentino", "3", "peso argentino" -> {
                            calcu.Calculator(valor, moedaRec.getArg());
                            exc2 = "Peso Argentino";
                        }
                        case "Peso Colombiano", "4", "peso colombiano" -> {
                            calcu.Calculator(valor, moedaRec.getColo());
                            exc2 = "Peso Colombiano";
                        }
                        case "Peso Chileno", "5", "peso chileno" -> {
                            calcu.Calculator(valor, moedaRec.getchi());
                            exc2 = "Peso Chileno";
                        }
                        case "Boliviano", "6", "boliviano" -> {
                            calcu.Calculator(valor, moedaRec.getBoli());
                            exc2 = "Boliviano";
                        }
                        default -> {
                            System.out.println("Opcao Invalida");
                            rep = true;
                        }
                    }
                } while (rep);
                do {
                    do {
                        rep = false;
                        System.out.println(String.format("""
                               Gostaria de:
                               8 - Converter %1$s para %2$s
                               9 - converter %2$s para %1$s
                               
                               Ps: digite apenas o numero
                               """, exc1, exc2));
                        opcao = scan.nextLine();

                        if (opcao.equals("8")) {
                            valFi = calcu.converteCon();
                        } else if (opcao.equals("9")) {
                            valFi = calcu.converteSel();
                        } else {
                            System.out.println("Opcao Invalida");
                            rep = true;
                        }
                    } while (rep);

                    if (opcao.equals("8")) {
                        System.out.println(String.format("""
                               Conversao bem sucedida!
                                                 
                               Voce escolheu converter %.3f
                               A moeda era do tipo %s e foi convertida para %s
                               resultando no valor de %.3f
                                                 
                               """, valor, exc1, exc2, valFi));
                    } else if (opcao.equals("9")) {
                        System.out.println(String.format("""
                               Conversao bem sucedida!
                                                 
                               Voce escolheu converter %.3f
                               A moeda era do tipo %s e foi convertida para %s
                               resultando no valor de %.3f
                                                 
                               """, valor, exc2, exc1, valFi));
                    }

                    System.out.println("""
                                    Quer repetir a conversao com mesmo valores e tipos
                                    ou continuar desde o inicio?
                                   
                                   Sim - repetir a conversao com mesmo valores e tipos
                                   Nao - continuar para o inicio
                                                                     
                                   """);
                    opc = scan.nextLine();
                } while (opc.equals("sim") || opc.equals("Sim"));
            } else {
                break;
            }

        } while (!opcao.equals("7") && !opcao.equals("Sair") && !opcao.equals("sair"));
        scan.close();
    }
}