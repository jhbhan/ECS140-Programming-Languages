import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Stack;

public class SymbolsTable{
	SymbolsTable(){}
	LinkedHashMap<String,String> symbolsTable = new LinkedHashMap<String, String>();
	//symbolsTable.put("name of whatever", value) to input
	//symbolsTable.get("name of whatever")
	Stack<String> scopeStack = new Stack<String>();
	//scope stack
	String breakpoint = "BREAKPOINT";


	public void insertSymbol(Token tok){
		if (isDeclared(tok)){
			System.err.println("redeclaration of variable " + tok.string);
		}else{
			symbolsTable.put(tok.string,null);
		}
//		Set<String> keys = this.symbolsTable.keySet();
//		for(String k:keys){
//            System.out.println(k+" -- "+this.symbolsTable.get(k));
//        }
	}

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


	public void newScope(){
		scopeStack.push(breakpoint);
	}

	public void endScope(){
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

	public void addVariable(String key){
		scopeStack.push(key);
	}
	//hash table hash<h,k>
	//symbols are inputted <name, value>
	//reads when a new declaration is found
	//find symbol in hash table
	//add symbol in hash table or modify symbols
}
