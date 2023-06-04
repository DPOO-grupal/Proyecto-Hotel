package vistaHuesped;

import javax.swing.JOptionPane;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoCrearReservasFrame;

public class HuespedaCrearReservasFrame extends EmpleadoCrearReservasFrame {

	private HuespedManager huespedManager;

	public HuespedaCrearReservasFrame(HuespedManager huespedManager) {
		super(huespedManager);
		this.huespedManager = huespedManager;

	}
	
	@Override
	protected void crearReserva() {
		try {
			int id = huespedManager.getGrupoEnCurso().getId();
			huespedManager.añadirReservaAHuesped(id);
			huespedManager.completarReserva();
			JOptionPane.showMessageDialog(null, "Su numero de Reserva es " + id);
			volverMenu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		
	}
	@Override
	public void estadoReserva() {
		super.estadoReserva();
		if(!huespedManager.hayReserva()) {
			huespedManager.reservaSoloConLider();
			huespedManager.añadirLider();
			resetDatos();

		}
		

	}
	
	

}


