import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.*;

public class parseToDB {
    public static void main(String[] args) {
        final String url = "http://covid19fund.gov.az/az/donation";


        try {
            //Connect to DB
            String dbUsername = "postgres";
            String dbPassword = "postgres";
            String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/jsoupTest";

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            connection.setAutoCommit(false);

            String sqlQuery = "insert into total_donations (benefactor, donation) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            final Document document = Jsoup.connect(url).get();

            //Total donations  AZN
            for (Element row : document.select("#general-local-in > table")) {
                for (int i = 0; i < document.select("#general-local-in > table > tbody > tr").size(); i++) {
                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donation = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();
                    if (from.equals("") || donation.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donation);

                        preparedStatement.executeUpdate();
                    }
                }
            }


//            //Government donations (only AZN)
            sqlQuery = "insert into government_donations (benefactor, donation) values (?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            for (Element row : document.select("#country-budget > table")) {
                for (int i = 2; i <= document.select("#country-budget > table > tbody > tr").size(); i++) {
                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donations = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();

                    if (from.equals("") || donations.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donations);
                        preparedStatement.executeUpdate();
                    }
                }
            }


//            //Legal entities donations AZN
            sqlQuery = "insert into legal_entities_donations (benefactor, donation) values (?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            for (Element row : document.select("div#legal-entities > div > table")) {

                for (int i = 1; i < document.select("#legal-entities > div > table > tbody > tr").size(); i++) {

                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donation = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();

                    if (from.equals("") || document.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donation);

                        preparedStatement.executeUpdate();
                    }
                }
            }


//            //Individual donations AZN
            sqlQuery = "insert into individual_donations (benefactor, donation) values (?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            for (Element row : document.select("#indivudal-local > div > table")) {
                for (int i = 0; i < document.select("#indivudal-local > div > table > tbody > tr").size(); i++) {

                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donation = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();

                    if (from.equals("") || donation.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donation);

                        preparedStatement.executeUpdate();
                    }

                }

            }


//            //Legal entities donations foreign currency
            sqlQuery = "insert into legal_entities_donations_fc (benefactor, donation) values (?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            for (Element row : document.select("#foreign-legal-entities > div > table")) {
                for (int i = 0; i < document.select("#foreign-legal-entities > div > table >tbody > tr").size(); i++) {

                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donations = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();

                    if (from.equals("") || donations.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donations);

                        preparedStatement.executeUpdate();
                    }
                }
            }


            //Individual donations foreign currency
            sqlQuery = "insert into individual_donations_fc(benefactor, donation) values (?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            for (Element row : document.select("#foreign-individuals > div > table")) {
                for (int i = 0; i < document.select("#foreign-individuals > div > table > tbody > tr").size(); i++) {

                    String from = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(1)").text();
                    String donations = row.select("tr:nth-of-type(" + i + ") > td:nth-of-type(2)").text();

                    if (from.equals("") || donations.equals("")) {
                        continue;
                    } else {
                        preparedStatement.setString(1, from);
                        preparedStatement.setString(2, donations);

                        preparedStatement.executeUpdate();
                    }
                }
            }
            connection.commit();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
