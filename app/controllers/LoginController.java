package controllers;

import java.sql.SQLException;

import bd.ConexaoDB;
import bd.PessoaDAO;
import login.SessionMnpt;
import models.LoginInput;
import models.Pessoa;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {

    private static SessionMnpt sessionMNPT = new SessionMnpt();
    private static PessoaDAO pessoaDAO = new PessoaDAO();

    public void logOut() {
        session().clear();
    }

    public static Result login() throws SQLException {
        final Form<LoginInput> userForm = Form.form(LoginInput.class).bindFromRequest();
        final LoginInput input = userForm.get();
        try {
            final Pessoa pessoa = pessoaDAO.getPessoaEmail(ConexaoDB.getConexaoMySQL(), input.getEmail());
            if (pessoaDAO.authenticateLogin(ConexaoDB.getConexaoMySQL(), input, pessoa)) {
                sessionMNPT.putInSession(pessoa);
                return redirect("/");

            }
            flash("erro", "Senha incorreta");
            return redirect("/login");
        } catch (final SQLException e) {
            flash("erro", "Credenciais de usuário inválidas");
            return redirect("/login");
        }
    }

}
