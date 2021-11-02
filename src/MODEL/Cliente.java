package MODEL;

public abstract class Cliente {

    /* Atributos do Cliente*/
    private int cod_cliente;
    private String nome_cliente;
    private int tipo_cliente;
    /* Atributos classe Contato e Endereço*/
    private ClienteEndereco cliEnd;
    private ClienteContato cliCont;

    /* Constructor */
    public Cliente() {
        this.cliEnd = new ClienteEndereco();
        this.cliCont = new ClienteContato();
    }

    public Cliente(int cod_cliente, String nome_cliente, int tipo_cliente) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.tipo_cliente = tipo_cliente;
        this.cliEnd = new ClienteEndereco();
        this.cliCont = new ClienteContato();
    }

    public Cliente(int cod_cliente, String nome_cliente, int tipo_cliente, ClienteEndereco cliEnd, ClienteContato cliCont) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.tipo_cliente = tipo_cliente;
        this.cliEnd = cliEnd;
        this.cliCont = cliCont;
    }

    /* Métodos Getter e Setter */
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

    public ClienteEndereco getCliEnd() {
        return cliEnd;
    }

    public void setCliEnd(ClienteEndereco cliEnd) {
        this.cliEnd = cliEnd;
    }

    public ClienteContato getCliCont() {
        return cliCont;
    }

    public void setCliCont(ClienteContato cliCont) {
        this.cliCont = cliCont;
    }

    
}
