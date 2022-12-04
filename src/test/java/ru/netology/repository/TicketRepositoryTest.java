package ru.netology.repository;

import org.junit.jupiter.api.*;
import ru.netology.domain.Ticket;

import java.util.Arrays;

public class TicketRepositoryTest {

    Ticket ticket1 = new Ticket(1, 11000, "LED", "SVO", 85);
    Ticket ticket2 = new Ticket(2, 25000, "LED", "DYU", 310);
    Ticket ticket3 = new Ticket(3, 21000, "LED", "DYU", 440);
    Ticket ticket4 = new Ticket(4, 36000, "LED", "IST", 240);
    Ticket ticket5 = new Ticket(5, 10500, "LED", "IST", 360);
    Ticket ticket6 = new Ticket(6, 3500, "SVO", "LED", 95);
    Ticket ticket7 = new Ticket(7, 4200, "LED", "SVO", 345);
    Ticket ticket8 = new Ticket(8, 3500, "SVO", "LED", 185);

    TicketRepository repository = new TicketRepository();

    @Test
    public void shouldSave() {
        repository.save(ticket1);
        repository.save(ticket7);

        Ticket[] expected = {ticket1, ticket7};
        Ticket[] actual = repository.getAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Nested
    public class WithInitiallyAddedTickets {

        @BeforeEach
        public void init() {
            repository.save(ticket1);
            repository.save(ticket2);
            repository.save(ticket3);
            repository.save(ticket4);
            repository.save(ticket5);
            repository.save(ticket6);
            repository.save(ticket7);
            repository.save(ticket8);
        }

        @Test
        public void shouldFindById() {
            Ticket expected = ticket1;
            Ticket actual = repository.findById(1);

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFindByWrongId() {
            Ticket expected = null;
            Ticket actual = repository.findById(9);

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveById() {
            repository.removeById(3);

            Ticket[] expected = {ticket1, ticket2, ticket4, ticket5, ticket6, ticket7, ticket8};
            Ticket[] actual = repository.getAll();

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveByWrongId() {

            repository.removeById(9);

            Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8};
            Ticket[] actual = repository.getAll();

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void properSorting() {
            Ticket[] expected = {ticket6, ticket8, ticket7, ticket5, ticket1, ticket3, ticket2, ticket4};
            Ticket[] actual = repository.getAll();
            Arrays.sort(actual);

            Assertions.assertArrayEquals(expected, actual);
        }
    }
}
