package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CucaException;
import models.Cuca;
import models.Evento;
import validators.CucaValidator;

public class CucaDAO {

    CucaValidator validator = new CucaValidator();

    public boolean inserirCuca(final Connection con, final Cuca cuca) throws SQLException {
        try {
            validator.validate(cuca);
            final PreparedStatement stm = con.prepareStatement("INSERT INTO cuca(tipo, origem)" + "VALUES(?, ?);");
            stm.setString(1, cuca.getTipo());
            stm.setString(2, cuca.getOrigem());
            stm.executeUpdate();

            final PreparedStatement stm2 = con.prepareStatement("SELECT id_cuca FROM cuca WHERE tipo = ? AND origem = ?;");
            stm2.setString(1, cuca.getTipo());
            stm2.setString(2, cuca.getOrigem());
            final ResultSet rs2 = stm2.executeQuery();
            if (rs2.next()) {
                cuca.setID(rs2.getLong("id_cuca"));
            }
            return true;
        } catch (final CucaException e) {
            throw e;
        }
    }

    public List<Cuca> consultaCucaSearchTerm(final Connection con, final String searchTerm) throws SQLException {
        final PreparedStatement stm2 = con.prepareStatement("SELECT * FROM cuca;");
        final List<Cuca> cucas = new ArrayList<>();
        final ResultSet rs2 = stm2.executeQuery();
        while (rs2.next()) {
            final Cuca cuca = getCuca(rs2);
            if (searchTerm.contains(cuca.getOrigem()) || searchTerm.contains(cuca.getTipo())) {
                cucas.add(cuca);
            }
        }
        return cucas;
    }

    public Cuca consultaCucaID(final Connection con, final long id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE id_cuca = ?;");
        stm.setLong(1, id);
        final ResultSet rs = stm.executeQuery();
        Cuca cuca = new Cuca();
        while (rs.next()) {
            cuca = getCuca(rs);
        }
        return cuca;
    }

    public List<Cuca> consultaAllCucas(final Connection con) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca;");
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = getCuca(rs);
            cucas.add(cuca);
        }
        return cucas;
    }

    public List<Cuca> consultaCucaOrigem(final Connection con, final String origem) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE origem = ?;");
        stm.setString(1, origem);
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = getCuca(rs);
            cucas.add(cuca);
        }
        return cucas;
    }

    public List<Cuca> consultaCucaTipo(final Connection con, final String tipo) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE tipo = ?;");
        stm.setString(1, tipo);
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = getCuca(rs);
            cucas.add(cuca);
        }
        return cucas;
    }

    public boolean removerCuca(final Connection con, final long id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("DELETE FROM cuca WHERE id_cuca = ?;");
        stm.setLong(1, id);
        return stm.executeUpdate() > 0;
    }

    public boolean atualizarCuca(final Connection con, final Cuca cuca) throws SQLException {
        try {
            validator.validate(cuca);
            final String sql = "Update cuca SET tipo = ?, origem = ? WHERE id_cuca = ?;";
            final PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cuca.getTipo());
            stm.setString(2, cuca.getOrigem());
            stm.setLong(3, cuca.getID());
            return stm.executeUpdate() > 0;
        } catch (final CucaException e) {
            throw e;
        }
    }

    public List<Cuca> getCucaEvento(final Connection con, final Evento evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT cuca_id_cuca FROM opcoes WHERE evento_id_evento = ?;");
        stm.setInt(1, evento.getID());
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = getCuca(rs);
            cucas.add(cuca);
        }
        return cucas;
    }

    private Cuca getCuca(final ResultSet rs) throws SQLException {
        final long id = rs.getInt("id_cuca");
        final String tipo = rs.getString("tipo");
        final String origem = rs.getString("origem");
        final Cuca cuca = new Cuca(origem, tipo, id);
        return cuca;
    }

}
