package mytwitter;

import Excecoes.MFPException;
import Excecoes.PDException;
import Excecoes.PEException;
import Excecoes.PIException;
import Excecoes.SIException;
import java.util.Scanner;

public class Menu {

    public void principal(MyTwitter myTwitter){
        Scanner nmr = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        
        int a=0,b=0,c;
        while(a==0){
            System.out.println("Bem vindo ao MyTwitter!\n");

            System.out.println("1- Criar perfil.");
            System.out.println("2- Gerenciar perfis.");
            System.out.println("3- Sair.");
            System.out.print("Escolha uma opção: ");
            int option = nmr.nextInt();

            switch(option){
                case 1:
                    Perfil pFis = new PessoaFisica("");
                    Perfil pJur = new PessoaJuridica("");
                    
                    System.out.println("\n1- Pessoa Física.");
                    System.out.println("2- Pessoa Jurídica.");
                    System.out.print("Escolha o tipo de perfil: ");
                    int tipoP = nmr.nextInt();

                    if(tipoP == 1)
                        try {
                            myTwitter.criarPerfil(pFis);
                        } catch (PEException ex) {
                            ex.getMessage();
                        }
                    else if(tipoP == 2)
                        try {
                            myTwitter.criarPerfil(pJur);
                        } catch (PEException ex) {
                            ex.getMessage();
                        }
                    break;
                case 2:
                    b=0;
                    while(b==0){
                        System.out.print("\nDigite o perfil que deseja gerenciar: @");
                        String buscarPerfil = '@' + str.nextLine();
                        if(!myTwitter.verificar(buscarPerfil)){
                            System.out.println("Perfil inexistente.\n");
                            c=1;
                            b++;
                        }
                        else
                            c=0;
                        
                        while(c==0){
                            System.out.println("1- Tweetar");
                            System.out.println("2- Ver timeline.");
                            System.out.println("3- Meus Tweets.");
                            System.out.println("4- Seguir Usuário.");
                            System.out.println("5- Ver seguidores.");
                            System.out.println("6- Editar perfil.");
                            System.out.println("7- Cancelar perfil.");
                            System.out.println("8- Gerenciar outro perfil.");
                            System.out.println("9- Voltar ao menu principal.");
                            System.out.print("Escolha uma opção: ");
                            int option2 = nmr.nextInt();
                            System.out.println("\n-------------------------\n");

                            switch(option2){
                                case 1:
                                    System.out.print("Digite o tweet (entre 1 e 140 caracteres.): ");
                                    String tweet = str.nextLine();
                                    try {
                                        myTwitter.tweetar(buscarPerfil, tweet);
                                    } catch (PIException ex) {
                                        ex.getMessage();
                                    } catch (PDException ex) {
                                        ex.getMessage();
                                    } catch (MFPException ex) {
                                        ex.getMessage();
                                    }
                                    System.out.print("\n-------------------------\n");
                                    break;
                                case 2:
                                    try {
                                        myTwitter.timeline(buscarPerfil);
                                    } catch (PIException ex) {
                                        ex.getMessage();
                                    } catch (PDException ex) {
                                        ex.getMessage();
                                    }
                                    System.out.print("-------------------------\n");
                                    break;
                                case 3:
                                    try {
                                        myTwitter.tweets(buscarPerfil);
                                    } catch (PIException ex) {
                                        ex.getMessage();
                                    } catch (PDException ex) {
                                        ex.getMessage();
                                    }
                                    System.out.print("-------------------------\n");
                                    break;
                                case 4:
                                    System.out.print("Digite o perfil que você deseja seguir: @");
                                    String seguido = '@' + str.nextLine();
                                    try {
                                        myTwitter.seguir(buscarPerfil, seguido);
                                    } catch (PIException ex) {
                                        ex.getMessage();
                                    } catch (PDException ex) {
                                        ex.getMessage();
                                    } catch (SIException ex) {
                                        ex.getMessage();
                                    }
                                    System.out.print("\n-------------------------\n");
                                    break;
                                case 5:
                                    try{
                                        System.out.print("Você tem " + myTwitter.numeroSeguidores(buscarPerfil) + " ");
                                        myTwitter.seguidores(buscarPerfil);
                                    }catch(PIException ex){
                                        ex.getMessage();
                                    }catch(PDException ex){
                                        ex.getMessage();
                                    }
                                    System.out.print("\n-------------------------\n");
                                    break;
                                case 6:
                                    myTwitter.editar(buscarPerfil);
                                    c++;
                                    System.out.print("\n-------------------------\n");
                                    break;
                                case 7:
                                    try {
                                        myTwitter.cancelarPerfil(buscarPerfil);
                                        c++;
                                    }catch (PDException ex) {
                                        ex.getMessage();
                                    }catch (PIException ex) {
                                        ex.getMessage();
                                    }
                                    System.out.print("\n-------------------------\n");
                                    break;
                                case 8:
                                    c++;
                                    break;
                                case 9:
                                    b++;
                                    c++;
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        }    
                    }
                    break;
                case 3:
                    a++;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}