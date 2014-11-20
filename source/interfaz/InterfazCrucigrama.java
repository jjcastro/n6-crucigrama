package interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.*;

import mundo.Crucigrama;

public class InterfazCrucigrama extends JFrame
{
	private PanelAcciones pnlAcciones;
	private PanelPrincipal pnlPrincipal;
	
	private Crucigrama crucigrama;
	
	public InterfazCrucigrama() throws Exception
	{
		crucigrama = new Crucigrama(new File("./data/crucigrama.properties")); // TEMPORAL
		
		setSize(780, 730);
		setLayout(new BorderLayout());
		
		ImageIcon imagen = new ImageIcon("./data/imagenes/banner.jpg");
		JLabel banner = new JLabel(imagen);
		add(banner, BorderLayout.NORTH);
		
		pnlAcciones = new PanelAcciones(this);
		add(pnlAcciones, BorderLayout.SOUTH);
		
		String[] palabrasH = crucigrama.darDescripcionesPalabrasH();
		String[] palabrasV = crucigrama.darDescripcionesPalabrasV();
		
		pnlPrincipal = new PanelPrincipal(crucigrama.darCasillas(), palabrasH, palabrasV, this);
		add(pnlPrincipal, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception
	{
		new InterfazCrucigrama();
	}
}
