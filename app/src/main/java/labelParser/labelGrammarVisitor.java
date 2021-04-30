// Generated from C:/Users/Omar Valdivia/Documents/GitHub/HealthApp/vision/final/app/src/main/java/com/google/firebase/codelab/textExtractor/analyzer\labelGrammar.g4 by ANTLR 4.9.1
package labelParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link labelGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface labelGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(labelGrammarParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(labelGrammarParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#tamanoPorcion_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTamanoPorcion_statement(labelGrammarParser.TamanoPorcion_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#porcionesEmpaque_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPorcionesEmpaque_statement(labelGrammarParser.PorcionesEmpaque_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#caloriasStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaloriasStatement(labelGrammarParser.CaloriasStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#grasaTotal_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrasaTotal_statement(labelGrammarParser.GrasaTotal_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#grasaSaturada_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrasaSaturada_statement(labelGrammarParser.GrasaSaturada_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#grasaTrans_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrasaTrans_statement(labelGrammarParser.GrasaTrans_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#carbs_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCarbs_statement(labelGrammarParser.Carbs_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#azucar_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAzucar_statement(labelGrammarParser.Azucar_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#colesterol_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColesterol_statement(labelGrammarParser.Colesterol_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#sodio_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSodio_statement(labelGrammarParser.Sodio_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link labelGrammarParser#proteina_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProteina_statement(labelGrammarParser.Proteina_statementContext ctx);
}