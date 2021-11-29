/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Date;
/**
 *
 * @author luizh
 */
public class Funcionario {
    private Endereco funcEndereco;
    private Contato funcContato;
    
    private int cod_funcionario;
    private String nome_funcionario;
    private String cpf_funcionario;
    private String rg_funcionario;
    private String senha;
    private Date data_admissao;
    private int status;
    private int nivel_acesso;

    public Funcionario() {
        this.funcContato = new Contato();
        this.funcEndereco = new Endereco();
    }

    public Funcionario(int cod_funcionario, String nome_funcionario, String cpf_funcionario, String rg_funcionario, String senha, Date data_admissao, int status, int nivel_acesso) {
        this.cod_funcionario = cod_funcionario;
        this.nome_funcionario = nome_funcionario;
        this.cpf_funcionario = cpf_funcionario;
        this.rg_funcionario = rg_funcionario;
        this.senha = senha;
        this.data_admissao = data_admissao;
        this.status = status;
        this.nivel_acesso = nivel_acesso;
        this.funcContato = new Contato();
        this.funcEndereco = new Endereco();
    }

    public Funcionario(Endereco funcEndereco, Contato funcContato, int cod_funcionario, String nome_funcionario, String cpf_funcionario, String rg_funcionario, String senha, Date data_admissao, int status, int nivel_acesso) {
        this.funcEndereco = funcEndereco;
        this.funcContato = funcContato;
        this.cod_funcionario = cod_funcionario;
        this.nome_funcionario = nome_funcionario;
        this.cpf_funcionario = cpf_funcionario;
        this.rg_funcionario = rg_funcionario;
        this.senha = senha;
        this.data_admissao = data_admissao;
        this.status = status;
        this.nivel_acesso = nivel_acesso;
    }

    public Endereco getFuncEndereco() {
        return funcEndereco;
    }

    public void setFuncEndereco(Endereco funcEndereco) {
        this.funcEndereco = funcEndereco;
    }

    public Contato getFuncContato() {
        return funcContato;
    }

    public void setFuncContato(Contato funcContato) {
        this.funcContato = funcContato;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getCpf_funcionario() {
        return cpf_funcionario;
    }

    public void setCpf_funcionario(String cpf_funcionario) {
        this.cpf_funcionario = cpf_funcionario;
    }

    public String getRg_funcionario() {
        return rg_funcionario;
    }

    public void setRg_funcionario(String rg_funcionario) {
        this.rg_funcionario = rg_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(int nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }
    
    
}
