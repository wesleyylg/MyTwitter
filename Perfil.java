package mytwitter;

import java.util.Vector;

public abstract class Perfil {
    private String usuario;
    private Vector<String> seguidores;
    private Vector<String> seguindo;
    private Vector<Tweet> meusTweets;
    private Vector<Tweet> timeline;
    private boolean ativo;
    
    public Perfil(String usuario){
        this.usuario = usuario;
        this.ativo = true;
        seguidores = new Vector();
        seguindo = new Vector();
        timeline = new Vector();
        meusTweets = new Vector();
    }
    
    public String getUsuario(){
        return this.usuario;
    }
    
    public Vector<String> getSeguidores(){
        return this.seguidores;
    }
    
    public Vector<String> getSeguindo(){
        return this.seguindo;
    }
    
    public Vector<Tweet> getMeusTweets(){
        return this.meusTweets;
    }
    
    public Vector<Tweet> getTimeline(){
        return this.timeline;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public void setAtivo(boolean valor){
        this.ativo = valor;
    }
    
    public void addSeguidor(String seguidor){
        seguidores.add(seguidor);
    }
    
    public void addSeguindo(String usuario){
        seguindo.add(usuario);
    }
    
    public void addTweet(Tweet tweet){
        timeline.add(tweet);
    }
    
    public void salvarTweet(Tweet tweet){
        meusTweets.add(tweet);
    }
    
    public boolean isAtivo(){
        if(ativo)
            return true;
        else
            return false;
    }
}