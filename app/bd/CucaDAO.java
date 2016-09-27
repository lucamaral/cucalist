package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cuca;
import models.Pessoa;
import models.Rating;
import validators.CucaValidator;

public class CucaDAO {

    CucaValidator validator = new CucaValidator();

    public boolean inserirCuca(final Connection con, final Cuca cuca) throws SQLException {
        validator.validate(cuca);
        final PreparedStatement stm = con.prepareStatement("INSERT INTO cuca(tipo, origem) " + "VALUES(?, ?);");
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
    }

    public void getNotas(final Connection con, final Cuca cuca, final int id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM nota WHERE cuca_id_cuca = ?");
        stm.setInt(1, (int) cuca.getID());
        final ResultSet rs = stm.executeQuery();
        final List<Integer> notas = new ArrayList<>();
        while (rs.next()) {
            final int nota = rs.getInt("nota");
            notas.add(nota);
            if (rs.getInt("pessoa_id_pessoa") == id) {
                cuca.setNota(nota);
            }
        }
        int total = 0;
        for (int i = 0; i < notas.size(); i++) {
            total += notas.get(i);
        }
        if (notas.size() > 0) {
            cuca.setNotaMedia(total / notas.size());
        }
    }

    public List<Cuca> consultaCucaSearchTerm(final Connection con, final String searchTerm, final Pessoa pessoa) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca;");
        final List<Cuca> cucas = new ArrayList<>();
        final ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            final Cuca cuca = buildCuca(rs);
            getNotas(con, cuca, pessoa.getID());
            if (searchTerm.contains(cuca.getOrigem()) || searchTerm.contains(cuca.getTipo())) {
                cucas.add(cuca);
            }
        }
        return cucas;
    }

    public List<Cuca> consultaAllCucas(final Connection con, final Pessoa pessoa) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca;");
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = buildCuca(rs);
            getNotas(con, cuca, pessoa.getID());
            cucas.add(cuca);
        }
        return cucas;
    }

    public Cuca consultaCucaID(final Connection con, final long id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE id_cuca = ?;");
        stm.setLong(1, id);
        final ResultSet rs = stm.executeQuery();
        Cuca cuca = new Cuca();
        while (rs.next()) {
            cuca = buildCuca(rs);
        }
        return cuca;
    }

    public List<Cuca> consultaCucaOrigem(final Connection con, final String origem) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE origem = ?;");
        stm.setString(1, origem);
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = buildCuca(rs);
            cucas.add(cuca);
        }
        return cucas;
    }

    public List<Cuca> consultaCucaTipo(final Connection con, final String tipo) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM cuca WHERE tipo = ?;");
        stm.setString(1, tipo);
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        do {
            final Cuca cuca = buildCuca(rs);
            cucas.add(cuca);
        } while (rs.next());
        return cucas;
    }

    public boolean darNota(final Connection con, final Rating rating, final int id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("INSERT INTO nota(Pessoa_id_pessoa, cuca_id_cuca, nota) " + "VALUES(?, ?, ?);");
        final PreparedStatement stm2 = con.prepareStatement("UPDATE nota SET nota = ? WHERE cuca_id_cuca = ? AND pessoa_id_pessoa = ?;");
        if (!hasNota(con, id, (int) rating.getId())) {
            stm.setInt(1, id);
            stm.setInt(2, (int) rating.getId());
            stm.setInt(3, rating.getRating());
            return stm.executeUpdate() > 0;
        } else {
            if (rating.getRating() > 0) {
                stm2.setInt(1, rating.getRating());
                stm2.setInt(2, (int) rating.getId());
                stm2.setInt(3, id);
                return stm2.executeUpdate() > 0;
            }
            return true;
        }
    }

    public boolean hasNota(final Connection con, final int idPessoa, final int idCuca) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * from nota WHERE cuca_id_cuca = ? AND pessoa_id_pessoa = ?;");
        stm.setInt(1, idCuca);
        stm.setInt(2, idPessoa);
        return stm.executeQuery().next();
    }

    public boolean removerCuca(final Connection con, final long id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("DELETE FROM cuca WHERE id_cuca = ?;");
        stm.setLong(1, id);
        return stm.executeUpdate() > 0;
    }

    public boolean atualizarCuca(final Connection con, final Cuca cuca) throws SQLException {
        validator.validate(cuca);
        final String sql = "Update cuca SET tipo = ?, origem = ? WHERE id_cuca = ?;";
        final PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, cuca.getTipo());
        stm.setString(2, cuca.getOrigem());
        stm.setLong(3, cuca.getID());
        return stm.executeUpdate() > 0;
    }

    public List<Cuca> getCucaEvento(final Connection con, final long evento) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT cuca_id_cuca FROM opções WHERE evento_id_evento = ?;");
        stm.setInt(1, (int) evento);
        final ResultSet rs = stm.executeQuery();
        final List<Cuca> cucas = new ArrayList<>();
        while (rs.next()) {
            final Cuca cuca = consultaCucaID(con, rs.getInt("cuca_id_cuca"));
            getNotas(con, cuca, 0);
            cucas.add(cuca);
        }
        return cucas;
    }

    private Cuca buildCuca(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id_cuca");
        final String tipo = rs.getString("tipo");
        final String origem = rs.getString("origem");
        final Cuca cuca = new Cuca(origem, tipo, id);
        return cuca;
    }

}
