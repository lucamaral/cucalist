package models;

import java.util.Date;

public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private Date aniversario;

    public Pessoa() {
    }

    public Pessoa(final String nome, final String email, final String senha, final Date aniversario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.aniversario = aniversario;
    }

    public Pessoa(final int id, final String nome, final String email, final String senha, final Date aniversario) {
        this(nome, email, senha, aniversario);
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public Date getAniversario() {
        return this.aniversario;
    }

    public void setID(final int idNovo) {
        this.id = idNovo;
    }

    public void setNome(final String nomeNovo) {
        this.nome = nomeNovo;
    }

    public void setEmail(final String emailNovo) {
        this.email = emailNovo;
    }

    public void setSenha(final String senhaNovo) {
        this.senha = senhaNovo;
    }

    public void setAniversario(final Date aniversarioNovo) {
        this.aniversario = aniversarioNovo;
    }

}
