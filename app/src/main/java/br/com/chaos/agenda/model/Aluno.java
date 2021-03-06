package br.com.chaos.agenda.model;

import java.io.Serializable;

/**
 * Created by gersonapollo on 09/06/17.
 */

public class Aluno implements Serializable{

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String site;
    private Double nota;
    private String caminhFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhFoto() {
        return caminhFoto;
    }

    public void setCaminhFoto(String caminhFoto) {
        this.caminhFoto = caminhFoto;
    }

    @Override
    public String toString() {
        return getId()+" - "+ getNome();
    }
}
