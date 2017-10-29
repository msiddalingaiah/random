// $ANTLR 3.0.1 sgc/Syntax.g 2008-05-01 23:08:37
 package com.madhu.minc.sgc; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SyntaxLexer extends Lexer {
    public static final int SHIFT_LEFT=45;
    public static final int COMMA=11;
    public static final int GREATER_THAN=20;
    public static final int MINUS=30;
    public static final int DEF=57;
    public static final int LOGICAL_OR=28;
    public static final int INC=21;
    public static final int OR_ASSIGN=37;
    public static final int XOR_ASSIGN=52;
    public static final int LESS_THAN=25;
    public static final int ELLIPSIS=17;
    public static final int REF=58;
    public static final int MOD=32;
    public static final int PLUS_ASSIGN=39;
    public static final int QUESTION=40;
    public static final int AND_ASSIGN=5;
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
    public static final int LBRACK=22;
    public static final int JUMP_IF=61;
    public static final int SEMI=44;
    public static final int BIT_SHIFT_RIGHT=8;
    public static final int DIV_ASSIGN=14;
    public static final int BIT_SHIFT_RIGHT_ASSIGN=9;
    public static final int DEC=12;
    public static final int EQUAL=18;
    public static final int ELSE=55;
    public static final int LOGICAL_AND=26;
    public static final int IF=54;
    public static final int SHIFT_RIGHT=47;
    public static final int EOF=-1;
    public static final int XOR=51;
    public static final int Tokens=66;
    public static final int RBRACK=41;
    public static final int COLON=10;
    public static final int DIV=13;
    public static final int LOGICAL_NOT=27;
    public static final int STAR=49;
    public static final int NOT=34;
    public SyntaxLexer() {;} 
    public SyntaxLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "sgc/Syntax.g"; }

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // sgc/Syntax.g:4:5: ( '&' )
            // sgc/Syntax.g:4:7: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start AND_ASSIGN
    public final void mAND_ASSIGN() throws RecognitionException {
        try {
            int _type = AND_ASSIGN;
            // sgc/Syntax.g:5:12: ( '&=' )
            // sgc/Syntax.g:5:14: '&='
            {
            match("&="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND_ASSIGN

    // $ANTLR start ASSIGN
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            // sgc/Syntax.g:6:8: ( '=' )
            // sgc/Syntax.g:6:10: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASSIGN

    // $ANTLR start AT
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            // sgc/Syntax.g:7:4: ( '@' )
            // sgc/Syntax.g:7:6: '@'
            {
            match('@'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT

    // $ANTLR start BIT_SHIFT_RIGHT
    public final void mBIT_SHIFT_RIGHT() throws RecognitionException {
        try {
            int _type = BIT_SHIFT_RIGHT;
            // sgc/Syntax.g:8:17: ( '>>>' )
            // sgc/Syntax.g:8:19: '>>>'
            {
            match(">>>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BIT_SHIFT_RIGHT

    // $ANTLR start BIT_SHIFT_RIGHT_ASSIGN
    public final void mBIT_SHIFT_RIGHT_ASSIGN() throws RecognitionException {
        try {
            int _type = BIT_SHIFT_RIGHT_ASSIGN;
            // sgc/Syntax.g:9:24: ( '>>>=' )
            // sgc/Syntax.g:9:26: '>>>='
            {
            match(">>>="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BIT_SHIFT_RIGHT_ASSIGN

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // sgc/Syntax.g:10:7: ( ':' )
            // sgc/Syntax.g:10:9: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // sgc/Syntax.g:11:7: ( ',' )
            // sgc/Syntax.g:11:9: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start DEC
    public final void mDEC() throws RecognitionException {
        try {
            int _type = DEC;
            // sgc/Syntax.g:12:5: ( '--' )
            // sgc/Syntax.g:12:7: '--'
            {
            match("--"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DEC

    // $ANTLR start DIV
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            // sgc/Syntax.g:13:5: ( '/' )
            // sgc/Syntax.g:13:7: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV

    // $ANTLR start DIV_ASSIGN
    public final void mDIV_ASSIGN() throws RecognitionException {
        try {
            int _type = DIV_ASSIGN;
            // sgc/Syntax.g:14:12: ( '/=' )
            // sgc/Syntax.g:14:14: '/='
            {
            match("/="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV_ASSIGN

    // $ANTLR start DOT
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            // sgc/Syntax.g:15:5: ( '.' )
            // sgc/Syntax.g:15:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOT

    // $ANTLR start DOTSTAR
    public final void mDOTSTAR() throws RecognitionException {
        try {
            int _type = DOTSTAR;
            // sgc/Syntax.g:16:9: ( '.*' )
            // sgc/Syntax.g:16:11: '.*'
            {
            match(".*"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOTSTAR

    // $ANTLR start ELLIPSIS
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            // sgc/Syntax.g:17:10: ( '...' )
            // sgc/Syntax.g:17:12: '...'
            {
            match("..."); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELLIPSIS

    // $ANTLR start EQUAL
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            // sgc/Syntax.g:18:7: ( '==' )
            // sgc/Syntax.g:18:9: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUAL

    // $ANTLR start GREATER_OR_EQUAL
    public final void mGREATER_OR_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQUAL;
            // sgc/Syntax.g:19:18: ( '>=' )
            // sgc/Syntax.g:19:20: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATER_OR_EQUAL

    // $ANTLR start GREATER_THAN
    public final void mGREATER_THAN() throws RecognitionException {
        try {
            int _type = GREATER_THAN;
            // sgc/Syntax.g:20:14: ( '>' )
            // sgc/Syntax.g:20:16: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATER_THAN

    // $ANTLR start INC
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            // sgc/Syntax.g:21:5: ( '++' )
            // sgc/Syntax.g:21:7: '++'
            {
            match("++"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INC

    // $ANTLR start LBRACK
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            // sgc/Syntax.g:22:8: ( '[' )
            // sgc/Syntax.g:22:10: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACK

    // $ANTLR start LCURLY
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            // sgc/Syntax.g:23:8: ( '{' )
            // sgc/Syntax.g:23:10: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LCURLY

    // $ANTLR start LESS_OR_EQUAL
    public final void mLESS_OR_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_OR_EQUAL;
            // sgc/Syntax.g:24:15: ( '<=' )
            // sgc/Syntax.g:24:17: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LESS_OR_EQUAL

    // $ANTLR start LESS_THAN
    public final void mLESS_THAN() throws RecognitionException {
        try {
            int _type = LESS_THAN;
            // sgc/Syntax.g:25:11: ( '<' )
            // sgc/Syntax.g:25:13: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LESS_THAN

    // $ANTLR start LOGICAL_AND
    public final void mLOGICAL_AND() throws RecognitionException {
        try {
            int _type = LOGICAL_AND;
            // sgc/Syntax.g:26:13: ( '&&' )
            // sgc/Syntax.g:26:15: '&&'
            {
            match("&&"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOGICAL_AND

    // $ANTLR start LOGICAL_NOT
    public final void mLOGICAL_NOT() throws RecognitionException {
        try {
            int _type = LOGICAL_NOT;
            // sgc/Syntax.g:27:13: ( '!' )
            // sgc/Syntax.g:27:15: '!'
            {
            match('!'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOGICAL_NOT

    // $ANTLR start LOGICAL_OR
    public final void mLOGICAL_OR() throws RecognitionException {
        try {
            int _type = LOGICAL_OR;
            // sgc/Syntax.g:28:12: ( '||' )
            // sgc/Syntax.g:28:14: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOGICAL_OR

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // sgc/Syntax.g:29:8: ( '(' )
            // sgc/Syntax.g:29:10: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // sgc/Syntax.g:30:7: ( '-' )
            // sgc/Syntax.g:30:9: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start MINUS_ASSIGN
    public final void mMINUS_ASSIGN() throws RecognitionException {
        try {
            int _type = MINUS_ASSIGN;
            // sgc/Syntax.g:31:14: ( '-=' )
            // sgc/Syntax.g:31:16: '-='
            {
            match("-="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS_ASSIGN

    // $ANTLR start MOD
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            // sgc/Syntax.g:32:5: ( '%' )
            // sgc/Syntax.g:32:7: '%'
            {
            match('%'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MOD

    // $ANTLR start MOD_ASSIGN
    public final void mMOD_ASSIGN() throws RecognitionException {
        try {
            int _type = MOD_ASSIGN;
            // sgc/Syntax.g:33:12: ( '%=' )
            // sgc/Syntax.g:33:14: '%='
            {
            match("%="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MOD_ASSIGN

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // sgc/Syntax.g:34:5: ( '~' )
            // sgc/Syntax.g:34:7: '~'
            {
            match('~'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start NOT_EQUAL
    public final void mNOT_EQUAL() throws RecognitionException {
        try {
            int _type = NOT_EQUAL;
            // sgc/Syntax.g:35:11: ( '!=' )
            // sgc/Syntax.g:35:13: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT_EQUAL

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // sgc/Syntax.g:36:4: ( '|' )
            // sgc/Syntax.g:36:6: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start OR_ASSIGN
    public final void mOR_ASSIGN() throws RecognitionException {
        try {
            int _type = OR_ASSIGN;
            // sgc/Syntax.g:37:11: ( '|=' )
            // sgc/Syntax.g:37:13: '|='
            {
            match("|="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR_ASSIGN

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // sgc/Syntax.g:38:6: ( '+' )
            // sgc/Syntax.g:38:8: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start PLUS_ASSIGN
    public final void mPLUS_ASSIGN() throws RecognitionException {
        try {
            int _type = PLUS_ASSIGN;
            // sgc/Syntax.g:39:13: ( '+=' )
            // sgc/Syntax.g:39:15: '+='
            {
            match("+="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS_ASSIGN

    // $ANTLR start QUESTION
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            // sgc/Syntax.g:40:10: ( '?' )
            // sgc/Syntax.g:40:12: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUESTION

    // $ANTLR start RBRACK
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            // sgc/Syntax.g:41:8: ( ']' )
            // sgc/Syntax.g:41:10: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACK

    // $ANTLR start RCURLY
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            // sgc/Syntax.g:42:8: ( '}' )
            // sgc/Syntax.g:42:10: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RCURLY

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // sgc/Syntax.g:43:8: ( ')' )
            // sgc/Syntax.g:43:10: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start SEMI
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            // sgc/Syntax.g:44:6: ( ';' )
            // sgc/Syntax.g:44:8: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMI

    // $ANTLR start SHIFT_LEFT
    public final void mSHIFT_LEFT() throws RecognitionException {
        try {
            int _type = SHIFT_LEFT;
            // sgc/Syntax.g:45:12: ( '<<' )
            // sgc/Syntax.g:45:14: '<<'
            {
            match("<<"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SHIFT_LEFT

    // $ANTLR start SHIFT_LEFT_ASSIGN
    public final void mSHIFT_LEFT_ASSIGN() throws RecognitionException {
        try {
            int _type = SHIFT_LEFT_ASSIGN;
            // sgc/Syntax.g:46:19: ( '<<=' )
            // sgc/Syntax.g:46:21: '<<='
            {
            match("<<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SHIFT_LEFT_ASSIGN

    // $ANTLR start SHIFT_RIGHT
    public final void mSHIFT_RIGHT() throws RecognitionException {
        try {
            int _type = SHIFT_RIGHT;
            // sgc/Syntax.g:47:13: ( '>>' )
            // sgc/Syntax.g:47:15: '>>'
            {
            match(">>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SHIFT_RIGHT

    // $ANTLR start SHIFT_RIGHT_ASSIGN
    public final void mSHIFT_RIGHT_ASSIGN() throws RecognitionException {
        try {
            int _type = SHIFT_RIGHT_ASSIGN;
            // sgc/Syntax.g:48:20: ( '>>=' )
            // sgc/Syntax.g:48:22: '>>='
            {
            match(">>="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SHIFT_RIGHT_ASSIGN

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // sgc/Syntax.g:49:6: ( '*' )
            // sgc/Syntax.g:49:8: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start STAR_ASSIGN
    public final void mSTAR_ASSIGN() throws RecognitionException {
        try {
            int _type = STAR_ASSIGN;
            // sgc/Syntax.g:50:13: ( '*=' )
            // sgc/Syntax.g:50:15: '*='
            {
            match("*="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR_ASSIGN

    // $ANTLR start XOR
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            // sgc/Syntax.g:51:5: ( '^' )
            // sgc/Syntax.g:51:7: '^'
            {
            match('^'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end XOR

    // $ANTLR start XOR_ASSIGN
    public final void mXOR_ASSIGN() throws RecognitionException {
        try {
            int _type = XOR_ASSIGN;
            // sgc/Syntax.g:52:12: ( '^=' )
            // sgc/Syntax.g:52:14: '^='
            {
            match("^="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end XOR_ASSIGN

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // sgc/Syntax.g:53:5: ( 'int' )
            // sgc/Syntax.g:53:7: 'int'
            {
            match("int"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // sgc/Syntax.g:54:4: ( 'if' )
            // sgc/Syntax.g:54:6: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // sgc/Syntax.g:55:6: ( 'else' )
            // sgc/Syntax.g:55:8: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start WHILE
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            // sgc/Syntax.g:56:7: ( 'while' )
            // sgc/Syntax.g:56:9: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHILE

    // $ANTLR start NEWLINE
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            // sgc/Syntax.g:192:8: ( ( '\\r' )? '\\n' )
            // sgc/Syntax.g:192:10: ( '\\r' )? '\\n'
            {
            // sgc/Syntax.g:192:10: ( '\\r' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='\r') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // sgc/Syntax.g:192:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
             skip(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEWLINE

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // sgc/Syntax.g:193:3: ( ( ' ' | '\\t' )+ )
            // sgc/Syntax.g:193:9: ( ' ' | '\\t' )+
            {
            // sgc/Syntax.g:193:9: ( ' ' | '\\t' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\t'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // sgc/Syntax.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

             skip(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start IDENT
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            // sgc/Syntax.g:197:8: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
            // sgc/Syntax.g:197:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
            {
            // sgc/Syntax.g:197:12: ( 'a' .. 'z' | 'A' .. 'Z' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='A' && LA3_0<='Z')||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // sgc/Syntax.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENT

    // $ANTLR start INTEGER_LITERAL
    public final void mINTEGER_LITERAL() throws RecognitionException {
        try {
            int _type = INTEGER_LITERAL;
            // sgc/Syntax.g:198:17: ( ( '0' .. '9' )+ )
            // sgc/Syntax.g:198:21: ( '0' .. '9' )+
            {
            // sgc/Syntax.g:198:21: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // sgc/Syntax.g:198:21: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGER_LITERAL

    public void mTokens() throws RecognitionException {
        // sgc/Syntax.g:1:8: ( AND | AND_ASSIGN | ASSIGN | AT | BIT_SHIFT_RIGHT | BIT_SHIFT_RIGHT_ASSIGN | COLON | COMMA | DEC | DIV | DIV_ASSIGN | DOT | DOTSTAR | ELLIPSIS | EQUAL | GREATER_OR_EQUAL | GREATER_THAN | INC | LBRACK | LCURLY | LESS_OR_EQUAL | LESS_THAN | LOGICAL_AND | LOGICAL_NOT | LOGICAL_OR | LPAREN | MINUS | MINUS_ASSIGN | MOD | MOD_ASSIGN | NOT | NOT_EQUAL | OR | OR_ASSIGN | PLUS | PLUS_ASSIGN | QUESTION | RBRACK | RCURLY | RPAREN | SEMI | SHIFT_LEFT | SHIFT_LEFT_ASSIGN | SHIFT_RIGHT | SHIFT_RIGHT_ASSIGN | STAR | STAR_ASSIGN | XOR | XOR_ASSIGN | INT | IF | ELSE | WHILE | NEWLINE | WS | IDENT | INTEGER_LITERAL )
        int alt5=57;
        switch ( input.LA(1) ) {
        case '&':
            {
            switch ( input.LA(2) ) {
            case '&':
                {
                alt5=23;
                }
                break;
            case '=':
                {
                alt5=2;
                }
                break;
            default:
                alt5=1;}

            }
            break;
        case '=':
            {
            int LA5_2 = input.LA(2);

            if ( (LA5_2=='=') ) {
                alt5=15;
            }
            else {
                alt5=3;}
            }
            break;
        case '@':
            {
            alt5=4;
            }
            break;
        case '>':
            {
            switch ( input.LA(2) ) {
            case '>':
                {
                switch ( input.LA(3) ) {
                case '>':
                    {
                    int LA5_70 = input.LA(4);

                    if ( (LA5_70=='=') ) {
                        alt5=6;
                    }
                    else {
                        alt5=5;}
                    }
                    break;
                case '=':
                    {
                    alt5=45;
                    }
                    break;
                default:
                    alt5=44;}

                }
                break;
            case '=':
                {
                alt5=16;
                }
                break;
            default:
                alt5=17;}

            }
            break;
        case ':':
            {
            alt5=7;
            }
            break;
        case ',':
            {
            alt5=8;
            }
            break;
        case '-':
            {
            switch ( input.LA(2) ) {
            case '-':
                {
                alt5=9;
                }
                break;
            case '=':
                {
                alt5=28;
                }
                break;
            default:
                alt5=27;}

            }
            break;
        case '/':
            {
            int LA5_8 = input.LA(2);

            if ( (LA5_8=='=') ) {
                alt5=11;
            }
            else {
                alt5=10;}
            }
            break;
        case '.':
            {
            switch ( input.LA(2) ) {
            case '.':
                {
                alt5=14;
                }
                break;
            case '*':
                {
                alt5=13;
                }
                break;
            default:
                alt5=12;}

            }
            break;
        case '+':
            {
            switch ( input.LA(2) ) {
            case '=':
                {
                alt5=36;
                }
                break;
            case '+':
                {
                alt5=18;
                }
                break;
            default:
                alt5=35;}

            }
            break;
        case '[':
            {
            alt5=19;
            }
            break;
        case '{':
            {
            alt5=20;
            }
            break;
        case '<':
            {
            switch ( input.LA(2) ) {
            case '<':
                {
                int LA5_52 = input.LA(3);

                if ( (LA5_52=='=') ) {
                    alt5=43;
                }
                else {
                    alt5=42;}
                }
                break;
            case '=':
                {
                alt5=21;
                }
                break;
            default:
                alt5=22;}

            }
            break;
        case '!':
            {
            int LA5_14 = input.LA(2);

            if ( (LA5_14=='=') ) {
                alt5=32;
            }
            else {
                alt5=24;}
            }
            break;
        case '|':
            {
            switch ( input.LA(2) ) {
            case '=':
                {
                alt5=34;
                }
                break;
            case '|':
                {
                alt5=25;
                }
                break;
            default:
                alt5=33;}

            }
            break;
        case '(':
            {
            alt5=26;
            }
            break;
        case '%':
            {
            int LA5_17 = input.LA(2);

            if ( (LA5_17=='=') ) {
                alt5=30;
            }
            else {
                alt5=29;}
            }
            break;
        case '~':
            {
            alt5=31;
            }
            break;
        case '?':
            {
            alt5=37;
            }
            break;
        case ']':
            {
            alt5=38;
            }
            break;
        case '}':
            {
            alt5=39;
            }
            break;
        case ')':
            {
            alt5=40;
            }
            break;
        case ';':
            {
            alt5=41;
            }
            break;
        case '*':
            {
            int LA5_24 = input.LA(2);

            if ( (LA5_24=='=') ) {
                alt5=47;
            }
            else {
                alt5=46;}
            }
            break;
        case '^':
            {
            int LA5_25 = input.LA(2);

            if ( (LA5_25=='=') ) {
                alt5=49;
            }
            else {
                alt5=48;}
            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA5_66 = input.LA(3);

                if ( (LA5_66=='t') ) {
                    int LA5_75 = input.LA(4);

                    if ( ((LA5_75>='A' && LA5_75<='Z')||(LA5_75>='a' && LA5_75<='z')) ) {
                        alt5=56;
                    }
                    else {
                        alt5=50;}
                }
                else {
                    alt5=56;}
                }
                break;
            case 'f':
                {
                int LA5_67 = input.LA(3);

                if ( ((LA5_67>='A' && LA5_67<='Z')||(LA5_67>='a' && LA5_67<='z')) ) {
                    alt5=56;
                }
                else {
                    alt5=51;}
                }
                break;
            default:
                alt5=56;}

            }
            break;
        case 'e':
            {
            int LA5_27 = input.LA(2);

            if ( (LA5_27=='l') ) {
                int LA5_68 = input.LA(3);

                if ( (LA5_68=='s') ) {
                    int LA5_77 = input.LA(4);

                    if ( (LA5_77=='e') ) {
                        int LA5_82 = input.LA(5);

                        if ( ((LA5_82>='A' && LA5_82<='Z')||(LA5_82>='a' && LA5_82<='z')) ) {
                            alt5=56;
                        }
                        else {
                            alt5=52;}
                    }
                    else {
                        alt5=56;}
                }
                else {
                    alt5=56;}
            }
            else {
                alt5=56;}
            }
            break;
        case 'w':
            {
            int LA5_28 = input.LA(2);

            if ( (LA5_28=='h') ) {
                int LA5_69 = input.LA(3);

                if ( (LA5_69=='i') ) {
                    int LA5_78 = input.LA(4);

                    if ( (LA5_78=='l') ) {
                        int LA5_83 = input.LA(5);

                        if ( (LA5_83=='e') ) {
                            int LA5_85 = input.LA(6);

                            if ( ((LA5_85>='A' && LA5_85<='Z')||(LA5_85>='a' && LA5_85<='z')) ) {
                                alt5=56;
                            }
                            else {
                                alt5=53;}
                        }
                        else {
                            alt5=56;}
                    }
                    else {
                        alt5=56;}
                }
                else {
                    alt5=56;}
            }
            else {
                alt5=56;}
            }
            break;
        case '\n':
        case '\r':
            {
            alt5=54;
            }
            break;
        case '\t':
        case ' ':
            {
            alt5=55;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=56;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=57;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( AND | AND_ASSIGN | ASSIGN | AT | BIT_SHIFT_RIGHT | BIT_SHIFT_RIGHT_ASSIGN | COLON | COMMA | DEC | DIV | DIV_ASSIGN | DOT | DOTSTAR | ELLIPSIS | EQUAL | GREATER_OR_EQUAL | GREATER_THAN | INC | LBRACK | LCURLY | LESS_OR_EQUAL | LESS_THAN | LOGICAL_AND | LOGICAL_NOT | LOGICAL_OR | LPAREN | MINUS | MINUS_ASSIGN | MOD | MOD_ASSIGN | NOT | NOT_EQUAL | OR | OR_ASSIGN | PLUS | PLUS_ASSIGN | QUESTION | RBRACK | RCURLY | RPAREN | SEMI | SHIFT_LEFT | SHIFT_LEFT_ASSIGN | SHIFT_RIGHT | SHIFT_RIGHT_ASSIGN | STAR | STAR_ASSIGN | XOR | XOR_ASSIGN | INT | IF | ELSE | WHILE | NEWLINE | WS | IDENT | INTEGER_LITERAL );", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // sgc/Syntax.g:1:10: AND
                {
                mAND(); 

                }
                break;
            case 2 :
                // sgc/Syntax.g:1:14: AND_ASSIGN
                {
                mAND_ASSIGN(); 

                }
                break;
            case 3 :
                // sgc/Syntax.g:1:25: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 4 :
                // sgc/Syntax.g:1:32: AT
                {
                mAT(); 

                }
                break;
            case 5 :
                // sgc/Syntax.g:1:35: BIT_SHIFT_RIGHT
                {
                mBIT_SHIFT_RIGHT(); 

                }
                break;
            case 6 :
                // sgc/Syntax.g:1:51: BIT_SHIFT_RIGHT_ASSIGN
                {
                mBIT_SHIFT_RIGHT_ASSIGN(); 

                }
                break;
            case 7 :
                // sgc/Syntax.g:1:74: COLON
                {
                mCOLON(); 

                }
                break;
            case 8 :
                // sgc/Syntax.g:1:80: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 9 :
                // sgc/Syntax.g:1:86: DEC
                {
                mDEC(); 

                }
                break;
            case 10 :
                // sgc/Syntax.g:1:90: DIV
                {
                mDIV(); 

                }
                break;
            case 11 :
                // sgc/Syntax.g:1:94: DIV_ASSIGN
                {
                mDIV_ASSIGN(); 

                }
                break;
            case 12 :
                // sgc/Syntax.g:1:105: DOT
                {
                mDOT(); 

                }
                break;
            case 13 :
                // sgc/Syntax.g:1:109: DOTSTAR
                {
                mDOTSTAR(); 

                }
                break;
            case 14 :
                // sgc/Syntax.g:1:117: ELLIPSIS
                {
                mELLIPSIS(); 

                }
                break;
            case 15 :
                // sgc/Syntax.g:1:126: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 16 :
                // sgc/Syntax.g:1:132: GREATER_OR_EQUAL
                {
                mGREATER_OR_EQUAL(); 

                }
                break;
            case 17 :
                // sgc/Syntax.g:1:149: GREATER_THAN
                {
                mGREATER_THAN(); 

                }
                break;
            case 18 :
                // sgc/Syntax.g:1:162: INC
                {
                mINC(); 

                }
                break;
            case 19 :
                // sgc/Syntax.g:1:166: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 20 :
                // sgc/Syntax.g:1:173: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 21 :
                // sgc/Syntax.g:1:180: LESS_OR_EQUAL
                {
                mLESS_OR_EQUAL(); 

                }
                break;
            case 22 :
                // sgc/Syntax.g:1:194: LESS_THAN
                {
                mLESS_THAN(); 

                }
                break;
            case 23 :
                // sgc/Syntax.g:1:204: LOGICAL_AND
                {
                mLOGICAL_AND(); 

                }
                break;
            case 24 :
                // sgc/Syntax.g:1:216: LOGICAL_NOT
                {
                mLOGICAL_NOT(); 

                }
                break;
            case 25 :
                // sgc/Syntax.g:1:228: LOGICAL_OR
                {
                mLOGICAL_OR(); 

                }
                break;
            case 26 :
                // sgc/Syntax.g:1:239: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 27 :
                // sgc/Syntax.g:1:246: MINUS
                {
                mMINUS(); 

                }
                break;
            case 28 :
                // sgc/Syntax.g:1:252: MINUS_ASSIGN
                {
                mMINUS_ASSIGN(); 

                }
                break;
            case 29 :
                // sgc/Syntax.g:1:265: MOD
                {
                mMOD(); 

                }
                break;
            case 30 :
                // sgc/Syntax.g:1:269: MOD_ASSIGN
                {
                mMOD_ASSIGN(); 

                }
                break;
            case 31 :
                // sgc/Syntax.g:1:280: NOT
                {
                mNOT(); 

                }
                break;
            case 32 :
                // sgc/Syntax.g:1:284: NOT_EQUAL
                {
                mNOT_EQUAL(); 

                }
                break;
            case 33 :
                // sgc/Syntax.g:1:294: OR
                {
                mOR(); 

                }
                break;
            case 34 :
                // sgc/Syntax.g:1:297: OR_ASSIGN
                {
                mOR_ASSIGN(); 

                }
                break;
            case 35 :
                // sgc/Syntax.g:1:307: PLUS
                {
                mPLUS(); 

                }
                break;
            case 36 :
                // sgc/Syntax.g:1:312: PLUS_ASSIGN
                {
                mPLUS_ASSIGN(); 

                }
                break;
            case 37 :
                // sgc/Syntax.g:1:324: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 38 :
                // sgc/Syntax.g:1:333: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 39 :
                // sgc/Syntax.g:1:340: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 40 :
                // sgc/Syntax.g:1:347: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 41 :
                // sgc/Syntax.g:1:354: SEMI
                {
                mSEMI(); 

                }
                break;
            case 42 :
                // sgc/Syntax.g:1:359: SHIFT_LEFT
                {
                mSHIFT_LEFT(); 

                }
                break;
            case 43 :
                // sgc/Syntax.g:1:370: SHIFT_LEFT_ASSIGN
                {
                mSHIFT_LEFT_ASSIGN(); 

                }
                break;
            case 44 :
                // sgc/Syntax.g:1:388: SHIFT_RIGHT
                {
                mSHIFT_RIGHT(); 

                }
                break;
            case 45 :
                // sgc/Syntax.g:1:400: SHIFT_RIGHT_ASSIGN
                {
                mSHIFT_RIGHT_ASSIGN(); 

                }
                break;
            case 46 :
                // sgc/Syntax.g:1:419: STAR
                {
                mSTAR(); 

                }
                break;
            case 47 :
                // sgc/Syntax.g:1:424: STAR_ASSIGN
                {
                mSTAR_ASSIGN(); 

                }
                break;
            case 48 :
                // sgc/Syntax.g:1:436: XOR
                {
                mXOR(); 

                }
                break;
            case 49 :
                // sgc/Syntax.g:1:440: XOR_ASSIGN
                {
                mXOR_ASSIGN(); 

                }
                break;
            case 50 :
                // sgc/Syntax.g:1:451: INT
                {
                mINT(); 

                }
                break;
            case 51 :
                // sgc/Syntax.g:1:455: IF
                {
                mIF(); 

                }
                break;
            case 52 :
                // sgc/Syntax.g:1:458: ELSE
                {
                mELSE(); 

                }
                break;
            case 53 :
                // sgc/Syntax.g:1:463: WHILE
                {
                mWHILE(); 

                }
                break;
            case 54 :
                // sgc/Syntax.g:1:469: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 55 :
                // sgc/Syntax.g:1:477: WS
                {
                mWS(); 

                }
                break;
            case 56 :
                // sgc/Syntax.g:1:480: IDENT
                {
                mIDENT(); 

                }
                break;
            case 57 :
                // sgc/Syntax.g:1:486: INTEGER_LITERAL
                {
                mINTEGER_LITERAL(); 

                }
                break;

        }

    }


 

}