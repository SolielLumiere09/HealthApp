// Generated from C:/Users/Omar Valdivia/Documents/GitHub/HealthApp/vision/final/app/src/main/java/com/google/firebase/codelab/textExtractor/analyzer\labelGrammar.g4 by ANTLR 4.9.1
package labelParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link labelGrammarParser}.
 */
public interface labelGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(labelGrammarParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(labelGrammarParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(labelGrammarParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(labelGrammarParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#tamanoPorcion_statement}.
	 * @param ctx the parse tree
	 */
	void enterTamanoPorcion_statement(labelGrammarParser.TamanoPorcion_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#tamanoPorcion_statement}.
	 * @param ctx the parse tree
	 */
	void exitTamanoPorcion_statement(labelGrammarParser.TamanoPorcion_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#porcionesEmpaque_statement}.
	 * @param ctx the parse tree
	 */
	void enterPorcionesEmpaque_statement(labelGrammarParser.PorcionesEmpaque_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#porcionesEmpaque_statement}.
	 * @param ctx the parse tree
	 */
	void exitPorcionesEmpaque_statement(labelGrammarParser.PorcionesEmpaque_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#caloriasStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaloriasStatement(labelGrammarParser.CaloriasStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#caloriasStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaloriasStatement(labelGrammarParser.CaloriasStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#grasaTotal_statement}.
	 * @param ctx the parse tree
	 */
	void enterGrasaTotal_statement(labelGrammarParser.GrasaTotal_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#grasaTotal_statement}.
	 * @param ctx the parse tree
	 */
	void exitGrasaTotal_statement(labelGrammarParser.GrasaTotal_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#grasaSaturada_statement}.
	 * @param ctx the parse tree
	 */
	void enterGrasaSaturada_statement(labelGrammarParser.GrasaSaturada_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#grasaSaturada_statement}.
	 * @param ctx the parse tree
	 */
	void exitGrasaSaturada_statement(labelGrammarParser.GrasaSaturada_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#grasaTrans_statement}.
	 * @param ctx the parse tree
	 */
	void enterGrasaTrans_statement(labelGrammarParser.GrasaTrans_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#grasaTrans_statement}.
	 * @param ctx the parse tree
	 */
	void exitGrasaTrans_statement(labelGrammarParser.GrasaTrans_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#carbs_statement}.
	 * @param ctx the parse tree
	 */
	void enterCarbs_statement(labelGrammarParser.Carbs_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#carbs_statement}.
	 * @param ctx the parse tree
	 */
	void exitCarbs_statement(labelGrammarParser.Carbs_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#azucar_statement}.
	 * @param ctx the parse tree
	 */
	void enterAzucar_statement(labelGrammarParser.Azucar_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#azucar_statement}.
	 * @param ctx the parse tree
	 */
	void exitAzucar_statement(labelGrammarParser.Azucar_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#colesterol_statement}.
	 * @param ctx the parse tree
	 */
	void enterColesterol_statement(labelGrammarParser.Colesterol_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#colesterol_statement}.
	 * @param ctx the parse tree
	 */
	void exitColesterol_statement(labelGrammarParser.Colesterol_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#sodio_statement}.
	 * @param ctx the parse tree
	 */
	void enterSodio_statement(labelGrammarParser.Sodio_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#sodio_statement}.
	 * @param ctx the parse tree
	 */
	void exitSodio_statement(labelGrammarParser.Sodio_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link labelGrammarParser#proteina_statement}.
	 * @param ctx the parse tree
	 */
	void enterProteina_statement(labelGrammarParser.Proteina_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link labelGrammarParser#proteina_statement}.
	 * @param ctx the parse tree
	 */
	void exitProteina_statement(labelGrammarParser.Proteina_statementContext ctx);
}