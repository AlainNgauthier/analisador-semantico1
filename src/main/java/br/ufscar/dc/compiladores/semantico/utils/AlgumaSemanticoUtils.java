package br.ufscar.dc.compiladores.semantico.utils;

import br.ufscar.dc.compiladores.semantico.AlgumaParser;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AlgumaSemanticoUtils {

    
    public static List<String> erros = new ArrayList<>();
    public static boolean podeRetornar = false;
    public static void adicionaErro(String msg) {
        erros.add(msg);
    }

    public static Variavel verificaDeclGlobal(Escopos escopo, AlgumaParser.Declaracao_globalContext ctx) {
        System.out.println("!global!");
        String decl = ctx.start.getText();
        Variavel entrada = null;
        switch (decl) {
            case "procedimento":
                System.out.println("Procedimento");
                entrada = AlgumaSemanticoUtils.verificaProcedimento(escopo, ctx);
                break;
            case "funcao":
                System.out.println("Funcao " + ctx.getChild(1).getText());
                AlgumaSemanticoUtils.podeRetornar = true;
                entrada = AlgumaSemanticoUtils.verificaFuncao(escopo, ctx);
                AlgumaSemanticoUtils.podeRetornar = false;
                break;
        }
        return entrada;
    }

    public static Variavel verificaProcedimento(Escopos escopo, AlgumaParser.Declaracao_globalContext ctx) {
        String nomeProc = ctx.IDENT().getText();
        Variavel ret = new Variavel(nomeProc, new TipoAlguma(TipoAlguma.TipoBasico.PROCEDIMENTO));
        escopo.criarNovoEscopo();
        if (ctx.parametros() != null) {
            List<Variavel> parametros = verificaParametros(escopo, ctx.parametros());
            ret.procedimento.setParametros(parametros);
            for (var p : parametros) {
                escopo.obterEscopoAtual().adicionar(p);
            }
        }
        if (ctx.declaracao_local() != null) {
            List<Variavel> declaracoes = new ArrayList<>();
            for (var decl : ctx.declaracao_local()) {
                declaracoes.addAll(verificaDeclLocal(escopo, decl));
            }
            for (var decl : declaracoes) {
                escopo.obterEscopoAtual().adicionar(decl);
            }

            ret.procedimento.setVariaveisLocais(declaracoes);
        }
        if (ctx.cmd() != null) {
            for (var cmd : ctx.cmd()) {
                verificaCmd(escopo.obterEscopoAtual(), cmd);
            }
        }
        escopo.abandonarEscopo();
        return ret;
    }

    public static Variavel verificaFuncao(Escopos escopo, AlgumaParser.Declaracao_globalContext ctx) {
        String nomeFuncao = ctx.IDENT().getText();
        TipoAlguma tipoRetorno = verificaTipoEstendido(ctx.tipo_estendido());
        if (tipoRetorno.tipoBasico != null && tipoRetorno.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
            erroTipoNaoDeclarado(ctx.start.getLine(), ctx.tipo_estendido().getText());
        }
        Variavel ret = new Variavel(nomeFuncao, new TipoAlguma(TipoAlguma.TipoBasico.FUNCAO));
        escopo.criarNovoEscopo();
        ret.funcao.setTipoRetorno(tipoRetorno);
        if (ctx.parametros() != null) {
            List<Variavel> parametros = verificaParametros(escopo, ctx.parametros());
            ret.funcao.setParametros(parametros);
            for (var p : parametros) {
                escopo.obterEscopoAtual().adicionar(p);
            }
        }
        List<Variavel> declaracoes = new ArrayList<>();
        for (var decl : ctx.declaracao_local()) {
            declaracoes.addAll(verificaDeclLocal(escopo, decl));
        }
        for (var decl : declaracoes) {
            escopo.obterEscopoAtual().adicionar(decl);
        }
        ret.funcao.setVariaveisLocais(declaracoes);
        for (var cmd : ctx.cmd()) {
            verificaCmd(escopo.obterEscopoAtual(), cmd);
        }
        escopo.abandonarEscopo();
        return ret;

    }

    public static List<Variavel> verificaParametros(Escopos escopo, AlgumaParser.ParametrosContext ctx) {
        List<Variavel> ret = new ArrayList<>();
        for (var parametro : ctx.parametro()) {
            ret.addAll(verificaParametro(escopo, parametro));
        }
        return ret;
    }

    public static List<Variavel> verificaParametro(Escopos escopo, AlgumaParser.ParametroContext ctx) {
        TipoAlguma tipo = verificaTipoEstendido(ctx.tipo_estendido());
        List<Variavel> ret = new ArrayList<>();
        for (var ident : ctx.identificador()) {
            Variavel novaVar = verificaIdentificador(escopo.obterEscopoAtual(), ident);
            if (novaVar.tipo != null) {
                erroIdentificadorJaDeclarado(ident.start.getLine(), ident.getText());
            } else {
                novaVar = new Variavel(ident.getText(), tipo);
                if (tipo.tipoCriado != null) {
                    for (var ts : escopo.percorrerEscoposAninhados()) {
                        Variavel aux = adicionaTipoCriado(ts, novaVar, tipo.tipoCriado);
                        if (aux.tipo != null) {
                            novaVar = new Variavel(novaVar.nome, new TipoAlguma(TipoAlguma.TipoBasico.REGISTRO));
                            if (novaVar.registro == null) {
                            }
                            novaVar = aux;
                        }
                    }
                }
                escopo.obterEscopoAtual().adicionar(novaVar);
                ret.add(novaVar);
            }
        }
        return ret;
    }
    public static List<Variavel> verificaDeclLocal(Escopos escopo, AlgumaParser.Declaracao_localContext ctx) {
        String decl = ctx.getStart().getText();
        List<Variavel> variaveis = new ArrayList<>();
        switch (decl) {
            case "declare":
                System.out.println("Declaracao simples");
                variaveis = verificaVariavel(escopo, ctx.variavel());
                break;
            case "constante":
                System.out.println("Declaracao constante");
                variaveis.add(verificaDeclConstante(escopo, ctx));
                break;
            case "tipo":
                System.out.println("Declaracao Tipo");
                variaveis.add(verificaDeclTipo(escopo, ctx));
                break;
        }
        return variaveis;
    }

    public static Variavel verificaDeclConstante(Escopos escopo, AlgumaParser.Declaracao_localContext ctx) {
        TipoAlguma tipo = new TipoAlguma(verificaTipoBasico(ctx.tipo_basico()));
        if (tipo.tipoBasico != null && tipo.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
            erroTipoNaoDeclarado(ctx.start.getLine(), ctx.tipo().getText());
            System.out.println("TIPO BASICO NAO ENCONTRADO DECL CONSTANTE");
            return null;
        }
        return new Variavel(ctx.IDENT().getText(), tipo);
    }

    public static Variavel verificaDeclTipo(Escopos escopo, AlgumaParser.Declaracao_localContext ctx) {
        TipoAlguma tipoIDENT = verificaTipo(ctx.tipo()); 
        if (tipoIDENT.tipoBasico != null && tipoIDENT.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
            if (tipoIDENT.tipoBasico != null && tipoIDENT.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
                erroTipoNaoDeclarado(ctx.start.getLine(), ctx.tipo().getText());
            }
            return null;
        }
        String nome = ctx.IDENT().getText();
        TipoAlguma.adicionaTipoCriado(nome);
        Variavel novoTipo = new Variavel(nome, tipoIDENT);
        if (novoTipo.tipo.tipoBasico == TipoAlguma.TipoBasico.REGISTRO) {
            novoTipo.registro = verificaRegistro(escopo, ctx.tipo().registro()).registro;
        }
        return novoTipo;
    }

    public static void verificaCmd(TabelaDeSimbolos ts, AlgumaParser.CmdContext ctx) {
        if (ctx.cmdAtribuicao() != null) {
            System.out.println("CMD Atribuicao");
            verificaCmdAtribuicao(ts, ctx.cmdAtribuicao());
        } else if (ctx.cmdEscreva() != null) {
            System.out.println("CMD Escreva");
            verificaCmdEscreva(ts, ctx.cmdEscreva());
        } else if (ctx.cmdLeia() != null) {
            System.out.println("CMD Leia");
            verificaCmdLeia(ts, ctx.cmdLeia());
        } else if (ctx.cmdEnquanto() != null) {
            System.out.println("CMD Enquanto");
            verificaCmdEnquanto(ts, ctx.cmdEnquanto());
        } else if (ctx.cmdSe() != null) {
            System.out.println("CMD Se");
            verificaCmdSe(ts, ctx.cmdSe());
        } else if (ctx.cmdFaca() != null) {
            System.out.println("CMD Faca");
            verificaCmdFaca(ts, ctx.cmdFaca());
        } else if (ctx.cmdRetorne() != null) {
            verificaCmdRetorne(ctx.cmdRetorne());
        }
    }

    public static void verificaCmdLeia(TabelaDeSimbolos ts, AlgumaParser.CmdLeiaContext ctx) {
        List<Integer> ponteiros = new ArrayList<>();
        String[] idents = ctx.getText().split(",");
        for (int i = 0; i < idents.length; i++) {
            if (idents[i].contains("^")) {
                System.out.println("CMD LEIA contem ponteiro");
                ponteiros.add(i);
            }
        }
        for (var ident : ctx.identificador()) {
            Variavel v = verificaIdentificador(ts, ident);
            if (v != null && v.tipo == null) {
                erroIdentificadorNaoDeclarado(ident.getStart().getLine(), ident.getText());
            }
        }
    }

    public static void verificaCmdEscreva(TabelaDeSimbolos ts, AlgumaParser.CmdEscrevaContext ctx) {
        for (var exp : ctx.expressao()) {
            verificaExpressao(ts, exp);
        }
    }

    public static void verificaCmdAtribuicao(TabelaDeSimbolos ts, AlgumaParser.CmdAtribuicaoContext ctx) {
        Variavel esquerdo = verificaIdentificador(ts, ctx.identificador());
        TipoAlguma tipoEsquerdo = esquerdo.tipo;
        if (tipoEsquerdo == null) {
            erroIdentificadorNaoDeclarado(ctx.identificador().start.getLine(), ctx.identificador().getText());
            return;
        }
        TipoAlguma tipoDireito = verificaExpressao(ts, ctx.expressao());
        String pont = "";
        if (ctx.getChild(0).getText().contains("^")) {
            pont += "^";
            tipoEsquerdo = esquerdo.getTipoPonteiroAninhado();
        }
        if (tipoEsquerdo.tipoBasico != null && tipoDireito.tipoBasico != null) {
            if (verificaEquivalenciaTipos(tipoEsquerdo, tipoDireito).tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
                erroAtribuicaoIncompativel(ctx.identificador().start.getLine(), pont + ctx.identificador().getText());
            }
        }
    }

    public static void verificaCmdEnquanto(TabelaDeSimbolos ts, AlgumaParser.CmdEnquantoContext ctx) {
        verificaExpressao(ts, ctx.expressao());
    }

    public static void verificaCmdSe(TabelaDeSimbolos ts, AlgumaParser.CmdSeContext ctx) {
        verificaExpressao(ts, ctx.expressao());
        for (var cmd : ctx.cmd()) {
            verificaCmd(ts, cmd);
        }
    }

    public static void verificaCmdFaca(TabelaDeSimbolos ts, AlgumaParser.CmdFacaContext ctx) {
        for (var cmd : ctx.cmd()) {
            verificaCmd(ts, cmd);
        }
        verificaExpressao(ts, ctx.expressao());
    }

    public static void verificaCmdRetorne(AlgumaParser.CmdRetorneContext ctx) {
        if (!AlgumaSemanticoUtils.podeRetornar) {
            AlgumaSemanticoUtils.adicionaErro("Linha " + ctx.start.getLine() + ": comando retorne nao permitido nesse escopo");
        }
    }

    public static List<Variavel> verificaVariavel(Escopos escopo, AlgumaParser.VariavelContext ctx) {
        List<Variavel> variaveis = new ArrayList<>();
        TipoAlguma tipo = verificaTipo(ctx.tipo());
        if (tipo.tipoBasico != null && tipo.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
            erroTipoNaoDeclarado(ctx.start.getLine(), ctx.tipo().getText());
        }
        for (var ident : ctx.identificador()) {
            Variavel novaVar = verificaIdentificador(escopo.obterEscopoAtual(), ident);
            if (novaVar.tipo != null) {
                erroIdentificadorJaDeclarado(ident.getStart().getLine(), ident.getText());
            } else {
                novaVar = new Variavel(novaVar.nome, tipo);
                if (tipo.tipoBasico == TipoAlguma.TipoBasico.REGISTRO) {
                    novaVar.registro = verificaRegistro(escopo, ctx.tipo().registro()).registro;
                } else if (tipo.tipoCriado != null) {
                    novaVar = adicionaTipoCriado(escopo.obterEscopoAtual(), novaVar, tipo.tipoCriado);
                }
                escopo.obterEscopoAtual().adicionar(novaVar);
                variaveis.add(novaVar);
            }
        }
        return variaveis;
    }

    public static Variavel verificaRegistro(Escopos escopo, AlgumaParser.RegistroContext ctx) {
        Variavel reg = new Variavel(null, new TipoAlguma(TipoAlguma.TipoBasico.REGISTRO));
        escopo.criarNovoEscopo();
        for (int i = 0; i < ctx.variavel().size(); i++) {
            reg.registro.adicionaNoRegistro(verificaVariavel(escopo, ctx.variavel(i)));
        }
        escopo.abandonarEscopo();
        return reg;
    }
    
    public static TipoAlguma verificaTipo(AlgumaParser.TipoContext ctx) {
        if (ctx.registro() != null) {
            return new TipoAlguma(TipoAlguma.TipoBasico.REGISTRO);
        }
        return verificaTipoEstendido(ctx.tipo_estendido());
    }

    public static TipoAlguma verificaTipoEstendido(AlgumaParser.Tipo_estendidoContext ctx) {
        if (ctx.getChild(0).getText().contains("^")) {
            TipoAlguma tipoPonteiro = new TipoAlguma(TipoAlguma.TipoBasico.PONTEIRO);
            TipoAlguma tipoAponta = verificaTipoBasicoIdent(ctx.tipo_basico_ident());
            return new TipoAlguma(tipoPonteiro, tipoAponta);
        }
        return verificaTipoBasicoIdent(ctx.tipo_basico_ident());
    }

    public static TipoAlguma verificaTipoBasicoIdent(AlgumaParser.Tipo_basico_identContext ctx) {
        if (ctx.tipo_basico() != null) {
            return new TipoAlguma(verificaTipoBasico(ctx.tipo_basico()));
        }
        if (TipoAlguma.existeTipoCriado(ctx.IDENT().getText())) {
            return new TipoAlguma(TipoAlguma.getTipoCriado(ctx.IDENT().getText()));
        }
        return new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
    }

    public static TipoAlguma.TipoBasico verificaTipoBasico(AlgumaParser.Tipo_basicoContext ctx) {
        TipoAlguma.TipoBasico tipo = TipoAlguma.TipoBasico.INVALIDO;
        switch (ctx.getText()) {
            case "inteiro":
                tipo = TipoAlguma.TipoBasico.INTEIRO;
                break;
            case "real":
                tipo = TipoAlguma.TipoBasico.REAL;
                break;
            case "literal":
                tipo = TipoAlguma.TipoBasico.LITERAL;
                break;
            case "logico":
                tipo = TipoAlguma.TipoBasico.LOGICO;
                break;
        }
        return tipo;
    }

    public static TipoAlguma verificaExpressao(TabelaDeSimbolos ts, AlgumaParser.ExpressaoContext ctx) {
        TipoAlguma tipoPrimeiroTermo = verificaTermoLogico(ts, ctx.termo_logico(0));
        if (ctx.termo_logico().size() > 1) {
            for (int i = 1; i < ctx.termo_logico().size(); i++) {
                TipoAlguma tipoSegundoTermo = verificaTermoLogico(ts, ctx.termo_logico(i));
                tipoPrimeiroTermo = verificaEquivalenciaTipos(tipoPrimeiroTermo, tipoSegundoTermo);
            }
            if (tipoPrimeiroTermo.tipoBasico != TipoAlguma.TipoBasico.INVALIDO) {
                tipoPrimeiroTermo.tipoBasico = TipoAlguma.TipoBasico.LOGICO;
            }
        }
        return tipoPrimeiroTermo;
    }

    public static TipoAlguma verificaTermoLogico(TabelaDeSimbolos ts, AlgumaParser.Termo_logicoContext ctx) {
        TipoAlguma tipoPrimeiroFator = verificaFatorLogico(ts, ctx.fator_logico(0));
        if (ctx.fator_logico().size() > 1) {
            for (int i = 1; i < ctx.fator_logico().size(); i++) {
                TipoAlguma tipoSegundoFator = verificaFatorLogico(ts, ctx.fator_logico(i));
                tipoPrimeiroFator = verificaEquivalenciaTipos(tipoPrimeiroFator, tipoSegundoFator);
            }
            if (tipoPrimeiroFator.tipoBasico != TipoAlguma.TipoBasico.INVALIDO) {
                tipoPrimeiroFator.tipoBasico = TipoAlguma.TipoBasico.LOGICO;
            }
        }
        return tipoPrimeiroFator;
    }

    public static TipoAlguma verificaFatorLogico(TabelaDeSimbolos ts, AlgumaParser.Fator_logicoContext ctx) {
        TipoAlguma parcelaLogica = verificaParcelaLogica(ts, ctx.parcela_logica());
        if (ctx.getChild(0).getText().contains("nao")) {
            return verificaEquivalenciaTipos(parcelaLogica, new TipoAlguma(TipoAlguma.TipoBasico.LOGICO));
        }
        return parcelaLogica;
    }

    public static TipoAlguma verificaParcelaLogica(TabelaDeSimbolos ts, AlgumaParser.Parcela_logicaContext ctx) {
        TipoAlguma tipoParcela;
        if (ctx.exp_relacional() != null) {
            tipoParcela = verificaExpRelacional(ts, ctx.exp_relacional());
        } else {
            tipoParcela = new TipoAlguma(TipoAlguma.TipoBasico.LOGICO);
        }
        return tipoParcela;
    }

    public static TipoAlguma verificaExpRelacional(TabelaDeSimbolos ts, AlgumaParser.Exp_relacionalContext ctx) {
        TipoAlguma tipoPrimeiraExp = verificaExpAritmetica(ts, ctx.exp_aritmetica(0));
        if (ctx.exp_aritmetica().size() > 1) {
            TipoAlguma tipoSegundaExp = verificaExpAritmetica(ts, ctx.exp_aritmetica(1));
            tipoPrimeiraExp = verificaEquivalenciaTipos(tipoPrimeiraExp, tipoSegundaExp);
            if (tipoPrimeiraExp.tipoBasico != TipoAlguma.TipoBasico.INVALIDO) {
                tipoPrimeiraExp.tipoBasico = TipoAlguma.TipoBasico.LOGICO;
            }
        }
        return tipoPrimeiraExp;
    }

    public static TipoAlguma verificaExpAritmetica(TabelaDeSimbolos ts, AlgumaParser.Exp_aritmeticaContext ctx) {
        TipoAlguma tipoPrimeiroTermo = verificaTermo(ts, ctx.termo(0));
        if (ctx.termo().size() > 1) {
            for (int i = 1; i < ctx.termo().size(); i++) {
                TipoAlguma tipoSegundoTermo = verificaTermo(ts, ctx.termo(i));
                tipoPrimeiroTermo = verificaEquivalenciaTipos(tipoPrimeiroTermo, tipoSegundoTermo);
            }
            for (var op : ctx.op1()) {
                if (op.getText().contains("+") && tipoPrimeiroTermo.tipoBasico == TipoAlguma.TipoBasico.LITERAL) {
                    tipoPrimeiroTermo = verificaEquivalenciaTipos(tipoPrimeiroTermo, new TipoAlguma(TipoAlguma.TipoBasico.LITERAL));
                } else {
                    tipoPrimeiroTermo = verificaEquivalenciaTipos(tipoPrimeiroTermo, new TipoAlguma(TipoAlguma.TipoBasico.INTEIRO));
                }
            }
        }
        return tipoPrimeiroTermo;
    }

    public static TipoAlguma verificaTermo(TabelaDeSimbolos ts, AlgumaParser.TermoContext ctx) {
        TipoAlguma tipoPrimeiroFator = verificaFator(ts, ctx.fator(0));
        if (ctx.fator().size() > 1) {
            for (int i = 1; i < ctx.fator().size(); i++) {
                TipoAlguma tipoSegundoFator = verificaFator(ts, ctx.fator(i));
                tipoPrimeiroFator = verificaEquivalenciaTipos(tipoPrimeiroFator, tipoSegundoFator);
            }
        }
        return tipoPrimeiroFator;
    }

    public static TipoAlguma verificaFator(TabelaDeSimbolos ts, AlgumaParser.FatorContext ctx) {
        TipoAlguma tipoPrimeiraParcela = verificaParcela(ts, ctx.parcela(0));
        if (ctx.parcela().size() > 1) {
            for (int i = 1; i < ctx.parcela().size(); i++) {
                TipoAlguma tipoSegundaParcela = verificaParcela(ts, ctx.parcela(i));
                tipoPrimeiraParcela = verificaEquivalenciaTipos(tipoPrimeiraParcela, tipoSegundaParcela);
            }
        }
        return tipoPrimeiraParcela;
    }

    public static TipoAlguma verificaParcela(TabelaDeSimbolos ts, AlgumaParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null) {
            TipoAlguma pUnario = verificaParcelaUnario(ts, ctx.parcela_unario());
            if (ctx.op_unario() != null) {
                if (pUnario.tipoBasico != TipoAlguma.TipoBasico.INTEIRO && pUnario.tipoBasico != TipoAlguma.TipoBasico.REAL) {
                    return new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
                }
                return pUnario;
            }
            return pUnario;
        }
        return verificaParcelaNaoUnario(ts, ctx.parcela_nao_unario());
    }

    public static TipoAlguma verificaParcelaUnario(TabelaDeSimbolos ts, AlgumaParser.Parcela_unarioContext ctx) {
        if (ctx.identificador() != null) {
            if (ctx.getChild(0).getText().contains("^")) {
                System.out.println("PARCELA UNARIO COM ^ =>" + ctx.getText());
            }
            Variavel ident = verificaIdentificador(ts, ctx.identificador());
            if (ident.tipo == null) {
                erroIdentificadorNaoDeclarado(ctx.identificador().start.getLine(), ident.nome);
                return new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
            }
            return ident.tipo;
        } else if (ctx.IDENT() != null) {
            return verificaMetodo(ts, ctx.IDENT(), ctx.expressao());
        } else if (ctx.NUM_INT() != null) {
            return new TipoAlguma(TipoAlguma.TipoBasico.INTEIRO);
        } else if (ctx.NUM_REAL() != null) {
            return new TipoAlguma(TipoAlguma.TipoBasico.REAL);
        }
        TipoAlguma primeiraExp = verificaExpressao(ts, ctx.expressao(0));
        if (ctx.expressao().size() > 1) {
            for (int i = 1; i < ctx.expressao().size(); i++) {
                TipoAlguma segundaExp = verificaExpressao(ts, ctx.expressao(i));
                primeiraExp = verificaEquivalenciaTipos(primeiraExp, segundaExp);
            }
        }
        return primeiraExp;
    }

    public static TipoAlguma verificaParcelaNaoUnario(TabelaDeSimbolos ts, AlgumaParser.Parcela_nao_unarioContext ctx) {
        if (ctx.CADEIA() != null) {
            return new TipoAlguma(TipoAlguma.TipoBasico.LITERAL);
        } else {
            if (ctx.getChild(0).getText().contains("&")) {
                return new TipoAlguma(TipoAlguma.TipoBasico.ENDERECO);
            }
            Variavel ident = verificaIdentificador(ts, ctx.identificador());
            if (ident.tipo == null) {
                erroIdentificadorNaoDeclarado(ctx.identificador().start.getLine(), ident.nome);
                return new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
            }
            return ident.tipo;
        }
    }

    public static Variavel verificaIdentificador(TabelaDeSimbolos ts, AlgumaParser.IdentificadorContext ctx) {
        String nome = ctx.IDENT(0).getText();
        if (ctx.dimensao().getChildCount() != 0) {
        }
        if (ts.existe(nome)) {
            Variavel ret = ts.getVariavel(nome);
            if (ctx.IDENT().size() > 1) {
                ret = ret.getVarNoRegistro(ctx.IDENT(1).getText());
                if (ret == null) {
                    erroIdentificadorNaoDeclarado(ctx.start.getLine(), ctx.getText());
                }
            }
            return ret;
        }
        nome = ctx.IDENT(0).getText();
        for (int i = 1; i < ctx.IDENT().size(); i++) {
            nome += "." + ctx.IDENT(i);
        }
        return new Variavel(nome, null);
    }

    public static TipoAlguma verificaEquivalenciaTipos(TipoAlguma a, TipoAlguma b) {
        TipoAlguma t = new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
        if (a.tipoBasico == TipoAlguma.TipoBasico.PONTEIRO && b.tipoBasico == TipoAlguma.TipoBasico.ENDERECO) {
            t.tipoBasico = TipoAlguma.TipoBasico.PONTEIRO;
        } else if ((a.tipoBasico == TipoAlguma.TipoBasico.REAL && b.tipoBasico == TipoAlguma.TipoBasico.REAL)
                || (a.tipoBasico == TipoAlguma.TipoBasico.REAL && b.tipoBasico == TipoAlguma.TipoBasico.INTEIRO)
                || (a.tipoBasico == TipoAlguma.TipoBasico.INTEIRO && b.tipoBasico == TipoAlguma.TipoBasico.REAL)
                || (a.tipoBasico == TipoAlguma.TipoBasico.INTEIRO && b.tipoBasico == TipoAlguma.TipoBasico.INTEIRO)) {
            t.tipoBasico = TipoAlguma.TipoBasico.REAL;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.LITERAL && b.tipoBasico == TipoAlguma.TipoBasico.LITERAL) {
            t.tipoBasico = TipoAlguma.TipoBasico.LITERAL;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.LOGICO && b.tipoBasico == TipoAlguma.TipoBasico.LOGICO) {
            t.tipoBasico = TipoAlguma.TipoBasico.LOGICO;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.REGISTRO && b.tipoBasico == TipoAlguma.TipoBasico.REGISTRO) {
            System.out.println("REGISTRO NA VERIFICAO DE EQUIVALENCIA");
            t.tipoBasico = TipoAlguma.TipoBasico.REGISTRO;
        }
        return t;
    }

    public static TipoAlguma verificaEquivalenciaTiposExatos(TipoAlguma a, TipoAlguma b) {
        TipoAlguma t = new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
        if (a.tipoBasico == TipoAlguma.TipoBasico.ENDERECO && b.tipoBasico == TipoAlguma.TipoBasico.PONTEIRO) {
            t.tipoBasico = TipoAlguma.TipoBasico.PONTEIRO;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.INTEIRO && b.tipoBasico == TipoAlguma.TipoBasico.INTEIRO) {
            t.tipoBasico = TipoAlguma.TipoBasico.INTEIRO;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.REAL && b.tipoBasico == TipoAlguma.TipoBasico.REAL) {
            t.tipoBasico = TipoAlguma.TipoBasico.REAL;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.LITERAL && b.tipoBasico == TipoAlguma.TipoBasico.LITERAL) {
            t.tipoBasico = TipoAlguma.TipoBasico.LITERAL;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.LOGICO && b.tipoBasico == TipoAlguma.TipoBasico.LOGICO) {
            t.tipoBasico = TipoAlguma.TipoBasico.LOGICO;
        } else if (a.tipoBasico == TipoAlguma.TipoBasico.REGISTRO && b.tipoBasico == TipoAlguma.TipoBasico.REGISTRO) {
            System.out.println("REGISTRO NA VERIFICAO DE EQUIVALENCIA");
            t.tipoBasico = TipoAlguma.TipoBasico.REGISTRO;
        }
        return t;
    }

    public static Variavel adicionaTipoCriado(TabelaDeSimbolos ts, Variavel v, String nome) {
        if (ts.existe(nome)) {
            Variavel modelo = ts.getVariavel(nome);
            if (modelo.tipo.tipoBasico == TipoAlguma.TipoBasico.REGISTRO) {
                Variavel ret = new Variavel(v.nome, new TipoAlguma(TipoAlguma.TipoBasico.REGISTRO));
                ret.setRegistro(modelo.getRegistro());
                ret.tipo = v.tipo;
                return ret;
            }
        }
        return new Variavel(null, null);
    }

    public static TipoAlguma verificaMetodo(TabelaDeSimbolos ts, TerminalNode IDENT, List<AlgumaParser.ExpressaoContext> expressoes) {
        TipoAlguma ret = null;
        Variavel metodo;
        if (!ts.existe(IDENT.getText())) {
            System.out.println("IDENT em verifica METODO nao EXISTE");
            metodo = null;
        } else {
            metodo = ts.getVariavel(IDENT.getText());
        }
        if (metodo.funcao != null) {
            if (metodo.funcao.getParametros().size() != expressoes.size()) {
                ret = new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
            }
            for (var exp : expressoes) {
                TipoAlguma tipoExp = verificaExpressao(ts, exp);
                if (ret == null || ret.tipoBasico != TipoAlguma.TipoBasico.INVALIDO) {
                    ret = verificaEquivalenciaTiposExatos(metodo.funcao.getTipoRetorno(), tipoExp);
                }
            }
        }
        if (ret == null || ret.tipoBasico == TipoAlguma.TipoBasico.INVALIDO) {
            erroIncompatibilidadeParametros(IDENT.getSymbol().getLine(), IDENT.getText());
            return new TipoAlguma(TipoAlguma.TipoBasico.INVALIDO);
        }
        return ret;
    }

    public static void erroIdentificadorNaoDeclarado(int linha, String nome) {
        AlgumaSemanticoUtils.adicionaErro("Linha " + linha + ": identificador " + nome + " nao declarado");
        System.out.println("Linha " + linha + ": identificador " + nome + " nao declarado");
    }

    public static void erroIdentificadorJaDeclarado(int linha, String nome) {
        AlgumaSemanticoUtils.adicionaErro("Linha " + linha + ": identificador " + nome + " ja declarado anteriormente");
        System.out.println("Linha " + linha + ": identificador " + nome + " ja declarado anteriormente");
    }

    public static void erroAtribuicaoIncompativel(int linha, String nome) {
        AlgumaSemanticoUtils.adicionaErro("Linha " + linha + ": atribuicao nao compativel para " + nome);
        System.out.println("Linha " + linha + ": atribuicao nao compativel para " + nome);
    }

    public static void erroTipoNaoDeclarado(int linha, String nome) {
        AlgumaSemanticoUtils.adicionaErro("Linha " + linha + ": tipo " + nome + " nao declarado");
        System.out.println("Linha " + linha + ": tipo " + nome + " nao declarado");
    }

    public static void erroIncompatibilidadeParametros(int linha, String nome) {
        AlgumaSemanticoUtils.adicionaErro("Linha " + linha + ": incompatibilidade de parametros na chamada de " + nome);
        System.out.println("Linha " + linha + ": incompatibilidade de parametros na chamada de " + nome);
    }
}
