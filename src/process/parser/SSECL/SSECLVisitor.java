// Generated from java-escape by ANTLR 4.11.1
package process.parser.SSECL;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SSECLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SSECLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SSECLParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(SSECLParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#configuration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfiguration(SSECLParser.ConfigurationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#entityState}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityState(SSECLParser.EntityStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#entityDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityDef(SSECLParser.EntityDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#attributeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeDef(SSECLParser.AttributeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#connectionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnectionDef(SSECLParser.ConnectionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#connection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnection(SSECLParser.ConnectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(SSECLParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SSECLParser#visualizationDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisualizationDef(SSECLParser.VisualizationDefContext ctx);
}