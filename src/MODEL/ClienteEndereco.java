package MODEL;

public class ClienteEndereco {

    private int cod_clienteendereco;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private int cep;
    
    /* Método Getter e Setter */

    public int getCod_clienteendereco() {
        return cod_clienteendereco;
    }

    public void setCod_clienteendereco(int cod_clienteendereco) {
        this.cod_clienteendereco = cod_clienteendereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
    
    
}
