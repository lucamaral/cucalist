package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.LoginInput;
import models.Pessoa;

public class PessoaDAO {

    public void addPessoa(final Connection con, final Pessoa pessoa) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("INSERT INTO pessoa(nome, email, senha, aniversario)" + "VALUES(?, ?, ?, ?);");
        stm.setString(1, pessoa.getNome());
        stm.setString(2, pessoa.getEmail());
        stm.setString(3, pessoa.getSenha());
        stm.setDate(4, new java.sql.Date(pessoa.getAniversario().getTime()));
        stm.executeUpdate();
        final PreparedStatement stm2 = con.prepareStatement("SELECT id_pessoa FROM pessoa WHERE nome = ?;");
        stm2.setString(1, pessoa.getNome());
        final ResultSet rs2 = stm2.executeQuery();
        if (rs2.next()) {
            pessoa.setID(rs2.getInt("id_pessoa"));
        }
    }

    public boolean authenticateLogin(final Connection con, final LoginInput input, final Pessoa pessoa) throws SQLException {
        return pessoa.getSenha().equals(input.getPassword());
    }

    public Pessoa getPessoaEmail(final Connection con, final String email) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE email = ?");
        stm.setString(1, email);
        final ResultSet rs = stm.executeQuery();
        rs.next();
        return buildPessoa(rs);
    }

    public boolean deletePessoa(final Connection con, final Pessoa pessoa) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("DELETE FROM pessoa WHERE id_pessoa = ?;");
        stm.setInt(1, pessoa.getID());
        return stm.executeUpdate() > 0;
    }

    public boolean updatePessoa(final Connection con, final Pessoa pessoa) throws SQLException {
        final String sql = "Update pessoa SET senha = ?, nome = ?, email = ?, aniversario = ? WHERE id_pessoa = ?;";
        final PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, pessoa.getSenha());
        stm.setString(2, pessoa.getNome());
        stm.setString(3, pessoa.getEmail());
        stm.setDate(4, new java.sql.Date(pessoa.getAniversario().getTime()));
        stm.setInt(5, pessoa.getID());
        return stm.executeUpdate() > 0;
    }

    public boolean validaLogin(final Connection con, final String senha, final String email) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE email = ? AND senha = ?;");
        stm.setString(1, email);
        stm.setString(2, senha);
        final ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public List<Pessoa> getEventoParticipantes(final Connection con, final long id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT pessoa_id_pessoa FROM participantes WHERE evento_id_evento = ?;");
        stm.setInt(1, (int) id);
        final ResultSet rs = stm.executeQuery();
        final List<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (rs.next()) {
            final Pessoa pessoa = getPessoa(con, rs.getInt("pessoa_id_pessoa"));
            pessoas.add(pessoa);
        }
        return pessoas;
    }

    public List<Pessoa> getAllPessoas(final Connection con) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa");
        final ResultSet rs = stm.executeQuery();
        final List<Pessoa> pessoas = new ArrayList<>();
        while (rs.next()) {
            final Pessoa pessoa = buildPessoa(rs);
            pessoas.add(pessoa);
        }
        return pessoas;
    }

    public List<String> getAllEmails(final Connection con) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT email FROM pessoa");
        final ResultSet rs = stm.executeQuery();
        final List<String> emails = new ArrayList<>();
        while (rs.next()) {
            emails.add(rs.getString("email"));
        }
        return emails;
    }

    public Pessoa getPessoa(final Connection con, final int id) throws SQLException {
        final PreparedStatement stm = con.prepareStatement("SELECT * FROM pessoa WHERE id_pessoa = ?;");
        stm.setInt(1, id);
        final ResultSet rs = stm.executeQuery();
        Pessoa pessoa = null;
        if (rs.next()) {
            pessoa = buildPessoa(rs);
        }
        return pessoa;
    }

    private Pessoa buildPessoa(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id_pessoa");
        final String nome = rs.getString("nome");
        final String email = rs.getString("email");
        final String senha = rs.getString("senha");
        final Date aniversario = new java.util.Date(rs.getDate("aniversario").getTime());
        final Pessoa pessoa = new Pessoa(id, nome, email, senha, aniversario);
        return pessoa;
    }

}
