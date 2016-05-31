package bd;

import java.util.List;

import models.Pessoa;

public class Empresa {

	private String nome;
	private int id;
	private List<Pessoa> funcionarios;
	
	public List<Pessoa> getFuncionarios(){
		return this.funcionarios;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setFuncionarios(List<Pessoa> newFuncionarios){
		this.funcionarios = newFuncionarios;
	}
	
	public void setID(int newID){
		this.id = newID;
	}
	
	public void setNome(String newNome){
		this.nome = newNome;
	}
	
	
}
