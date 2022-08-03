package br.ufscar.dc.compiladores.semantico;

import br.ufscar.dc.compiladores.semantico.utils.Escopos;
import br.ufscar.dc.compiladores.semantico.utils.AlgumaSemanticoUtils;
import br.ufscar.dc.compiladores.semantico.utils.TabelaDeSimbolos;
import br.ufscar.dc.compiladores.semantico.utils.Variavel;
import java.util.ArrayList;
import java.util.List;

public class AlgumaSemantico extends AlgumaBaseVisitor<Void> {
    Escopos escopo;

    public Escopos getEscopos() {
        return escopo;
    }

    @Override
    public Void visitPrograma(AlgumaParser.ProgramaContext ctx) {
        escopo = new Escopos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDecl_local_global(AlgumaParser.Decl_local_globalContext ctx) {
        List<Variavel> entrada = new ArrayList<>();
        if (ctx.declaracao_global() != null) {
            entrada.add(AlgumaSemanticoUtils.verificaDeclGlobal(escopo, ctx.declaracao_global()));
        } else {
            entrada = AlgumaSemanticoUtils.verificaDeclLocal(escopo, ctx.declaracao_local());
        }
        for (var v : entrada) {
            escopo.obterEscopoAtual().adicionar(v);
        }
        return super.visitDecl_local_global(ctx);
    }

    @Override
    public Void visitCorpo(AlgumaParser.CorpoContext ctx) {
        List<Variavel> varsDeclaradas = new ArrayList<>();
        for (var decl : ctx.declaracao_local()) {
            varsDeclaradas.addAll(AlgumaSemanticoUtils.verificaDeclLocal(escopo, decl));
            for (var v : varsDeclaradas) {
                escopo.obterEscopoAtual().adicionar(v);
            }
        }
        for (AlgumaParser.CmdContext cmd : ctx.cmd()) {
            AlgumaSemanticoUtils.verificaCmd(escopo.obterEscopoAtual(), cmd);
        }
        return super.visitCorpo(ctx);
    }
}
