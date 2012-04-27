package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/26/12
 * Time: 12:48
 */
public class CreateDBServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = new Database();
        database.createDatabase();

        PrintWriter writer = resp.getWriter();

        writer.println("Database created!");
        writer.close();
    }
}
