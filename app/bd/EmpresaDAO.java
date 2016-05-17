package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaDAO {

	public void inserirEmpresa(Connection con, Empresa empresa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("INSERT INTO empresa (nome) VALUES (?)");
			stm.setString(1, empresa.getNome());
		stm.executeUpdate();
		PreparedStatement stm2 = con.prepareStatement("SELECT id_empresa FROM empresa WHERE nome = ?;");
		stm2.setString(1, empresa.getNome());
		ResultSet rs2 = stm2.executeQuery();
		if(rs2.next()){
			empresa.setID(rs2.getInt("id_empresa"));
		}
	}
	
	public boolean removerEmpresa(Connection con, Empresa empresa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("DELETE FROM empresa WHERE id_empresa = ?;");
		stm.setInt(1, empresa.getID());
		return stm.executeUpdate() > 0;
	}
	
	public void atualizarEmpresa(Connection con, Empresa empresa) throws SQLException{
		PreparedStatement stm = con.prepareStatement("UPDATE empresa SET nome = ? WHERE id_empresa = ?");
		stm.setString(1, empresa.getNome());
		stm.setInt(2, empresa.getID());
		stm.executeUpdate();
	}
	
	public void atualizarFuncionarios(Connection con, Empresa empresa, PessoaDAO dao) throws SQLException{
		empresa.setFuncionarios(dao.consultaPorEmpresa(con, empresa));
	}
	
	//consulta por empresa retorna lista de funcionarios
	
}

