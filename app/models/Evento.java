package models;

import java.util.Date;
import java.util.List;

public class Evento {

    private int id;
    private String descricao;
    private String titulo;
    private Date prazo;
    private List<Integer> participantes;
    private List<Integer> cucas;

    public Evento(final int id, final String titulo, final String descricao, final Date prazo, final List<Integer> participantes, final List<Integer> cucas) {
        this(titulo, descricao, prazo, participantes, cucas);
        this.id = id;
    }

    public Evento(final String titulo, final String descricao, final Date prazo, final List<Integer> participantes, final List<Integer> cucas) {
        this(titulo, descricao, prazo);
        this.participantes = participantes;
        this.cucas = cucas;
    }

    public Evento(final int id, final String titulo, final String descricao, final Date prazo) {
        this(titulo, descricao, prazo);
        this.id = id;
    }

    public Evento(final String titulo, final String descricao, final Date prazo) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.prazo = prazo;
        this.id = 0;
    }

    public Evento() {
        super();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getID() {
        return this.id;
    }

    public Date getPrazo() {
        return this.prazo;
    }

    public List<Integer> getParticipantes() {
        return this.participantes;
    }

    public List<Integer> getCucas() {
        return this.cucas;
    }

    public void setID(final int id) {
        this.id = id;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public void setPrazo(final Date prazo) {
        this.prazo = prazo;
    }

    public void setParticipantes(final List<Integer> participantesPessoa) {
        this.participantes = participantesPessoa;
    }

    public void setCucas(final List<Integer> cucas) {
        this.cucas = cucas;
    }

}
