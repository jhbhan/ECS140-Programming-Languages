import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SymbolsTable{
	SymbolsTable(){
		stackOfTables = new Stack<>();
		stackOfTables.push(new HashSet<>());
		scopeMarker = 0;
	}

	private Stack<HashSet<String>> stackOfTables;	//symbolsTable.put("name of whatever", value) to input
	//symbolsTable.get("name of whatever")

  private int scopeMarker; // this determines the total nested blocks  
  public int getScopeMarker(){ return scopeMarker; } 
  //returns scopeMarker

	public void insertSymbol(Token tok){
		if (stackOfTables.elementAt(scopeMarker).contains(tok.string)){
			System.err.println("redeclaration of variable " + tok.string);
		}else{
			stackOfTables.elementAt(scopeMarker).add(tok.string);
		}
	}
  //inserts the symbol into the symbol table
  //if already declared, print error message
  //else put symbol into symbol table

	public void assignValue(Token tok){
		if (!(stackOfTables.elementAt(scopeMarker).contains(tok.string))){
			System.err.println(tok.string + " is an undeclared variable on line " + tok.lineNumber);
			System.exit(1);
		}
	}

  public boolean sameScope(Token tok){
    return false;
  }

	public void newScope(){
		scopeMarker++; 
   		stackOfTables.push(new HashSet());
    }
  //increases depth each time program enters a new scope

	public void endScope(){
    	scopeMarker--;
    	stackOfTables.pop();
	}

	//no tilda, recursively find
	public boolean locateVariable(Token tok){
		int scope = scopeMarker;
		while(!stackOfTables.elementAt(scope).contains(tok.string)){
			scope--;
			if (scope < 0){
				return false;
			}
		}
			return true;
	}

	//tilda id, global, just check scope 0
	public boolean locateVariableGlobal(Token tok){
		return stackOfTables.elementAt(0).contains(tok.string);
	}

	// if ~0. current scope, if ~num: scopenumber scope
	public boolean locateVariable(Token tok, int scope){
		if (scope == 0){
			return stackOfTables.elementAt(scopeMarker).contains(tok.string);
		}
		else{
			return stackOfTables.elementAt(scope).contains(tok.string);
	}
	}

	//hash table hash<h,k>
	//symbols are inputted <name, value>
	//reads when a new declaration is found
	//find symbol in hash table
	//add symbol in hash table or modify symbols
}
