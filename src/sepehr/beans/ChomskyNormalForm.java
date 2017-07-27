package sepehr.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

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

        // Remove null variables
        productions = removeNullableVariables(productions);
        // Remove repeated variables
        productions = removeRepeatedProductions(productions);
        // Get all unit productions and substitute them
        ArrayList<Production> unitProductions = getUnitProductions(productions);
        for (int i = 0; i < unitProductions.size(); i++) {
            substitute(productions, unitProductions.get(i));
            productions.remove(unitProductions.get(i));
            productions = removeRepeatedProductions(productions);
        }

        // Remove repeated productions
        for (int j = 0; j < productions.size(); j++) {
            for (int l = 0; l < productions.size(); l++) {
                if (productions.get(j).getExpression().equals(productions.get(l).getExpression())
                        && productions.get(j).getSymbol().equals(productions.get(l).getSymbol())
                        && j != l) {
                    productions.remove(productions.get(l));
                    break;
                }
            }
        }
        
        System.out.println("Unit Productions : " + unitProductions.toString());
        return productions;
    }

    private static ArrayList<Production> removeNullableVariables(ArrayList<Production> productions) {
        // Remove nullable variables
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            Production p = itr.next();
            if (p.getSymbol().endsWith("#")) {
                itr.remove();
            }
        }
    	return productions;
	}
    
    private static ArrayList<Production> removeRepeatedProductions(ArrayList<Production> productions) {
        // Remove repeated productions
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            Production p = itr.next();
            if (p.getSymbol().length()==1
                    && p.getSymbol().equals(p.getSymbol().toUpperCase())
                    && p.getExpression().equals(p.getSymbol())) {
            		itr.remove();
            }
        }
    	return productions;
	}
    
    private static ArrayList<Production> getUnitProductions(ArrayList<Production> productions) {
        // Unit Productions
    	ArrayList<Production> unitProductions = new ArrayList<>();
    	for(Iterator<Production> itr = productions.iterator(); itr.hasNext();) {
            Production p = itr.next();
            if (p.getSymbol().length()==1
                    && p.getSymbol().equals(p.getSymbol().toUpperCase())) {
            	if (p.getExpression().equals(p.getSymbol())) {
            		itr.remove();			// Remove repeated productions
				} else {
					// found p as a unit production
					unitProductions.add(p);
				}
            }
        }
    	
    	return unitProductions;
	}

    private static ArrayList<Production> substitute (ArrayList<Production> productions, Production unitProduction) {

        productions.addAll(productions.stream().filter(
                p -> p.getSymbol().contains(unitProduction.getExpression())).map(p -> new Production(p.getExpression(),
                p.getSymbol().replace(unitProduction.getExpression(), unitProduction.getSymbol()))).collect(Collectors.toList()));

        return productions;
    }
}
