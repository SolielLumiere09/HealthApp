package com.google.firebase.codelab.textExtractor.analyzer;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabelCleaner {

    /*
    // En vocales tomaremos la que sea debido a que las confunde mucho
    private static final String v = "([a|e|i|o|u|á|é|í|ó|ú|A|E|I|O|U|Á|É|Í|Ó|Ú])";

    private static final String FILTRO_TAM_PORCION = "[T|t]"+v+"m"+v+"[n|ñ]"+v+"d"+v+"l"+v+"[P|p]"+v+"rc"+v+v+"n[0-9]?[oz]?[0-9]+g?" ;

    private static final String FILTRO_PORCIONES = "[E|e]mp"+v+"q"+v+v+"[0-9]|" ;

    private static final String FILTRO_CALORIAS = "[C|c]"+v+"[L|l]"+v+"[R|r]"+v+v+"[S|s][0-9]+" ;

    private static final String FILTRO_GRASAS_TOTALES = "[G|g]r"+v+"s"+v+"s?[T|t]"+v+"t"+v+"l[0-9]+g?" ;

    private static final String FILTRO_GRASAS_SATURADAS = "[G|g]r"+v+"s"+v+"s?[S|s]"+v+"t"+v+"r"+v+"d"+v+"s?[0-9]+g?" ;

    private static final String FILTRO_GRASAS_TRANS = "[G|g]r"+v+"s"+v+"s?[T|t]r"+v+"ns[0-9]+g?" ;

    private static final String FILTRO_CARBS = "[C|c]"+v+"rb"+v+"h"+v+"dr"+v+"t"+v+"s[0-9]+g?|" +
                                               "[C|c]"+v+"rb"+v+"h"+v+"dr"+v+"t"+v+"st"+v+"t"+v+"l"+v+"s[0-9]+g?|"+
                                                "[C|c]"+v+"rb[0-9]+g?";

    private static final String FILTRO_AZUCAR = "[A|a][z|n]"+v+"c"+v+"r"+v+"s[0-9]+g?" ;

    private static final String FILTRO_COLESTEROL = "[C|c]"+v+"l"+v+"st"+v+"r"+v+"l[0-9]+m?g?";

    private static final String FILTRO_SODIO = "[S|s]"+v+"d"+v+v+"[0-9]+m?g?" ;

    private static final String FILTRO_PROTEINA = "[P|p]r"+v+"t"+v+v+"n"+v+"[0-9]+g?" ;
    */
    private static final String v = "([a|e|i|o|u|á|é|í|ó|ú|A|E|I|O|U|Á|É|Í|Ó|Ú])";

    private static final String FILTRO_TAM_PORCION = "[T|t]amano[a-z]*[0-9]+g?";

    private static final String FILTRO_PORCIONES = "Empaque[0-9]+|Envase[0-9]+";

    private static final String FILTRO_CALORIAS = "Calorias[0-9]+";

    private static final String FILTRO_GRASAS_TOTALES = "Grasa[T|t]otal[0-9]+g?";

    private static final String FILTRO_GRASAS_SATURADAS = "Grasa[s]?[S|s]?aturada[s]?[0-9]+g?";

    private static final String FILTRO_GRASAS_TRANS = "Grasas[T|t]rans[0-9]?+g?";

    private static final String FILTRO_CARBS = "[C|c]" + v + "rb" + v + "h" + v + "dr" + v + "t" + v + "s[0-9]+g?|" +
            "[C|c]" + v + "rb" + v + "h" + v + "dr" + v + "t" + v + "st" + v + "t" + v + "l" + v + "s[0-9]+g?|" +
            "[C|c]" + v + "rb[0-9]+g?";

    private static final String FILTRO_AZUCAR = "[A|a][z|n]" + v + "c" + v + "r" + v + "s[0-9]+g?";

    private static final String FILTRO_COLESTEROL = "Colesterol[0-9]+m?g?";

    private static final String FILTRO_SODIO = "[S|s]odio[0-9]+m?g?";

    private static final String FILTRO_PROTEINA = "[P|p]roteina[0-9]+g?";

    private static final String[] FILTERS = {
            FILTRO_TAM_PORCION,
            FILTRO_PORCIONES,
            FILTRO_CALORIAS,
            FILTRO_GRASAS_TOTALES,
            FILTRO_GRASAS_SATURADAS,
            FILTRO_GRASAS_TRANS,
            FILTRO_CARBS,
            FILTRO_AZUCAR,
            FILTRO_COLESTEROL,
            FILTRO_SODIO,
            FILTRO_PROTEINA
    };


    public static String cleanLabelText(String labelText) {
        StringBuilder filteredString = new StringBuilder();

        //limpiar cadena
        labelText = labelText.replaceAll("([:|-]|' ')|([0-9]+%)|(\\.)|(\\()|(\\))|(/)", "");
        labelText = labelText.replaceAll("[á]", "a");
        labelText = labelText.replaceAll("[é]", "e");
        labelText = labelText.replaceAll("[í]", "i");
        labelText = labelText.replaceAll("[ó]", "o");
        labelText = labelText.replaceAll("[o][o]", "o");
        labelText = labelText.replaceAll("[ú]", "u");
        labelText = labelText.replaceAll("[Í]", "I");
        labelText = labelText.replaceAll("[ñ]", "n");

        Log.d("FILTROS", labelText);

        for (String expression : FILTERS) {
            Pattern pattern = Pattern.compile(expression);
            Matcher m = pattern.matcher(labelText);

            if (m.find()) {
                filteredString.append(m.group()).append("\n\r");
            }
        }
        Log.d("STATEMENTS", filteredString.toString());
        return filteredString.toString();
    }
}