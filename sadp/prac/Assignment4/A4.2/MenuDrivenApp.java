//Provide a way to access elements of a collection sequentially without exposing its underlying structure.
import java.util.*;
// Create a MenuItem class to represent items in the menu.
class MenuItem {
    public String name;
    public String description;
    public int price;

    public MenuItem(String name,String description ,int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public String getDesc(){
		return description;    
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - "+description+" - Rs" + price;
    }
}

 //Create a Menu interface.
interface Menu {
    Iterator<MenuItem> createIterator();
}

 //Create concrete menu classes.
//It stores menuitems in an ArrayList & provides an iterator to iterate through them.
class BreakfastMenu implements Menu {
    private ArrayList<MenuItem> items;

    public BreakfastMenu() {
        items = new ArrayList<>();
        addItem("\t1.Poha","Served Hot with Lemon" ,50);
        addItem("\t2.Pancakes","Served with dark chocolate" ,100);
        addItem("\t3.Butter and Toast","Served with Jam & Sugar" ,150);
    }

    public void addItem(String name,String description ,int price) {
        MenuItem menuItem = new MenuItem(name,description ,price);
        items.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return items.iterator();
    }
}

//It implements Iterator interface & provides methods to check if there are more item(hasNext) & retrieve next item(next).
class LunchMenu implements Menu {
    private MenuItem[] items;
    private int numberOfItems;

    public LunchMenu() {
        items = new MenuItem[3];
        addItem("\t1.Paneer Sandwich","Grilled with cheese" ,249);
        addItem("\t2.Vegetable Salad","With White Sauce" ,499);
        addItem("\t3.Burger with Fries","With Cheese dip" ,599);
    }

    public void addItem(String name,String description ,int price) {
        if (numberOfItems < items.length) {
            items[numberOfItems] = new MenuItem(name, description,price);
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new LunchMenuIterator(items);
    }
}

class DinnerMenu implements Menu {
    private ArrayList<MenuItem> items;

    public DinnerMenu() {
        items = new ArrayList<>();
        addItem("\t1.Dal-Khichadi","Achaar & Papad" ,299);
        addItem("\t2.Chole-Bhature","Achaar & Lassi" ,349);
        addItem("\t3.Steak Dinner","Butter Naan & Jeera Rice",1499);
    }

    public void addItem(String name,String description ,int price) {
        MenuItem menuItem = new MenuItem(name,description ,price);
        items.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return items.iterator();
    }
}

 //Custom iterator for LunchMenu
class LunchMenuIterator implements Iterator<MenuItem> {
    public MenuItem[] items;
    private int position = 0;

    public LunchMenuIterator(MenuItem[] items) {
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

 // Create a MenuDrivenApp to demonstrate the menu and iterator.
public class MenuDrivenApp {
    public static void main(String[] args) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu = new LunchMenu();
        DinnerMenu dinnerMenu = new DinnerMenu();

		System.out.println("\n\tWadeshwar Restaurant");
        printMenu(breakfastMenu,"\tBreakfast Menu");
        printMenu(lunchMenu,"\tLunch Menu");
        printMenu(dinnerMenu,"\tDinner Menu");
    }

    public static void printMenu(Menu menu, String menuName) {
        System.out.println(menuName + ":\n");

        Iterator<MenuItem> iterator = menu.createIterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem);
        }

        System.out.println();
    }
}

