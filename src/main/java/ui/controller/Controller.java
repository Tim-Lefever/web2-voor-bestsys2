package ui.controller;

import domain.db.PuzzelDB;
import domain.model.Puzzel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private PuzzelDB pDB = new PuzzelDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command;
        if (request.getParameter("command") != null) {
            command = (String) request.getParameter("command");
        } else {
            command = "home";
        }
        String destination;
        switch (command) {
            case "home":
                destination = home(request, response);
                break;
            case "overview":
                destination = overview(request, response);
                break;
            case "voegToe":
                destination = voegToe(request, response);
                break;
            case "voegToeFormulier":
                destination = voegToeFormulier(request, response);
                break;
            case "pasAan":
                destination = pasAan(request, response);
                break;
            case "pasAanConfirmation":
                destination = pasAanConfirmation(request, response);
                break;
            case "pasAanConfirmed":
                destination = pasAanConfirmed(request, response);
                break;
            case "verwijderConfirmation":
                destination = verwijderConfirmation(request, response);
                break;
            case "verwijder":
                destination = verwijder(request, response);
                break;
            case "zoekPagina":
                destination = zoekPagina(request, response);
                break;
            case "zoek":
                destination = zoek(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String verwijder(HttpServletRequest request, HttpServletResponse response) {
        String idSting = request.getParameter("id");
        int id = Integer.parseInt(idSting);
        this.pDB.deletePuzzelOnId(id);
        return overview(request, response);
    }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        String zoekOpdracht = request.getParameter("zoekOpdracht");
        if (zoekOpdracht.trim().isEmpty()) {
            request.setAttribute("foutmelding", "Vul een geldige waarde in.");
            return "zoek.jsp";
        }
        request.setAttribute("zoekOpdracht", zoekOpdracht);
        ArrayList<Puzzel> filteredPuzzels = pDB.findPuzzelsOnString(zoekOpdracht);
        request.setAttribute("filteredPuzzels", filteredPuzzels);
        return "zoek_result.jsp";
    }

    private String zoekPagina(HttpServletRequest request, HttpServletResponse response) {
        return "zoek.jsp";
    }

    private String pasAanConfirmed(HttpServletRequest request, HttpServletResponse response) {
        Puzzel aanTePassen = (Puzzel) request.getSession().getAttribute("aanTePassenPuzzel");
        Puzzel newPuzzel = (Puzzel) request.getSession().getAttribute("newPuzzel");
        update(aanTePassen, newPuzzel, request);
        request.getSession().invalidate();

        Cookie cookie = new Cookie("LaatstAangePasteId", Integer.toString(aanTePassen.getId()));
        response.addCookie(cookie);

        return overview(request, response);
    }

    private String pasAanConfirmation(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Puzzel p = new Puzzel();

        setNaam(p, request, errors);
        setMerk(p, request, errors);
        setAantalStukken(p, request, errors);
        setThema(p, request, errors);
        setKleur(p, request, errors);

        if (errors.size() == 0) {
            if (this.pDB.getPuzzels().contains(p)) {
                errors.add("Deze puzzel is al opgeslagen.");
            } else {
                request.getSession().setAttribute("newPuzzel", p);
                return "pasAanConfirmation.jsp";
            }
        }
        request.setAttribute("errors", errors);
        return pasAan(request, response);
    }

    private void update(Puzzel p, Puzzel newPuzzel, HttpServletRequest request) {
        p.setNaam(newPuzzel.getNaam());
        p.setMerk(newPuzzel.getMerk());
        p.setAantalStukken(newPuzzel.getAantalStukken());
        p.setKleur(newPuzzel.getKleur());
        p.setThema(newPuzzel.getThema());

    }

    private String pasAan(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Puzzel p = this.pDB.findPuzzelOnId(id);
        request.getSession().setAttribute("aanTePassenPuzzel", p);
        return "pas-aan.jsp";
    }

    private String verwijderConfirmation(HttpServletRequest request, HttpServletResponse response) {
        String teVerwijderenIdString = request.getParameter("id");
        int teVerwijderenId = Integer.parseInt(teVerwijderenIdString);
        Puzzel teVerwijderen = this.pDB.findPuzzelOnId(teVerwijderenId);
        request.setAttribute("teVerwijderenPuzzel", teVerwijderen);
        return "verwijderConfirmation.jsp";
    }

    private String voegToeFormulier(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        Puzzel puzzel = new Puzzel();
        setNaam(puzzel, request, errors);
        setMerk(puzzel, request, errors);
        setAantalStukken(puzzel, request, errors);
        setThema(puzzel, request, errors);
        setKleur(puzzel, request, errors);

        if (errors.size() == 0) {
            try {
                pDB.add(puzzel);
                return overview(request, response);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voeg_toe.jsp";
    }

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        return "voeg_toe.jsp";
    }


    private String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("puzzels", pDB.getPuzzels());
        request.setAttribute("grootstePuzzel", pDB.getPuzzelMeesteStukken());
        request.setAttribute("aantalPuzzels", pDB.getAantalPuzzels());
        return "overview.jsp";
    }

    private String home(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("aantalPuzzels", pDB.getAantalPuzzels());

        Cookie idLaatstAangepastCookie = this.getCookieWithKey(request, "LaatstAangePasteId");
        if (idLaatstAangepastCookie != null) {
            int idLaatsAangepastInt = Integer.parseInt(idLaatstAangepastCookie.getValue());
            request.setAttribute("idLaatstAangepast", idLaatsAangepastInt);
            Puzzel laatstAangepast = this.pDB.findPuzzelOnId(idLaatsAangepastInt);
            request.setAttribute("laatsAangepastePuzzel", laatstAangepast);
        }

        return "home.jsp";
    }


    private void setNaam(Puzzel p, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("vorigeNaam", naam);
            p.setNaam(naam);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", naamHasErrors);
        }
    }

    private void setMerk(Puzzel p, HttpServletRequest request, ArrayList<String> errors) {
        String merk = request.getParameter("merk");
        boolean merkHasErrors = false;
        try {
            request.setAttribute("vorigMerk", merk);
            p.setMerk(merk);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            merkHasErrors = true;
        } finally {
            request.setAttribute("merkHasErrors", merkHasErrors);
        }
    }

    private void setAantalStukken(Puzzel puzzel, HttpServletRequest request, ArrayList<String> errors) {
        String aantalStukken = request.getParameter("aantalStukken");
        boolean aantalStukkenHasErrors = false;
        try {
            request.setAttribute("vorigAantalStukken", aantalStukken);
            puzzel.setAantalStukken(Integer.parseInt(aantalStukken));
        } catch (NumberFormatException e) {
            errors.add("Vul een nummer in voor het aantal stukken.");
            aantalStukkenHasErrors = true;
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            aantalStukkenHasErrors = true;
        } finally {
            request.setAttribute("aantalStukkenHasErrors", aantalStukkenHasErrors);
        }
    }

    private void setThema(Puzzel p, HttpServletRequest request, ArrayList<String> errors) {
        String thema = request.getParameter("thema");
        boolean themaHasErrors = false;
        try {
            request.setAttribute("vorigThema", thema);
            p.setThema(thema);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            themaHasErrors = true;
        } finally {
            request.setAttribute("themaHasErrors", themaHasErrors);
        }
    }

    private void setKleur(Puzzel p, HttpServletRequest request, ArrayList<String> errors) {
        String kleur = request.getParameter("kleur");
        boolean kleurHasErrors = false;
        try {
            request.setAttribute("vorigeKleur", kleur);
            p.setKleur(kleur);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            kleurHasErrors = true;
        } finally {
            request.setAttribute("kleurHasErrors", kleurHasErrors);
        }
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }
}
