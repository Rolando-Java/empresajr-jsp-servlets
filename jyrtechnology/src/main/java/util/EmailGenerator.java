package util;

public class EmailGenerator {

    public static String getHeaders(String urlImagen) {
        return "<style>"
                + "    h2   {color: #212121; font-family:Candara,arial,helvetica;}"
                + "    p    {color: #8c8c8c; font-family:Candara,arial,helvetica; padding-left: 10px;}"
                + "</style>"
                + "<div>"
                + "    <img src='" + urlImagen + "' height='300'>"
                + "</div>";
    }

    public static String getBody(String subtitle, String[] texts) {
        String htmlTexts = "";
        for (int i = 0; i < texts.length; i++) {
            htmlTexts += "<p>" + EncodingStringUtils.encodeHtml(texts[i]) + "</p>";
        }
        return "<div style='margin: 20px'>"
                + "    <h4>" + EncodingStringUtils.encodeHtml(subtitle) + "</h4>"
                + htmlTexts
                + "</div>";
    }

}
