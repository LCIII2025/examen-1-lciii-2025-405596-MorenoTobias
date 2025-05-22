package org.example.parking;

import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;

public class TicketTest {

	private final Cliente cliente = new Cliente("42856378","Tobias");
	private Ticket ticket;

	@Test
	public void test_CalcularPrecio__Succesful_Less_Than_One_Hour_CAR() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.AUTO);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(5);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(100.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Succesful_Less_Than_One_Hour_SUV() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.SUV);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(5);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(130.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Succesful_Less_Than_One_Hour_PICKUP() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.PICKUP);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(5);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(180.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Succesful_Two_Hours_CAR() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.AUTO);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(65);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(200.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Succesful_Two_Hours_SUV() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.SUV);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(65);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(260.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Succesful_Two_Hours_PICKUP() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.PICKUP);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.plusMinutes(65);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(360.0,ticket.calcularPrecio());
	}

	@Test
	public void test_CalcularPrecio__Error_Salida_Lower_Than_Ingreso() {
		Vehiculo vehiculo = new Vehiculo("ad595bm","Polo",Vehiculo.Tipo.PICKUP);
		LocalDateTime entrada = LocalDateTime.now();
		LocalDateTime salida = entrada.minusMinutes(65);
		ticket = new Ticket(cliente, vehiculo, entrada, salida);

		assertEquals(0.0,ticket.calcularPrecio());
	}
}
