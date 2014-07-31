package test;

import guicreator.RadianceEngine;

public class Test {
	public static void main(String[] args){
		System.out.println("Starting test");
		RadianceEngine gui = new RadianceEngine();
		gui.loadXML("example_gui.xml");
		gui.buildGUI();
		gui.GUIVisible(true);
		System.out.println("Test Complete");
	}
}
