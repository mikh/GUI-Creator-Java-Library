package guicreator;

import java.awt.Component;
import java.util.ArrayList;

import xml_parser.XMLNode;
import xml_parser.XML_Parser;

import javax.swing.*;

import string_operations.StrOps;

public class RadianceEngine extends GUIEngine{
	ArrayList<String> guiConstructionAlgorithm;
	ArrayList<Component> gui;
	
	String str_output;
	
	public RadianceEngine(){
		engineName = "Radiance";
		
	}

	@Override
	public void loadXML(String XML) {
		parser = new XML_Parser();
		parser.parseXML(XML);
		guiConstructionAlgorithm = new ArrayList<String>();
		
		ArrayList<XMLNode> rootList = parser.startTreeWalk();
		ArrayList<XMLNode> children = new ArrayList<XMLNode>(), attributes = new ArrayList<XMLNode>(), leaves = new ArrayList<XMLNode>();
		
		
		for(int ii = 0; ii < rootList.size(); ii++){
			parser.setWalkNode(rootList.get(ii));
			parser.getNodeData(children, attributes, leaves);
			ArrayList<String> attrib = new ArrayList<String>(), value = new ArrayList<String>();
			parser.getAttributes(rootList.get(ii), attrib, value);
			
			int constructor = -1;
			String constructor_values = null;

			for(int jj = 0; jj < attrib.size(); jj++){
				if(getAction(attrib.get(jj)) == 0){
					constructor = jj;
					break;
				}
			}

			if(constructor != -1){
				guiConstructionAlgorithm.add("constructorargs_" + rootList.get(ii).text + "_" + str_output);
			} else
				guiConstructionAlgorithm.add("constructor_" + rootList.get(ii).text);
		}
		
		StrOps.printArrayList(guiConstructionAlgorithm);
	}

	@Override
	public void buildGUI() {
		gui = new ArrayList<Component>();
		for(int ii = 0; ii < guiConstructionAlgorithm.size(); ii++){
			String value = "";
			int type = getAction(guiConstructionAlgorithm.get(ii));
			if(type == 0){
				try{
					gui.add((Component)Class.forName(str_output).newInstance());
				} catch(ClassNotFoundException e){
					System.out.println(String.format("[ERROR] cantrip.guicreator.radianceengine error :: class %s not found", str_output));
				} catch (InstantiationException e) {
					System.out.println(String.format("[ERROR] cantrip.guicreator.radianceengine error :: %s could not be instantiated", str_output));
				} catch (IllegalAccessException e) {
					System.out.println(String.format("[ERROR] cantrip.guicreator.radianceengine error :: illegal access on class creation of %s", str_output));
				}
			}
		}
	}

	@Override
	public void GUIVisible(boolean visible) {
		for(int ii = 0; ii < gui.size(); ii++){
			gui.get(ii).setVisible(true);
		}
	}

	private int getAction(String str){
		int index = str.indexOf("_");
		str_output = str.substring(index+1);
		String action = str.substring(0, index);
		if(action.equals("constructor"))
			return 0;
		else if(action.equals("method"))
			return 1;
		else if(action.equals("value"))
			return 2;
		else if(action.equals("constructorargs"))
			return 3;
		else
			return -1;
	}
}
