import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Stack;

public class SymbolsTable{
	SymbolsTable(){}
  Stack<LinkedHashMap> stackOfTables = new Stack<LinkedHashMap> ();
	LinkedHashMap<String,String> symbolsTable = new LinkedHashMap<String, String>();
	//symbolsTable.put("name of whatever", value) to input
	//symbolsTable.get("name of whatever")
	Stack<String> scopeStack = new Stack<String>(); //scope stack

  private int scopeMarker = 0; // this determines the total nested blocks  
<<<<<<< HEAD
  
=======
	String breakpoint = "BREAKPOINT"; // this determines if it is the end of a scope

>>>>>>> d5a25626bf722ac4f67ebd6b72fdedfc477ef878
  public int getScopeMarker(){ return scopeMarker; } 
  //returns scopeMarker

	public void insertSymbol(Token tok){
		if (isDeclared(tok)){
			System.err.println("redeclaration of variable " + tok.string + 
                         " --- line " + tok.lineNumber);
		}else{
			symbolsTable.put(tok.string,null);
		}
//		Set<String> keys = this.symbolsTable.keySet();
//		for(String k:keys){
//            System.out.println(k+" -- "+this.symbolsTable.get(k));
//        }
	}
  //inserts the symbol into the symbol table
  //if already declared, print error message
  //else put symbol into symbol table

	public void assignValue(String key, String value){
			symbolsTable.put(key, value);
	//		Set<String> keys = this.symbolsTable.keySet();
	//	for(String k:keys){
    //        System.out.println(k+" -- "+this.symbolsTable.get(k));
    //    }
	}


	public boolean isDeclared(Token tok){
		if (tok != null){
		if(symbolsTable.containsKey(tok.string)){
			return true;
		}
		else{
			return false;
		}}
		else{
			System.out.println("hi");
			return false;
		}
	}//if not declared and wanting to assign, or if declared and wants to declare again, error

  public boolean sameScope(Token tok){
    return false;
  }

	public void newScope(){
    stackOfTables.push(symbolsTable);
    symbolsTable.clear();
    scopeMarker++; 
		scopeStack.push(breakpoint);
	}
  //increases depth each time program enters a new scope

	public void endScope(){
    scopeMarker--;
    symbolsTable.putall(stackOfTables.pop());
		if (scopeStack.peek() == breakpoint){
			scopeStack.pop();
			return;
		}else{
			symbolsTable.remove(scopeStack.pop()); 
		}
		//peek until you see a breakpoint
		//if breakpoint, stop
		//else pop the variable from stack and pop it from the hash table
	}
  //decreases depth each time program leaves a new scope

	public void addVariable(String key){
		scopeStack.push(key);
	}
	//hash table hash<h,k>
	//symbols are inputted <name, value>
	//reads when a new declaration is found
	//find symbol in hash table
	//add symbol in hash table or modify symbols
}
