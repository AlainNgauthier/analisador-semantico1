
package br.ufscar.dc.compiladores.semantico.utils;

import java.util.ArrayList;
import java.util.List;

public class Variavel {
    public String nome;
    public TipoAlguma tipo;
    Ponteiro ponteiro;
    Registro registro;
    Procedimento procedimento;
    Funcao funcao;
    
    public Variavel(String nome, TipoAlguma tipo) {
        this.nome = nome;
        this.tipo = tipo;
        if (this.tipo != null && this.tipo.tipoBasico != null) {
            switch (this.tipo.tipoBasico) {
                case REGISTRO:
                    this.registro = new Registro();
                    break;
                case PONTEIRO:
                    this.ponteiro = new Ponteiro(this.tipo.tipoAninhado);
                    break;
                case FUNCAO:
                    this.funcao = new Funcao();
                    break;
                case PROCEDIMENTO:
                    this.procedimento = new Procedimento();
                    break;
                default:
                    break;
            }
        }
    }
    
    public class Ponteiro {
        private TipoAlguma apontaPara;
        
        public Ponteiro (TipoAlguma apontaPara) {
            this.apontaPara = apontaPara;
        }
        
        public TipoAlguma getTipoAninhado() {
            
            return this.apontaPara.getTipoAninhado();
        }
    }

    public class Registro {
        private List<Variavel> variaveis;
        
        public Registro() {
            this.variaveis = new ArrayList<>();
        }
        
        public void adicionaNoRegistro(List<Variavel> novasVars) {
            this.variaveis.addAll(novasVars);
        }
        
        public Variavel getVariavel (String nome) {
            for (var v : this.variaveis) {
                if (v.nome.equals(nome)) {
                    return v;
                }
            }
            return null;
        }
        
        public List<Variavel> getTodasVariaveis() {
            return variaveis;
        }
    }
    
    public class Funcao {
        private TipoAlguma tipoRetorno;
        private List<Variavel> variaveisLocais;
        private List<Variavel> parametros;
        
        public void setTipoRetorno(TipoAlguma tipoRetorno) {
            this.tipoRetorno = tipoRetorno;
        }
        
        public void setVariaveisLocais(List<Variavel> vars) {
            this.variaveisLocais = vars;
        }
        
        public void setParametros(List<Variavel> vars) {
            this.parametros = vars;
        }
        
        public TipoAlguma getTipoRetorno() {
            return this.tipoRetorno;
        }
        
        public List<Variavel> getParametros() {
            return this.parametros;
        }
        
        public List<Variavel> getVariaveisLocais() {
            return this.variaveisLocais;
        }
    }

    public class Procedimento {
        private List<Variavel> variaveisLocais;
        private List<Variavel> parametros;
        
        public void setVariaveisLocais(List<Variavel> vars) {
            this.variaveisLocais = vars;
        }
        
        public void setParametros(List<Variavel> vars) {
            this.parametros = vars;
        }
        
        public List<Variavel> getParametros() {
            return this.parametros;
        }
        
        public List<Variavel> getVariaveisLocais() {
            return this.variaveisLocais;
        }
    }

    public TipoAlguma getTipoPonteiroAninhado() {
        return this.ponteiro.getTipoAninhado();
    }
    
    public Variavel getVarNoRegistro(String nome) {
        return this.registro.getVariavel(nome);
    }

    public Registro getRegistro() {
        return registro;
    }
    
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
    
    public Procedimento getProcedimento() {
        return this.procedimento;
    }
    
    public Funcao getFuncao() {
        return this.funcao;
    }
}