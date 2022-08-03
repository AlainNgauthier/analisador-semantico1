package br.ufscar.dc.compiladores.semantico;

/**
 *
 * @author alain.djamba
 */

import br.ufscar.dc.compiladores.semantico.AlgumaParser.ProgramaContext;
import br.ufscar.dc.compiladores.semantico.utils.AlgumaSemanticoUtils;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Principal {

    
    public static void main(String[] args) throws IOException {
        try ( PrintWriter writer = new PrintWriter(args[1])) {
            
            /* Criacão do arquivo de saída e leitura do arquivo de saída */
            CharStream cs = CharStreams.fromFileName(args[0]);
            AlgumaLexer lexer = new AlgumaLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgumaParser parser = new AlgumaParser(tokens);

            /* HandleError: classe que gera o arquivo de saída */
            ErrorHandler erro = new ErrorHandler();
            parser.removeErrorListeners();
            parser.addErrorListener(erro);
            ProgramaContext arvore = null;
            AlgumaSemantico las = new AlgumaSemantico();
            try {
                
                arvore = parser.programa();
                las.visitPrograma(arvore);
                for (var msg : AlgumaSemanticoUtils.erros) {
                    writer.println(msg);
                }
                writer.println("Fim da compilacao");
            } catch (ParseCancellationException exception) {
                writer.println(exception.getMessage());
                writer.println("Fim da compilacao");
            }
            writer.close();
        }
    }
}

