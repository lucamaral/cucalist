package bd;

import java.util.Date;

public class Pessoa {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private Date aniversario;
	private int IDEmpresa;
	
	public Pessoa(){}
	
	public Pessoa(String nome, String email, String senha, Date aniversario, int IDEmpresa){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.aniversario = aniversario;
		this.IDEmpresa = IDEmpresa;
	}
	
	public Pessoa(int id, String nome, String email, String senha, Date aniversario, int IDEmpresa){
		this(nome, email, senha, aniversario, IDEmpresa);
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getSenha(){
		return this.senha;
	}
	
	public Date getAniversario(){
		return this.aniversario;
	}
	
	public int getIDEmpresa(){
		return this.IDEmpresa;
	}
	
	public void setID(int idNovo){
		this.id = idNovo;
	}
	
	public void setNome(String nomeNovo){
		this.nome = nomeNovo;
	}

	public void setEmail(String emailNovo){
		this.email = emailNovo;
	}

	public void setSenha(String senhaNovo){
		this.senha = senhaNovo;
	}

	public void setAniversario(Date aniversarioNovo){
		this.aniversario = aniversarioNovo;
	}

	public void setIDEmpresa(int IDEmpresaNovo){
		this.IDEmpresa = IDEmpresaNovo;
	}
	
}
