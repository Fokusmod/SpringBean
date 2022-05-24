package ru.geekbrains.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.homework.Config.ApplicationConfig;
import java.util.Scanner;



public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Cart cart;

    static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    public static void main(String[] args) {

        start();
    }

    public static void start() {

        cart = context.getBean(Cart.class);
        System.out.println("<<<Добро пожаловать в продуктовый магазин>>>");
        System.out.println("-Для просмотра каталога товаров введите -view- например: view");
        System.out.println("-Для добавления товара в корзину введите id например: add 5");
        System.out.println("-Для удаления товара из корзины введите id например: del 4");
        System.out.println("-Чтобы войти в корзину введите -cart-");
        System.out.println("-Для создания новой корзины введите -create-");
        System.out.println("-Для выхода из продуктового магазина введите /end");




        while (true) {
            String request = scanner.nextLine().toLowerCase().trim();

            if (request.equals("view")) {
                ProductRepository repository = context.getBean(ProductRepository.class);
                repository.getAllProduct();
            }


            if (request.contains("add")) {
                String s = request.substring(3);
                if (s.contains(" ")) {
                    s = s.substring(1);
                }

                try {
                    int productId = Integer.parseInt(s);
                    cart.addProduct(productId);
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат добавления товара в корзину");
                }
            }

            if (request.contains("del")) {
                String s = request.substring(3);
                if (s.contains(" ")) {
                    s = s.substring(1);
                }
                try {
                    int productId = Integer.parseInt(s);
                    cart.deleteProduct(productId);
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат удаления товара из корзины");
                }
            }

            if (request.equals("cart")) {
                cart.listInCart();
            }

            if (request.equals("create")) {
                System.out.println("<<<Новая корзина создана>>>");
                cart = context.getBean(Cart.class);
            }

            if (request.equals("/end")) {
                break;
            }
        }
    }
}


