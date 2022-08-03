
package br.ufscar.dc.compiladores.semantico.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TipoAlguma {
    public enum TipoBasico{
        INTEIRO,
        REAL,
        LITERAL,
        LOGICO,
        PONTEIRO,
        ENDERECO,
        REGISTRO,
        PROCEDIMENTO,
        FUNCAO,
        INVALIDO
    }
    
    public static List<String> tiposCriados = new ArrayList<>();
    
    public TipoAlguma.TipoBasico tipoBasico;
    public String tipoCriado;
    
    public TipoAlguma tipoAninhado;
    
    public TipoAlguma(TipoAlguma.TipoBasico tipo) {
        this.tipoBasico = tipo;
        this.tipoCriado = null;
        this.tipoAninhado = null;
    }
    
    public TipoAlguma(String tipo) {
        this.tipoBasico = null;
        this.tipoCriado = tipo;
        this.tipoAninhado = null;
    }
    
    public TipoAlguma(TipoAlguma tipoPai, TipoAlguma tipoFilho) {
        // insere um novo tipo
        if (tipoPai.tipoBasico != null) {
            this.tipoBasico = tipoPai.tipoBasico;
            this.tipoCriado = null;
        } else {
            this.tipoBasico = null;
            this.tipoCriado = tipoPai.tipoCriado;
        }
        this.tipoAninhado = tipoFilho;
    }
    
    public TipoAlguma getTipoAninhado() {
        // retira o tipo "mais ao fundo"
        if (this.tipoAninhado == null) {
            return this;
        }
        TipoAlguma tipo = this.tipoAninhado;
        while (tipo.tipoAninhado != null) {
            tipo = tipo.getTipoAninhado();
        }
        return tipo;
    }
    
        public static void adicionaTipoCriado(String tipo) {
        tiposCriados.add(tipo);
    }
    
    public static String getTipoCriado(String tipo) {
        List<String> existe = tiposCriados.stream()
                .filter(str -> str.trim().contains(tipo))
                .collect(Collectors.toList());
        return existe.get(0);
    }
    
    public static boolean existeTipoCriado(String tipo) {
        List<String> existe = tiposCriados.stream()
                .filter(str -> str.trim().contains(tipo))
                .collect(Collectors.toList());
        return existe.size() > 0;
    }
    
    public String imprime() {
        String s = null;
        if (this.tipoBasico != null) {
            switch (tipoBasico) {
                case INTEIRO:
                    s = "int";
                    break;
                case REAL:
                    s = "float";
                    break;
                case LITERAL:
                    s = "char";
                    break;
            }
            return s;
        }
        return this.tipoCriado;
    }
    
    public String imprimePorcentagem() {
        String s = null;
        if (this.tipoBasico != null) {
            switch (tipoBasico) {
                case INTEIRO:
                    s = "%d";
                    break;
                case REAL:
                    s = "%f";
                    break;
                case LITERAL:
                    s = "%s";
                    break;
            }
            return s;
        }
        System.out.println("tipolA imprimePorcentagem tem criado");
        return this.tipoCriado;
    }
}