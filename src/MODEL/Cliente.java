package MODEL;

public class Cliente {

    /* Atributos do Cliente*/
    private int cod_cliente;
    private String nome_cliente;
    private int tipo_cliente;
    /* Associando Cliente Fisica */
    private ClienteFisica cliFisica;
    /* Associando Cliente Juridica*/
    private ClienteJuridica cliJuridica;
    /* Associando Endereço */
    private ClienteEndereco cliEndereco;
    /* Associando Contato*/
    private ClienteContato cliContato;

    /* Construtores */
    public Cliente() {
        this.cliFisica = new ClienteFisica();
        this.cliJuridica = new ClienteJuridica();
        this.cliEndereco = new ClienteEndereco();
        this.cliContato = new ClienteContato();
    }

    public Cliente(int cod_cliente, String nome_cliente, int tipo_cliente) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.tipo_cliente = tipo_cliente;
        this.cliFisica = new ClienteFisica();
        this.cliJuridica = new ClienteJuridica();
        this.cliEndereco = new ClienteEndereco();
        this.cliContato = new ClienteContato();
    }

    public Cliente(int cod_cliente, String nome_cliente, int tipo_cliente, ClienteFisica cliFisica, ClienteJuridica cliJuridica, ClienteEndereco cliEndereco, ClienteContato cliContato) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.tipo_cliente = tipo_cliente;
        this.cliFisica = cliFisica;
        this.cliJuridica = cliJuridica;
        this.cliEndereco = cliEndereco;
        this.cliContato = cliContato;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(int tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public ClienteFisica getCliFisica() {
        return cliFisica;
    }

    public void setCliFisica(ClienteFisica cliFisica) {
        this.cliFisica = cliFisica;
    }

    public ClienteJuridica getCliJuridica() {
        return cliJuridica;
    }

    public void setCliJuridica(ClienteJuridica cliJuridica) {
        this.cliJuridica = cliJuridica;
    }

    public ClienteEndereco getCliEndereco() {
        return cliEndereco;
    }

    public void setCliEndereco(ClienteEndereco cliEndereco) {
        this.cliEndereco = cliEndereco;
    }

    public ClienteContato getCliContato() {
        return cliContato;
    }

    public void setCliContato(ClienteContato cliContato) {
        this.cliContato = cliContato;
    }

}
