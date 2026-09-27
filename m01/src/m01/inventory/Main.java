package m01.inventory;

import m01.inventory.model.Item;
import m01.inventory.service.InventoryService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService(5, 50.0);
        Scanner scanner = new Scanner(System.in);

        Item apple = new Item("Яблоко", 0.2, 10);
        Item sword = new Item("Меч", 5.0, 1);
        Item potion = new Item("Зелье", 0.5, 5);

        System.out.println("Добро пожаловать в текстовый инвентарь RPG!");
        System.out.println("Введите 'help' для вывода списка команд.");

        while (true) {
            inventory.printInventory();
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "add":
                        if (parts.length < 3) {
                            System.out.println("Ошибка: Неверный формат. Используйте: add <предмет> <кол-во>");
                            break;
                        }
                        Item chosen = findItem(parts[1], apple, sword, potion);
                        if (chosen == null) {
                            System.out.println("Ошибка: Предмет '" + parts[1] + "' не существует в базе данных!");
                            break;
                        }
                        int addAmount = Integer.parseInt(parts[2]);
                        if (inventory.addItem(chosen, addAmount)) {
                            System.out.println("Успешно: Предмет(ы) добавлен(ы).");
                        } else {
                            System.out.println("Ошибка: Не удалось добавить предмет (превышен вес или нет свободных слотов).");
                        }
                        break;

                    case "remove":
                        if (parts.length < 3) {
                            System.out.println("Ошибка: Неверный формат. Используйте: remove <слот> <кол-во>");
                            break;
                        }
                        int removeSlot = Integer.parseInt(parts[1]);
                        int removeAmount = Integer.parseInt(parts[2]);
                        if (inventory.removeItem(removeSlot, removeAmount)) {
                            System.out.println("Успешно: Предмет(ы) удален(ы).");
                        } else {
                            System.out.println("Ошибка: Не удалось удалить предметы (неверный слот, отрицательное кол-во или слот пуст).");
                        }
                        break;

                    case "move":
                        if (parts.length < 3) {
                            System.out.println("Ошибка: Неверный формат. Используйте: move <из_слота> <в_слот>");
                            break;
                        }
                        int fromSlot = Integer.parseInt(parts[1]);
                        int toSlot = Integer.parseInt(parts[2]);
                        if (inventory.moveItem(fromSlot, toSlot)) {
                            System.out.println("Успешно: Предмет перемещен / стеки объединены.");
                        } else {
                            System.out.println("Ошибка: Перемещение невозможно (неверные слоты, слот назначения занят другим типом или стек полон).");
                        }
                        break;

                    case "search":
                        if (parts.length < 2) {
                            System.out.println("Ошибка: Введите поисковый запрос.");
                            break;
                        }
                        System.out.println("Результаты поиска для '" + parts[1] + "':");
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
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: Параметры количества и номеров слотов должны быть целыми числами!");
            }
        }
    }

    private static Item findItem(String name, Item apple, Item sword, Item potion) {
        if (name.equalsIgnoreCase("яблоко")) return apple;
        if (name.equalsIgnoreCase("меч")) return sword;
        if (name.equalsIgnoreCase("зелье")) return potion;
        return null;
    }

    private static void printHelp() {
        System.out.println("\nДоступные команды:");
        System.out.println("  add <яблоко|меч|зелье> <кол-во>  - Добавить предмет");
        System.out.println("  remove <слот> <кол-во>          - Удалить из слота");
        System.out.println("  move <из_слота> <в_слот>         - Переместить или объединить стек");
        System.out.println("  search <название>               - Найти предмет");
        System.out.println("  exit                            - Выйти");
    }
}
