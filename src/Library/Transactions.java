package Library;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Calvin on 3/13/2017.
 */
public final class Transactions {
    private static HashMap<Integer, ArrayList<Transaction>> map = new HashMap<>(); // visitorId, borrowed books list
    public static final File FILE = new File("transactions.ser");

    public Transactions(){
        map = new HashMap<>();
        try {
            if (!FILE.createNewFile()) {
                load();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Helper function to check if all transactions of a visitor are paid off
     */
    public static boolean checkCanBorrow(Integer visitorId) {
        ArrayList<Transaction> transactions = map.get(visitorId);
        if(transactions != null){
        for (Transaction transaction : transactions) {
                Fine fine = transaction.getFine();
                if (fine != null) {
                    if (fine.getBalance() > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String borrow(Integer visitorId, ArrayList<Long> isbns) {
        LocalDate dueDate = null;
        if (!Visitors.getMap().containsKey(visitorId)) {
            return("The visitor ID does not match a registered visitor.");
        } else if (map.get(visitorId) != null &&  map.get(visitorId).size() + isbns.size() > 5) {
            return("The borrow request would cause the visitor to exceed 5 borrowed books.");
        }
        else if(!checkCanBorrow(visitorId)){
            return("The visitor owes the library a fine for books that were previously not returned or returned late.");
        }
        else {
            for (Long isbn : isbns) {
                Book book = Books.getBookHash().get(isbn);
                if (book == null) {
                    return("One or more of the book IDs specified do not match the IDs for the most" +
                            " recent library book search.");
                } else {
                    if (book.getNumAvailableCopies() < 1) {
                        return("There no available copies of one or more books");
                    } else {
                        book.setNumAvailableCopies(book.getNumAvailableCopies() - 1);
                        dueDate = Time.getDate().plusDays(7);
                        Transaction transaction = new Transaction(isbn, Time.getDate(), dueDate);
                        if (map.get(visitorId) == null) {
                            ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();
                            transactionsList.add(transaction);
                            map.put(visitorId, transactionsList);
                        } else {
                            map.get(visitorId).add(transaction);
                        }
                    }
                }
            }
        }
        return("Books have been borrowed and are due " +
                dueDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    public static String findBooks(Integer visitorId) {
        if(!Visitors.getMap().containsKey(visitorId)){
            return("The visitor ID does not match a registered visitor.");
        }
        else if(map.get(visitorId) == null){
            return "0";
        }
        else{
            ArrayList<Transaction> transactions = map.get(visitorId);
            String result = transactions.size() + " books borrowed\n";
            int tempId = 0;
            for (Transaction transaction : transactions) {
                tempId++;
                Book book = Books.getBookHash().get(transaction.getIsbn());
                result += (tempId + "," + book.getIsbn() + "," + book.getTitle() + "," +
                        transaction.getDateBorrowed().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "\n");
            }
            return result;
        }
    }

    public static String _return(Integer visitorId, ArrayList<Long> isbns) {
        String response = "Error in return function api";
        if (!Visitors.getMap().containsKey(visitorId)) {
            return("The visitor ID does not match a registered visitor.");
        } else {
            for (Long isbn : isbns) {
                Book book = Books.getBookHash().get(isbn);
                if (book == null) {
                    return("One or more of the book IDs are not valid.");
                } else {
                    if (map.containsKey(visitorId) && map.get(visitorId) != null){
                        ArrayList<Transaction> transactions = map.get(visitorId);
                        for (Transaction transaction : transactions){
                            if(transaction.getReturnedDate() == null && transaction.getIsbn().equals(isbn) ){
                                //Set the returned date, get fee, increase amount of available copies and set the retuned date
                                transaction.setReturnedDate(Time.getDate());
                                Integer fee = transaction.getFine().getBalance();
                                book.setNumAvailableCopies(book.getNumAvailableCopies() + 1);
                                if (fee > 0) {
                                    return ("Book: " + isbn + " is overdue. Cost = $" + fee + "\n");
                                } else {
                                    return ("Success");
                                }
                            }
                        }


                    } else {
                        return("The visitor currently does not have any borrowed books.");
                    }
                }
            }
        }
        return response;
    }

    public static String pay(Integer visitorId, Integer amount) {
        ArrayList<Transaction> transactions = map.get(visitorId);
        ArrayList<Fine> fines = new ArrayList<>();
        Integer totalBalance = 0;

        if (!Visitors.getMap().containsKey(visitorId)) {
            return ("The visitor ID does not match a registered visitor.");
        } else if (amount < 0) {
            return ("The specified amount is negative.");
        }
        // Add fines that don't have a zero balance to the array list
        for (Transaction transaction : transactions) {
                Fine fine = transaction.getFine();
                if(fine != null){
                    if(fine.getBalance() > 0){
                        fines.add(fine);

                    }
                }
            }
        for (Fine fine : fines) {
            totalBalance += fine.getBalance();
            if (totalBalance > amount) {
                return ("The specified amount exceeds the balance.");
            }
        }
        // Pay fines by the smallest ones first
        Collections.sort(fines);
        for (int i = 0; i < fines.size(); i++) {
            Integer balance = fines.get(i).getBalance();
            if (amount > balance) {
                amount -= balance;
            }
            fines.get(i).pay(amount);
        }
        return ("success," + totalBalance);
    }

    public static void save() {
        try
        {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<Integer, ArrayList<Transaction>>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }


    public static HashMap<Integer, ArrayList<Transaction>> getMap(){
        return map;
    }
}
