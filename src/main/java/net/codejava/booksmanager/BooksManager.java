package net.codejava.booksmanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BooksManager {
    public static void main(String[] args) {
        // Step a: Create an EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookUnit");

        // Step b: Create an EntityManager
        EntityManager em = emf.createEntityManager();

        // Step c: Begin a transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Step d1: Persist a Book object
            Book book = new Book();
            book.setTitle("Effective Java");
            book.setAuthor("Joshua Bloch");
            book.setPrice(45.50f);
            em.persist(book);
            System.out.println("Book persisted: " + book);

            // Step d2: Update the Book object
            book.setPrice(50.00f);  // Changing the price
            em.merge(book);
            System.out.println("Book updated: " + book);

            // Step d3: Find the Book object by its ID
            Book foundBook = em.find(Book.class, book.getBookId());
            System.out.println("Book found: " + foundBook);



            // Step e: Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // Roll back in case of an error
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            // Step f: Close the EntityManager
            em.close();
        }

        // Step g: Close the EntityManagerFactory
        emf.close();
}

}
