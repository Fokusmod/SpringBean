package ru.geekbrains.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class ProductRepository {
    private String[] arrayTitles = new String[14];
    private List<Integer> prices;
    private List<String> titles;
    private List<Product> catalog;
    int count = 5;

    @Autowired
    public ProductRepository() {
        this.catalog = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.titles = new ArrayList<>();
        initializeArrayTitles(arrayTitles);
        generateTitle();
        generatePrice();
    }

    private void initializeArrayTitles(String[] array) {
        array[0] = "Apple";
        array[1] = "Banana";
        array[2] = "Orange";
        array[3] = "Apricot";
        array[4] = "Avocado";
        array[5] = "Pineapple";
        array[6] = "Bergamot";
        array[7] = "Durian";
        array[8] = "Grapefruit";
        array[9] = "Kiwi";
        array[10] = "Lime";
        array[11] = "Melon";
        array[12] = "Papaya";
        array[13] = "Pear";
    }

    @PostConstruct
    public void addFiveProduct() {
        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            products[i] = new Product(i + 1, titles.get(i), prices.get(i));
            catalog.add(products[i]);
        }
    }

    public void generatePrice() {

        int min = 100;
        int max = 300;
        int diff = max - min;

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int price = random.nextInt(diff + 1);
            price += min;
            prices.add(price);
        }
    }

    public void generateTitle() {
        int count = 0;
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        while (count != 5) {
            int number = random.nextInt(arrayTitles.length);
            if (!numbers.contains(number)) {
                numbers.add(number);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            titles.add(arrayTitles[numbers.get(i)]);
        }


    }

    public void getAllProduct() {

        String string1 = "<<<Product catalog>>>";
        System.out.println(string1);

        for (Product product : catalog) {
            System.out.print("ID = " + product.getId() + " ");
            System.out.println(product.getTitle());
        }
        System.out.println();
    }

    public Product getProductById(int id) {
        for (Product product : catalog) {
            if (product.getId() == id) {
                return product;
            }
        }
        System.out.println("Product not found");
        return null;
    }

}
