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
		
		for(int ii = 0; ii < rootList.size(); ii++){
			printAllTags(rootList.get(ii), "");
		}
		
	}
	
	private void parseComponent(GUI_Component comp, XMLNode node){
		ArrayList<XMLNode> attributes = node.getAttributes();
		for(XMLNode nn : attributes){
			String t = nn.text;
			if(t.equals("constructor")){
				ArrayList<String> leaves = nn.getLeaves(nn);
				for(String s : leaves){
					int index = StrOps.findNonDelimitedPatternAfterIndex(s, "_", 0);
					comp.arguments.add(new StringPair(s.substring(0,index), s.substring(index+1)));
				}
			} else{
				int index = StrOps.findNonDelimitedPatternAfterIndex(t, "_", 0);
				String type = t.substring(0, index);
				String name = t.substring(index+1);
				GUI_Component newComp = new GUI_Component(type, name);
				if(type.equals("method")){		/*** DIstinction b/w method and value not necessary, operations performed are identical. Only good for the warning statment ***/
					
				} else if(type.equals("value")){
					
				} else{
					System.out.println("[WARNING] gui_creator.ShadowEngine.parseComponent warning:: type " + type + " is not recognized. Ignoring...");
				}
			}
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
