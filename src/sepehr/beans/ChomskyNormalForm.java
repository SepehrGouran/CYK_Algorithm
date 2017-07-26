package sepehr.beans;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sepehr on 7/26/2017.
 */
public class ChomskyNormalForm {

    public static ArrayList<Production> getChomskyNormalFormProductions (ArrayList<Production> cykProductions) {

        if (isCNF(cykProductions)) {
            return cykProductions;
        } else {
            return toChomskyNormalForm(cykProductions);
        }
    }

    public static boolean isCNF (ArrayList<Production> productions) {

        for (Production p : productions) {
            if (p.getSymbol().length() == 1
                    && p.getSymbol().equals(p.getSymbol().toLowerCase())
                    && !p.getSymbol().endsWith("#")) {                                    // Production with a terminal

            } else if (p.getSymbol().length() == 2
                    && p.getSymbol().equals(p.getSymbol().toUpperCase())) {               // Production with two variables

            } else {                                                                      // Grammar is not a CNF
                return false;
            }
        }

        return true;
    }

    public static ArrayList<Production> toChomskyNormalForm (ArrayList<Production> productions) {

        ArrayList<Production> cnf = new ArrayList<>();

        ArrayList<Character> terminals = new ArrayList<>();

        productions = removeNullableVariables(productions);
        productions = removeRepetedProductions(productions);
        productions = unitProductions(productions);
        
        
        System.out.println(terminals.toString());

        return productions;
    }
    
    private static ArrayList<Production> removeNullableVariables(ArrayList<Production> productions) {
    	
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            Production p = itr.next();
            // Remove nullable variables
            if (p.getSymbol().endsWith("#")) {
                itr.remove();
            }
        }
    	
    	return productions;
	}
    
    private static ArrayList<Production> removeRepetedProductions(ArrayList<Production> productions) {
    	
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            // Unit Productions
            Production p = itr.next();
            
            if (p.getSymbol().length()==1
                    && p.getSymbol().equals(p.getSymbol().toUpperCase()) 
                    && p.getExpression().equals(p.getSymbol())) {
            		itr.remove();			// Remove repeated productions
            }
        }
    	
    	return productions;
	}
    
    private static ArrayList<Production> unitProductions(ArrayList<Production> productions) {
    	
    	ArrayList<Production> unitProductions = new ArrayList<Production>();
    	
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            // Unit Productions
            Production p = itr.next();
            
            if (p.getSymbol().length()==1
                    && p.getSymbol().equals(p.getSymbol().toUpperCase())) {
            	if (p.getExpression().equals(p.getSymbol())) {
            		itr.remove();			// Remove repeated productions
				} else {
					// found p as unit production
					Production unit = p;
					for (Iterator<Production> itr2 = productions.iterator(); itr2.hasNext();) {
						Production pro = itr2.next();
						if (pro.getSymbol().contains(unit.getSymbol())) {
							productions.add(new Production(pro.getExpression(), unit.getSymbol()));
						}
					}
					
					productions = removeRepetedProductions(productions);					
				}
            }
        }
    	
    	unitProductions = removeRepetedProductions(productions);
    	
    	return unitProductions;
	}
}
