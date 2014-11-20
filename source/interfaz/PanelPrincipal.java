package interfaz;

import java.awt.GridLayout;

import javax.swing.*;

public class PanelPrincipal extends JPanel
{
	private InterfazCrucigrama interfaz;
	
	private JTextField[][] casillas;
	
	public PanelPrincipal(int filas, int columnas, InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(1,2));
		
		JPanel pnlCrucigrama = new JPanel();
		
		JPanel pnlDescripciones = new JPanel();
		
		
	}
}
