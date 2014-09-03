package guicreator;

import java.util.ArrayList;

import data_structures.StringPair;
import string_operations.StrOps;
import xml_parser.XMLNode;
import xml_parser.XML_Parser;

public class ShadowEngine extends GUIEngine{
	ArrayList<GUI_Component> GUI_component_root_list = new ArrayList<GUI_Component>();

	@Override
	public void loadXML(String XML) {
		parser = new XML_Parser();
		parser.parseXML(XML);
		
		ArrayList<XMLNode> rootList = parser.getRootList();
		
		for(XMLNode node : rootList){
			GUI_component_root_list.add(new GUI_Component("object", node.text));			
			parseComponent(GUI_component_root_list.get(GUI_component_root_list.size()-1), node);
		}
		
		for(GUI_Component g : GUI_component_root_list)
			System.out.println(g.print());
		
	}
	
	private void parseComponent(GUI_Component comp, XMLNode node){
		ArrayList<XMLNode> attributes = node.getAttributes();
		for(XMLNode nn : attributes){
			String t = nn.text;
			if(t.equals("constructor")){
				ArrayList<String> leaves = nn.getLeaves(nn);
				for(String s : leaves){
					s = StrOps.removeQuotes(s);
					int index = StrOps.findNonDelimitedPatternAfterIndex(s, "_", 0);
					if(index == -1)
						System.out.println("[WARNING] gui_creator.ShadowEngine.parseComponent warning:: leaf \"" + s + "\" has not underscore. Ignoring...");
					else
						comp.arguments.add(new StringPair(s.substring(0,index), s.substring(index+1)));
				}
			} else{
				t = StrOps.removeQuotes(t);
				int index = StrOps.findNonDelimitedPatternAfterIndex(t, "_", 0);
				if(index == -1)
					System.out.println("[WARNING] gui_creator.ShadowEngine.parseComponent warning:: leaf \"" + t + "\" has not underscore. Error creating GUI_Component.");
				String type = t.substring(0, index);
				String name = t.substring(index+1);
				GUI_Component newComp = new GUI_Component(type, name);
				ArrayList<String> newCompArguments = nn.getLeaves(nn);
				for(String s : newCompArguments){
					s = StrOps.removeQuotes(s);
					index = StrOps.findNonDelimitedPatternAfterIndex(s, "_", 0);
					if(index == -1)
						System.out.println("[WARNING] gui_creator.ShadowEngine.parseComponent warning:: leaf \"" + s + "\" has not underscore. Ignoring...");
					else
						newComp.arguments.add(new StringPair(s.substring(0, index), s.substring(index+1)));
				}
				comp.attributes.add(newComp);
				if(!type.equals("method") && !type.equals("value")){
					System.out.println("[WARNING] gui_creator.ShadowEngine.parseComponent warning:: type " + type + " is not recognized. Ignoring...");
				}
			}
		}
		
		ArrayList<XMLNode> children = node.getTags();
		for(XMLNode nn : children){
			GUI_Component newComp = new GUI_Component("object", node.text);
			parseComponent(newComp, nn);
			comp.children.add(newComp);
		}
	}
	
	private void printAllTags(XMLNode node, String tab_string){
		System.out.println(tab_string + node.text);
		ArrayList<XMLNode> children = node.getTags();
		for(int ii = 0; ii < children.size(); ii++){
			printAllTags(children.get(ii), tab_string + "\t");
		}
	}

	@Override
	public void buildGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GUIVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

}
