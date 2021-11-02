package MODEL;

public class ClienteJuridica extends Cliente{

    private String cnpj;
    private String inscricao_estadual;

    /* Métodos Getter e Setter */
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

}
