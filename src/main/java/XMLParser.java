import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.ArrayList;

class Book {
    String author;
    String title;
    String genre;
    double price;
    String publishDate;
    String description;

    public Book(String author, String title, String genre, double price, String description, String publishDate) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.description = description;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Author: " + author + ", Title: " + title + ", Genre: " + genre +
                ", Price: " + price + ", Publish Date: " + publishDate + ", Description: " + description;
    }
}

public class XMLParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("book");

            ArrayList<Book> books = new ArrayList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    double price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());
                    String publishDate = eElement.getElementsByTagName("publish_date").item(0).getTextContent();
                    int year = Integer.parseInt(publishDate.split("-")[0]);

                    if (price > 10 && year > 2005) {
                        String author = eElement.getElementsByTagName("author").item(0).getTextContent();
                        String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                        String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
                        String description = eElement.getElementsByTagName("description").item(0).getTextContent();

                        Book book = new Book(author, title, genre, price, publishDate, description);
                        books.add(book);
                    }
                }
            }

            for (Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
