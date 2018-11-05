/* *** This file is given as part of the programming assignment. *** */
import java.util.Hashtable;
import java.util.Stack;

public class Parser {


    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
	tok = scanner.scan();
    }
    Token temp;
    Token temp2;

    SymbolsTable symTable = new SymbolsTable();
    private Scan scanner;

    Parser(Scan scanner) {
	this.scanner = scanner;
	scan();
	program();
	if( tok.kind != TK.EOF )
	    parse_error("junk after logical end of program");
    }

    private void program() {
	block();
    }

    private void block(){
	declaration_list();
	statement_list();
    }

    private void declaration_list() {
	// below checks whether tok is in first set of declaration.
	// here, that's easy since there's only one token kind in the set.
	// in other places, though, there might be more.
	// so, you might want to write a general function to handle that.
	while( is(TK.DECLARE) ) {
	    declaration();
	}
    }

    private void declaration() {
	mustbe(TK.DECLARE);
	temp = tok;
	mustbe(TK.ID);
	symTable.insertSymbol(temp);
	while(is(TK.COMMA)){
	    scan();
	    temp = tok;
	    mustbe(TK.ID);
	    symTable.insertSymbol(temp);
	}
    }

    private void statement_list() {
    	while(isStatement()){
    		statement();
    	}
    } 

    private boolean isStatement(){
    	if((isAssignment() || is(TK.PRINT)) || (is(TK.DO) || is(TK.IF))){
    		return true;
    	}
    	else {return false;}
    }

	private boolean isAssignment(){
//		System.out.println("here?");
		if(is(TK.TILDE) || is(TK.ID)){
			return true;
		}
		return false;
	}

    private void statement(){
    	if(isAssignment()){
    		tkAssign();
    	}
    	else if(is(TK.PRINT)){
    		tkPrint();
    	}else if(is(TK.DO)){
        //newScope
        symTable.newScope();
        System.out.println("Start of Scope --- Current Depth: " 
                            + symTable.getScopeMarker());
    		tkDo();
        symTable.endScope();
        System.out.println("End of Scope --- Current Depth: " 
                            + symTable.getScopeMarker() + "\n");
        //endScope
    	}else if(is(TK.IF)){
        //newScope
        symTable.newScope();
        System.out.println("Start of Scope --- Current Depth: " 
                            + symTable.getScopeMarker());
    		tkIf();
        symTable.endScope();
        System.out.println("End of Scope --- Current Depth: "  
                            + symTable.getScopeMarker() + "\n");
        //endScope
	    }else{
	    	parse_error("asdf1");
	    }
    }

    private void tkAssign(){
    	temp = tok;
    	ref_id();
    	mustbe(TK.ASSIGN);
    	temp2 = tok;
    	expression();
    	symTable.assignValue(temp.string,temp2.string);
    }

    private void tkPrint(){
    	mustbe(TK.PRINT);
    	expression();
    }

    private void tkDo(){
    	mustbe(TK.DO);
    	guarded_command();
    	mustbe(TK.ENDDO);
    }

    private void tkIf(){
    	mustbe(TK.IF);
    	guarded_command();
    	while(is(TK.ELSEIF)){
    		scan();
    		guarded_command();
    	}
    	if(is(TK.ELSE)){
    		scan();
    		block();
    	}
    	mustbe(TK.ENDIF);
    }

    private void expression(){
    	term();
    	while(is(TK.PLUS) || is(TK.MINUS)){
    		scan();
    		term();
    	}
    }

    private void term(){
    	factor();
    	while(is(TK.TIMES) || is (TK.DIVIDE)){
    		scan();
    		factor();
    	}
    }

    private void factor(){
    	if(is(TK.LPAREN)){
    		scan();
    		expression();
    		mustbe(TK.RPAREN);
    	}else if(isRef_id()){
    		ref_id();
    	}else{
    		mustbe(TK.NUM);
    	}
    }

    private boolean isRef_id(){
    	if(is(TK.TILDE) || is(TK.ID)){
    		return true;
    	}
    	else{return false;}
    }

    private void ref_id(){ 
    	temp = tok;
    	if(!(symTable.isDeclared(temp))){
    		 assignment_error();
		  };

      if(is(TK.TILDE)){
    		scan();
    		if(is(TK.NUM)){
    			scan();
    		}
    	}
    	mustbe(TK.ID);
    }

    private void guarded_command(){
    	expression();
    	mustbe(TK.THEN);
    	block();
    }

    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    // ensure current token is tk and skip over it.
    private void mustbe(TK tk) {
	if( tok.kind != tk ) {
	    System.err.println( "mustbe: want " + tk + ", got " +
				    tok);
	    parse_error( "missing token (mustbe)" );
	}
	scan();
    }

    private void parse_error(String msg) {
	System.err.println( "can't parse: line "
			    + tok.lineNumber + " " + msg );
	System.exit(1);
    }

    private void assignment_error() {
    	System.err.println(temp.string + " is an undeclared variable on line " + tok.lineNumber);
		System.exit(1);
    }
}
