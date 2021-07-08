import java.io.*;
import java.util.ArrayList;

/**
 * Test
 */
public class OneAllianceSecurityObject {
    private static final String OBJECTS_FILE = "D:\\Docs\\OneAllianceSecurity\\dbobjects.txt";
    private static final String OBJECTS_FOLDER = "D:\\Docs\\OneAllianceSecurity\\data\\";
    private static final String START_HTML_FILE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\"><head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<link rel=\"stylesheet\" href=\"../style/style.css\">\n" +
            "<script src=\"../style/libs/jQuery_1.12.4.min.js\"></script>\n" +
            "<script type=\"text/javascript\">if (screen.width > 700) document.write ('<script type=\"text/javascript\" src=\"../style/js.js\" ></' + 'script>');</script>\n" +
            "<title>@@@@@@@</title>\n" +
            "</head><body>";
    private static final String END_HTML_FILE = "</body></html>";

    public static void main(String[] args) {
        ArrayList<String[]> keys = readObjects();
        writeHTMLFile(keys);
    }

    /**
     *
     * @return
     */
    private static ArrayList<String[]> readObjects() {
        ArrayList<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(OBJECTS_FILE))) {

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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OBJECTS_FOLDER))) {

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
