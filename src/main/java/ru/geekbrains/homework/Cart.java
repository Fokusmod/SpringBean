package ru.geekbrains.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {

    private List<Product> cart;
    private ProductRepository catalog;

    @Autowired
    public Cart() {
        this.cart = new ArrayList<>();
    }

    public void addProduct(int id) {
        catalog = Main.context.getBean(ProductRepository.class);
        if (id > catalog.count) {
            System.out.println("Товара с таким id не существует");
        } else {
            cart.add(catalog.getProductById(id));
            System.out.println("<<<Товар " + catalog.getProductById(id).getTitle() + " был добавлен в корзину>>>");
        }
    }

    public void deleteProduct(int id) {
        if (cart.size()== 0) {
            System.out.println("<<<В корзине ничего нет>>>");
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == id) {
                cart.remove(cart.get(i));
                System.out.println("<<<Товар " + cart.get(i).getTitle() + " был удален из корзины>>>");
                return;
            }
        }
        System.out.println("В вашей корзине нет товара с таким id");
    }

    public void listInCart() {
        if (cart.size() != 0) {
            System.out.println("В корзине ----->");
            for (Product product : cart) {
                System.out.print("ID = " + product.getId() + " ");
                System.out.println(product.getTitle() + " ");
            }
            totalPrice();
        } else {
            System.out.println("<<<Корзина пуста>>>");
        }
    }

    private void totalPrice() {
        int count = 0;
        for (Product product : cart) {
            count += product.getPrice();
        }
        System.out.println("<<<Товаров к корзине на сумму: " + count + " >>>");
    }
}
