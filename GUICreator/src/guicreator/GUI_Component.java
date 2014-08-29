package guicreator;

import java.util.ArrayList;

import data_structures.StringPair;

public class GUI_Component {
	public String type;			//can be "object", "method", "value"
	public String identifier;		//name of object, method, or value
	public ArrayList<StringPair> arguments = new ArrayList<StringPair>();	//for an object this will be the constructor, for a method this will be the arguments, for a value this will be just the <value_type, value>
	public ArrayList<GUI_Component> attributes = new ArrayList<GUI_Component>();	//this will be blank for non-objects
	public ArrayList<GUI_Component> children = new ArrayList<GUI_Component>();		//this will be blank for non-objects
	public GUI_Component(String n_type, String n_ident){
		type = n_type;
		identifier = n_ident;
	}
}

