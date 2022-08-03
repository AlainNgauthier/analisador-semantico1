// Generated from br/ufscar/dc/compiladores/semantico/Alguma.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.semantico;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AlgumaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AlgumaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AlgumaParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(AlgumaParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(AlgumaParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(AlgumaParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(AlgumaParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(AlgumaParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(AlgumaParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(AlgumaParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(AlgumaParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(AlgumaParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(AlgumaParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(AlgumaParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(AlgumaParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(AlgumaParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(AlgumaParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(AlgumaParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(AlgumaParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(AlgumaParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(AlgumaParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(AlgumaParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(AlgumaParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(AlgumaParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(AlgumaParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(AlgumaParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(AlgumaParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(AlgumaParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(AlgumaParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(AlgumaParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(AlgumaParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(AlgumaParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(AlgumaParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(AlgumaParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(AlgumaParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(AlgumaParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(AlgumaParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(AlgumaParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(AlgumaParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(AlgumaParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp3(AlgumaParser.Op3Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(AlgumaParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(AlgumaParser.Parcela_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(AlgumaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(AlgumaParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(AlgumaParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(AlgumaParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(AlgumaParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(AlgumaParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(AlgumaParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op_logico_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_1(AlgumaParser.Op_logico_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaParser#op_logico_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_2(AlgumaParser.Op_logico_2Context ctx);
}