package org.example.parking;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class EstacionamientoTest {

	private Estacionamiento estacionamiento = new Estacionamiento();

	@Test
	public void ingresarVehiculo__Succesful() {
		Vehiculo auto = new Vehiculo("ad595bm", "POLO", Vehiculo.Tipo.AUTO);

		assertTrue(estacionamiento.ingresarVehiculo("42856378", "Tobias", auto));
		assertEquals(1, estacionamiento.listarVehiculosEstacionados().size());
	}

	@Test
	public void ingresarVehiculo__Full_Capacity_Rejected() {
		for (int i = 0; i < 50; i++) {
			Vehiculo v = new Vehiculo("ad595bm" + i, "POLO " + i, Vehiculo.Tipo.AUTO);
			estacionamiento.ingresarVehiculo("42856378" + i, "Tobias" + i, v);
		}
		Vehiculo auto = new Vehiculo("ad595bm", "POLO", Vehiculo.Tipo.AUTO);
		assertFalse(estacionamiento.ingresarVehiculo("42856378", "Tobias", auto));

	}

	@Test
	public void ingresarVehiculo__Same_Patente_Rejected() {
		Vehiculo v1 = new Vehiculo("ad595bm", "POLO", Vehiculo.Tipo.AUTO);
		Vehiculo v2 = new Vehiculo("ad595bm", "POLO", Vehiculo.Tipo.AUTO);

		assertTrue(estacionamiento.ingresarVehiculo("42856378", "Tobias", v1));
		assertFalse(estacionamiento.ingresarVehiculo("42856378", "Tobias", v2));

	}

	@Test
	public void retirarVehiculo__Succesful() throws Exception {
		Vehiculo auto = new Vehiculo("ad595bm", "POLO", Vehiculo.Tipo.AUTO);
		estacionamiento.ingresarVehiculo("42856378", "Tobias", auto);

		Ticket ticket = estacionamiento.retirarVehiculo("ad595bm");

		assertNotNull(ticket);
		assertNotNull(ticket.getHoraSalida());
		assertEquals("ad595bm", ticket.getVehiculo().getPatente());
		assertEquals(0, estacionamiento.listarVehiculosEstacionados().size());
	}

	@Test
	public void retirarVehiculo__Not_Found() {
		Exception exception = assertThrows(Exception.class, () -> {
			estacionamiento.retirarVehiculo("ad595bm");
		});

		assertEquals("Vehiculo no encontrado", exception.getMessage());
	}

}