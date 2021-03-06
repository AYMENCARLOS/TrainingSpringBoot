package guru.springframework.demo.bootstrap;

import guru.springframework.demo.model.Author;
import guru.springframework.demo.model.Book;
import guru.springframework.demo.model.Publisher;
import guru.springframework.demo.repositories.AuthorRepository;
import guru.springframework.demo.repositories.BookRepository;
import guru.springframework.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DemoContextListener  implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DemoContextListener(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){


        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisherRepository.save(publisher);

        // Eric
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design", "51234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444",publisher);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }

}
