package com.csmoe;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.csmoe.frame.MainFrame;

public class Application {
	public static void main(String[] args) {
		// ¶Ô macosµÄÊÊÅä
		if(System.getProperty("os.name").startsWith("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar","true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "SprView");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MainFrame app = new MainFrame();
		if(args.length > 0) {
			app.loadSpr(args[0]);
		}
	}
}
