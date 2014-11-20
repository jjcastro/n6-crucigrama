package interfaz;

import java.awt.Color;
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
	
	public PanelPrincipal(char[][] casillas, String[] palabrasH, String[] palabrasV, InterfazCrucigrama pInterfaz)
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
		configurarDescripciones(palabrasH, palabrasV);
		
		add(pnlCrucigrama);
		add(pnlDescripciones);
	}

	private void configurarCrucigrama(char[][] casillas)
	{
		int filas = casillas.length;
		int columnas = casillas[0].length;
		
		pnlCrucigrama.setLayout(new GridLayout(filas + 1, columnas + 1, 1, 1));
		pnlCrucigrama.add(new JLabel());
		
		// INICIALIZAR LA MATRIZ Y TODOS LOS CAMPOS
		campos = new JTextField[filas][columnas];
		
		// LLENAR LA PRIMERA FILA
		for(int i = 1; i < columnas + 1; i++)
		{
			pnlCrucigrama.add(new JLabel(""+i, SwingConstants.CENTER));
		}
		
		for(int i = 1; i < filas + 1; i++)
		{
			pnlCrucigrama.add(new JLabel(""+i, SwingConstants.CENTER));

			for(int j = 0; j < columnas; j++)
			{
				campos[i-1][j] = new JTextField();
				
				if(casillas[i-1][j] == '$')
				{
					campos[i-1][j].setEnabled(false);
					campos[i-1][j].setBackground(Color.BLACK);
				}
				
				pnlCrucigrama.add(campos[i-1][j]);
			}
		}
	}
	
	private void configurarDescripciones(String[] palabrasH, String[] palabrasV)
	{
		String descripcionesH = "<html>";
		for(int i = 0; i < palabrasH.length; i++)
		{
			descripcionesH += palabrasH[i] + "\n";
		}
		
		descripcionesH += "</html>";
		
		String descripcionesV = "<html>";
		for(int i = 0; i < palabrasV.length; i++)
		{
			descripcionesV += palabrasH[i] + "\n";
		}
		
		descripcionesV += "</html>";
		
		
		txtDescripcionesH.setText(descripcionesH);
		txtDescripcionesV.setText(descripcionesV);
	}
}