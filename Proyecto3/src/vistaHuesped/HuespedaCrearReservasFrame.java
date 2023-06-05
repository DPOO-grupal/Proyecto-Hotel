package vistaHuesped;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Pagos.PagosFrame;
import controlador.WindowManager;
import vistaEmpleado.EmpleadoCrearReservasFrame;

public class HuespedaCrearReservasFrame extends EmpleadoCrearReservasFrame {

	private HuespedManager huespedManager;
	private JFrame PagosFrame;

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
			double precioReservaDescuento = windowManager.getGrupo(id).getReserva().getPrecioReserva()*0.9;
			PagosFrame = new PagosFrame(windowManager, precioReservaDescuento, id+"");
			volverMenu();
			int option = JOptionPane.showConfirmDialog(null, "¿Desea pagar ahora?\nSe le aplicará un 10% de descuento.","Reserva exitosa",JOptionPane.YES_NO_OPTION);
			if (option==JOptionPane.YES_OPTION) {
				huespedManager.mostraVentanaPagos(PagosFrame, false);
				huespedManager.getGrupo(id).pagarReservaConDescuento(precioReservaDescuento);
			} else {
				JOptionPane.showMessageDialog(null, "Recuerde hacer check-out al final de su estadia. Feliz dia.");
			}
			
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


