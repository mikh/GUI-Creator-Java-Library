package test;

import guicreator.RadianceEngine;
import guicreator.ShadowEngine;

public class Test {
	public static void main(String[] args){
		System.out.println("Starting test");
		ShadowEngine gui = new ShadowEngine();
		gui.loadXML("example_gui.xml");
		
		//RadianceEngine gui = new RadianceEngine();
		//gui.loadXML("example_gui.xml");
		//gui.buildGUI();
		//gui.GUIVisible(true);
		System.out.println("Test Complete");
	}
}
