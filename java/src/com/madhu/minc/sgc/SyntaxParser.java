// $ANTLR 3.0.1 sgc/Syntax.g 2008-05-01 23:08:36
 package com.madhu.minc.sgc; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class SyntaxParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "AND_ASSIGN", "ASSIGN", "AT", "BIT_SHIFT_RIGHT", "BIT_SHIFT_RIGHT_ASSIGN", "COLON", "COMMA", "DEC", "DIV", "DIV_ASSIGN", "DOT", "DOTSTAR", "ELLIPSIS", "EQUAL", "GREATER_OR_EQUAL", "GREATER_THAN", "INC", "LBRACK", "LCURLY", "LESS_OR_EQUAL", "LESS_THAN", "LOGICAL_AND", "LOGICAL_NOT", "LOGICAL_OR", "LPAREN", "MINUS", "MINUS_ASSIGN", "MOD", "MOD_ASSIGN", "NOT", "NOT_EQUAL", "OR", "OR_ASSIGN", "PLUS", "PLUS_ASSIGN", "QUESTION", "RBRACK", "RCURLY", "RPAREN", "SEMI", "SHIFT_LEFT", "SHIFT_LEFT_ASSIGN", "SHIFT_RIGHT", "SHIFT_RIGHT_ASSIGN", "STAR", "STAR_ASSIGN", "XOR", "XOR_ASSIGN", "INT", "IF", "ELSE", "WHILE", "DEF", "REF", "DECLARATION", "BLOCK", "JUMP_IF", "IDENT", "INTEGER_LITERAL", "NEWLINE", "WS"
    };
    public static final int SHIFT_LEFT=45;
    public static final int COMMA=11;
    public static final int GREATER_THAN=20;
    public static final int MINUS=30;
    public static final int DEF=57;
    public static final int INC=21;
    public static final int LOGICAL_OR=28;
    public static final int XOR_ASSIGN=52;
    public static final int OR_ASSIGN=37;
    public static final int LESS_THAN=25;
    public static final int REF=58;
    public static final int ELLIPSIS=17;
    public static final int MOD=32;
    public static final int PLUS_ASSIGN=39;
    public static final int AND_ASSIGN=5;
    public static final int QUESTION=40;
    public static final int OR=36;
    public static final int NEWLINE=64;
    public static final int DOT=15;
    public static final int BLOCK=60;
    public static final int RCURLY=42;
    public static final int DECLARATION=59;
    public static final int AND=4;
    public static final int LCURLY=23;
    public static final int INT=53;
    public static final int ASSIGN=6;
    public static final int LESS_OR_EQUAL=24;
    public static final int RPAREN=43;
    public static final int STAR_ASSIGN=50;
    public static final int LPAREN=29;
    public static final int PLUS=38;
    public static final int INTEGER_LITERAL=63;
    public static final int SHIFT_RIGHT_ASSIGN=48;
    public static final int MINUS_ASSIGN=31;
    public static final int AT=7;
    public static final int GREATER_OR_EQUAL=19;
    public static final int NOT_EQUAL=35;
    public static final int SHIFT_LEFT_ASSIGN=46;
    public static final int IDENT=62;
    public static final int WHILE=56;
    public static final int MOD_ASSIGN=33;
    public static final int WS=65;
    public static final int DOTSTAR=16;
    public static final int JUMP_IF=61;
    public static final int LBRACK=22;
    public static final int SEMI=44;
    public static final int BIT_SHIFT_RIGHT=8;
    public static final int DIV_ASSIGN=14;
    public static final int BIT_SHIFT_RIGHT_ASSIGN=9;
    public static final int ELSE=55;
    public static final int EQUAL=18;
    public static final int DEC=12;
    public static final int LOGICAL_AND=26;
    public static final int IF=54;
    public static final int SHIFT_RIGHT=47;
    public static final int EOF=-1;
    public static final int XOR=51;
    public static final int RBRACK=41;
    public static final int COLON=10;
    public static final int DIV=13;
    public static final int LOGICAL_NOT=27;
    public static final int STAR=49;
    public static final int NOT=34;

        public SyntaxParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "sgc/Syntax.g"; }


    public static class program_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start program
    // sgc/Syntax.g:87:1: program : block ;
    public final program_return program() throws RecognitionException {
        program_return retval = new program_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        block_return block1 = null;



        try {
            // sgc/Syntax.g:87:8: ( block )
            // sgc/Syntax.g:88:2: block
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_block_in_program862);
            block1=block();
            _fsp--;

            adaptor.addChild(root_0, block1.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end program

    public static class declaration_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start declaration
    // sgc/Syntax.g:91:1: declaration : type IDENT SEMI -> ^( DECLARATION type IDENT ) ;
    public final declaration_return declaration() throws RecognitionException {
        declaration_return retval = new declaration_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token IDENT3=null;
        Token SEMI4=null;
        type_return type2 = null;


        SGCTree IDENT3_tree=null;
        SGCTree SEMI4_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // sgc/Syntax.g:91:12: ( type IDENT SEMI -> ^( DECLARATION type IDENT ) )
            // sgc/Syntax.g:92:2: type IDENT SEMI
            {
            pushFollow(FOLLOW_type_in_declaration872);
            type2=type();
            _fsp--;

            stream_type.add(type2.getTree());
            IDENT3=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_declaration874); 
            stream_IDENT.add(IDENT3);

            SEMI4=(Token)input.LT(1);
            match(input,SEMI,FOLLOW_SEMI_in_declaration876); 
            stream_SEMI.add(SEMI4);


            // AST REWRITE
            // elements: type, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (SGCTree)adaptor.nil();
            // 92:20: -> ^( DECLARATION type IDENT )
            {
                // sgc/Syntax.g:92:23: ^( DECLARATION type IDENT )
                {
                SGCTree root_1 = (SGCTree)adaptor.nil();
                root_1 = (SGCTree)adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION"), root_1);

                adaptor.addChild(root_1, stream_type.next());
                adaptor.addChild(root_1, stream_IDENT.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end declaration

    public static class type_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start type
    // sgc/Syntax.g:95:1: type : INT ;
    public final type_return type() throws RecognitionException {
        type_return retval = new type_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token INT5=null;

        SGCTree INT5_tree=null;

        try {
            // sgc/Syntax.g:95:5: ( INT )
            // sgc/Syntax.g:96:2: INT
            {
            root_0 = (SGCTree)adaptor.nil();

            INT5=(Token)input.LT(1);
            match(input,INT,FOLLOW_INT_in_type898); 
            INT5_tree = (SGCTree)adaptor.create(INT5);
            adaptor.addChild(root_0, INT5_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end type

    public static class statement_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start statement
    // sgc/Syntax.g:99:1: statement : ( evalExpression | assignmentStatement | ifStatement | whileStatement | block );
    public final statement_return statement() throws RecognitionException {
        statement_return retval = new statement_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        evalExpression_return evalExpression6 = null;

        assignmentStatement_return assignmentStatement7 = null;

        ifStatement_return ifStatement8 = null;

        whileStatement_return whileStatement9 = null;

        block_return block10 = null;



        try {
            // sgc/Syntax.g:99:10: ( evalExpression | assignmentStatement | ifStatement | whileStatement | block )
            int alt1=5;
            switch ( input.LA(1) ) {
            case LPAREN:
            case INTEGER_LITERAL:
                {
                alt1=1;
                }
                break;
            case IDENT:
                {
                int LA1_2 = input.LA(2);

                if ( (LA1_2==ASSIGN) ) {
                    alt1=2;
                }
                else if ( (LA1_2==DIV||(LA1_2>=GREATER_OR_EQUAL && LA1_2<=GREATER_THAN)||(LA1_2>=LESS_OR_EQUAL && LA1_2<=LESS_THAN)||LA1_2==MINUS||LA1_2==PLUS||LA1_2==SEMI||LA1_2==STAR) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("99:1: statement : ( evalExpression | assignmentStatement | ifStatement | whileStatement | block );", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case IF:
                {
                alt1=3;
                }
                break;
            case WHILE:
                {
                alt1=4;
                }
                break;
            case LCURLY:
                {
                alt1=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("99:1: statement : ( evalExpression | assignmentStatement | ifStatement | whileStatement | block );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // sgc/Syntax.g:100:2: evalExpression
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_evalExpression_in_statement908);
                    evalExpression6=evalExpression();
                    _fsp--;

                    adaptor.addChild(root_0, evalExpression6.getTree());

                    }
                    break;
                case 2 :
                    // sgc/Syntax.g:101:2: assignmentStatement
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_assignmentStatement_in_statement913);
                    assignmentStatement7=assignmentStatement();
                    _fsp--;

                    adaptor.addChild(root_0, assignmentStatement7.getTree());

                    }
                    break;
                case 3 :
                    // sgc/Syntax.g:102:2: ifStatement
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement918);
                    ifStatement8=ifStatement();
                    _fsp--;

                    adaptor.addChild(root_0, ifStatement8.getTree());

                    }
                    break;
                case 4 :
                    // sgc/Syntax.g:103:2: whileStatement
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement923);
                    whileStatement9=whileStatement();
                    _fsp--;

                    adaptor.addChild(root_0, whileStatement9.getTree());

                    }
                    break;
                case 5 :
                    // sgc/Syntax.g:104:2: block
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_block_in_statement928);
                    block10=block();
                    _fsp--;

                    adaptor.addChild(root_0, block10.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end statement

    public static class ifStatement_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ifStatement
    // sgc/Syntax.g:107:1: ifStatement : IF parenthesizedExpression ifStat= multiStatement ( ELSE elseStat= multiStatement -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat) | -> ^( IF parenthesizedExpression JUMP_IF $ifStat) ) ;
    public final ifStatement_return ifStatement() throws RecognitionException {
        ifStatement_return retval = new ifStatement_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token IF11=null;
        Token ELSE13=null;
        multiStatement_return ifStat = null;

        multiStatement_return elseStat = null;

        parenthesizedExpression_return parenthesizedExpression12 = null;


        SGCTree IF11_tree=null;
        SGCTree ELSE13_tree=null;
        RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_multiStatement=new RewriteRuleSubtreeStream(adaptor,"rule multiStatement");
        RewriteRuleSubtreeStream stream_parenthesizedExpression=new RewriteRuleSubtreeStream(adaptor,"rule parenthesizedExpression");
        try {
            // sgc/Syntax.g:107:12: ( IF parenthesizedExpression ifStat= multiStatement ( ELSE elseStat= multiStatement -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat) | -> ^( IF parenthesizedExpression JUMP_IF $ifStat) ) )
            // sgc/Syntax.g:108:2: IF parenthesizedExpression ifStat= multiStatement ( ELSE elseStat= multiStatement -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat) | -> ^( IF parenthesizedExpression JUMP_IF $ifStat) )
            {
            IF11=(Token)input.LT(1);
            match(input,IF,FOLLOW_IF_in_ifStatement938); 
            stream_IF.add(IF11);

            pushFollow(FOLLOW_parenthesizedExpression_in_ifStatement940);
            parenthesizedExpression12=parenthesizedExpression();
            _fsp--;

            stream_parenthesizedExpression.add(parenthesizedExpression12.getTree());
            pushFollow(FOLLOW_multiStatement_in_ifStatement944);
            ifStat=multiStatement();
            _fsp--;

            stream_multiStatement.add(ifStat.getTree());
            // sgc/Syntax.g:109:9: ( ELSE elseStat= multiStatement -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat) | -> ^( IF parenthesizedExpression JUMP_IF $ifStat) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ELSE) ) {
                alt2=1;
            }
            else if ( (LA2_0==LCURLY||LA2_0==LPAREN||LA2_0==RCURLY||LA2_0==IF||LA2_0==WHILE||(LA2_0>=IDENT && LA2_0<=INTEGER_LITERAL)) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("109:9: ( ELSE elseStat= multiStatement -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat) | -> ^( IF parenthesizedExpression JUMP_IF $ifStat) )", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // sgc/Syntax.g:109:13: ELSE elseStat= multiStatement
                    {
                    ELSE13=(Token)input.LT(1);
                    match(input,ELSE,FOLLOW_ELSE_in_ifStatement959); 
                    stream_ELSE.add(ELSE13);

                    pushFollow(FOLLOW_multiStatement_in_ifStatement963);
                    elseStat=multiStatement();
                    _fsp--;

                    stream_multiStatement.add(elseStat.getTree());

                    // AST REWRITE
                    // elements: elseStat, parenthesizedExpression, ifStat, IF
                    // token labels: 
                    // rule labels: ifStat, retval, elseStat
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"token ifStat",ifStat!=null?ifStat.tree:null);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"token elseStat",elseStat!=null?elseStat.tree:null);

                    root_0 = (SGCTree)adaptor.nil();
                    // 110:14: -> ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat)
                    {
                        // sgc/Syntax.g:110:17: ^( IF parenthesizedExpression JUMP_IF $ifStat $elseStat)
                        {
                        SGCTree root_1 = (SGCTree)adaptor.nil();
                        root_1 = (SGCTree)adaptor.becomeRoot(stream_IF.next(), root_1);

                        adaptor.addChild(root_1, stream_parenthesizedExpression.next());
                        adaptor.addChild(root_1, adaptor.create(JUMP_IF, "JUMP_IF"));
                        adaptor.addChild(root_1, stream_ifStat.next());
                        adaptor.addChild(root_1, stream_elseStat.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 2 :
                    // sgc/Syntax.g:111:15: 
                    {

                    // AST REWRITE
                    // elements: parenthesizedExpression, IF, ifStat
                    // token labels: 
                    // rule labels: ifStat, retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"token ifStat",ifStat!=null?ifStat.tree:null);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (SGCTree)adaptor.nil();
                    // 111:15: -> ^( IF parenthesizedExpression JUMP_IF $ifStat)
                    {
                        // sgc/Syntax.g:111:18: ^( IF parenthesizedExpression JUMP_IF $ifStat)
                        {
                        SGCTree root_1 = (SGCTree)adaptor.nil();
                        root_1 = (SGCTree)adaptor.becomeRoot(stream_IF.next(), root_1);

                        adaptor.addChild(root_1, stream_parenthesizedExpression.next());
                        adaptor.addChild(root_1, adaptor.create(JUMP_IF, "JUMP_IF"));
                        adaptor.addChild(root_1, stream_ifStat.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end ifStatement

    public static class whileStatement_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start whileStatement
    // sgc/Syntax.g:115:1: whileStatement : WHILE parenthesizedExpression multiStatement -> ^( WHILE parenthesizedExpression JUMP_IF multiStatement ) ;
    public final whileStatement_return whileStatement() throws RecognitionException {
        whileStatement_return retval = new whileStatement_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token WHILE14=null;
        parenthesizedExpression_return parenthesizedExpression15 = null;

        multiStatement_return multiStatement16 = null;


        SGCTree WHILE14_tree=null;
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleSubtreeStream stream_multiStatement=new RewriteRuleSubtreeStream(adaptor,"rule multiStatement");
        RewriteRuleSubtreeStream stream_parenthesizedExpression=new RewriteRuleSubtreeStream(adaptor,"rule parenthesizedExpression");
        try {
            // sgc/Syntax.g:115:15: ( WHILE parenthesizedExpression multiStatement -> ^( WHILE parenthesizedExpression JUMP_IF multiStatement ) )
            // sgc/Syntax.g:116:2: WHILE parenthesizedExpression multiStatement
            {
            WHILE14=(Token)input.LT(1);
            match(input,WHILE,FOLLOW_WHILE_in_whileStatement1039); 
            stream_WHILE.add(WHILE14);

            pushFollow(FOLLOW_parenthesizedExpression_in_whileStatement1041);
            parenthesizedExpression15=parenthesizedExpression();
            _fsp--;

            stream_parenthesizedExpression.add(parenthesizedExpression15.getTree());
            pushFollow(FOLLOW_multiStatement_in_whileStatement1043);
            multiStatement16=multiStatement();
            _fsp--;

            stream_multiStatement.add(multiStatement16.getTree());

            // AST REWRITE
            // elements: parenthesizedExpression, WHILE, multiStatement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (SGCTree)adaptor.nil();
            // 117:8: -> ^( WHILE parenthesizedExpression JUMP_IF multiStatement )
            {
                // sgc/Syntax.g:117:11: ^( WHILE parenthesizedExpression JUMP_IF multiStatement )
                {
                SGCTree root_1 = (SGCTree)adaptor.nil();
                root_1 = (SGCTree)adaptor.becomeRoot(stream_WHILE.next(), root_1);

                adaptor.addChild(root_1, stream_parenthesizedExpression.next());
                adaptor.addChild(root_1, adaptor.create(JUMP_IF, "JUMP_IF"));
                adaptor.addChild(root_1, stream_multiStatement.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end whileStatement

    public static class multiStatement_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multiStatement
    // sgc/Syntax.g:120:1: multiStatement : statement -> ^( BLOCK statement ) ;
    public final multiStatement_return multiStatement() throws RecognitionException {
        multiStatement_return retval = new multiStatement_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        statement_return statement17 = null;


        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // sgc/Syntax.g:120:15: ( statement -> ^( BLOCK statement ) )
            // sgc/Syntax.g:121:2: statement
            {
            pushFollow(FOLLOW_statement_in_multiStatement1072);
            statement17=statement();
            _fsp--;

            stream_statement.add(statement17.getTree());

            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (SGCTree)adaptor.nil();
            // 121:15: -> ^( BLOCK statement )
            {
                // sgc/Syntax.g:121:18: ^( BLOCK statement )
                {
                SGCTree root_1 = (SGCTree)adaptor.nil();
                root_1 = (SGCTree)adaptor.becomeRoot(adaptor.create(BLOCK, "BLOCK"), root_1);

                adaptor.addChild(root_1, stream_statement.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end multiStatement

    public static class parenthesizedExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parenthesizedExpression
    // sgc/Syntax.g:124:1: parenthesizedExpression : LPAREN expression RPAREN ;
    public final parenthesizedExpression_return parenthesizedExpression() throws RecognitionException {
        parenthesizedExpression_return retval = new parenthesizedExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token LPAREN18=null;
        Token RPAREN20=null;
        expression_return expression19 = null;


        SGCTree LPAREN18_tree=null;
        SGCTree RPAREN20_tree=null;

        try {
            // sgc/Syntax.g:124:24: ( LPAREN expression RPAREN )
            // sgc/Syntax.g:125:2: LPAREN expression RPAREN
            {
            root_0 = (SGCTree)adaptor.nil();

            LPAREN18=(Token)input.LT(1);
            match(input,LPAREN,FOLLOW_LPAREN_in_parenthesizedExpression1094); 
            pushFollow(FOLLOW_expression_in_parenthesizedExpression1097);
            expression19=expression();
            _fsp--;

            adaptor.addChild(root_0, expression19.getTree());
            RPAREN20=(Token)input.LT(1);
            match(input,RPAREN,FOLLOW_RPAREN_in_parenthesizedExpression1099); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end parenthesizedExpression

    public static class block_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start block
    // sgc/Syntax.g:128:1: block : LCURLY ( declaration )* ( statement )* RCURLY ;
    public final block_return block() throws RecognitionException {
        block_return retval = new block_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token LCURLY21=null;
        Token RCURLY24=null;
        declaration_return declaration22 = null;

        statement_return statement23 = null;


        SGCTree LCURLY21_tree=null;
        SGCTree RCURLY24_tree=null;

        try {
            // sgc/Syntax.g:128:6: ( LCURLY ( declaration )* ( statement )* RCURLY )
            // sgc/Syntax.g:129:2: LCURLY ( declaration )* ( statement )* RCURLY
            {
            root_0 = (SGCTree)adaptor.nil();

            LCURLY21=(Token)input.LT(1);
            match(input,LCURLY,FOLLOW_LCURLY_in_block1110); 
            // sgc/Syntax.g:129:10: ( declaration )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==INT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // sgc/Syntax.g:129:11: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_block1114);
            	    declaration22=declaration();
            	    _fsp--;

            	    adaptor.addChild(root_0, declaration22.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // sgc/Syntax.g:129:25: ( statement )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==LCURLY||LA4_0==LPAREN||LA4_0==IF||LA4_0==WHILE||(LA4_0>=IDENT && LA4_0<=INTEGER_LITERAL)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // sgc/Syntax.g:129:26: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block1119);
            	    statement23=statement();
            	    _fsp--;

            	    adaptor.addChild(root_0, statement23.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            RCURLY24=(Token)input.LT(1);
            match(input,RCURLY,FOLLOW_RCURLY_in_block1123); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end block

    public static class assignmentStatement_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start assignmentStatement
    // sgc/Syntax.g:132:1: assignmentStatement : IDENT ASSIGN expression SEMI -> ^( ASSIGN IDENT expression ) ;
    public final assignmentStatement_return assignmentStatement() throws RecognitionException {
        assignmentStatement_return retval = new assignmentStatement_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token IDENT25=null;
        Token ASSIGN26=null;
        Token SEMI28=null;
        expression_return expression27 = null;


        SGCTree IDENT25_tree=null;
        SGCTree ASSIGN26_tree=null;
        SGCTree SEMI28_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // sgc/Syntax.g:132:20: ( IDENT ASSIGN expression SEMI -> ^( ASSIGN IDENT expression ) )
            // sgc/Syntax.g:133:2: IDENT ASSIGN expression SEMI
            {
            IDENT25=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_assignmentStatement1134); 
            stream_IDENT.add(IDENT25);

            ASSIGN26=(Token)input.LT(1);
            match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentStatement1136); 
            stream_ASSIGN.add(ASSIGN26);

            pushFollow(FOLLOW_expression_in_assignmentStatement1138);
            expression27=expression();
            _fsp--;

            stream_expression.add(expression27.getTree());
            SEMI28=(Token)input.LT(1);
            match(input,SEMI,FOLLOW_SEMI_in_assignmentStatement1140); 
            stream_SEMI.add(SEMI28);


            // AST REWRITE
            // elements: expression, ASSIGN, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (SGCTree)adaptor.nil();
            // 133:31: -> ^( ASSIGN IDENT expression )
            {
                // sgc/Syntax.g:133:34: ^( ASSIGN IDENT expression )
                {
                SGCTree root_1 = (SGCTree)adaptor.nil();
                root_1 = (SGCTree)adaptor.becomeRoot(stream_ASSIGN.next(), root_1);

                adaptor.addChild(root_1, stream_IDENT.next());
                adaptor.addChild(root_1, stream_expression.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end assignmentStatement

    public static class evalExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start evalExpression
    // sgc/Syntax.g:136:1: evalExpression : expression SEMI ;
    public final evalExpression_return evalExpression() throws RecognitionException {
        evalExpression_return retval = new evalExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token SEMI30=null;
        expression_return expression29 = null;


        SGCTree SEMI30_tree=null;

        try {
            // sgc/Syntax.g:136:15: ( expression SEMI )
            // sgc/Syntax.g:137:2: expression SEMI
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_expression_in_evalExpression1160);
            expression29=expression();
            _fsp--;

            adaptor.addChild(root_0, expression29.getTree());
            SEMI30=(Token)input.LT(1);
            match(input,SEMI,FOLLOW_SEMI_in_evalExpression1162); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end evalExpression

    public static class expression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expression
    // sgc/Syntax.g:140:1: expression : relationalExpression ;
    public final expression_return expression() throws RecognitionException {
        expression_return retval = new expression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        relationalExpression_return relationalExpression31 = null;



        try {
            // sgc/Syntax.g:140:11: ( relationalExpression )
            // sgc/Syntax.g:141:2: relationalExpression
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_expression1173);
            relationalExpression31=relationalExpression();
            _fsp--;

            adaptor.addChild(root_0, relationalExpression31.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expression

    public static class relationalExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start relationalExpression
    // sgc/Syntax.g:144:1: relationalExpression : additiveExpression ( ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN ) additiveExpression )* ;
    public final relationalExpression_return relationalExpression() throws RecognitionException {
        relationalExpression_return retval = new relationalExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token LESS_OR_EQUAL33=null;
        Token GREATER_OR_EQUAL34=null;
        Token LESS_THAN35=null;
        Token GREATER_THAN36=null;
        additiveExpression_return additiveExpression32 = null;

        additiveExpression_return additiveExpression37 = null;


        SGCTree LESS_OR_EQUAL33_tree=null;
        SGCTree GREATER_OR_EQUAL34_tree=null;
        SGCTree LESS_THAN35_tree=null;
        SGCTree GREATER_THAN36_tree=null;

        try {
            // sgc/Syntax.g:144:21: ( additiveExpression ( ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN ) additiveExpression )* )
            // sgc/Syntax.g:145:2: additiveExpression ( ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN ) additiveExpression )*
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_relationalExpression1183);
            additiveExpression32=additiveExpression();
            _fsp--;

            adaptor.addChild(root_0, additiveExpression32.getTree());
            // sgc/Syntax.g:146:3: ( ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN ) additiveExpression )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=GREATER_OR_EQUAL && LA6_0<=GREATER_THAN)||(LA6_0>=LESS_OR_EQUAL && LA6_0<=LESS_THAN)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // sgc/Syntax.g:147:4: ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN ) additiveExpression
            	    {
            	    // sgc/Syntax.g:147:4: ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN )
            	    int alt5=4;
            	    switch ( input.LA(1) ) {
            	    case LESS_OR_EQUAL:
            	        {
            	        alt5=1;
            	        }
            	        break;
            	    case GREATER_OR_EQUAL:
            	        {
            	        alt5=2;
            	        }
            	        break;
            	    case LESS_THAN:
            	        {
            	        alt5=3;
            	        }
            	        break;
            	    case GREATER_THAN:
            	        {
            	        alt5=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("147:4: ( LESS_OR_EQUAL | GREATER_OR_EQUAL | LESS_THAN | GREATER_THAN )", 5, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt5) {
            	        case 1 :
            	            // sgc/Syntax.g:147:8: LESS_OR_EQUAL
            	            {
            	            LESS_OR_EQUAL33=(Token)input.LT(1);
            	            match(input,LESS_OR_EQUAL,FOLLOW_LESS_OR_EQUAL_in_relationalExpression1197); 
            	            LESS_OR_EQUAL33_tree = (SGCTree)adaptor.create(LESS_OR_EQUAL33);
            	            root_0 = (SGCTree)adaptor.becomeRoot(LESS_OR_EQUAL33_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // sgc/Syntax.g:148:8: GREATER_OR_EQUAL
            	            {
            	            GREATER_OR_EQUAL34=(Token)input.LT(1);
            	            match(input,GREATER_OR_EQUAL,FOLLOW_GREATER_OR_EQUAL_in_relationalExpression1207); 
            	            GREATER_OR_EQUAL34_tree = (SGCTree)adaptor.create(GREATER_OR_EQUAL34);
            	            root_0 = (SGCTree)adaptor.becomeRoot(GREATER_OR_EQUAL34_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // sgc/Syntax.g:149:8: LESS_THAN
            	            {
            	            LESS_THAN35=(Token)input.LT(1);
            	            match(input,LESS_THAN,FOLLOW_LESS_THAN_in_relationalExpression1217); 
            	            LESS_THAN35_tree = (SGCTree)adaptor.create(LESS_THAN35);
            	            root_0 = (SGCTree)adaptor.becomeRoot(LESS_THAN35_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // sgc/Syntax.g:150:8: GREATER_THAN
            	            {
            	            GREATER_THAN36=(Token)input.LT(1);
            	            match(input,GREATER_THAN,FOLLOW_GREATER_THAN_in_relationalExpression1227); 
            	            GREATER_THAN36_tree = (SGCTree)adaptor.create(GREATER_THAN36);
            	            root_0 = (SGCTree)adaptor.becomeRoot(GREATER_THAN36_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_additiveExpression_in_relationalExpression1238);
            	    additiveExpression37=additiveExpression();
            	    _fsp--;

            	    adaptor.addChild(root_0, additiveExpression37.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end relationalExpression

    public static class additiveExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start additiveExpression
    // sgc/Syntax.g:156:1: additiveExpression : multiplicativeExpression ( ( PLUS | MINUS ) multiplicativeExpression )* ;
    public final additiveExpression_return additiveExpression() throws RecognitionException {
        additiveExpression_return retval = new additiveExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token PLUS39=null;
        Token MINUS40=null;
        multiplicativeExpression_return multiplicativeExpression38 = null;

        multiplicativeExpression_return multiplicativeExpression41 = null;


        SGCTree PLUS39_tree=null;
        SGCTree MINUS40_tree=null;

        try {
            // sgc/Syntax.g:156:19: ( multiplicativeExpression ( ( PLUS | MINUS ) multiplicativeExpression )* )
            // sgc/Syntax.g:157:2: multiplicativeExpression ( ( PLUS | MINUS ) multiplicativeExpression )*
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1256);
            multiplicativeExpression38=multiplicativeExpression();
            _fsp--;

            adaptor.addChild(root_0, multiplicativeExpression38.getTree());
            // sgc/Syntax.g:158:3: ( ( PLUS | MINUS ) multiplicativeExpression )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==MINUS||LA8_0==PLUS) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // sgc/Syntax.g:159:4: ( PLUS | MINUS ) multiplicativeExpression
            	    {
            	    // sgc/Syntax.g:159:4: ( PLUS | MINUS )
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==PLUS) ) {
            	        alt7=1;
            	    }
            	    else if ( (LA7_0==MINUS) ) {
            	        alt7=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("159:4: ( PLUS | MINUS )", 7, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // sgc/Syntax.g:159:8: PLUS
            	            {
            	            PLUS39=(Token)input.LT(1);
            	            match(input,PLUS,FOLLOW_PLUS_in_additiveExpression1270); 
            	            PLUS39_tree = (SGCTree)adaptor.create(PLUS39);
            	            root_0 = (SGCTree)adaptor.becomeRoot(PLUS39_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // sgc/Syntax.g:160:8: MINUS
            	            {
            	            MINUS40=(Token)input.LT(1);
            	            match(input,MINUS,FOLLOW_MINUS_in_additiveExpression1280); 
            	            MINUS40_tree = (SGCTree)adaptor.create(MINUS40);
            	            root_0 = (SGCTree)adaptor.becomeRoot(MINUS40_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1291);
            	    multiplicativeExpression41=multiplicativeExpression();
            	    _fsp--;

            	    adaptor.addChild(root_0, multiplicativeExpression41.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end additiveExpression

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multiplicativeExpression
    // sgc/Syntax.g:166:1: multiplicativeExpression : primaryExpression ( ( STAR | DIV ) primaryExpression )* ;
    public final multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        multiplicativeExpression_return retval = new multiplicativeExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token STAR43=null;
        Token DIV44=null;
        primaryExpression_return primaryExpression42 = null;

        primaryExpression_return primaryExpression45 = null;


        SGCTree STAR43_tree=null;
        SGCTree DIV44_tree=null;

        try {
            // sgc/Syntax.g:166:25: ( primaryExpression ( ( STAR | DIV ) primaryExpression )* )
            // sgc/Syntax.g:167:2: primaryExpression ( ( STAR | DIV ) primaryExpression )*
            {
            root_0 = (SGCTree)adaptor.nil();

            pushFollow(FOLLOW_primaryExpression_in_multiplicativeExpression1309);
            primaryExpression42=primaryExpression();
            _fsp--;

            adaptor.addChild(root_0, primaryExpression42.getTree());
            // sgc/Syntax.g:168:3: ( ( STAR | DIV ) primaryExpression )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==DIV||LA10_0==STAR) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // sgc/Syntax.g:169:4: ( STAR | DIV ) primaryExpression
            	    {
            	    // sgc/Syntax.g:169:4: ( STAR | DIV )
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==STAR) ) {
            	        alt9=1;
            	    }
            	    else if ( (LA9_0==DIV) ) {
            	        alt9=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("169:4: ( STAR | DIV )", 9, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // sgc/Syntax.g:169:8: STAR
            	            {
            	            STAR43=(Token)input.LT(1);
            	            match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression1323); 
            	            STAR43_tree = (SGCTree)adaptor.create(STAR43);
            	            root_0 = (SGCTree)adaptor.becomeRoot(STAR43_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // sgc/Syntax.g:170:8: DIV
            	            {
            	            DIV44=(Token)input.LT(1);
            	            match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression1333); 
            	            DIV44_tree = (SGCTree)adaptor.create(DIV44);
            	            root_0 = (SGCTree)adaptor.becomeRoot(DIV44_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_primaryExpression_in_multiplicativeExpression1344);
            	    primaryExpression45=primaryExpression();
            	    _fsp--;

            	    adaptor.addChild(root_0, primaryExpression45.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end multiplicativeExpression

    public static class primaryExpression_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start primaryExpression
    // sgc/Syntax.g:176:1: primaryExpression : ( literal | IDENT -> ^( REF IDENT ) | parenthesizedExpression );
    public final primaryExpression_return primaryExpression() throws RecognitionException {
        primaryExpression_return retval = new primaryExpression_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token IDENT47=null;
        literal_return literal46 = null;

        parenthesizedExpression_return parenthesizedExpression48 = null;


        SGCTree IDENT47_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // sgc/Syntax.g:176:18: ( literal | IDENT -> ^( REF IDENT ) | parenthesizedExpression )
            int alt11=3;
            switch ( input.LA(1) ) {
            case INTEGER_LITERAL:
                {
                alt11=1;
                }
                break;
            case IDENT:
                {
                alt11=2;
                }
                break;
            case LPAREN:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("176:1: primaryExpression : ( literal | IDENT -> ^( REF IDENT ) | parenthesizedExpression );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // sgc/Syntax.g:177:2: literal
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_primaryExpression1362);
                    literal46=literal();
                    _fsp--;

                    adaptor.addChild(root_0, literal46.getTree());

                    }
                    break;
                case 2 :
                    // sgc/Syntax.g:178:2: IDENT
                    {
                    IDENT47=(Token)input.LT(1);
                    match(input,IDENT,FOLLOW_IDENT_in_primaryExpression1367); 
                    stream_IDENT.add(IDENT47);


                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (SGCTree)adaptor.nil();
                    // 178:8: -> ^( REF IDENT )
                    {
                        // sgc/Syntax.g:178:11: ^( REF IDENT )
                        {
                        SGCTree root_1 = (SGCTree)adaptor.nil();
                        root_1 = (SGCTree)adaptor.becomeRoot(adaptor.create(REF, "REF"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 3 :
                    // sgc/Syntax.g:179:2: parenthesizedExpression
                    {
                    root_0 = (SGCTree)adaptor.nil();

                    pushFollow(FOLLOW_parenthesizedExpression_in_primaryExpression1380);
                    parenthesizedExpression48=parenthesizedExpression();
                    _fsp--;

                    adaptor.addChild(root_0, parenthesizedExpression48.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end primaryExpression

    public static class literal_return extends ParserRuleReturnScope {
        SGCTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start literal
    // sgc/Syntax.g:182:1: literal : INTEGER_LITERAL ;
    public final literal_return literal() throws RecognitionException {
        literal_return retval = new literal_return();
        retval.start = input.LT(1);

        SGCTree root_0 = null;

        Token INTEGER_LITERAL49=null;

        SGCTree INTEGER_LITERAL49_tree=null;

        try {
            // sgc/Syntax.g:182:8: ( INTEGER_LITERAL )
            // sgc/Syntax.g:183:2: INTEGER_LITERAL
            {
            root_0 = (SGCTree)adaptor.nil();

            INTEGER_LITERAL49=(Token)input.LT(1);
            match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_literal1390); 
            INTEGER_LITERAL49_tree = (SGCTree)adaptor.create(INTEGER_LITERAL49);
            adaptor.addChild(root_0, INTEGER_LITERAL49_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (SGCTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end literal


 

    public static final BitSet FOLLOW_block_in_program862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_declaration872 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_IDENT_in_declaration874 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_evalExpression_in_statement908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentStatement_in_statement913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_ifStatement938 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_parenthesizedExpression_in_ifStatement940 = new BitSet(new long[]{0xC140000020800000L});
    public static final BitSet FOLLOW_multiStatement_in_ifStatement944 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_ELSE_in_ifStatement959 = new BitSet(new long[]{0xC140000020800000L});
    public static final BitSet FOLLOW_multiStatement_in_ifStatement963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_whileStatement1039 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_parenthesizedExpression_in_whileStatement1041 = new BitSet(new long[]{0xC140000020800000L});
    public static final BitSet FOLLOW_multiStatement_in_whileStatement1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_multiStatement1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenthesizedExpression1094 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_expression_in_parenthesizedExpression1097 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenthesizedExpression1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_block1110 = new BitSet(new long[]{0xC160040020800000L});
    public static final BitSet FOLLOW_declaration_in_block1114 = new BitSet(new long[]{0xC160040020800000L});
    public static final BitSet FOLLOW_statement_in_block1119 = new BitSet(new long[]{0xC140040020800000L});
    public static final BitSet FOLLOW_RCURLY_in_block1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_assignmentStatement1134 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ASSIGN_in_assignmentStatement1136 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_expression_in_assignmentStatement1138 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMI_in_assignmentStatement1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_evalExpression1160 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMI_in_evalExpression1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalExpression_in_expression1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1183 = new BitSet(new long[]{0x0000000003180002L});
    public static final BitSet FOLLOW_LESS_OR_EQUAL_in_relationalExpression1197 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_GREATER_OR_EQUAL_in_relationalExpression1207 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_LESS_THAN_in_relationalExpression1217 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_GREATER_THAN_in_relationalExpression1227 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1238 = new BitSet(new long[]{0x0000000003180002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1256 = new BitSet(new long[]{0x0000004040000002L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression1270 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_MINUS_in_additiveExpression1280 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1291 = new BitSet(new long[]{0x0000004040000002L});
    public static final BitSet FOLLOW_primaryExpression_in_multiplicativeExpression1309 = new BitSet(new long[]{0x0002000000002002L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression1323 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression1333 = new BitSet(new long[]{0xC000000020000000L});
    public static final BitSet FOLLOW_primaryExpression_in_multiplicativeExpression1344 = new BitSet(new long[]{0x0002000000002002L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_primaryExpression1367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenthesizedExpression_in_primaryExpression1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_LITERAL_in_literal1390 = new BitSet(new long[]{0x0000000000000002L});

}