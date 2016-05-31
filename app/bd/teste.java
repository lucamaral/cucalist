package bd;

import java.sql.Connection;
import java.util.Date;

import models.Evento;

import java.sql.SQLException;
import java.util.Calendar;

public class teste {

	public static void main(String[] args) throws SQLException{
		ConexaoDB condb = new ConexaoDB();
		Connection con = condb.getConexaoMySQL();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2016, 8, 19);
		Date date = cal.getTime();
		EventoDAO dao = new EventoDAO();
		Evento evento = new Evento("aniversario", "aniversario do estagiario", date, 1);
		dao.inserirEvento(con, evento);
	}
}
