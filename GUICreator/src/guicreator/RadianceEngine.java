package guicreator;

import java.util.ArrayList;

import xml_parser.XMLNode;
import xml_parser.XML_Parser;
import javax.swing.*;

public class RadianceEngine extends GUIEngine{
	ArrayList<String> guiConstructionAlgorithm;
	
	public RadianceEngine(){
		engineName = "Radiance";
	}

	@Override
	public void loadXML(String XML) {
		parser = new XML_Parser();
		parser.parseXML(XML);
		guiConstructionAlgorithm = new ArrayList<String>();
		
		ArrayList<XMLNode> rootList = parser.startTreeWalk();
		ArrayList<XMLNode> children = null, attributes = null, leaves = null;
		
		
		for(int ii = 0; ii < rootList.size(); ii++){
			parser.getNodeData(children, attributes, leaves);
			guiConstructionAlgorithm.add("create_" + rootList.get(ii).text);
			ArrayList<String> attrib = null, value = null;
			parser.getAttributes(rootList.get(ii), attrib, value);
			String name = "";
			for(int jj = 0; jj < attrib.size(); jj++){
				int index = getAction(attrib.get(jj), name);
				if(index == 
			}
		}
	}

	@Override
	public void buildGUI() {

	}

	@Override
	public void GUIVisible(boolean visible) {
		
	}

	private int getAction(String str, String name){
		int index = str.indexOf("_");
		name = str.substring(index+1);
		String action = str.substring(0, index);
		if(action.equals("create"))
			return 0;
		else if(action.equals("method"))
			return 1;
		else if(action.equals("value"))
			return 2;
		else
			return -1;
	}
}
