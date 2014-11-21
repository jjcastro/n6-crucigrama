package interfaz;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel con las acciones del crucigrama
 * @author jj.castro10
 *
 */
public class PanelAcciones extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Comando para validar palabras horizontales
	 */
	private static final String VALIDARH = "VALIDARH";
	/**
	 * Comando para validar palabras verticales
	 */
	private static final String VALIDARV = "VALIDARV";
	/**
	 * Comando para solucionar
	 */
	private static final String SOLUCIONAR = "SOLUCIONAR";
	/**
	 * Comando para limpiar
	 */
	private static final String LIMPIAR = "LIMPIAR";
	/**
	 * Comando para cargar
	 */
	private static final String CARGAR = "CARGAR";
	/**
	 * Comando para extensión 1
	 */
	private static final String OPCION1 = "OPCION1";
	/**
	 * Comando para extensión 2
	 */
	private static final String OPCION2 = "OPCION2";

	/**
	 * Variable para guardar la ventana principal
	 */
	private InterfazCrucigrama interfaz;
	
	/**
	 * Botón para validar palabras horizontales
	 */
	private JButton btnValidarHorizontales;
	/**
	 * Botón para validar palabras verticales
	 */
	private JButton btnValidarVerticales;
	/**
	 * Botón para solucionar
	 */
	private JButton btnSolucionar;
	/**
	 * Botón para limpiar
	 */
	private JButton btnLimpiar;
	/**
	 * Botón para cargar
	 */
	private JButton btnCargar;
	/**
	 * Botón para extensión 1
	 */
	private JButton btnOpcion1;
	/**
	 * Botón para extensión 2
	 */
	private JButton btnOpcion2;
	
	/**
	 * Crea un nuevo panel de acciones
	 * @param pInterfaz ventana principal
	 */
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
		else if(comando.equals(SOLUCIONAR))
		{
			interfaz.solucionar();
		}
		else if(comando.equals(LIMPIAR))
		{
			interfaz.limpiar();
		}
		else if(comando.equals(OPCION1))
		{
			interfaz.opcion1();
		}
		else if(comando.equals(OPCION2))
		{
			interfaz.opcion2();
		}
	}
}
