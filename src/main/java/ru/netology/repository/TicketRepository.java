package ru.netology.repository;

import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public Ticket[] getAll() {
        return tickets;
    }

    public void removeById(int id) {
        if (findById(id) != null) {
            Ticket[] tmp = new Ticket[tickets.length - 1];
            int indexForCopy = 0;
            for (Ticket ticket : tickets) {
                if (ticket.getId() != id) {
                    tmp[indexForCopy] = ticket;
                    indexForCopy++;
                }
            }
            tickets = tmp;
        }
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void save(Ticket newProduct) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        tmp[tmp.length - 1] = newProduct;
        tickets = tmp;
    }
}
