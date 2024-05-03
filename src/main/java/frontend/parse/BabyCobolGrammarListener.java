// Generated from D:/SE_PROJECT_GROUP_ByteTheBullet_Giulia/src/main/java/frontend/parse/BabyCobolGrammar.g4 by ANTLR 4.13.1
package frontend.parse;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BabyCobolGrammar}.
 */
public interface BabyCobolGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BabyCobolGrammar.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BabyCobolGrammar.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#identificationdivision}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationdivision(BabyCobolGrammar.IdentificationdivisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#identificationdivision}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationdivision(BabyCobolGrammar.IdentificationdivisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#identificationname}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationname(BabyCobolGrammar.IdentificationnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#identificationname}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationname(BabyCobolGrammar.IdentificationnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#identificationvalue}.
	 * @param ctx the parse tree
	 */
	void enterIdentificationvalue(BabyCobolGrammar.IdentificationvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#identificationvalue}.
	 * @param ctx the parse tree
	 */
	void exitIdentificationvalue(BabyCobolGrammar.IdentificationvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#datadivision}.
	 * @param ctx the parse tree
	 */
	void enterDatadivision(BabyCobolGrammar.DatadivisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#datadivision}.
	 * @param ctx the parse tree
	 */
	void exitDatadivision(BabyCobolGrammar.DatadivisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#dataDescriptionEntry}.
	 * @param ctx the parse tree
	 */
	void enterDataDescriptionEntry(BabyCobolGrammar.DataDescriptionEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#dataDescriptionEntry}.
	 * @param ctx the parse tree
	 */
	void exitDataDescriptionEntry(BabyCobolGrammar.DataDescriptionEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#level}.
	 * @param ctx the parse tree
	 */
	void enterLevel(BabyCobolGrammar.LevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#level}.
	 * @param ctx the parse tree
	 */
	void exitLevel(BabyCobolGrammar.LevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#proceduredivision}.
	 * @param ctx the parse tree
	 */
	void enterProceduredivision(BabyCobolGrammar.ProceduredivisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#proceduredivision}.
	 * @param ctx the parse tree
	 */
	void exitProceduredivision(BabyCobolGrammar.ProceduredivisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#using}.
	 * @param ctx the parse tree
	 */
	void enterUsing(BabyCobolGrammar.UsingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#using}.
	 * @param ctx the parse tree
	 */
	void exitUsing(BabyCobolGrammar.UsingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(BabyCobolGrammar.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(BabyCobolGrammar.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(BabyCobolGrammar.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(BabyCobolGrammar.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#paragraph}.
	 * @param ctx the parse tree
	 */
	void enterParagraph(BabyCobolGrammar.ParagraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#paragraph}.
	 * @param ctx the parse tree
	 */
	void exitParagraph(BabyCobolGrammar.ParagraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#accept}.
	 * @param ctx the parse tree
	 */
	void enterAccept(BabyCobolGrammar.AcceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#accept}.
	 * @param ctx the parse tree
	 */
	void exitAccept(BabyCobolGrammar.AcceptContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(BabyCobolGrammar.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(BabyCobolGrammar.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlter(BabyCobolGrammar.AlterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlter(BabyCobolGrammar.AlterContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#procedurename}.
	 * @param ctx the parse tree
	 */
	void enterProcedurename(BabyCobolGrammar.ProcedurenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#procedurename}.
	 * @param ctx the parse tree
	 */
	void exitProcedurename(BabyCobolGrammar.ProcedurenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(BabyCobolGrammar.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(BabyCobolGrammar.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#callfile}.
	 * @param ctx the parse tree
	 */
	void enterCallfile(BabyCobolGrammar.CallfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#callfile}.
	 * @param ctx the parse tree
	 */
	void exitCallfile(BabyCobolGrammar.CallfileContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#byreference}.
	 * @param ctx the parse tree
	 */
	void enterByreference(BabyCobolGrammar.ByreferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#byreference}.
	 * @param ctx the parse tree
	 */
	void exitByreference(BabyCobolGrammar.ByreferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#bycontent}.
	 * @param ctx the parse tree
	 */
	void enterBycontent(BabyCobolGrammar.BycontentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#bycontent}.
	 * @param ctx the parse tree
	 */
	void exitBycontent(BabyCobolGrammar.BycontentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#byvalue}.
	 * @param ctx the parse tree
	 */
	void enterByvalue(BabyCobolGrammar.ByvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#byvalue}.
	 * @param ctx the parse tree
	 */
	void exitByvalue(BabyCobolGrammar.ByvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#filename}.
	 * @param ctx the parse tree
	 */
	void enterFilename(BabyCobolGrammar.FilenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#filename}.
	 * @param ctx the parse tree
	 */
	void exitFilename(BabyCobolGrammar.FilenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#callprogram}.
	 * @param ctx the parse tree
	 */
	void enterCallprogram(BabyCobolGrammar.CallprogramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#callprogram}.
	 * @param ctx the parse tree
	 */
	void exitCallprogram(BabyCobolGrammar.CallprogramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#callprogramusing}.
	 * @param ctx the parse tree
	 */
	void enterCallprogramusing(BabyCobolGrammar.CallprogramusingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#callprogramusing}.
	 * @param ctx the parse tree
	 */
	void exitCallprogramusing(BabyCobolGrammar.CallprogramusingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#callprogramreturning}.
	 * @param ctx the parse tree
	 */
	void enterCallprogramreturning(BabyCobolGrammar.CallprogramreturningContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#callprogramreturning}.
	 * @param ctx the parse tree
	 */
	void exitCallprogramreturning(BabyCobolGrammar.CallprogramreturningContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#functionname}.
	 * @param ctx the parse tree
	 */
	void enterFunctionname(BabyCobolGrammar.FunctionnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#functionname}.
	 * @param ctx the parse tree
	 */
	void exitFunctionname(BabyCobolGrammar.FunctionnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#programname}.
	 * @param ctx the parse tree
	 */
	void enterProgramname(BabyCobolGrammar.ProgramnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#programname}.
	 * @param ctx the parse tree
	 */
	void exitProgramname(BabyCobolGrammar.ProgramnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#copy}.
	 * @param ctx the parse tree
	 */
	void enterCopy(BabyCobolGrammar.CopyContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#copy}.
	 * @param ctx the parse tree
	 */
	void exitCopy(BabyCobolGrammar.CopyContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#display}.
	 * @param ctx the parse tree
	 */
	void enterDisplay(BabyCobolGrammar.DisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#display}.
	 * @param ctx the parse tree
	 */
	void exitDisplay(BabyCobolGrammar.DisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#divide}.
	 * @param ctx the parse tree
	 */
	void enterDivide(BabyCobolGrammar.DivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#divide}.
	 * @param ctx the parse tree
	 */
	void exitDivide(BabyCobolGrammar.DivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#evaluate}.
	 * @param ctx the parse tree
	 */
	void enterEvaluate(BabyCobolGrammar.EvaluateContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#evaluate}.
	 * @param ctx the parse tree
	 */
	void exitEvaluate(BabyCobolGrammar.EvaluateContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#whenclause}.
	 * @param ctx the parse tree
	 */
	void enterWhenclause(BabyCobolGrammar.WhenclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#whenclause}.
	 * @param ctx the parse tree
	 */
	void exitWhenclause(BabyCobolGrammar.WhenclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#goto}.
	 * @param ctx the parse tree
	 */
	void enterGoto(BabyCobolGrammar.GotoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#goto}.
	 * @param ctx the parse tree
	 */
	void exitGoto(BabyCobolGrammar.GotoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#perform}.
	 * @param ctx the parse tree
	 */
	void enterPerform(BabyCobolGrammar.PerformContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#perform}.
	 * @param ctx the parse tree
	 */
	void exitPerform(BabyCobolGrammar.PerformContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(BabyCobolGrammar.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(BabyCobolGrammar.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(BabyCobolGrammar.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(BabyCobolGrammar.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#loopvarying}.
	 * @param ctx the parse tree
	 */
	void enterLoopvarying(BabyCobolGrammar.LoopvaryingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#loopvarying}.
	 * @param ctx the parse tree
	 */
	void exitLoopvarying(BabyCobolGrammar.LoopvaryingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#loopwhile}.
	 * @param ctx the parse tree
	 */
	void enterLoopwhile(BabyCobolGrammar.LoopwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#loopwhile}.
	 * @param ctx the parse tree
	 */
	void exitLoopwhile(BabyCobolGrammar.LoopwhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#loopuntil}.
	 * @param ctx the parse tree
	 */
	void enterLoopuntil(BabyCobolGrammar.LoopuntilContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#loopuntil}.
	 * @param ctx the parse tree
	 */
	void exitLoopuntil(BabyCobolGrammar.LoopuntilContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(BabyCobolGrammar.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(BabyCobolGrammar.MoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(BabyCobolGrammar.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(BabyCobolGrammar.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#nextsentence}.
	 * @param ctx the parse tree
	 */
	void enterNextsentence(BabyCobolGrammar.NextsentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#nextsentence}.
	 * @param ctx the parse tree
	 */
	void exitNextsentence(BabyCobolGrammar.NextsentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#signal}.
	 * @param ctx the parse tree
	 */
	void enterSignal(BabyCobolGrammar.SignalContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#signal}.
	 * @param ctx the parse tree
	 */
	void exitSignal(BabyCobolGrammar.SignalContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#stop}.
	 * @param ctx the parse tree
	 */
	void enterStop(BabyCobolGrammar.StopContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#stop}.
	 * @param ctx the parse tree
	 */
	void exitStop(BabyCobolGrammar.StopContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#subtract}.
	 * @param ctx the parse tree
	 */
	void enterSubtract(BabyCobolGrammar.SubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#subtract}.
	 * @param ctx the parse tree
	 */
	void exitSubtract(BabyCobolGrammar.SubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#atomic}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(BabyCobolGrammar.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#atomic}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(BabyCobolGrammar.AtomicContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#stringliteral}.
	 * @param ctx the parse tree
	 */
	void enterStringliteral(BabyCobolGrammar.StringliteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#stringliteral}.
	 * @param ctx the parse tree
	 */
	void exitStringliteral(BabyCobolGrammar.StringliteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#boolexpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolexpr(BabyCobolGrammar.BoolexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#boolexpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolexpr(BabyCobolGrammar.BoolexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(BabyCobolGrammar.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(BabyCobolGrammar.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(BabyCobolGrammar.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(BabyCobolGrammar.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(BabyCobolGrammar.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(BabyCobolGrammar.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(BabyCobolGrammar.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(BabyCobolGrammar.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(BabyCobolGrammar.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(BabyCobolGrammar.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(BabyCobolGrammar.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(BabyCobolGrammar.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#keywords}.
	 * @param ctx the parse tree
	 */
	void enterKeywords(BabyCobolGrammar.KeywordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#keywords}.
	 * @param ctx the parse tree
	 */
	void exitKeywords(BabyCobolGrammar.KeywordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(BabyCobolGrammar.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(BabyCobolGrammar.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(BabyCobolGrammar.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(BabyCobolGrammar.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link BabyCobolGrammar#int}.
	 * @param ctx the parse tree
	 */
	void enterInt(BabyCobolGrammar.IntContext ctx);
	/**
	 * Exit a parse tree produced by {@link BabyCobolGrammar#int}.
	 * @param ctx the parse tree
	 */
	void exitInt(BabyCobolGrammar.IntContext ctx);
}