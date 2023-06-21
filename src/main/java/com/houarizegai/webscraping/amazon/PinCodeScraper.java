package com.houarizegai.webscraping.amazon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PinCodeScraper {
    private static final String BASE_URL = "http://www.referencer.in/PIN_Codes.aspx";

    public static void main(String[] args) {
        try {
            List<String> cityNames = getCityNames();
            for (String cityName : cityNames) {
                List<String[]> cityData = scrapeCityData(cityName);
                saveToSpreadsheet(cityName, cityData);
            }
            System.out.println("Data scraping and saving completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static List<String> getCityNames() throws IOException {
        List<String> cityNames = new ArrayList<>();
        Document doc = Jsoup.connect(BASE_URL).get();
        Elements options = doc.select("select#ctl00_Main_DropDownList1 option");
        for (Element option : options) {
            cityNames.add(option.text());
        }
        return cityNames;
    }

    private static List<String[]> scrapeCityData(String cityName) throws IOException {
        List<String[]> cityData = new ArrayList<>();
        Document doc = Jsoup.connect(BASE_URL).data("ctl00$Main$DropDownList1", cityName).post();
        Elements rows = doc.select("table#ctl00_Main_GridView1 tr:not(.HeaderStyle)");
        for (Element row : rows) {
            Elements columns = row.select("td");
            String[] rowData = new String[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                rowData[i] = columns.get(i).text();
            }
            cityData.add(rowData);
        }
        return cityData;
    }

    private static void saveToSpreadsheet(String cityName, List<String[]> cityData) throws IOException {
        String fileName = cityName + ".csv";
        StringBuilder csvContent = new StringBuilder();
        for (String[] rowData : cityData) {
            csvContent.append(String.join(",", rowData)).append("\n");
        }
        Files.write(Paths.get(fileName), csvContent.toString().getBytes());
        System.out.println("Data for " + cityName + " saved to " + fileName);
    }
}
