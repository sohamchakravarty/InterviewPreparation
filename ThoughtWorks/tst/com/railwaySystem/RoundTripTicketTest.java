package com.railwaySystem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.railwaySystem.ticket.RoundTripTicket;
import com.railwaySystem.utils.Station;
import com.railwaySystem.utils.Train;

public class RoundTripTicketTest {

	private static RoundTripTicket ticket;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Train train = new Train();
		Station src = new Station("Kodambakkam");
		Station dest = new Station("Guindy");
		ticket = new RoundTripTicket(train, src, dest);
	}

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.railwaySystem.ticket.tw.journey.AbstractTicket#bookTicket()}.
	 */
	@Test
	public void testBookTicket() {
		assertEquals(17.5, ticket.getFareDoubleValue(), 0.0);
	}

}