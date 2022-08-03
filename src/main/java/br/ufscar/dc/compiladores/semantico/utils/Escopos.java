package br.ufscar.dc.compiladores.semantico.utils;

import java.util.LinkedList;
import java.util.List;

public class Escopos {
    public LinkedList<TabelaDeSimbolos> pilhaDeTabelas;
    
    public Escopos() {
        this.pilhaDeTabelas = new LinkedList<>();
        criarNovoEscopo();
    }
    
    public void criarNovoEscopo() {
        pilhaDeTabelas.push(new TabelaDeSimbolos());
    }
    
    public TabelaDeSimbolos obterEscopoAtual() {
        return pilhaDeTabelas.peek();
    }
    
    public List<TabelaDeSimbolos> percorrerEscoposAninhados() {
        return pilhaDeTabelas;
    }
    
    public void abandonarEscopo() {
        pilhaDeTabelas.pop();
    }
}
