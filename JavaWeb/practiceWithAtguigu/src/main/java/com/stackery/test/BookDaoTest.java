package com.stackery.test;

import com.stackery.dao.BookDao;
import com.stackery.dao.impl.BookDaoImpl;
import com.stackery.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        int testBook = bookDao.addBook(new Book(null, "testBook", "191125", new BigDecimal(9999), 1100000, 0, null
        ));
        System.out.println(testBook);
    }

    @Test
    public void deleteBookById() {

        int i = bookDao.deleteBookById(24);
        System.out.println(i);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"testBook", "Y4tacker", new BigDecimal(9999),1100000,0,null
        ));
    }

    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(21) );
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}