package bd;

import java.util.Date;
import java.util.List;

import models.Cuca;

public class Evento {

	private int id;
	private String status;
	private String descricao;
	private String titulo;
	private Date prazo;
	private int empresaID;
	private List<Cuca> opcoes;
	private List<Pessoa> participantes;
	
	public Evento(int id, String titulo, String descricao, Date prazo, String status, int empresaID){
		this.descricao = descricao;
		this.status = status;
		this.titulo = titulo;
		this.prazo = prazo;
		this.empresaID = empresaID;
	}
	
	public Evento(String titulo, String descricao, Date prazo, int empresaID){
		this.descricao = descricao;
		this.titulo = titulo;
		this.prazo = prazo;
		this.empresaID = empresaID;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public int getID(){
		return this.id;
	}

	public int getIDEmpresa(){
		return this.empresaID;
	}
	
	public Date getPrazo(){
		return this.prazo;
	}
	
	public List getOpcoes(){
		return this.opcoes;
	}
	
	public List getParticipantes(){
		return this.participantes;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public void setPrazo(Date prazo){
		this.prazo = prazo;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public void setIDEmpresa(int idempresa){
		this.empresaID = idempresa;
	}
	
	public void setOpcoes(List opcoesCuca){
		this.opcoes = opcoesCuca;
	}
	
	public void setParticipantes(List participantesPessoa){
		this.participantes = participantesPessoa;
	}
	
}
