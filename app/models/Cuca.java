package models;

public class Cuca {
    private String tipo;
    private String origem;
    private long id;
    private int nota;
    private double notaMedia;

    public Cuca(final String origem, final String tipo, final long id) {
        this.origem = origem;
        this.tipo = tipo;
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(final int nota) {
        this.nota = nota;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(final double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public Cuca() {
        super();
    }

    public Cuca(final String origem, final String tipo) {
        this.origem = origem;
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getOrigem() {
        return this.origem;
    }

    public long getID() {
        return this.id;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public void setID(final long id) {
        this.id = id;
    }

}
