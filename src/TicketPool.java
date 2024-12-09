import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int capacity;

    public TicketPool(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (tickets.size() >= capacity) {
            wait();
        }
        tickets.add(ticket);
        System.out.println("Added " + ticket);
        notifyAll();
    }

    public synchronized Ticket removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait();
        }
        Ticket ticket = tickets.poll();
        System.out.println("Removed " + ticket);
        notifyAll();
        return ticket;
    }
}

