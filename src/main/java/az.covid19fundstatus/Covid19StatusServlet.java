package az.covid19fundstatus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Covid19StatusServlet", urlPatterns = "/status")
public class Covid19StatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String url = "http://covid19fund.gov.az/az/donation";

        String governmentDonation;
        String legalEntities;
        String individualLocal;
        String totalDonation;


        try {
            final Document document = Jsoup.connect(url).get();


            for (Element row : document.select("div#general-local-in > .table-bordered.table > tbody > tr:nth-of-type(4)")) {

                totalDonation = row.select("th:nth-of-type(2)").text().replaceAll("[ |,]", "");

                request.setAttribute("totalDonation", totalDonation);
            }


            for (Element row : document.select("div#country-budget > .table-bordered.table > tbody > tr:nth-of-type(2)")) {

                governmentDonation = row.select("td:nth-of-type(2)").text().replaceAll("[ |,]", "");

                request.setAttribute("governmentDonation", governmentDonation);
            }

            for (Element row : document.select("div#legal-entities > div > table > tbody > tr:last-child > td:nth-child(2)")) {

                legalEntities = row.select("td:nth-of-type(2)").text().replaceAll("[ |,]", "");

                request.setAttribute("legalEntities", legalEntities);
            }

            for (Element row : document.select("div#indivudal-local > div > table > tbody > tr:last-child > td:nth-child(2)")) {

                individualLocal = row.select("td:nth-of-type(2)").text().replaceAll("[ |,]", "");

                request.setAttribute("individualLocal", individualLocal);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("stats.jsp").forward(request, response);
    }
}
