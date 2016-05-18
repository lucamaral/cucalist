package bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cuca;
import play.db.DB;

public class teste {

	public static void main(String[] args) throws SQLException{
		ConexaoDB condb = new ConexaoDB();
		Connection con = condb.getConexaoMySQL();
		CucaDAO dao = new CucaDAO();
		System.out.println(dao.consultaCucaID(con, 2).getOrigem());
		dao.removerCuca(con, dao.consultaCucaID(con, 2));
		System.out.println(dao.consultaCucaID(con, 2).getOrigem());
	}
}
