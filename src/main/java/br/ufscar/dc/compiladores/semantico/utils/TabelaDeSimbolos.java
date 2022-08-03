package br.ufscar.dc.compiladores.semantico.utils;

import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {
    private final Map<String, Variavel> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap();
    }

    public void adicionar(Variavel v) {
        tabela.put(v.nome, v);
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public TipoAlguma verificar(String nome) {
        return tabela.get(nome).tipo;
    }

    public Variavel getVariavel(String nome) {
        return tabela.get(nome);
    }
}
