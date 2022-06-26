import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operacoes {
    private String listaOperacoes;

    public String calculate(String listaOperacoes) {
        this.listaOperacoes = listaOperacoes;

        // Verificar quais operações está sendo solicitado
        if(this.listaOperacoes.matches("([0-9]+(\\+|\\s)[0-9]+)+")) {
            return "SOMA: " + operacao(this.listaOperacoes, '+');
        } else if (this.listaOperacoes.matches("([0-9]+\\*[0-9]+)+")) {
            return "MULTIPLICAÇÃO: " + operacao(this.listaOperacoes, '*');
        } else if (this.listaOperacoes.matches("([0-9]+\\/[0-9]+)+")) {
            return "DIVISÃO: " + operacao(this.listaOperacoes, '/');
        } else if (this.listaOperacoes.matches("([0-9]+\\-[0-9]+)+")) {
            return "SUBTRAÇÃO: " + operacao(this.listaOperacoes, '-');
        }
        return "Operação desconhecida!";
    }

    /*
     * Métodos responsáveis por realizar a operação conforme o operador informado como argumento.
     * */
    private String operacao(String listagem, char operacao) {
        // Montando expressão regular para pegar os numeros de uma operação
        Pattern patternNum = Pattern.compile("[0-9]+");
        Matcher m = patternNum.matcher(listagem.toUpperCase(Locale.ROOT));

        // Realizando a soma
        double result = 0;
        int num_capturado = 0;
        while(m.find()) {
            num_capturado = Integer.parseInt(m.group());
            result = operacao == '+' ? result + num_capturado :
                    operacao == '*' ? (result == 0 ? 1 : result) * num_capturado :
                    operacao == '/' ? (result == 0 ? num_capturado : result / num_capturado) :
                    operacao == '-' ? (result == 0 ? num_capturado : result - num_capturado) :
                    operacao == '%' ? (result == 0 ? num_capturado : result * num_capturado / 100) :
                    operacao == '^' ? (result == 0 ? num_capturado : Math.pow(result, num_capturado)) :
                    operacao == 'r' ?  Math.sqrt(num_capturado) : 0;
        }

        return String.valueOf(result);
    }
}
