package com.juandavidsanchez.spring5webapp.bootstrap;

import com.juandavidsanchez.spring5webapp.model.Author;
import com.juandavidsanchez.spring5webapp.model.Book;
import com.juandavidsanchez.spring5webapp.model.Publisher;
import com.juandavidsanchez.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.juandavidsanchez.spring5webapp.repositories.AuthorRepository;
import com.juandavidsanchez.spring5webapp.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pubHarper = new Publisher("Harper Collins", "29 rue des Ardennes");

        Book  ddd = new Book("Domain Driven Design", "1234", pubHarper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(pubHarper);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher pubWrox = new Publisher("Wrox", "30 rue des Ardennes");
        Book noEJB = new Book("J2EE Development without EJB", "23444", pubWrox );
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(pubWrox);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }


}
