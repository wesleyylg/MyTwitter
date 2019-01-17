package mytwitter;

public class PessoaFisica extends Perfil{
    private long CPF;
    private int idade;
    private String sexo;

    public PessoaFisica(String usuario) {
        super(usuario);
    }
    
    public long getCPF(){
        return this.CPF;
    }
    
    public void setCPF(long cpf){
        this.CPF = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
}