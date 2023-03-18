// Generated from java-escape by ANTLR 4.11.1
package process.parser.SSECL;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SSECLParser}.
 */
public interface SSECLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SSECLParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(SSECLParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(SSECLParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#configuration}.
	 * @param ctx the parse tree
	 */
	void enterConfiguration(SSECLParser.ConfigurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#configuration}.
	 * @param ctx the parse tree
	 */
	void exitConfiguration(SSECLParser.ConfigurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#entityState}.
	 * @param ctx the parse tree
	 */
	void enterEntityState(SSECLParser.EntityStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#entityState}.
	 * @param ctx the parse tree
	 */
	void exitEntityState(SSECLParser.EntityStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#entityDef}.
	 * @param ctx the parse tree
	 */
	void enterEntityDef(SSECLParser.EntityDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#entityDef}.
	 * @param ctx the parse tree
	 */
	void exitEntityDef(SSECLParser.EntityDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#attributeDef}.
	 * @param ctx the parse tree
	 */
	void enterAttributeDef(SSECLParser.AttributeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#attributeDef}.
	 * @param ctx the parse tree
	 */
	void exitAttributeDef(SSECLParser.AttributeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#connectionDef}.
	 * @param ctx the parse tree
	 */
	void enterConnectionDef(SSECLParser.ConnectionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#connectionDef}.
	 * @param ctx the parse tree
	 */
	void exitConnectionDef(SSECLParser.ConnectionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#connection}.
	 * @param ctx the parse tree
	 */
	void enterConnection(SSECLParser.ConnectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#connection}.
	 * @param ctx the parse tree
	 */
	void exitConnection(SSECLParser.ConnectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(SSECLParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(SSECLParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SSECLParser#visualizationDef}.
	 * @param ctx the parse tree
	 */
	void enterVisualizationDef(SSECLParser.VisualizationDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SSECLParser#visualizationDef}.
	 * @param ctx the parse tree
	 */
	void exitVisualizationDef(SSECLParser.VisualizationDefContext ctx);
}