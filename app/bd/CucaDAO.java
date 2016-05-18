package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cuca;

public class CucaDAO {
	
	public void inserirCuca(Connection con, Cuca cuca) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO cuca(tipo, origem)" +
		"VALUES(?, ?);");
			stm.setString(1, cuca.getTipo());
			stm.setString(2, cuca.getOrigem());
		stm.executeUpdate();
		
		PreparedStatement stm2 = con.prepareStatement("SELECT id_cuca FROM cuca WHERE tipo = ? AND origem = ?;");
		stm2.setString(1, cuca.getTipo());
		stm2.setString(2, cuca.getOrigem());
		ResultSet rs2 = stm2.executeQuery();
		if(rs2.next()){
			cuca.setID(rs2.getLong("id_cuca"));
		}
	}
	
	public List<Cuca> consultaCucaSearchTerm(Connection con, String searchTerm) throws SQLException{
		PreparedStatement stm1 = con.prepareStatement("SELECT CONCAT(tipo, ' ', origem) FROM cuca;");
		ResultSet rs1 = stm1.executeQuery();
		PreparedStatement stm2 = con.prepareStatement("SELECT * FROM cuca;");
		List<Cuca> cucas = new ArrayList<>();
		ResultSet rs2 = stm2.executeQuery();
		while(rs2.next()){
			Cuca cuca = getCuca(rs2);
			if(searchTerm.contains(cuca.getOrigem()) || searchTerm.contains(cuca.getTipo())){
				cucas.add(cuca);
			}
		}
		return cucas;		
	}
	
	public Cuca consultaCucaID(Connection con, long id) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE id_cuca = ?;");
		stm.setLong(1, id);
		ResultSet rs = stm.executeQuery();
		Cuca cuca = new Cuca();
		while(rs.next()){
			cuca = getCuca(rs);
		}
		return cuca;
	}
	
	public List<Cuca> consultaAllCucas(Connection con) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca;");
		ResultSet rs = stm.executeQuery();
		List<Cuca> cucas = new ArrayList<>();
		while(rs.next()){
			Cuca cuca = getCuca(rs);
			cucas.add(cuca);
		}
		return cucas;
	}
	
	
	public List<Cuca> consultaCucaOrigem(Connection con, String origem) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE origem = ?;");
		stm.setString(1, origem);
		ResultSet rs = stm.executeQuery();
		List<Cuca> cucas = new ArrayList<>();
		while(rs.next()){
			Cuca cuca = getCuca(rs);
			cucas.add(cuca);
		}
		return cucas;
	}

	public List<Cuca> consultaCucaTipo(Connection con, String tipo) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE tipo = ?;");
		stm.setString(1, tipo);
		ResultSet rs = stm.executeQuery();
		List<Cuca> cucas = new ArrayList<>();
		while(rs.next()){
			Cuca cuca = getCuca(rs);
			cucas.add(cuca);
		}
		return cucas;
	}
	
	public boolean removerCuca(Connection con, Cuca cuca) throws SQLException{
		PreparedStatement stm = con.prepareStatement("DELETE FROM cuca WHERE id_cuca = ?;");
		stm.setLong(1, cuca.getID());
		return stm.executeUpdate() > 0;
	}
	
	public boolean atualizarCuca(Connection con, Cuca cuca) throws SQLException{
		String sql = "Update cuca SET tipo = ?, origem = ? WHERE id_evento = ?;";
		PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cuca.getTipo());
			stm.setString(2, cuca.getOrigem());
			stm.setLong(3, cuca.getID());
		return stm.executeUpdate() > 0;
	}
	
	//mesmo esquema de parti, só vem id
	
	public List<Cuca> getCucaEvento(Connection con, Evento evento) throws SQLException{
		PreparedStatement stm = con.prepareStatement("SELECT cuca_id_cuca FROM opcoes WHERE evento_id_evento = ?;");
		stm.setInt(1, evento.getID());
		ResultSet rs = stm.executeQuery();
		List<Cuca> cucas = new ArrayList<>();
		while(rs.next()){
			Cuca cuca = getCuca(rs);
			cucas.add(cuca);
		}
		return cucas;
	}
	
	private Cuca getCuca(ResultSet rs) throws SQLException {
		long id = rs.getInt("id_cuca");
		String tipo = rs.getString("tipo");
		String origem = rs.getString("origem");
		Cuca cuca = new Cuca(origem, tipo, id);
		return cuca;
	}
	
	
}

