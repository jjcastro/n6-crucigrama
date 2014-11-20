package interfaz;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelPrincipal extends JPanel
{
	private InterfazCrucigrama interfaz;
	
	private JTextField[][] casillas;
	
	private JTextArea txtDescripcionesH;
	private JTextArea txtDescripcionesV;
	
	public PanelPrincipal(int filas, int columnas, InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(1,2));
		
		JPanel pnlCrucigrama = new JPanel();
		
		JPanel pnlDescripciones = new JPanel();
		pnlDescripciones.setLayout(new GridLayout(2,1));
		
		txtDescripcionesH = new JTextArea();
		txtDescripcionesH.setBorder(new TitledBorder("Horizontales"));
		
		txtDescripcionesV = new JTextArea();
		txtDescripcionesH.setBorder(new TitledBorder("Verticales"));
		
		pnlDescripciones.add(txtDescripcionesH);
		pnlDescripciones.add(txtDescripcionesV);
		
		add(pnlCrucigrama);
		add(pnlDescripciones);
	}
}
