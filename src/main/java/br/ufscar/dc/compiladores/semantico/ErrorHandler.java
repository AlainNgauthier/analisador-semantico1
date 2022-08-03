package br.ufscar.dc.compiladores.semantico;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ErrorHandler extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
            int charPositionInLine, String msg, RecognitionException e) 
                throws ParseCancellationException{
        Token t = (Token) offendingSymbol;
        String padrao = AlgumaLexer.VOCABULARY.getSymbolicName(t.getType());
        
        // armazenando a mensagem de erro
        String buffer = "";
        if (padrao != null && padrao.equals("COMENTARIO_ERRO")) {
            buffer = "Linha " + t.getLine() + ": comentario nao fechado";
        }
        else if (padrao != null && padrao.equals("CADEIA_ERRO")) {
            buffer = "Linha " + t.getLine() + ": cadeia literal nao fechada";
        }
        else if (padrao != null && padrao.equals("SIMBOLO_ERRO")) {
            buffer = "Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado";
        }
        else if (t.getType() == Token.EOF) {
            buffer = "Linha " + t.getLine() + ": erro sintatico proximo a EOF";
        }
        else {
            buffer = "Linha " + t.getLine() + ": erro sintatico proximo a " + t.getText();
        }
        throw new ParseCancellationException(buffer);
    }
}