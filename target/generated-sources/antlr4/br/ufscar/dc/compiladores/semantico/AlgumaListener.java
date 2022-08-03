// Generated from br/ufscar/dc/compiladores/semantico/Alguma.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.semantico;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AlgumaParser}.
 */
public interface AlgumaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AlgumaParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AlgumaParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(AlgumaParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(AlgumaParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(AlgumaParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(AlgumaParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(AlgumaParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(AlgumaParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(AlgumaParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(AlgumaParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(AlgumaParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(AlgumaParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(AlgumaParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(AlgumaParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AlgumaParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AlgumaParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(AlgumaParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(AlgumaParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(AlgumaParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(AlgumaParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(AlgumaParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(AlgumaParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(AlgumaParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(AlgumaParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(AlgumaParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(AlgumaParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(AlgumaParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(AlgumaParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(AlgumaParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(AlgumaParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(AlgumaParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(AlgumaParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(AlgumaParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(AlgumaParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(AlgumaParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(AlgumaParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(AlgumaParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(AlgumaParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(AlgumaParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(AlgumaParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(AlgumaParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(AlgumaParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(AlgumaParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(AlgumaParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(AlgumaParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(AlgumaParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(AlgumaParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(AlgumaParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(AlgumaParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(AlgumaParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(AlgumaParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(AlgumaParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(AlgumaParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(AlgumaParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(AlgumaParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(AlgumaParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(AlgumaParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(AlgumaParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(AlgumaParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(AlgumaParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(AlgumaParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(AlgumaParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(AlgumaParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(AlgumaParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(AlgumaParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(AlgumaParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(AlgumaParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(AlgumaParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(AlgumaParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(AlgumaParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(AlgumaParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(AlgumaParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(AlgumaParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(AlgumaParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(AlgumaParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(AlgumaParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(AlgumaParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(AlgumaParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(AlgumaParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(AlgumaParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(AlgumaParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(AlgumaParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(AlgumaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(AlgumaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(AlgumaParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(AlgumaParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(AlgumaParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(AlgumaParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(AlgumaParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(AlgumaParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(AlgumaParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(AlgumaParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(AlgumaParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(AlgumaParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(AlgumaParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(AlgumaParser.Parcela_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(AlgumaParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(AlgumaParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(AlgumaParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(AlgumaParser.Op_logico_2Context ctx);
}