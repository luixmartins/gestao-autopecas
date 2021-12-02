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

    private static  int nivel_acesso;
    private static int cod_funcionario;
    private static String senha;
    private static String nome;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Usuario.nome = nome;
    }

    public Usuario() {

    }

    public Usuario(int nivel_acesso, int cod_funcionario, String senha, String nome) {
        this.nivel_acesso = nivel_acesso;
        this.cod_funcionario = cod_funcionario;
        this.senha = senha;
        this.nome = nome;
    }

    public static int getNivel_acesso() {
        return nivel_acesso;
    }

    public static void setNivel_acesso(int nivel_acesso) {
        Usuario.nivel_acesso = nivel_acesso;
    }

    public static int getCod_funcionario() {
        return cod_funcionario;
    }

    public static void setCod_funcionario(int cod_funcionario) {
        Usuario.cod_funcionario = cod_funcionario;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Usuario.senha = senha;
    }
    
    

}
