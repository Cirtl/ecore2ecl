// Generated from java-escape by ANTLR 4.11.1
package process.parser.SSECL;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SSECLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		BLOCK=18, TO=19, FROM=20, ID=21, INT=22, SEQ=23, WS=24, LINE_COMMENT=25, 
		MULT_COMMENT=26;
	public static final int
		RULE_description = 0, RULE_configuration = 1, RULE_entityState = 2, RULE_entityDef = 3, 
		RULE_attributeDef = 4, RULE_connectionDef = 5, RULE_connection = 6, RULE_functionDef = 7, 
		RULE_visualizationDef = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"description", "configuration", "entityState", "entityDef", "attributeDef", 
			"connectionDef", "connection", "functionDef", "visualizationDef"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'config'", "'{'", "'starter:'", "'mono:'", "'color:'", "'}'", 
			"'entities'", "'('", "')'", "'extends'", "'acts'", "'attributes'", "':'", 
			"'connections'", "'..'", "'functions'", "'visualization'", null, "'to'", 
			"'from'", null, null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "BLOCK", "TO", "FROM", "ID", "INT", 
			"SEQ", "WS", "LINE_COMMENT", "MULT_COMMENT"
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
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SSECLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DescriptionContext extends ParserRuleContext {
		public ConfigurationContext config;
		public EntityStateContext entity;
		public EntityDefContext entityDef;
		public List<EntityDefContext> def = new ArrayList<EntityDefContext>();
		public ConfigurationContext configuration() {
			return getRuleContext(ConfigurationContext.class,0);
		}
		public EntityStateContext entityState() {
			return getRuleContext(EntityStateContext.class,0);
		}
		public List<EntityDefContext> entityDef() {
			return getRuleContexts(EntityDefContext.class);
		}
		public EntityDefContext entityDef(int i) {
			return getRuleContext(EntityDefContext.class,i);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_description);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			((DescriptionContext)_localctx).config = configuration();
			setState(19);
			((DescriptionContext)_localctx).entity = entityState();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(20);
				((DescriptionContext)_localctx).entityDef = entityDef();
				((DescriptionContext)_localctx).def.add(((DescriptionContext)_localctx).entityDef);
				}
				}
				setState(25);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConfigurationContext extends ParserRuleContext {
		public Token starter;
		public Token mono;
		public Token color;
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public List<TerminalNode> ID() { return getTokens(SSECLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SSECLParser.ID, i);
		}
		public ConfigurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configuration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterConfiguration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitConfiguration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitConfiguration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigurationContext configuration() throws RecognitionException {
		ConfigurationContext _localctx = new ConfigurationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_configuration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(27);
			match(T__1);
			setState(28);
			match(T__2);
			setState(29);
			((ConfigurationContext)_localctx).starter = match(ID);
			setState(30);
			match(SEQ);
			setState(31);
			match(T__3);
			setState(32);
			((ConfigurationContext)_localctx).mono = match(ID);
			setState(33);
			match(SEQ);
			setState(34);
			match(T__4);
			setState(35);
			((ConfigurationContext)_localctx).color = match(ID);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEQ) {
				{
				setState(36);
				match(SEQ);
				}
			}

			setState(39);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EntityStateContext extends ParserRuleContext {
		public Token ID;
		public List<Token> entities = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SSECLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SSECLParser.ID, i);
		}
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public EntityStateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterEntityState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitEntityState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitEntityState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityStateContext entityState() throws RecognitionException {
		EntityStateContext _localctx = new EntityStateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_entityState);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__6);
			setState(42);
			match(T__7);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(43);
				((EntityStateContext)_localctx).ID = match(ID);
				((EntityStateContext)_localctx).entities.add(((EntityStateContext)_localctx).ID);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEQ) {
					{
					{
					setState(44);
					match(SEQ);
					setState(45);
					((EntityStateContext)_localctx).ID = match(ID);
					((EntityStateContext)_localctx).entities.add(((EntityStateContext)_localctx).ID);
					}
					}
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(53);
			match(T__8);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EntityDefContext extends ParserRuleContext {
		public Token name;
		public Token extend;
		public Token ID;
		public List<Token> acts = new ArrayList<Token>();
		public AttributeDefContext attr;
		public ConnectionDefContext connectionDef;
		public List<ConnectionDefContext> connections = new ArrayList<ConnectionDefContext>();
		public FunctionDefContext function;
		public VisualizationDefContext vis;
		public List<TerminalNode> ID() { return getTokens(SSECLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SSECLParser.ID, i);
		}
		public AttributeDefContext attributeDef() {
			return getRuleContext(AttributeDefContext.class,0);
		}
		public FunctionDefContext functionDef() {
			return getRuleContext(FunctionDefContext.class,0);
		}
		public VisualizationDefContext visualizationDef() {
			return getRuleContext(VisualizationDefContext.class,0);
		}
		public List<ConnectionDefContext> connectionDef() {
			return getRuleContexts(ConnectionDefContext.class);
		}
		public ConnectionDefContext connectionDef(int i) {
			return getRuleContext(ConnectionDefContext.class,i);
		}
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public EntityDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterEntityDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitEntityDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitEntityDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityDefContext entityDef() throws RecognitionException {
		EntityDefContext _localctx = new EntityDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_entityDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			((EntityDefContext)_localctx).name = match(ID);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(56);
				match(T__9);
				setState(57);
				((EntityDefContext)_localctx).extend = match(ID);
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(60);
				match(T__10);
				setState(61);
				((EntityDefContext)_localctx).ID = match(ID);
				((EntityDefContext)_localctx).acts.add(((EntityDefContext)_localctx).ID);
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEQ) {
					{
					{
					setState(62);
					match(SEQ);
					setState(63);
					((EntityDefContext)_localctx).ID = match(ID);
					((EntityDefContext)_localctx).acts.add(((EntityDefContext)_localctx).ID);
					}
					}
					setState(68);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(71);
			match(T__1);
			setState(72);
			((EntityDefContext)_localctx).attr = attributeDef();
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(73);
				((EntityDefContext)_localctx).connectionDef = connectionDef();
				((EntityDefContext)_localctx).connections.add(((EntityDefContext)_localctx).connectionDef);
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__13 );
			setState(78);
			((EntityDefContext)_localctx).function = functionDef();
			setState(79);
			((EntityDefContext)_localctx).vis = visualizationDef();
			setState(80);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeDefContext extends ParserRuleContext {
		public Token ID;
		public List<Token> names = new ArrayList<Token>();
		public List<Token> types = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SSECLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SSECLParser.ID, i);
		}
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public AttributeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterAttributeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitAttributeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitAttributeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeDefContext attributeDef() throws RecognitionException {
		AttributeDefContext _localctx = new AttributeDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attributeDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__11);
			setState(83);
			match(T__1);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(84);
				((AttributeDefContext)_localctx).ID = match(ID);
				((AttributeDefContext)_localctx).names.add(((AttributeDefContext)_localctx).ID);
				setState(85);
				match(T__12);
				setState(86);
				((AttributeDefContext)_localctx).ID = match(ID);
				((AttributeDefContext)_localctx).types.add(((AttributeDefContext)_localctx).ID);
				setState(93);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(87);
						match(SEQ);
						setState(88);
						((AttributeDefContext)_localctx).ID = match(ID);
						((AttributeDefContext)_localctx).names.add(((AttributeDefContext)_localctx).ID);
						setState(89);
						match(T__12);
						setState(90);
						((AttributeDefContext)_localctx).ID = match(ID);
						((AttributeDefContext)_localctx).types.add(((AttributeDefContext)_localctx).ID);
						}
						} 
					}
					setState(95);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEQ) {
					{
					setState(96);
					match(SEQ);
					}
				}

				}
			}

			setState(101);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConnectionDefContext extends ParserRuleContext {
		public Token name;
		public ConnectionContext connection;
		public List<ConnectionContext> connections = new ArrayList<ConnectionContext>();
		public TerminalNode ID() { return getToken(SSECLParser.ID, 0); }
		public List<ConnectionContext> connection() {
			return getRuleContexts(ConnectionContext.class);
		}
		public ConnectionContext connection(int i) {
			return getRuleContext(ConnectionContext.class,i);
		}
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public ConnectionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connectionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterConnectionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitConnectionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitConnectionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectionDefContext connectionDef() throws RecognitionException {
		ConnectionDefContext _localctx = new ConnectionDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_connectionDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__13);
			setState(104);
			((ConnectionDefContext)_localctx).name = match(ID);
			setState(105);
			match(T__1);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TO || _la==FROM) {
				{
				setState(106);
				((ConnectionDefContext)_localctx).connection = connection();
				((ConnectionDefContext)_localctx).connections.add(((ConnectionDefContext)_localctx).connection);
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(107);
						match(SEQ);
						setState(108);
						((ConnectionDefContext)_localctx).connection = connection();
						((ConnectionDefContext)_localctx).connections.add(((ConnectionDefContext)_localctx).connection);
						}
						} 
					}
					setState(113);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEQ) {
					{
					setState(114);
					match(SEQ);
					}
				}

				}
			}

			setState(119);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConnectionContext extends ParserRuleContext {
		public Token type;
		public Token lower;
		public Token range;
		public Token upper;
		public Token ID;
		public List<Token> targets = new ArrayList<Token>();
		public List<TerminalNode> INT() { return getTokens(SSECLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SSECLParser.INT, i);
		}
		public List<TerminalNode> ID() { return getTokens(SSECLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SSECLParser.ID, i);
		}
		public TerminalNode TO() { return getToken(SSECLParser.TO, 0); }
		public TerminalNode FROM() { return getToken(SSECLParser.FROM, 0); }
		public List<TerminalNode> SEQ() { return getTokens(SSECLParser.SEQ); }
		public TerminalNode SEQ(int i) {
			return getToken(SSECLParser.SEQ, i);
		}
		public ConnectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterConnection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitConnection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitConnection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectionContext connection() throws RecognitionException {
		ConnectionContext _localctx = new ConnectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_connection);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			((ConnectionContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==TO || _la==FROM) ) {
				((ConnectionContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(122);
			((ConnectionContext)_localctx).lower = match(INT);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(123);
				((ConnectionContext)_localctx).range = match(T__14);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(124);
					((ConnectionContext)_localctx).upper = match(INT);
					}
				}

				}
			}

			setState(129);
			match(T__7);
			setState(130);
			((ConnectionContext)_localctx).ID = match(ID);
			((ConnectionContext)_localctx).targets.add(((ConnectionContext)_localctx).ID);
			setState(135);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(131);
					match(SEQ);
					setState(132);
					((ConnectionContext)_localctx).ID = match(ID);
					((ConnectionContext)_localctx).targets.add(((ConnectionContext)_localctx).ID);
					}
					} 
				}
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(138);
			match(T__8);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDefContext extends ParserRuleContext {
		public Token content;
		public TerminalNode BLOCK() { return getToken(SSECLParser.BLOCK, 0); }
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitFunctionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(T__15);
			setState(141);
			((FunctionDefContext)_localctx).content = match(BLOCK);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VisualizationDefContext extends ParserRuleContext {
		public Token content;
		public TerminalNode BLOCK() { return getToken(SSECLParser.BLOCK, 0); }
		public VisualizationDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visualizationDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).enterVisualizationDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SSECLListener ) ((SSECLListener)listener).exitVisualizationDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SSECLVisitor ) return ((SSECLVisitor<? extends T>)visitor).visitVisualizationDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VisualizationDefContext visualizationDef() throws RecognitionException {
		VisualizationDefContext _localctx = new VisualizationDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_visualizationDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__16);
			setState(144);
			((VisualizationDefContext)_localctx).content = match(BLOCK);
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
		"\u0004\u0001\u001a\u0093\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u0016"+
		"\b\u0000\n\u0000\f\u0000\u0019\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001&\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"/\b\u0002\n\u0002\f\u00022\t\u0002\u0003\u00024\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003;\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003A\b\u0003\n\u0003"+
		"\f\u0003D\t\u0003\u0003\u0003F\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0004\u0003K\b\u0003\u000b\u0003\f\u0003L\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\\\b"+
		"\u0004\n\u0004\f\u0004_\t\u0004\u0001\u0004\u0003\u0004b\b\u0004\u0003"+
		"\u0004d\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005n\b\u0005\n\u0005"+
		"\f\u0005q\t\u0005\u0001\u0005\u0003\u0005t\b\u0005\u0003\u0005v\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006~\b\u0006\u0003\u0006\u0080\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006\u0086\b\u0006\n\u0006\f\u0006\u0089"+
		"\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\u0087\u0000\t\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0000\u0001\u0001\u0000\u0013\u0014\u009a\u0000\u0012"+
		"\u0001\u0000\u0000\u0000\u0002\u001a\u0001\u0000\u0000\u0000\u0004)\u0001"+
		"\u0000\u0000\u0000\u00067\u0001\u0000\u0000\u0000\bR\u0001\u0000\u0000"+
		"\u0000\ng\u0001\u0000\u0000\u0000\fy\u0001\u0000\u0000\u0000\u000e\u008c"+
		"\u0001\u0000\u0000\u0000\u0010\u008f\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0003\u0002\u0001\u0000\u0013\u0017\u0003\u0004\u0002\u0000\u0014\u0016"+
		"\u0003\u0006\u0003\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0016\u0019"+
		"\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0001\u0000\u0000\u0000\u0018\u0001\u0001\u0000\u0000\u0000\u0019\u0017"+
		"\u0001\u0000\u0000\u0000\u001a\u001b\u0005\u0001\u0000\u0000\u001b\u001c"+
		"\u0005\u0002\u0000\u0000\u001c\u001d\u0005\u0003\u0000\u0000\u001d\u001e"+
		"\u0005\u0015\u0000\u0000\u001e\u001f\u0005\u0017\u0000\u0000\u001f \u0005"+
		"\u0004\u0000\u0000 !\u0005\u0015\u0000\u0000!\"\u0005\u0017\u0000\u0000"+
		"\"#\u0005\u0005\u0000\u0000#%\u0005\u0015\u0000\u0000$&\u0005\u0017\u0000"+
		"\u0000%$\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'(\u0005\u0006\u0000\u0000(\u0003\u0001\u0000\u0000\u0000"+
		")*\u0005\u0007\u0000\u0000*3\u0005\b\u0000\u0000+0\u0005\u0015\u0000\u0000"+
		",-\u0005\u0017\u0000\u0000-/\u0005\u0015\u0000\u0000.,\u0001\u0000\u0000"+
		"\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000"+
		"\u0000\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u00003+\u0001"+
		"\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u0000"+
		"56\u0005\t\u0000\u00006\u0005\u0001\u0000\u0000\u00007:\u0005\u0015\u0000"+
		"\u000089\u0005\n\u0000\u00009;\u0005\u0015\u0000\u0000:8\u0001\u0000\u0000"+
		"\u0000:;\u0001\u0000\u0000\u0000;E\u0001\u0000\u0000\u0000<=\u0005\u000b"+
		"\u0000\u0000=B\u0005\u0015\u0000\u0000>?\u0005\u0017\u0000\u0000?A\u0005"+
		"\u0015\u0000\u0000@>\u0001\u0000\u0000\u0000AD\u0001\u0000\u0000\u0000"+
		"B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000"+
		"\u0000DB\u0001\u0000\u0000\u0000E<\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0005\u0002\u0000\u0000HJ\u0003"+
		"\b\u0004\u0000IK\u0003\n\u0005\u0000JI\u0001\u0000\u0000\u0000KL\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000"+
		"MN\u0001\u0000\u0000\u0000NO\u0003\u000e\u0007\u0000OP\u0003\u0010\b\u0000"+
		"PQ\u0005\u0006\u0000\u0000Q\u0007\u0001\u0000\u0000\u0000RS\u0005\f\u0000"+
		"\u0000Sc\u0005\u0002\u0000\u0000TU\u0005\u0015\u0000\u0000UV\u0005\r\u0000"+
		"\u0000V]\u0005\u0015\u0000\u0000WX\u0005\u0017\u0000\u0000XY\u0005\u0015"+
		"\u0000\u0000YZ\u0005\r\u0000\u0000Z\\\u0005\u0015\u0000\u0000[W\u0001"+
		"\u0000\u0000\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000"+
		"]^\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000"+
		"\u0000`b\u0005\u0017\u0000\u0000a`\u0001\u0000\u0000\u0000ab\u0001\u0000"+
		"\u0000\u0000bd\u0001\u0000\u0000\u0000cT\u0001\u0000\u0000\u0000cd\u0001"+
		"\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0005\u0006\u0000\u0000"+
		"f\t\u0001\u0000\u0000\u0000gh\u0005\u000e\u0000\u0000hi\u0005\u0015\u0000"+
		"\u0000iu\u0005\u0002\u0000\u0000jo\u0003\f\u0006\u0000kl\u0005\u0017\u0000"+
		"\u0000ln\u0003\f\u0006\u0000mk\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000ps\u0001\u0000"+
		"\u0000\u0000qo\u0001\u0000\u0000\u0000rt\u0005\u0017\u0000\u0000sr\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tv\u0001\u0000\u0000\u0000"+
		"uj\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000"+
		"\u0000wx\u0005\u0006\u0000\u0000x\u000b\u0001\u0000\u0000\u0000yz\u0007"+
		"\u0000\u0000\u0000z\u007f\u0005\u0016\u0000\u0000{}\u0005\u000f\u0000"+
		"\u0000|~\u0005\u0016\u0000\u0000}|\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~\u0080\u0001\u0000\u0000\u0000\u007f{\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0005\b\u0000\u0000\u0082\u0087\u0005\u0015\u0000\u0000\u0083"+
		"\u0084\u0005\u0017\u0000\u0000\u0084\u0086\u0005\u0015\u0000\u0000\u0085"+
		"\u0083\u0001\u0000\u0000\u0000\u0086\u0089\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0088"+
		"\u008a\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0005\t\u0000\u0000\u008b\r\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0005\u0010\u0000\u0000\u008d\u008e\u0005\u0012\u0000\u0000\u008e\u000f"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0011\u0000\u0000\u0090\u0091"+
		"\u0005\u0012\u0000\u0000\u0091\u0011\u0001\u0000\u0000\u0000\u0011\u0017"+
		"%03:BEL]acosu}\u007f\u0087";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}