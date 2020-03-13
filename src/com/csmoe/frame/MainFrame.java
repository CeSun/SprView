package com.csmoe.frame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.csmoe.core.SprLoader;


public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel image = new JLabel();

	Insets inset;
	Icon[][] frame = new Icon[0][0];
	Timer task;
	int curframe = 0;
	JMenuBar menuBar ;
	JFileChooser chooser = new JFileChooser(); 
	int barHeight;
	public MainFrame() {
        this.setIconImage(new ImageIcon("./src/resource/SprView.png").getImage());
		
		// TODO Auto-generated constructor stub
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("文件");
		JMenu aboutMenu = new JMenu("关于");
		// 一级菜单添加到菜单栏
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);

		JMenuItem openMenuItem = new JMenuItem("打开");
		JMenuItem exitMenuItem = new JMenuItem("退出");
		fileMenu.add(openMenuItem);
		fileMenu.add(exitMenuItem);
		JMenuItem about = new JMenuItem("关于");
		aboutMenu.add(about);
		setJMenuBar(menuBar);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Half-Life Sprite File Type(*.spr)", "spr");
		chooser.setFileFilter(filter);
		about.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this,"SprView by Dust \n QQ:709653366 \n WebSite:http://www.csmod.cn");
		}); 
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = chooser.showOpenDialog(getContentPane());
				if (i == JFileChooser.APPROVE_OPTION) {
					String filepath = chooser.getSelectedFile().getAbsolutePath(); 
					loadSpr(filepath);
				}
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		this.setVisible(true);
		this.setTitle("SprView");
		this.setLayout(null);
		image.setLocation(3, 3);
		inset = this.getInsets();
		this.add(image);
		this.setSize(256 + 6 + inset.left + inset.right , 256 + 6 + inset.top + inset.bottom + menuBar.getHeight());
		center ();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public void loadSpr(String filename) {
		filename = filename.replace("\\", "/");
		String name = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
		if(name.indexOf("/")>=0) {
			name = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
		}
		try {
			SprLoader spr = new SprLoader(filename);
			this.frame = new Icon[spr.getImageBuf().length][0];
			int w = spr.getWidth() + 6 + inset.left + inset.right;
			if(w < 264) 
				w = 264;
			this.setSize(w , spr.getHeight() + 6 + inset.top + inset.bottom + menuBar.getHeight());
			switch (spr.getFrameType()) {
			case 0:
				for (int i = 0 ; i < spr.getImageBuf().length ; i ++ ) {
					this.frame[i] = new Icon[spr.getImageBuf()[i].length];
					Icon icon = new ImageIcon(spr.getImageBuf()[i][0]);
					this.frame[i][0] = icon;
				}
				this.curframe = 0 ;
				this.image.setIcon(this.frame[curframe][0]);
				this.curframe ++;
				if(curframe >= frame.length)
					curframe = 0;
				break;
			case 1:
			case 2:

			}
			this.image.setSize(spr.getWidth(), spr.getHeight());
			if(task!=null) {

			} else {
				task = new Timer();
				task.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						image.setIcon(frame[curframe][0]);
						curframe ++;
						if(curframe >= frame.length)
							curframe = 0;
					}
				}, 100, 100);
			}

		this.setTitle(name + " - SprView" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
		
	}
	
	public void center () {
		int windowWidth = this.getWidth(); // 获得窗口宽   
		int windowHeight = this.getHeight(); // 获得窗口高   
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包   
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸   
		int screenWidth = screenSize.width; // 获取屏幕的宽   
		int screenHeight = screenSize.height; // 获取屏幕的高   
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
	}
}
