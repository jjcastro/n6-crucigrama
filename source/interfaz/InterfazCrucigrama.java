package interfaz;

import java.awt.BorderLayout;

import javax.swing.*;

import mundo.Crucigrama;

public class InterfazCrucigrama extends JFrame
{
	private PanelAcciones pnlAcciones;
	private PanelPrincipal pnlPrincipal;
	
	private Crucigrama crucigrama;
	
	public InterfazCrucigrama()
	{
		setLayout(new BorderLayout());
		
		JLabel banner = new JLabel("./data/imagenes/banner.jpg");
		add(banner, BorderLayout.NORTH);
		
		pack();
		setVisible(true);
		
	}
}
