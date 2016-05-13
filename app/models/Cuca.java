package models;

//@Entity(name = "cuca")
public class Cuca /* extends Model */ {
	
	//usando essa variavel pra testar como funciona, depois implemento banco de dados
	private static long classeID = 1;
	
	
	/*
     * private static final long serialVersionUID = 2832319250723749667L;
     */

    /*
     * @Id
     * 
     * @GeneratedValue
     * 
     * @Column(name = "cuca_id")
     */
    private Long id;

    // @Column(name = "cuca_tipo")
    private String tipo;

    // @Column(name = "cuca_origem")
    private String origem;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public long getClasseID(){
    	long id = this.classeID;
    	classeID++;
    	return id;
    }
    
    // public static Finder<Long, Cuca> find = new Finder<>(Long.class, Cuca.class);

}
