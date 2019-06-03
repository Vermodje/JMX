package servlet;

import accountServer.AccountServer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServerServlet extends HttpServlet {
    private AccountServer accountServer;

    public AccountServerServlet(AccountServer accountServer){
        this.accountServer = accountServer;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(accountServer.getUsersLimit());
    }
}
