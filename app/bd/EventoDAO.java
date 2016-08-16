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
import validators.EventoValidator;

public class EventoDAO {

    EventoValidator validator = new EventoValidator();

    public void inserirEvento(final Connection con, final Evento evento) throws SQLException {
        validator.validate(evento);
        final PreparedStatement stm = con.prepareStatement("INSERT INTO evento(titulo, descricao, DATA_FINALl)" + "VALUES(?, ?, ?);");
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
    }

    public boolean removerEvento(final Connection con, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("DELETE FROM evento WHERE id_evento = ?;");
        stm.setInt(1, evento.getID());
        return stm.executeUpdate() > 0;
    }

    public boolean atualizarEvento(final Connection con, final Evento evento) throws SQLException {
        final String sql = "Update evento SET titulo = ?, descricao = ?, DATA_FINALl = ?, empresa_id_empresa = ? WHERE id_evento = ?;";
        final PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, evento.getTitulo());
        stm.setString(2, evento.getDescricao());
        stm.setDate(3, new java.sql.Date(evento.getPrazo().getTime()));
        stm.setInt(4, evento.getIDEmpresa());
        stm.setInt(5, evento.getID());
        return stm.executeUpdate() > 0;
    }

    public void addParticipante(final Connection con, final Pessoa pessoa, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("INSERT INTO participantes(Pessoa_id_pessoa, evento_id_evento) VALUES(?, ?);");
        stm.setInt(1, pessoa.getID());
        stm.setInt(2, evento.getID());
        stm.executeUpdate();
    }

    public int getIDParticipante(final Connection con, final Pessoa pessoa, final Evento evento) throws SQLException {
        final PreparedStatement stm =
                con.prepareStatement("SELECT id_participante FROM participantes WHERE pessoa_id_pessoa = " + "? AND (evento_id_evento = ?);");
        stm.setInt(1, pessoa.getID());
        stm.setInt(2, evento.getID());
        final ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_participante");
        }
        return 0;
    }

    public void atualizarOpcoes(final Connection con, final Evento evento, final CucaDAO dao) throws SQLException {
        evento.setOpcoes(dao.getCucaEvento(con, evento));
    }

    public void atualizarParticipantes(final Connection con, final Evento evento, final PessoaDAO dao) throws SQLException {
        evento.setParticipantes(dao.consultaPorEvento(con, evento));
    }

    public void declararNumCucas(final Connection con, final Pessoa pessoa, final Evento evento, final int cuca_total) throws SQLException {
        final PreparedStatement stm =
                con.prepareStatement("INSERT INTO numerocuca(participantes_id_participante, cuca_total, cuca_deve, cuca_paga)" + "VALUES(?, ?, ?, ?);");
        stm.setInt(1, getIDParticipante(con, pessoa, evento));
        stm.setInt(2, cuca_total);
        stm.setInt(3, cuca_total);
        stm.setInt(4, 0);
        stm.executeUpdate();
    }

    public int getCucaTotal(final Connection con, final Pessoa pessoa, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT cuca_total FROM numerocuca WHERE participantes_id_participante = ?;");
        stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
        final ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("cuca_total");
        }
        return 0;
    }

    public int getCucaDeve(final Connection con, final Pessoa pessoa, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT cuca_deve FROM numerocuca WHERE participantes_id_participante = ?;");
        stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
        final ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("cuca_deve");
        }
        return 0;
    }

    public int getCucaPaga(final Connection con, final Pessoa pessoa, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT cuca_paga FROM numerocuca WHERE participantes_id_participante = ?;");
        stm.setInt(1, this.getIDParticipante(con, pessoa, evento));
        final ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt("cuca_paga");
        }
        return 0;
    }

    public void updateCucaPaga(final Connection con, final Pessoa pessoa, final Evento evento, final int cucas_pagas) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("UPDATE numerocuca SET cuca_deve = ?, " + "cuca_paga = ? WHERE Participantes_id_participante = ?;");
        final int cuca_deve = getCucaTotal(con, pessoa, evento) - cucas_pagas;
        stm.setInt(1, cuca_deve);
        stm.setInt(2, cucas_pagas);
        stm.setInt(3, this.getIDParticipante(con, pessoa, evento));
        stm.executeUpdate();
    }

    // só vem o id, usar id do parti pra consultar evento e voltar todas as informações

    public List<Evento> consultaEventoPessoa(final Connection con, final Pessoa pessoa) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT evento_id_evento FROM participantes WHERE pessoa_id_pessoa = ?;");
        stm.setInt(1, pessoa.getID());
        final ResultSet rs = stm.executeQuery();
        final List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            final Evento eventoY = getEvento(rs);
            eventos.add(eventoY);
        }
        return eventos;
    }

    public List<Evento> getAllEventos(final Connection con) throws SQLException {
        System.out.println("cheguei no all eventos");
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
        final ResultSet rs = stm.executeQuery();
        final List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            final Evento evento = getEvento(rs);
            eventos.add(evento);

        }
        return eventos;
    }

    public List<Evento> consultaEventoSearchTerm(final Connection con, final String searchTerm) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento;");
        final ResultSet rs = stm.executeQuery();
        final List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            final Evento evento = getEvento(rs);
            if (searchTerm.contains(evento.getTitulo())) {
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public Evento consultaEventoID(final Connection con, final int id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM evento WHERE id_evento = ?;");
        stm.setInt(1, id);
        final ResultSet rs = stm.executeQuery();
        Evento evento = null;
        if (rs.next()) {
            evento = getEvento(rs);
        }
        return evento;
    }

    private Evento getEvento(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id_evento");
        final String titulo = rs.getString("titulo");
        final String descricao = rs.getString("descricao");
        final Date prazo = new java.util.Date(rs.getDate("DATA_FINALl").getTime());
        final Evento evento = new Evento(id, titulo, descricao, prazo, 1);
        return evento;
    }
}
