package mytwitter;

public class PessoaJuridica extends Perfil{
    private long CNPJ;
    private String empresa;
    
    public PessoaJuridica(String usuario) {
        super(usuario);
    }
    
    public long getCNPJ(){
        return this.CNPJ;
    }
    
    public void setCNPJ(long cnpj){
        this.CNPJ = cnpj;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    
}