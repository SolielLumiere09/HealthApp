// Generated from C:/Users/Omar Valdivia/Documents/GitHub/HealthApp/vision/final/app/src/main/java/com/google/firebase/codelab/textExtractor/analyzer\labelGrammar.g4 by ANTLR 4.9.1
package labelParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class labelGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMERO=1, VOWEL=2, TAMANODEPORCION=3, OZ=4, EMPAQUE=5, CALORIAS=6, GRASA=7, 
		TOTAL=8, SATURADAS=9, TRANS=10, CARBOHIDRATOS=11, AZUCARES=12, COLESTEROL=13, 
		SODIO=14, PROTEINAS=15, G=16, WS=17, ANY=18;
	public static final int
		RULE_init = 0, RULE_statements = 1, RULE_tamanoPorcion_statement = 2, 
		RULE_porcionesEmpaque_statement = 3, RULE_caloriasStatement = 4, RULE_grasaTotal_statement = 5, 
		RULE_grasaSaturada_statement = 6, RULE_grasaTrans_statement = 7, RULE_carbs_statement = 8, 
		RULE_azucar_statement = 9, RULE_colesterol_statement = 10, RULE_sodio_statement = 11, 
		RULE_proteina_statement = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"init", "statements", "tamanoPorcion_statement", "porcionesEmpaque_statement", 
			"caloriasStatement", "grasaTotal_statement", "grasaSaturada_statement", 
			"grasaTrans_statement", "carbs_statement", "azucar_statement", "colesterol_statement", 
			"sodio_statement", "proteina_statement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NUMERO", "VOWEL", "TAMANODEPORCION", "OZ", "EMPAQUE", "CALORIAS", 
			"GRASA", "TOTAL", "SATURADAS", "TRANS", "CARBOHIDRATOS", "AZUCARES", 
			"COLESTEROL", "SODIO", "PROTEINAS", "G", "WS", "ANY"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "labelGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public labelGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InitContext extends ParserRuleContext {
		public List<StatementsContext> statements() {
			return getRuleContexts(StatementsContext.class);
		}
		public StatementsContext statements(int i) {
			return getRuleContext(StatementsContext.class,i);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TAMANODEPORCION) | (1L << EMPAQUE) | (1L << CALORIAS) | (1L << GRASA) | (1L << CARBOHIDRATOS) | (1L << AZUCARES) | (1L << COLESTEROL) | (1L << SODIO) | (1L << PROTEINAS))) != 0)) {
				{
				{
				setState(26);
				statements();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public TamanoPorcion_statementContext tamanoPorcion_statement() {
			return getRuleContext(TamanoPorcion_statementContext.class,0);
		}
		public PorcionesEmpaque_statementContext porcionesEmpaque_statement() {
			return getRuleContext(PorcionesEmpaque_statementContext.class,0);
		}
		public CaloriasStatementContext caloriasStatement() {
			return getRuleContext(CaloriasStatementContext.class,0);
		}
		public GrasaTotal_statementContext grasaTotal_statement() {
			return getRuleContext(GrasaTotal_statementContext.class,0);
		}
		public GrasaSaturada_statementContext grasaSaturada_statement() {
			return getRuleContext(GrasaSaturada_statementContext.class,0);
		}
		public GrasaTrans_statementContext grasaTrans_statement() {
			return getRuleContext(GrasaTrans_statementContext.class,0);
		}
		public Carbs_statementContext carbs_statement() {
			return getRuleContext(Carbs_statementContext.class,0);
		}
		public Azucar_statementContext azucar_statement() {
			return getRuleContext(Azucar_statementContext.class,0);
		}
		public Colesterol_statementContext colesterol_statement() {
			return getRuleContext(Colesterol_statementContext.class,0);
		}
		public Sodio_statementContext sodio_statement() {
			return getRuleContext(Sodio_statementContext.class,0);
		}
		public Proteina_statementContext proteina_statement() {
			return getRuleContext(Proteina_statementContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements);
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				tamanoPorcion_statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				porcionesEmpaque_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
				caloriasStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(35);
				grasaTotal_statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(36);
				grasaSaturada_statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(37);
				grasaTrans_statement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(38);
				carbs_statement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(39);
				azucar_statement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(40);
				colesterol_statement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(41);
				sodio_statement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(42);
				proteina_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TamanoPorcion_statementContext extends ParserRuleContext {
		public TerminalNode TAMANODEPORCION() { return getToken(labelGrammarParser.TAMANODEPORCION, 0); }
		public List<TerminalNode> NUMERO() { return getTokens(labelGrammarParser.NUMERO); }
		public TerminalNode NUMERO(int i) {
			return getToken(labelGrammarParser.NUMERO, i);
		}
		public TerminalNode OZ() { return getToken(labelGrammarParser.OZ, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public TamanoPorcion_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tamanoPorcion_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterTamanoPorcion_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitTamanoPorcion_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitTamanoPorcion_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TamanoPorcion_statementContext tamanoPorcion_statement() throws RecognitionException {
		TamanoPorcion_statementContext _localctx = new TamanoPorcion_statementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tamanoPorcion_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(TAMANODEPORCION);
			setState(47);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(46);
				match(NUMERO);
				}
				break;
			}
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OZ) {
				{
				setState(49);
				match(OZ);
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMERO) {
				{
				setState(52);
				match(NUMERO);
				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(55);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PorcionesEmpaque_statementContext extends ParserRuleContext {
		public TerminalNode EMPAQUE() { return getToken(labelGrammarParser.EMPAQUE, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public PorcionesEmpaque_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_porcionesEmpaque_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterPorcionesEmpaque_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitPorcionesEmpaque_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitPorcionesEmpaque_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PorcionesEmpaque_statementContext porcionesEmpaque_statement() throws RecognitionException {
		PorcionesEmpaque_statementContext _localctx = new PorcionesEmpaque_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_porcionesEmpaque_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(EMPAQUE);
			setState(59);
			match(NUMERO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaloriasStatementContext extends ParserRuleContext {
		public TerminalNode CALORIAS() { return getToken(labelGrammarParser.CALORIAS, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public CaloriasStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caloriasStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterCaloriasStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitCaloriasStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitCaloriasStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaloriasStatementContext caloriasStatement() throws RecognitionException {
		CaloriasStatementContext _localctx = new CaloriasStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_caloriasStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(CALORIAS);
			setState(62);
			match(NUMERO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrasaTotal_statementContext extends ParserRuleContext {
		public TerminalNode GRASA() { return getToken(labelGrammarParser.GRASA, 0); }
		public TerminalNode TOTAL() { return getToken(labelGrammarParser.TOTAL, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public GrasaTotal_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grasaTotal_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterGrasaTotal_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitGrasaTotal_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitGrasaTotal_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrasaTotal_statementContext grasaTotal_statement() throws RecognitionException {
		GrasaTotal_statementContext _localctx = new GrasaTotal_statementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_grasaTotal_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(GRASA);
			setState(65);
			match(TOTAL);
			setState(66);
			match(NUMERO);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(67);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrasaSaturada_statementContext extends ParserRuleContext {
		public TerminalNode GRASA() { return getToken(labelGrammarParser.GRASA, 0); }
		public TerminalNode SATURADAS() { return getToken(labelGrammarParser.SATURADAS, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public GrasaSaturada_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grasaSaturada_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterGrasaSaturada_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitGrasaSaturada_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitGrasaSaturada_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrasaSaturada_statementContext grasaSaturada_statement() throws RecognitionException {
		GrasaSaturada_statementContext _localctx = new GrasaSaturada_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grasaSaturada_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(GRASA);
			setState(71);
			match(SATURADAS);
			setState(72);
			match(NUMERO);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(73);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrasaTrans_statementContext extends ParserRuleContext {
		public TerminalNode GRASA() { return getToken(labelGrammarParser.GRASA, 0); }
		public TerminalNode TRANS() { return getToken(labelGrammarParser.TRANS, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public GrasaTrans_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grasaTrans_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterGrasaTrans_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitGrasaTrans_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitGrasaTrans_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrasaTrans_statementContext grasaTrans_statement() throws RecognitionException {
		GrasaTrans_statementContext _localctx = new GrasaTrans_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_grasaTrans_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(GRASA);
			setState(77);
			match(TRANS);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMERO) {
				{
				setState(78);
				match(NUMERO);
				}
			}

			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(81);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Carbs_statementContext extends ParserRuleContext {
		public TerminalNode CARBOHIDRATOS() { return getToken(labelGrammarParser.CARBOHIDRATOS, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode TOTAL() { return getToken(labelGrammarParser.TOTAL, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public Carbs_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_carbs_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterCarbs_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitCarbs_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitCarbs_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Carbs_statementContext carbs_statement() throws RecognitionException {
		Carbs_statementContext _localctx = new Carbs_statementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_carbs_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(CARBOHIDRATOS);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TOTAL) {
				{
				setState(85);
				match(TOTAL);
				}
			}

			setState(88);
			match(NUMERO);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(89);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Azucar_statementContext extends ParserRuleContext {
		public TerminalNode AZUCARES() { return getToken(labelGrammarParser.AZUCARES, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public Azucar_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_azucar_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterAzucar_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitAzucar_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitAzucar_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Azucar_statementContext azucar_statement() throws RecognitionException {
		Azucar_statementContext _localctx = new Azucar_statementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_azucar_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(AZUCARES);
			setState(93);
			match(NUMERO);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(94);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Colesterol_statementContext extends ParserRuleContext {
		public TerminalNode COLESTEROL() { return getToken(labelGrammarParser.COLESTEROL, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public Colesterol_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colesterol_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterColesterol_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitColesterol_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitColesterol_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Colesterol_statementContext colesterol_statement() throws RecognitionException {
		Colesterol_statementContext _localctx = new Colesterol_statementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_colesterol_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(COLESTEROL);
			setState(98);
			match(NUMERO);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(99);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sodio_statementContext extends ParserRuleContext {
		public TerminalNode SODIO() { return getToken(labelGrammarParser.SODIO, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public Sodio_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sodio_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterSodio_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitSodio_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitSodio_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sodio_statementContext sodio_statement() throws RecognitionException {
		Sodio_statementContext _localctx = new Sodio_statementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sodio_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(SODIO);
			setState(103);
			match(NUMERO);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(104);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Proteina_statementContext extends ParserRuleContext {
		public TerminalNode PROTEINAS() { return getToken(labelGrammarParser.PROTEINAS, 0); }
		public TerminalNode NUMERO() { return getToken(labelGrammarParser.NUMERO, 0); }
		public TerminalNode G() { return getToken(labelGrammarParser.G, 0); }
		public Proteina_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proteina_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).enterProteina_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof labelGrammarListener ) ((labelGrammarListener)listener).exitProteina_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof labelGrammarVisitor ) return ((labelGrammarVisitor<? extends T>)visitor).visitProteina_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Proteina_statementContext proteina_statement() throws RecognitionException {
		Proteina_statementContext _localctx = new Proteina_statementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_proteina_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(PROTEINAS);
			setState(108);
			match(NUMERO);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==G) {
				{
				setState(109);
				match(G);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24s\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\4\3\4\5\4\62\n\4\3\4\5\4\65\n"+
		"\4\3\4\5\48\n\4\3\4\5\4;\n\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5"+
		"\7G\n\7\3\b\3\b\3\b\3\b\5\bM\n\b\3\t\3\t\3\t\5\tR\n\t\3\t\5\tU\n\t\3\n"+
		"\3\n\5\nY\n\n\3\n\3\n\5\n]\n\n\3\13\3\13\3\13\5\13b\n\13\3\f\3\f\3\f\5"+
		"\fg\n\f\3\r\3\r\3\r\5\rl\n\r\3\16\3\16\3\16\5\16q\n\16\3\16\2\2\17\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\2\2\2~\2\37\3\2\2\2\4-\3\2\2\2\6/\3\2\2"+
		"\2\b<\3\2\2\2\n?\3\2\2\2\fB\3\2\2\2\16H\3\2\2\2\20N\3\2\2\2\22V\3\2\2"+
		"\2\24^\3\2\2\2\26c\3\2\2\2\30h\3\2\2\2\32m\3\2\2\2\34\36\5\4\3\2\35\34"+
		"\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2!\37\3\2\2\2"+
		"\".\5\6\4\2#.\5\b\5\2$.\5\n\6\2%.\5\f\7\2&.\5\16\b\2\'.\5\20\t\2(.\5\22"+
		"\n\2).\5\24\13\2*.\5\26\f\2+.\5\30\r\2,.\5\32\16\2-\"\3\2\2\2-#\3\2\2"+
		"\2-$\3\2\2\2-%\3\2\2\2-&\3\2\2\2-\'\3\2\2\2-(\3\2\2\2-)\3\2\2\2-*\3\2"+
		"\2\2-+\3\2\2\2-,\3\2\2\2.\5\3\2\2\2/\61\7\5\2\2\60\62\7\3\2\2\61\60\3"+
		"\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\65\7\6\2\2\64\63\3\2\2\2\64\65\3"+
		"\2\2\2\65\67\3\2\2\2\668\7\3\2\2\67\66\3\2\2\2\678\3\2\2\28:\3\2\2\29"+
		";\7\22\2\2:9\3\2\2\2:;\3\2\2\2;\7\3\2\2\2<=\7\7\2\2=>\7\3\2\2>\t\3\2\2"+
		"\2?@\7\b\2\2@A\7\3\2\2A\13\3\2\2\2BC\7\t\2\2CD\7\n\2\2DF\7\3\2\2EG\7\22"+
		"\2\2FE\3\2\2\2FG\3\2\2\2G\r\3\2\2\2HI\7\t\2\2IJ\7\13\2\2JL\7\3\2\2KM\7"+
		"\22\2\2LK\3\2\2\2LM\3\2\2\2M\17\3\2\2\2NO\7\t\2\2OQ\7\f\2\2PR\7\3\2\2"+
		"QP\3\2\2\2QR\3\2\2\2RT\3\2\2\2SU\7\22\2\2TS\3\2\2\2TU\3\2\2\2U\21\3\2"+
		"\2\2VX\7\r\2\2WY\7\n\2\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z\\\7\3\2\2[]\7"+
		"\22\2\2\\[\3\2\2\2\\]\3\2\2\2]\23\3\2\2\2^_\7\16\2\2_a\7\3\2\2`b\7\22"+
		"\2\2a`\3\2\2\2ab\3\2\2\2b\25\3\2\2\2cd\7\17\2\2df\7\3\2\2eg\7\22\2\2f"+
		"e\3\2\2\2fg\3\2\2\2g\27\3\2\2\2hi\7\20\2\2ik\7\3\2\2jl\7\22\2\2kj\3\2"+
		"\2\2kl\3\2\2\2l\31\3\2\2\2mn\7\21\2\2np\7\3\2\2oq\7\22\2\2po\3\2\2\2p"+
		"q\3\2\2\2q\33\3\2\2\2\22\37-\61\64\67:FLQTX\\afkp";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}