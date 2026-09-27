package m01.inventory;
import m01.inventory.model.Item;
import m01.inventory.service.InventoryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService(5, 50.0); // 5 слотов, макс вес 50кг
        Scanner scanner = new Scanner(System.in);

        Item apple = new Item("Яблоко", 0.2, 10);
        Item sword = new Item("Меч", 5.0, 1);
        Item potion = new Item("Зелье", 0.5, 5);
        
        System.out.println("Введите 'help' для вывода списка команд.");

        while (true) {
            inventory.printInventory();
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "add":
                    if (parts.length < 3) break;
                    Item chosen = parts[1].equalsIgnoreCase("яблоко") ? apple : parts[1].equalsIgnoreCase("меч") ? sword : potion;
                    inventory.addItem(chosen, Integer.parseInt(parts[2]));
                    break;
                case "remove":
                    if (parts.length < 3) break;
                    inventory.removeItem(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "move":
                    if (parts.length < 3) break;
                    inventory.moveItem(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "search":
                    if (parts.length < 2) break;
                    inventory.searchByName(parts[1]);
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Выход из игры.");
                    return;
                default:
                    System.out.println("Неизвестная команда. Введите 'help'.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("\nДоступные команды:");
        System.out.println("  add <яблоко|меч|зелье> <кол-во>  - Добавить предмет");
        System.out.println("  remove <слот> <кол-во>          - Удалить из слота");
        System.out.println("  move <из_слота> <в_слот>         - Переместить предмет или объединить стек");
        System.out.println("  search <название>               - Найти предмет по имени");
        System.out.println("  exit                            - Выйти");
    }
}
