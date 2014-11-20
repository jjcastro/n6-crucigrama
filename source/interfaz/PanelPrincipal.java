package interfaz;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelPrincipal extends JPanel
{
	private InterfazCrucigrama interfaz;
	
	private JTextField[][] campos;
	
	private JPanel pnlCrucigrama;
	private JPanel pnlDescripciones;
	
	private JLabel txtDescripcionesH;
	private JLabel txtDescripcionesV;
	
	public PanelPrincipal(char[][] casillas, InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(1,2));
		
		pnlCrucigrama = new JPanel();
		
		pnlDescripciones = new JPanel();
		pnlDescripciones.setLayout(new GridLayout(2,1));
		
		txtDescripcionesH = new JLabel();
		txtDescripcionesH.setBorder(new TitledBorder("Horizontales"));
		
		txtDescripcionesV = new JLabel();
		txtDescripcionesV.setBorder(new TitledBorder("Verticales"));
		
		pnlDescripciones.add(txtDescripcionesH);
		pnlDescripciones.add(txtDescripcionesV);
		
		configurarCrucigrama(casillas);
		
		add(pnlCrucigrama);
		add(pnlDescripciones);
	}

	private void configurarCrucigrama(char[][] casillas)
	{
		int filas = casillas.length;
		int columnas = casillas[0].length;
		
		campos = new JTextField[filas][columnas];
		
		pnlCrucigrama.setLayout(new GridLayout(1, columnas + 1, 2, 2));
		pnlCrucigrama.add(new JLabel());
		
		for(int i = 1; i < columnas + 1; i++)
		{
			pnlCrucigrama.add(new JLabel(""+i, SwingConstants.CENTER));
		}
	}
}
