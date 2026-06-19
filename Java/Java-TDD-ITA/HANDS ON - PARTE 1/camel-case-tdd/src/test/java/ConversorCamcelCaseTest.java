import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class ConversorCamcelCaseTest {

    @Test
    public void deveConverterPalavraMinuscula() {
        assertEquals( Arrays.asList("nome"), ConversorCamelCase.converterCamelCase("nome"));
    }
    
    @Test
    public void deveConverterInicialMinuscula() {
        assertEquals(Arrays.asList("nome"), ConversorCamelCase.converterCamelCase("Nome"));
    }

    @Test
    public void deveConveterComposto() {
        assertEquals(Arrays.asList("nome", "composto"), ConversorCamelCase.converterCamelCase("nomeComposto"));
    }

    @Test
    public void deveConverterInicialMaiuscula() {
        assertEquals(Arrays.asList("nome", "composto"), ConversorCamelCase.converterCamelCase("NomeComposto"));
    }

    @Test
    public void deveManterSigla() {
        assertEquals(Arrays.asList("CPF"), ConversorCamelCase.converterCamelCase("CPF"));
    }

    @Test
    public void deveConverterComposto() {
        assertEquals(Arrays.asList("numero", "CPF"), ConversorCamelCase.converterCamelCase("numeroCPF"));
    }

    @Test
    public void deveConverterCompostoMaior() {
        assertEquals(Arrays.asList("numero", "CPF", "contribuinte"), ConversorCamelCase.converterCamelCase("numeroCPFContribuinte"));
    }

    @Test
    public void deveConverterComNumero() {
        assertEquals(Arrays.asList("recupera", "10", "primeiros"), ConversorCamelCase.converterCamelCase("recupera10Primeiros"));
    }

    @Test
    public void naoDeveComecarComNumero() {
    try { ConversorCamelCase.converterCamelCase("10Primeiros");
        fail();
    } catch (IllegalArgumentException e) {
        assertEquals("Não deve começar com números",e.getMessage());
        }
    }

    @Test
    public void naoDevePermitirCaracterEspecial() {
        try {
        ConversorCamelCase.converterCamelCase("nome#Composto");
        fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Caracteres especiais não são permitidos", e.getMessage());
        }
    }
}
