package com.manideepla.bookerang.handlers;

import com.manideepla.bookerang.models.AddBookRequest;
import com.manideepla.bookerang.models.Book;
import com.manideepla.bookerang.models.UserCopy;
import com.manideepla.bookerang.models.UserCopyItem;
import com.manideepla.bookerang.models.NearbyBookItem;
import com.manideepla.bookerang.repositories.AuthorRepository;
import com.manideepla.bookerang.repositories.BookRepository;
import com.manideepla.bookerang.repositories.CopyRepository;
import com.manideepla.bookerang.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;


@Service
public class BookHandler {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    UserRepository userRepository;

    public Mono<UUID> addBook(AddBookRequest addBookRequest, String username) {
        return Mono.just(addBookRequest.author())
                .flatMap(authorName -> authorRepository.saveOrGetAuthor(authorName))
                .map(author -> new Book(addBookRequest.title(), author.id(), null))
                .flatMap(book -> bookRepository.saveOrGetBook(book.title(), book.author()))
                .map(savedBook -> new UserCopy(null, savedBook.id(), username))
                .flatMap(userCopy -> copyRepository.save(userCopy))
                .map(UserCopy::id);
    }

    public Mono<List<UserCopyItem>> getBooksOfAUser(String username) {
        return copyRepository.findAllByOwner(username)
                .flatMap(userCopy -> bookRepository.findById(userCopy.bookId())
                                              .map(book -> new UserCopyItem(userCopy.id().toString(), book.title())))
                .collectList();

    }

    public Mono<List<NearbyBookItem>> getBooksNearby(int radius) {
        Mono<String> username = ReactiveSecurityContextHolder.getContext().map(c -> c.getAuthentication().getName());
        return
                username.flatMapMany(u -> userRepository.findUsersWithinDistance(radius, u))
                        .flatMap(x -> getBooksOfAUser(x.username())
                                                    .flatMapMany(Flux::fromIterable)
                                                    .map(userCopyItem -> new NearbyBookItem(userCopyItem.id(),
                                                                                                         userCopyItem.title(),
                                                                                                         x.username(),
                                                                                                         x.distance())))
                        .collectList();

    }
}
