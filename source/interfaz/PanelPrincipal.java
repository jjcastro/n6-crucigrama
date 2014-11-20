package interfaz;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelPrincipal extends JPanel
{
	private InterfazCrucigrama interfaz;
	
	private JTextField[][] casillas;
	
	private JLabel txtDescripcionesH;
	private JLabel txtDescripcionesV;
	
	public PanelPrincipal(char[][] casillas, InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(1,2));
		
		JPanel pnlCrucigrama = new JPanel();
		
		JPanel pnlDescripciones = new JPanel();
		pnlDescripciones.setLayout(new GridLayout(2,1));
		
		txtDescripcionesH = new JLabel();
		txtDescripcionesH.setBorder(new TitledBorder("Horizontales"));
		
		txtDescripcionesV = new JLabel();
		txtDescripcionesV.setBorder(new TitledBorder("Verticales"));
		
		pnlDescripciones.add(txtDescripcionesH);
		pnlDescripciones.add(txtDescripcionesV);
		
		add(pnlCrucigrama);
		add(pnlDescripciones);
	}
}
