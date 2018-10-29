import java.util.Hashtable;
import java.util.Set;


public class SymbolsTable{
	Hashtable<String,String> symbolsTable = new Hashtable<String, String>();
	//symbolsTable.put("name of whatever", value) to input
	//symbolsTable.get("name of whatever")


	public void insertSymbol(String key){
		if (isDeclared(key)){
			//ERROR
		}else{
			symbolsTable.put(key,null);
		}
		
		System.out.println(key);
	}

	public void insertSymbol(String key, String value){
		if (isDeclared(key)){
			valueChange(key, value);
		}else{
			symbolsTable.put(key,value);
		}
	}



	public boolean isDeclared(String key){
		if(symbolsTable.get(key) == null){
			return false;
		}
		else{
			return true;
		}
	}//if not declared and wanting to assign, or if declared and wants to declare again, error

	public void valueChange(String key, String value){
		symbolsTable.put(key, value);
	}
	//hash table hash<h,k>
	//symbols are inputted <name, value>
	//reads when a new declaration is found
	//find symbol in hash table
	//add symbol in hash table or modify symbols
}
