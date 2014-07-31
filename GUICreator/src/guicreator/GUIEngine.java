package guicreator;

import xml_parser.XML_Parser;

public abstract class GUIEngine {
	protected String engineName = "base_engine";
	protected XML_Parser parser;
	
	
	public abstract void loadXML(String XML);
	public abstract void buildGUI();
	public abstract void GUIVisible(boolean visible);
}
