package Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Calvin on 3/13/2017.
 */
public final class Transactions {
    private static HashMap<String, ArrayList<Transaction>> transactionHash = new HashMap<>();
    // visitorId, borrowed books list


    public static String borrow(String visitorId, ArrayList<Long> isbns) {
        if (!Visitors.getVisitorHash().containsKey(visitorId)) {
            return("The visitor ID does not match a registered visitor.");
        } else if (transactionHash.get(visitorId).size() + isbns.size() > 5) {
            return("The borrow request would cause the visitor to exceed 5 borrowed books.");
        }
        // else if The visitor owes the library a fine for books that were previously not returned or returned late.
        else {
            for (Long isbn : isbns) {
                Book book = Books.getBookHash().get(isbn);
                if (book == null) {
                    return("One or more of the book IDs specified do not match the IDs for the most" +
                            " recent library book search.");
                } else {
                    if (book.getNumAvailableCopies() < 1) {
                        return("There no available copies of this book");
                    } else {
                        book.setNumAvailableCopies(book.getNumAvailableCopies() - 1);
                        LocalDate dueDate = Time.getDate().plusDays(7);
                        Transaction transaction = new Transaction(isbn, Time.getDate(), dueDate);
                        if (transactionHash.get(visitorId) == null) {
                            ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();
                            transactionsList.add(transaction);
                            transactionHash.put(visitorId, transactionsList);
                        } else {
                            transactionHash.get(visitorId).add(transaction);
                        }
                        return("Books have been borrowed and are due " +
                                dueDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                    }
                }
            }
        }
        return null;
    }

    public static String findBooks(Integer visitorId) {
        if (transactionHash.containsKey(visitorId)) {
            ArrayList<Transaction> transactions = transactionHash.get(visitorId);
            String result = transactions.size() + " books borrowed";
            int tempId = 0;
            for (Transaction transaction : transactions) {
                tempId++;
                Book book = Books.getBookHash().get(transaction.getIsbn());
                result += (tempId + " " + book.getIsbn() + " " + book.getTitle() + " " +
                        transaction.getDateBorrowed().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "\n");
            }
            return result;
        } else {
            return("The visitor ID does not match a registered visitor.");
        }
    }

    public static String _return(Integer visitorId, ArrayList<Long> isbns) {
        if (!Visitors.getVisitorHash().containsKey(visitorId)) {
            return("The visitor ID does not match a registered visitor.");
        } else {
            for (Long isbn : isbns) {
                Book book = Books.getBookHash().get(isbn);
                if (book == null) {
                    return("One or more of the book IDs are not valid.");
                } else {
                    if (transactionHash.containsValue(isbn)) {
                        book.setNumAvailableCopies(book.getNumAvailableCopies() + 1);
                        transactionHash.get(visitorId).remove(book);
                        return("FINE TO BE ADDED " + book.getIsbn());
                    } else {
                        return("One or more of the books are not currently borrowed the visitor.");
                    }
                }
            }
        }
        return null;
    }

    public static HashMap<String, ArrayList<Transaction>> getTransactionHash(){
        return transactionHash;
    }
}
