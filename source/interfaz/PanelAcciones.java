package interfaz;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelAcciones extends JPanel implements ActionListener
{
	private static final String VALIDARH = "VALIDARH";
	private static final String VALIDARV = "VALIDARV";
	private static final String SOLUCIONAR = "SOLUCIONAR";
	private static final String LIMPIAR = "LIMPIAR";
	private static final String CARGAR = "CARGAR";
	private static final String OPCION1 = "OPCION1";
	private static final String OPCION2 = "OPCION2";

	private InterfazCrucigrama interfaz;
	
	private JButton btnValidarHorizontales;
	private JButton btnValidarVerticales;
	private JButton btnSolucionar;
	private JButton btnLimpiar;
	private JButton btnCargar;
	private JButton btnOpcion1;
	private JButton btnOpcion2;
	
	public PanelAcciones(InterfazCrucigrama pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(2, 4));
		setBorder(new TitledBorder("Acciones"));
		
		btnValidarHorizontales = new JButton("Validar horizontales");
		btnValidarVerticales = new JButton("Validar verticales");
		btnSolucionar = new JButton("Solucionar");
		btnLimpiar = new JButton("Limpiar");
		btnCargar = new JButton("Cargar");
		btnOpcion1 = new JButton("Opcion 1");
		btnOpcion2 = new JButton("Opcion 2");
		
		add(btnValidarHorizontales);
		add(btnValidarVerticales);
		add(btnSolucionar);
		add(btnLimpiar);
		add(btnCargar);
		add(btnOpcion1);
		add(btnOpcion2);
		
		btnValidarHorizontales.setActionCommand(VALIDARH);
		btnValidarVerticales.setActionCommand(VALIDARV);
		btnSolucionar.setActionCommand(SOLUCIONAR);
		btnLimpiar.setActionCommand(LIMPIAR);
		btnCargar.setActionCommand(CARGAR);
		btnOpcion1.setActionCommand(OPCION1);
		btnOpcion2.setActionCommand(OPCION2);
		
		btnValidarHorizontales.addActionListener(this);
		btnValidarVerticales.addActionListener(this);
		btnSolucionar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnCargar.addActionListener(this);
		btnOpcion1.addActionListener(this);
		btnOpcion2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		
		if(comando.equals(CARGAR))
		{
			JFileChooser fc = new JFileChooser("./data");
			fc.setMultiSelectionEnabled(false);
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION)
			{
				File archivo = fc.getSelectedFile();
				interfaz.cargar(archivo);
			}
		}
		else if(comando.equals(VALIDARH))
		{
			interfaz.validarPalabrasHorizontales();
		}
		else if(comando.equals(VALIDARV))
		{
			interfaz.validarPalabrasVerticales();
		}
	}
}
