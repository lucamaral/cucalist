package models;

public class Cuca {
	private String tipo;
	private String origem;
	private long id;
	
	public Cuca(String origem, String tipo, long id){
		this.origem = origem;
		this.tipo = tipo;
		this.id = id;
	}
	
	public Cuca(){
		super();
	}
	
	public Cuca(String origem, String tipo){
		this.origem = origem;
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public String getOrigem(){
		return this.origem;
	}
	
	public long getID(){
		return this.id;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public void setOrigem(String origem){
		this.origem = origem;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
}

