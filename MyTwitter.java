package mytwitter;

import Excecoes.MFPException;
import Excecoes.PDException;
import Excecoes.PEException;
import Excecoes.PIException;
import Excecoes.SIException;
import Excecoes.UJCException;
import Excecoes.UNCException;
import java.util.Scanner;
import java.util.Vector;

public class MyTwitter implements ITwitter{

    private IRepositorioUsuario repositorio = new Repositorio();
    
    public void criarPerfil(Perfil usuario) throws PEException{
        Scanner nmr = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        Scanner lng = new Scanner(System.in);
        int opcao, idade;
        String nome, sexo, empresa;
        long cpf, cnpj;
        
        if(repositorio.buscar(usuario.getUsuario()) != null)
            throw new PEException();
        
        if(usuario instanceof PessoaFisica){
            System.out.print("Digite o nome de usuário: @");
            nome = '@' + str.nextLine();
            if(nome.charAt(0) != '@'){
                String aux = "@";
                nome = aux + nome;
            }
            
            usuario.setUsuario(nome);
            System.out.print("CPF: ");
            cpf = lng.nextLong();
            ((PessoaFisica) usuario).setCPF(cpf);
            try {
                repositorio.cadastrar(usuario);
                System.out.println("Perfil Cadastrado!");
            } catch (UJCException ex) {
                ex.getMessage();
            }
        }else if(usuario instanceof PessoaJuridica){
            System.out.print("Digite o nome de usuário: @");
            nome = '@' + str.nextLine();
            if(nome.charAt(0) != '@'){
                String aux = "@";
                nome = aux + nome;
            }
            usuario.setUsuario(nome);
            System.out.print("CNPJ: ");
            cnpj = lng.nextLong();
            ((PessoaJuridica) usuario).setCNPJ(cnpj);
            try {
                repositorio.cadastrar(usuario);
                System.out.println("Perfil Cadastrado!");
            } catch (UJCException ex) {
                ex.getMessage();
            }
        }
        System.out.print("\n\n");
    }

    public void cancelarPerfil(String usuario) throws PIException, PDException{
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo()){
                p.setAtivo(false);
                System.out.println("Perfil cancelado.\n");
            }
            else
                throw new PDException();
        else
            throw new PIException();
    }

    public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException{
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo())
                if(mensagem.length()>=1 && mensagem.length()<=140){
                    Tweet tweet = new Tweet(usuario, mensagem);
                    p.addTweet(tweet);
                    p.salvarTweet(tweet);
                    for(int i=0;i<p.getSeguidores().size();i++)
                        if(repositorio.buscar(p.getSeguidores().get(i)) != null)
                            if(repositorio.buscar(p.getSeguidores().get(i)).isAtivo())
                                repositorio.buscar(p.getSeguidores().get(i)).addTweet(tweet);
                    System.out.println("Tweetado!");
                }else
                    throw new MFPException();
            else
                throw new PDException();
        else
            throw new PIException();
    }

    public Vector<Tweet> timeline(String usuario) throws PIException, PDException{
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo()){
                for(int i=0;i<p.getTimeline().size();i++){
                    System.out.println(p.getTimeline().get(i).getUsuario() + ":");
                    System.out.println(p.getTimeline().get(i).getMensagem());
                    System.out.println();
                }
                return p.getTimeline();
            }else
                throw new PDException();
        else
            throw new PIException();
    }

    public Vector<Tweet> tweets(String usuario) throws PIException, PDException{
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo()){
                for(int i=0;i<p.getMeusTweets().size();i++)
                        System.out.println(p.getMeusTweets().get(i).getMensagem());
                        System.out.println();
                return p.getMeusTweets();
            }else
                throw new PDException();
        else
            throw new PIException();
    }

    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException{
        Perfil p = repositorio.buscar(seguido);
        Perfil p1 = repositorio.buscar(seguidor);
        int c=0;
        if(p!=null && p1!=null)
            if(p.isAtivo() && p1.isAtivo())
                if(!p.getUsuario().equals(p1.getUsuario())){
                    for(int i=0;i<p.getSeguidores().size();i++)
                        if(p.getSeguidores().get(i).equals(seguidor))
                            c++;
                    if(c==0){
                        p.addSeguidor(seguidor);
                        p1.addSeguindo(seguido);
                        System.out.println(seguidor + " seguiu " + seguido);
                    }else
                        System.out.println(seguidor + " já segue " + seguido);
                }
                else
                    throw new SIException();
            else
                throw new PDException();
        else
            throw new PIException();
        
    }

    public int numeroSeguidores(String usuario) throws PIException, PDException{
        int c=0;
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo()){
                for(int i=0;i<p.getSeguidores().size();i++){
                    Perfil p1 = repositorio.buscar(p.getSeguidores().get(i));
                    if(p1!=null)
                        if(p1.isAtivo())
                            c++;
                }
            }else
                throw new PDException();
        else
            throw new PIException();
        return c;
    }

    public Vector<Perfil> seguidores(String usuario) throws PIException, PDException{
        Perfil p = repositorio.buscar(usuario);
        Vector <Perfil> segAtv = new Vector();
        System.out.println("seguidores:");
        if(p!=null)
            if(p.isAtivo()){
                for(int i=0;i<p.getSeguidores().size();i++){
                    Perfil p1 = repositorio.buscar(p.getSeguidores().get(i));
                    if(p1!=null)
                        if(p1.isAtivo()){
                            System.out.println(p1.getUsuario());
                            segAtv.add(p1);
                    }
                }
            }else
                throw new PDException();
        else
            throw new PIException();
        return segAtv;
    }
    
    public void editar(String usuario){
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            if(p.isAtivo()){
            try {
                repositorio.atualizar(p);
                System.out.println("Perfil atualizado!");
            } catch (UNCException ex) {
                ex.getMessage();
            }
            }
            else
                System.out.println("Perfil inativo.");
        else
            System.out.println("Perfil inexistente");
    }
    
    public boolean verificar(String usuario){
        Perfil p = repositorio.buscar(usuario);
        if(p!=null)
            return true;
        else
            return false;
    }
    
    
}