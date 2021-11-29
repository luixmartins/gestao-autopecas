/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author luizh
 */
public class Usuario {
    private int nivel_acesso;
    private int cod_funcionario;
    private String senha;
    
    public Usuario(){
        
    }

    public Usuario(int nivel_acesso, int cod_funcionario, String senha) {
        this.nivel_acesso = nivel_acesso;
        this.cod_funcionario = cod_funcionario;
        this.senha = senha;
    }

    public int getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(int nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
