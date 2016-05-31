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

public class EventoDAO {
	
	public void inserirEvento(Connection con, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO evento(titulo, descricao, prazo, Empresa_id_empresa)" +
		"VALUES(?, ?, ?, ?);");
			stm.setString(1, evento.getTitulo());
			stm.setString(2, evento.getDescricao());
			stm.setDate(3, new java.sql.Date(evento.getPrazo().getTime()));
			stm.setInt(4, evento.getIDEmpresa());
		stm.executeUpdate();
		
		PreparedStatement stm2 = con.prepareStatement("SELECT id_evento FROM evento WHERE titulo = ?;");
		stm2.setString(1, evento.getTitulo());
		ResultSet rs2 = stm2.executeQuery();
		if(rs2.next()){
			evento.setID(rs2.getInt("id_evento"));
		}
	}
	
	public boolean removerEvento(Connection con, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("DELETE FROM pessoa WHERE id_pessoa = ?;");
		stm.setInt(1, evento.getID());
		return stm.executeUpdate() > 0;
	}
	
	public boolean atualizarEvento(Connection con, Evento evento) throws SQLException{
		String sql = "Update evento SET titulo = ?, descricao = ?, prazo = ?, empresa_id_empresa = ? WHERE id_evento = ?;";
		PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, evento.getTitulo());
			stm.setString(2, evento.getDescricao());
			stm.setDate(3, new java.sql.Date(evento.getPrazo().getTime()));
			stm.setInt(4, evento.getIDEmpresa());
			stm.setInt(5, evento.getID());
		return stm.executeUpdate() > 0;
	}
	
	public void addParticipante(Connection con, Pessoa pessoa, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO participantes(Pessoa_id_pessoa, evento_id_evento) VALUES(?, ?);");
			stm.setInt(1, pessoa.getID());
			stm.setInt(2, evento.getID());
		stm.executeUpdate();
	}
	
	public int getIDParticipante(Connection con, Pessoa pessoa, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT id_participante FROM participantes WHERE pessoa_id_pessoa = " + 
			"? AND (evento_id_evento = ?);");
		stm.setInt(1, pessoa.getID());
		stm.setInt(2, evento.getID());
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
			return rs.getInt("id_participante");
		}
		return 0;
	}

	public void atualizarOpcoes(Connection con, Evento evento, CucaDAO dao) throws SQLException{
		evento.setOpcoes(dao.getCucaEvento(con, evento));
	}
	
	public void atualizarParticipantes(Connection con, Evento evento, PessoaDAO dao) throws SQLException{
		evento.setParticipantes(dao.consultaPorEvento(con, evento));
	}
	
	public void declararNumCucas(Connection con, Pessoa pessoa, Evento evento, int cuca_total) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO numerocuca(participantes_id_participante, cuca_total, cuca_deve, cuca_paga)" + 
		"VALUES(?, ?, ?, ?);");
			stm.setInt(1, getIDParticipante(con, pessoa, evento));
			stm.setInt(2, cuca_total);
			stm.setInt(3, cuca_total);
			stm.setInt(4, 0);
		stm.executeUpdate();
	}
	
	public int getCucaTotal(Connection con, Pessoa pessoa, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT cuca_total FROM numerocuca WHERE participantes_id_participante = ?;");
		stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
			return rs.getInt("cuca_total");
		}
		return 0;
	}
	
	public int getCucaDeve(Connection con, Pessoa pessoa, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT cuca_deve FROM numerocuca WHERE participantes_id_participante = ?;");
		stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
			return rs.getInt("cuca_deve");
		}
		return 0;
	}
	
	public int getCucaPaga(Connection con, Pessoa pessoa, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT cuca_paga FROM numerocuca WHERE participantes_id_participante = ?;");
		stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
			return rs.getInt("cuca_paga");
		}
		return 0;
	}
	
	public void updateCucaPaga(Connection con, Pessoa pessoa, Evento evento, int cucas_pagas) throws SQLException{
		PreparedStatement stm = con.prepareStatement("UPDATE numerocuca SET cuca_deve = ?, " +
		"cuca_paga = ? WHERE Participantes_id_participante = ?;");
			int cuca_deve = getCucaTotal(con, pessoa, evento) - cucas_pagas;
			stm.setInt(1, cuca_deve);
			stm.setInt(2, cucas_pagas);
			stm.setInt(3, this.getIDParticipante(con, pessoa, evento));
		stm.executeUpdate();
	}
	
	//só vem o id, usar id do parti pra consultar evento e voltar todas as informações
	
	public List<Evento> consultaEventoPessoa(Connection con, Pessoa pessoa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT evento_id_evento FROM participantes WHERE pessoa_id_pessoa = ?;");
		stm.setInt(1, pessoa.getID());
		ResultSet rs = stm.executeQuery();
		List<Evento> eventos = new ArrayList<>();
		while(rs.next()){
			Evento eventoY = getEvento(rs);
			eventos.add(eventoY);
		}
		return eventos;
	}
	
	public List<Evento> getAllEventos(Connection con) throws SQLException{
		System.out.println("cheguei no all eventos");
		PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
		ResultSet rs = stm.executeQuery();
		List<Evento> eventos = new ArrayList<>();
		while(rs.next()){
			Evento evento = getEvento(rs);
			eventos.add(evento);
			
		}
		return eventos;
	}
	
	public List<Evento> consultaEventoSearchTerm(Connection con, String searchTerm) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
		ResultSet rs = stm.executeQuery();
		List<Evento> eventos = new ArrayList<>();
		while(rs.next()){
			Evento evento = getEvento(rs);
			if(searchTerm.contains(evento.getTitulo())){
				eventos.add(evento);
			}
		}
		return eventos;
	}
	
	public Evento consultaEventoID(Connection con, int id) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM evento WHERE id_evento = ?;");
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		Evento evento = null;
		if(rs.next()){
			evento = getEvento(rs);
		}
		return evento;
	}
	
	private Evento getEvento(ResultSet rs) throws SQLException {
		int idEmpresa = rs.getInt("empresa_id_empresa");
		int id = rs.getInt("id_evento");
		String titulo = rs.getString("titulo");
		String descricao = rs.getString("descricao");
		Date prazo = new java.util.Date(rs.getDate("prazo").getTime());
		Evento evento = new Evento(id, titulo, descricao, prazo, idEmpresa);
		return evento;
	}
}
