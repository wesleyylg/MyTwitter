package mytwitter;

import Excecoes.UJCException;
import Excecoes.UNCException;
import java.util.Scanner;
import java.util.Vector;

public class Repositorio implements IRepositorioUsuario {

    Vector<Perfil> perfis = new Vector();
    
    public void cadastrar(Perfil usuario) throws UJCException{
        if(buscar(usuario.getUsuario()) != null){
            throw new UJCException();
        }else
            perfis.add(usuario);
    }

    public Perfil buscar(String usuario) {
        for(int i=0;i<perfis.size();i++){
            if((perfis.get(i)).getUsuario().equals(usuario))
                return perfis.get(i);
        }
        return null;
    }

    public void atualizar(Perfil usuario) throws UNCException{
        int option;
        Scanner nmr = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        Scanner lng = new Scanner(System.in);
        
        if(buscar(usuario.getUsuario()) == null)
            throw new UNCException();
        
        if(usuario instanceof PessoaFisica && usuario.isAtivo()){
            System.out.println("1- Nome de usuário.");
            System.out.println("2- CPF.");
            System.out.print("Digite o dado a ser alterado: ");
            option = nmr.nextInt();
            switch(option){
                case 1:
                    String nm;
                    int c=0;
                    while(c==0){
                        System.out.print("Digite o novo nome de usuário: @");
                        nm = '@' + str.nextLine();
                        if(buscar(nm) == null){
                            for (int i=0;i<usuario.getSeguindo().size();i++) 
                                buscar(usuario.getSeguindo().get(i)).getSeguidores().add(buscar(usuario.getSeguindo().get(i)).getSeguidores().indexOf(usuario.getUsuario()), nm);
                            for (int i=0;i<usuario.getSeguidores().size();i++) 
                                if(buscar(usuario.getSeguidores().get(i)).getTimeline().get(i).getUsuario().equals(usuario.getUsuario()))
                                    buscar(usuario.getSeguidores().get(i)).getTimeline().get(i).setUsuario(nm);
                            for(int i=0;i<usuario.getTimeline().size();i++)
                                if(usuario.getTimeline().get(i).getUsuario().equals(usuario.getUsuario()))
                                    usuario.getTimeline().get(i).setUsuario(nm);
                            usuario.setUsuario(nm);
                            c++;
                        }
                        else
                            System.out.println("Usuario já existente.");
                    }
                    break;
                case 2:
                    long nc;
                    System.out.print("Digite o novo CPF: ");
                    nc = lng.nextLong();
                    ((PessoaFisica)usuario).setCPF(nc);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }if(usuario instanceof PessoaJuridica && usuario.isAtivo()){
            System.out.println("1- Nome de usuário.");
            System.out.println("2- CNPJ.");
            System.out.print("Digite o dado a ser alterado: ");
            option = nmr.nextInt();
            switch(option){
                case 1:
                    String nm;
                    int c=0;
                    while(c==0){
                        System.out.print("Digite o novo nome de usuário: @");
                        nm = '@' + str.nextLine();
                        if(buscar(nm) == null){
                            for (int i=0;i<usuario.getSeguindo().size();i++) 
                                buscar(usuario.getSeguindo().get(i)).getSeguidores().add(buscar(usuario.getSeguindo().get(i)).getSeguidores().indexOf(usuario.getUsuario()), nm);
                            for (int i=0;i<usuario.getSeguidores().size();i++) 
                                if(buscar(usuario.getSeguidores().get(i)).getTimeline().get(i).getUsuario().equals(usuario.getUsuario()))
                                    buscar(usuario.getSeguidores().get(i)).getTimeline().get(i).setUsuario(nm);
                            for(int i=0;i<usuario.getTimeline().size();i++)
                                if(usuario.getTimeline().get(i).getUsuario().equals(usuario.getUsuario()))
                                    usuario.getTimeline().get(i).setUsuario(nm);
                            usuario.setUsuario(nm);
                            c++;
                        }
                        else
                            System.out.println("Usuario já existente.");
                    }
                    break;
                case 2:
                    long nc;
                    System.out.print("Digite o novo CNPJ: ");
                    nc = lng.nextLong();
                    ((PessoaJuridica)usuario).setCNPJ(nc);
                    break;
            }
        }
    }
    
}