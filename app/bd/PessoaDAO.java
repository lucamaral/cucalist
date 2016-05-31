package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Evento;
import models.Pessoa;

public class PessoaDAO {

	public void inserirPessoa(Connection con, Pessoa pessoa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO pessoa(nome, email, senha, aniversario, Empresa_id_empresa)" + 
				"VALUES(?, ?, MD5(?), ?, ?);");
				stm.setString(1, pessoa.getNome());
				stm.setString(2, pessoa.getEmail());
				stm.setString(3, pessoa.getSenha());
				stm.setDate(4, new java.sql.Date(pessoa.getAniversario().getTime()));
				stm.setInt(5, pessoa.getIDEmpresa());
		stm.executeUpdate();
		PreparedStatement stm2 = con.prepareStatement("SELECT id_pessoa FROM pessoa WHERE nome = ?;");
		stm2.setString(1, pessoa.getNome());
		ResultSet rs2 = stm2.executeQuery();
		if(rs2.next()){
			pessoa.setID(rs2.getInt("id_pessoa"));
		}
	}
	
	public boolean removerPessoa(Connection con, Pessoa pessoa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("DELETE FROM pessoa WHERE id_pessoa = ?;");
		stm.setInt(1, pessoa.getID());
		return stm.executeUpdate() > 0;
	}
	
	public boolean atualizarPessoa(Connection con, Pessoa pessoa) throws SQLException{
		String sql = "Update pessoa SET senha = MD5(?), nome = ?, email = ?, aniversario = ?, empresa_id_empresa = ? WHERE id_pessoa = ?;";
		PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, pessoa.getSenha());
			stm.setString(2, pessoa.getNome());
			stm.setString(3, pessoa.getEmail());
			stm.setDate(4, new java.sql.Date(pessoa.getAniversario().getTime()));
			stm.setInt(5, pessoa.getIDEmpresa());
			stm.setInt(6, pessoa.getID());
		return stm.executeUpdate() > 0;
	}
	
	public boolean validaLogin(Connection con, String senha, String email) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE email = ? AND senha = MD5(?);");
			stm.setString(1, email);
			stm.setString(2, senha);
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public List<Pessoa> consultaPorEmpresa(Connection con, Empresa empresa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE empresa_id_empresa = ?;");
		stm.setInt(1, empresa.getID());
		ResultSet rs = stm.executeQuery();
		List<Pessoa> pessoas = new ArrayList();
		while(rs.next()){
			Pessoa pessoa = getPessoa(rs);
			pessoas.add(pessoa);
		}
		return pessoas;
	}
	
	public List<Pessoa> consultaPorEvento(Connection con, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT pessoa_id_pessoa FROM participantes WHERE evento_id_evento = ?;");
		stm.setInt(1, evento.getID());
		ResultSet rs = stm.executeQuery();
		List<Pessoa> pessoas = new ArrayList();
		while(rs.next()){
			Pessoa pessoa = getPessoa(rs);
			pessoas.add(pessoa);
		}
		return pessoas;
	}
	
	public Pessoa consultaPessoa(Connection con, int id) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE id_pessoa = ?;");
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		Pessoa pessoa = null;
		if(rs.next()){
			pessoa = getPessoa(rs);
		}
		return pessoa;
	}
	
	private Pessoa getPessoa(ResultSet rs) throws SQLException {
		int idEmpresa = rs.getInt("empresa_id_empresa");
		int id = rs.getInt("id_pessoa");
		String nome = rs.getString("nome");
		String email = rs.getString("email");
		String senha = rs.getString("senha");
		Date aniversario = new java.util.Date(rs.getDate("aniversario").getTime());
		Pessoa pessoa = new Pessoa(id, nome, email, senha, aniversario, idEmpresa);
		return pessoa;
	}
	
}
