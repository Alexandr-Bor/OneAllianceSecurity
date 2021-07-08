import java.io.*;
import java.util.ArrayList;

public class OneAllianceSecurityKey {
    private static final String KEYS_FILE = "D:\\Docs\\OneAllianceSecurity\\dbkeys.txt";
    private static final String HTML_FILE = "D:\\Docs\\OneAllianceSecurity\\all_key_j.html";
    private static final String START_HTML_FILE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\t<meta charset=\"UTF-8\">\n" +
            "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "\t<link rel=\"stylesheet\" href=\"style/style.css\">\n" +
            "\n" +
            "\t<script src=\"style/libs/jQuery_1.12.4.min.js\"></script>\n" +
            "\t<script src=\"style/js.js\"></script>\n" +
            "\t<script src=\"style/js_obj.js\"></script>\n" +
            "\n" +
            "\t<title>Ключи и Коды</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\t<section id=\"header_section\">\n" +
            "\t\t<nav class=\"navigation\">\n" +
            "\t\t\t<ul>\n" +
            "\t\t\t\t<li><a href=\"all_objects_j.html\">Объекты</a></li>\n" +
            "\t\t\t\t<li><span>Ключи и Коды</span></li>\n" +
            "\t\t\t</ul>\n" +
            "\t\t</nav>\n" +
            "\n" +
            "\t\t<form class=\"form_sorting\" action=\"\">\n" +
            "\t\t\t<input type=\"text\" id=\"searchObj\">\n" +
            "\t\t\t<label for=\"searchObj\">\n" +
            "\t\t\t\t<span id=\"clear_form\">Clear</span>\n" +
            "\t\t\t</label>\n" +
            "\t\t</form>\n" +
            "\t</section>\n" +
            "\n" +
            "\t<ol class=\"all_objects_key\" id=\"all_objects_security\">";
    private static final String END_HTML_FILE = "\t</ol>\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) {
        ArrayList<String[]> keys = readKeys();
        writeHTMLFile(keys);
    }

    /**
     *
     * @return
     */
    private static ArrayList<String[]> readKeys() {
        ArrayList<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(KEYS_FILE))) {

            while (true) {

                String[] key = new String[7]; // 0 number, 1 color, 2 street, 3 name, 4 locality, 5 kod, 6 comments
                String line = null;

                for (int i = 0; i < 7; i++) {
                    line = br.readLine();

                    if (line == null || line.equals("***") || line.startsWith("#"))
                        break;

                    key[i] = line;

                    // todo : add comments
                    if (i == 6)
                        br.readLine();
                }

                if (line != null && line.startsWith("#"))
                    continue;

                result.add(key);

                // if list keys is end
                if (line == null) {
                    break;
                }
            }

        } catch (IOException e) {
            // Do nothing...
        }

        return result;
    }

    /**
     *
     * @param keys
     */
    private static void writeHTMLFile(ArrayList<String[]> keys) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HTML_FILE))) {

            bw.write(START_HTML_FILE);

            for (String[] key : keys) {
                String li = "\n\t\t<li>\n" +
                        "\t\t\t<span class=\"clr " + (key[1] == null ? "" : key[1]) + "\"></span>\n" + // 1 color
                        "\t\t\t<span class=\"numberKey\">" + (key[0] == null ? "" : key[0]) + "</span>\n" + // 0 number
                        "\t\t\t<span class=\"addr\">\n" +
                        "\t\t\t\t<i class=\"kod\">" + (key[5] == null || key[5].equals("") ? "" : "(" + key[5] + ")") + "</i>\n" + // 5 kod
                        "\t\t\t\t<span class=\"address\">\n" +
                        "\t\t\t\t\t<i class=\"locality\">" + (key[4] == null || key[4].equals("") ? "" : key[4] + ", ") + "</i>\n" + // 4 locality
                        "\t\t\t\t\t<span class=\"street-house\">" + (key[2] == null ? "" : key[2] + ", ") + "</span>\n" + // 2 street
                        "\t\t\t\t\t<i class=\"name\">" + (key[3] == null ? "" : key[3]) + "</i>\n" + // 3 name
                        "\t\t\t\t\t<i class=\"comments\">" + (key[6] == null ? "" : "(" + key[6] + ")") + "</i>\n" + // 6 comments
                        "\t\t\t\t</span>\n" +
                        "\t\t\t</span>\n" +
                        "\t\t</li>";
                bw.write(li);
            }

            bw.write(END_HTML_FILE);

        } catch (IOException e) {
            // Do nothing...
        }
    }
}
