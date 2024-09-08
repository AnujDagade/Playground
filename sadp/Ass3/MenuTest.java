import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// MenuItem Class
class MenuItem {
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ", " + price + " -- " + description;
    }
}

// Menu Interface
interface Menu {
    Iterator<MenuItem> createIterator();
}

// BreakfastMenu Class
class BreakfastMenu implements Menu {
    private List<MenuItem> menuItems;

    public BreakfastMenu() {
        menuItems = new ArrayList<>();
        addItem("Pancakes", "Pancakes with syrup", true, 2.99);
        addItem("Waffles", "Waffles with blueberries", true, 3.49);
        addItem("Omelette", "Omelette with cheese and ham", false, 4.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}

// LunchMenu Class
class LunchMenu implements Menu {
    private MenuItem[] menuItems;
    private int numberOfItems = 0;

    public LunchMenu() {
        menuItems = new MenuItem[6];
        addItem("Vegetarian BLT", "Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= menuItems.length) {
            System.err.println("Menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new ArrayIterator(menuItems);
    }
}

// DinnerMenu Class
class DinnerMenu implements Menu {
    private List<MenuItem> menuItems;

    public DinnerMenu() {
        menuItems = new ArrayList<>();
        addItem("Pasta", "Spaghetti with Marinara Sauce", true, 3.89);
        addItem("Steak", "Grilled steak with vegetables", false, 12.99);
        addItem("Salad", "A mix of fresh vegetables", true, 5.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}

// ArrayIterator Class
class ArrayIterator implements Iterator<MenuItem> {
    private MenuItem[] items;
    private int position = 0;

    public ArrayIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }
}

// Waitress Class (Client)
class Waitress {
    private Menu breakfastMenu;
    private Menu lunchMenu;
    private Menu dinnerMenu;

    public Waitress(Menu breakfastMenu, Menu lunchMenu, Menu dinnerMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
        this.dinnerMenu = dinnerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> breakfastIterator = breakfastMenu.createIterator();
        Iterator<MenuItem> lunchIterator = lunchMenu.createIterator();
        Iterator<MenuItem> dinnerIterator = dinnerMenu.createIterator();

        System.out.println("MENU\n----\nBREAKFAST");
        printMenu(breakfastIterator);
        System.out.println("\nLUNCH");
        printMenu(lunchIterator);
        System.out.println("\nDINNER");
        printMenu(dinnerIterator);
    }

    private void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem);
        }
    }
}

// Main Class
public class MenuTest {
    public static void main(String[] args) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu = new LunchMenu();
        DinnerMenu dinnerMenu = new DinnerMenu();

        Waitress waitress = new Waitress(breakfastMenu, lunchMenu, dinnerMenu);
        waitress.printMenu();
    }
}