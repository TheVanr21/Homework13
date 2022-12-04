package ru.netology.manager;

import org.junit.jupiter.api.*;
import ru.netology.comparator.TicketByTravelTimeAscComparator;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(1, 11000, "LED", "SVO", 85);
    Ticket ticket2 = new Ticket(2, 25000, "LED", "DYU", 310);
    Ticket ticket3 = new Ticket(3, 21000, "LED", "DYU", 440);
    Ticket ticket4 = new Ticket(4, 36000, "LED", "IST", 240);
    Ticket ticket5 = new Ticket(5, 10500, "LED", "IST", 360);
    Ticket ticket6 = new Ticket(6, 3500, "SVO", "LED", 95);
    Ticket ticket7 = new Ticket(7, 4200, "LED", "SVO", 345);
    Ticket ticket8 = new Ticket(8, 3500, "SVO", "LED", 185);

    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

    @Test
    public void shouldAdd() {
        manager.add(ticket1);
        manager.add(ticket7);

        Ticket[] expected = {ticket1, ticket7};
        Ticket[] actual = repository.getAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Nested
    public class WithInitiallyAddedTickets {

        @BeforeEach
        public void init() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            manager.add(ticket7);
            manager.add(ticket8);
        }

        @Test
        public void shouldFindTicketsPriceSort() {
            Ticket[] expected = {ticket7, ticket1};
            Ticket[] actual = manager.findAll("LED", "SVO");

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldNotFindTicketsPriceSort() {
            Ticket[] expected = {};
            Ticket[] actual = manager.findAll("SVO", "DYU");

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFindTicketsTravelTimeSort() {
            Ticket[] expected = {ticket1, ticket7};
            Ticket[] actual = manager.findAll("LED", "SVO", comparator);

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldNotFindTicketsTravelTimeSort() {
            Ticket[] expected = {};
            Ticket[] actual = manager.findAll("SVO", "DYU", comparator);

            Assertions.assertArrayEquals(expected, actual);
        }
    }
}
