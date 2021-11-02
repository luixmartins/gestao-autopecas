package MODEL;

public class ClienteContato {

    private int telefone_cliente;
    private int celular_cliente;
    private String email;

    public int getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(int telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public int getCelular_cliente() {
        return celular_cliente;
    }

    public void setCelular_cliente(int celular_cliente) {
        this.celular_cliente = celular_cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
