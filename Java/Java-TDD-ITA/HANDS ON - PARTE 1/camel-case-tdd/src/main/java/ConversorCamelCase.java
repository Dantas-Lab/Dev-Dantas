import java.util.ArrayList;
import java.util.List;

public class ConversorCamelCase {

    public static List<String> converterCamelCase(String original) {
        validar(original);
        List<String> palavras = new ArrayList<>();
        String palavraAtual = "";
        for (int i = 0; i < original.length(); i++) {
        palavraAtual = processarLetra(original, i, palavras, palavraAtual);
        }
        palavras.add(normalizar(palavraAtual));
        return palavras;
    }

    private static String processarLetra(String original, int i, List<String> palavras, String palavraAtual) {
            char letra = original.charAt(i);
            if (deveQuebrar(original, i)) {
                palavras.add(normalizar(palavraAtual));
                palavraAtual = "";
            }
    return palavraAtual + letra;
}

    private static boolean deveQuebrar(String original, int i) {
        if (i == 0) {
            return false;
        }
        char atual = original.charAt(i);
        char anterior = original.charAt(i - 1);
        return mudouEntreLetraENumero(atual, anterior) || iniciouPalavra(original, i);
    }

    private static boolean mudouEntreLetraENumero(char atual, char anterior) {
        return Character.isDigit(atual) != Character.isDigit(anterior);
    }

    private static boolean iniciouPalavra(String original, int i) {
        char atual = original.charAt(i);
        char anterior = original.charAt(i - 1);
        if (!Character.isUpperCase(atual)) {
            return false;
        }
        boolean anteriorMinuscula = Character.isLowerCase(anterior);
        boolean proximaMinuscula = temProximaMinuscula(original, i);
        return anteriorMinuscula || proximaMinuscula;
    }

    private static boolean temProximaMinuscula(String original, int i) {
        if (i >= original.length() - 1) {
            return false;
        }
        return Character.isLowerCase(original.charAt(i + 1));
    }

    private static String normalizar(String palavra) {
        if (palavra.equals(palavra.toUpperCase())) {
            return palavra;
        }
        return palavra.toLowerCase();
    }

    private static void validar(String original) {
        if (Character.isDigit(original.charAt(0))) {
            throw new IllegalArgumentException("Não deve começar com números");
        }

        for (int i = 0; i < original.length(); i++) {
            if (!Character.isLetterOrDigit(original.charAt(i))) {
                throw new IllegalArgumentException("Caracteres especiais não são permitidos");
            }
        }
    }
}