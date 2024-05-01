// Generated from D:/SE_PROJECT_GROUP_ByteTheBullet_Giulia/src/main/java/frontend/parse/BabyCobolGrammar.g4 by ANTLR 4.13.1
package frontend.parse;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BabyCobolGrammar}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BabyCobolGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BabyCobolGrammar.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#identificationdivision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationdivision(BabyCobolGrammar.IdentificationdivisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#identificationname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationname(BabyCobolGrammar.IdentificationnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#identificationvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificationvalue(BabyCobolGrammar.IdentificationvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#datadivision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatadivision(BabyCobolGrammar.DatadivisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#dataDescriptionEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDescriptionEntry(BabyCobolGrammar.DataDescriptionEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevel(BabyCobolGrammar.LevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#proceduredivision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProceduredivision(BabyCobolGrammar.ProceduredivisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#using}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsing(BabyCobolGrammar.UsingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(BabyCobolGrammar.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(BabyCobolGrammar.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#paragraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParagraph(BabyCobolGrammar.ParagraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#accept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccept(BabyCobolGrammar.AcceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(BabyCobolGrammar.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter(BabyCobolGrammar.AlterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#procedurename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedurename(BabyCobolGrammar.ProcedurenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(BabyCobolGrammar.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#callfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfile(BabyCobolGrammar.CallfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#byreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByreference(BabyCobolGrammar.ByreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#bycontent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBycontent(BabyCobolGrammar.BycontentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#byvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByvalue(BabyCobolGrammar.ByvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(BabyCobolGrammar.FilenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#callprogram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallprogram(BabyCobolGrammar.CallprogramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#callprogramusing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallprogramusing(BabyCobolGrammar.CallprogramusingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#callprogramreturning}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallprogramreturning(BabyCobolGrammar.CallprogramreturningContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#functionname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionname(BabyCobolGrammar.FunctionnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#programname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramname(BabyCobolGrammar.ProgramnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#copy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopy(BabyCobolGrammar.CopyContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#display}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplay(BabyCobolGrammar.DisplayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#divide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(BabyCobolGrammar.DivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#evaluate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluate(BabyCobolGrammar.EvaluateContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#whenclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenclause(BabyCobolGrammar.WhenclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#goto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto(BabyCobolGrammar.GotoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#perform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPerform(BabyCobolGrammar.PerformContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(BabyCobolGrammar.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(BabyCobolGrammar.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#loopvarying}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopvarying(BabyCobolGrammar.LoopvaryingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#loopwhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopwhile(BabyCobolGrammar.LoopwhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#loopuntil}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopuntil(BabyCobolGrammar.LoopuntilContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(BabyCobolGrammar.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(BabyCobolGrammar.MultiplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#nextsentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNextsentence(BabyCobolGrammar.NextsentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#signal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal(BabyCobolGrammar.SignalContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#stop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStop(BabyCobolGrammar.StopContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#subtract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract(BabyCobolGrammar.SubtractContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic(BabyCobolGrammar.AtomicContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#stringliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringliteral(BabyCobolGrammar.StringliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#boolexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolexpr(BabyCobolGrammar.BoolexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(BabyCobolGrammar.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(BabyCobolGrammar.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(BabyCobolGrammar.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(BabyCobolGrammar.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(BabyCobolGrammar.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(BabyCobolGrammar.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#keywords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywords(BabyCobolGrammar.KeywordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(BabyCobolGrammar.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(BabyCobolGrammar.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link BabyCobolGrammar#int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(BabyCobolGrammar.IntContext ctx);
}