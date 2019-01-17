package mytwitter;

import Excecoes.UJCException;
import Excecoes.UNCException;

public interface IRepositorioUsuario {
    public void cadastrar(Perfil usuario) throws UJCException;
    public Perfil buscar(String usuario);
    public void atualizar(Perfil usuario) throws UNCException;
}