package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Evento;
import validators.EventoValidator;

public class EventoDAO {

    EventoValidator validator = new EventoValidator();

    public void addEvento(final Connection con, final Evento evento) throws SQLException {
        validator.validate(evento);
        final PreparedStatement stm = con.prepareStatement("INSERT INTO evento(titulo, descricao, prazo)" + "VALUES(?, ?, ?);");
        stm.setString(1, evento.getTitulo());
        stm.setString(2, evento.getDescricao());
        stm.setDate(3, new java.sql.Date(evento.getPrazo().getTime()));
        stm.executeUpdate();

        final PreparedStatement stm2 = con.prepareStatement("SELECT id_evento FROM evento WHERE titulo = ?;");
        stm2.setString(1, evento.getTitulo());
        final ResultSet rs2 = stm2.executeQuery();
        if (rs2.next()) {
            evento.setID(rs2.getInt("id_evento"));
        }
        for (final Integer partID : evento.getParticipantes()) {
            addParticipante(con, partID, evento.getID());
        }
    }

    public boolean deleteEvento(final Connection con, final Evento evento) throws SQLException {
        final PreparedStatement stm1 = con.prepareStatement("DELETE FROM participantes WHERE evento_id_evento = ?;");
        stm1.setInt(1, evento.getID());
        stm1.executeUpdate();
        final PreparedStatement stm2 = con.prepareStatement("DELETE FROM evento WHERE id_evento = ?;");
        stm2.setInt(1, evento.getID());
        return stm2.executeUpdate() > 0;
    }

    public boolean updateEvento(final Connection con, final Evento evento) throws SQLException {
        final String sql = "Update evento SET titulo = ?, descricao = ?, prazo = ? WHERE id_evento = ?;";
        final PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, evento.getTitulo());
        stm.setString(2, evento.getDescricao());
        stm.setDate(3, new java.sql.Date(evento.getPrazo().getTime()));
        stm.setInt(4, evento.getID());
        return stm.executeUpdate() > 0;
    }

    public void addParticipante(final Connection con, final int pessoa, final int evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("INSERT INTO participantes(Pessoa_id_pessoa, evento_id_evento) VALUES(?, ?);");
        stm.setInt(1, pessoa);
        stm.setInt(2, evento);
        stm.executeUpdate();
    }

    public List<Evento> getAllEventos(final Connection con) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
        final ResultSet rs = stm.executeQuery();
        final List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            final Evento evento = buildEvento(rs);
            eventos.add(evento);

        }
        return eventos;
    }

    public List<Evento> getEventoSearchTerm(final Connection con, final String searchTerm) throws SQLException {
        if (searchTerm == "") {
            return getAllEventos(con);
        }
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
        final ResultSet rs = stm.executeQuery();
        final List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            final Evento evento = buildEvento(rs);
            if (searchTerm.contains(evento.getTitulo())) {
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public Evento getEventoID(final Connection con, final int id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento WHERE id_evento = ?;");
        stm.setInt(1, id);
        final ResultSet rs = stm.executeQuery();
        Evento evento = null;
        if (rs.next()) {
            evento = buildEvento(rs);
        }
        return evento;
    }

    private Evento buildEvento(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id_evento");
        final String titulo = rs.getString("titulo");
        final String descricao = rs.getString("descricao");
        final Date prazo = new java.util.Date(rs.getDate("prazo").getTime());
        final Evento evento = new Evento(id, titulo, descricao, prazo);
        return evento;
    }
}
