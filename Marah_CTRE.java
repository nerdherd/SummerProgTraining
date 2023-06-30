import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.lang.Math;

public class Marah_CTRE {
    // initialize variables
    Double money = 1000.0;
    Integer productQuantity = 0;
    String newProduct = "";
    Scanner scanner = new Scanner(System.in);
    String done = "";

    // create lists with the items and prices
    List<String> items = new ArrayList<String>(); {
        items.add("Falcon500");
        items.add("Falcon Motor Shaft");
        items.add("CANcoder");
        items.add("CANivore");
        items.add("PDP");
        items.add("Battery Cable");
        items.add("Talon Tach");
        }
    List<Double> prices = new ArrayList<Double>(); {
        prices.add(219.99);
        prices.add(19.99);
        prices.add(69.99);
        prices.add(299.99);
        prices.add(204.99);
        prices.add(15.99);
        prices.add(13.99);
        }

    // create empty lists for the items and quantities bought
    List<String> boughtItems = new ArrayList<String>(); {
    }
    List<Integer> boughtQuantities = new ArrayList<Integer>(); {
    }

    public static void main(String[] args) {
        Marah_CTRE instance = new Marah_CTRE();
        System.out.println("\n" + "Below are the products that you can buy from CTRE:" + "\n");
        // for loop that iterates through the items and prices lists to print all items and corresponding prices
        for (int i = 0; i < 5; i++) {
            String product = instance.items.get(i) + " $" + (instance.prices.get(i)).toString();
            System.out.println(product);
        }
        instance.shopping();
    }

    // method that prints the amount of money left
    public void moneyLeft() {
        System.out.println("\n" + "You currently have $" + money + " left.");
    }

    // method that takes user input for products and quantities to add to cart
    public void shopping() {
        System.out.println("\n" + "What product would you like to add to your cart?");
        newProduct = "";
        while (newProduct.equals("")){
            try 
            {
                newProduct = scanner.nextLine();
                // ensure that input is one of the products
                if (newProduct.equals(items.get(0)) || newProduct.equals(items.get(1)) || newProduct.equals(items.get(2)) || newProduct.equals(items.get(3)) || newProduct.equals(items.get(4)) || newProduct.equals(items.get(5)) || newProduct.equals(items.get(6))){
                    System.out.println("\n" + "How many " + newProduct + "s " + "would you like to add to your cart?");
                }
                else {
                    System.out.println("Please enter a valid product.");
                    shopping();
                }
                
                try
                {
                    productQuantity = scanner.nextInt();
                    done = scanner.nextLine();
                    // ensure that quantity is a positive integer
                    while (productQuantity < 1){
                        System.out.println("\n" + "Please input a positive integer.");
                        productQuantity = scanner.nextInt();
                        done = scanner.nextLine();
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("\n" + "Sorry, that is not a valid answer.");
                    shopping();

                }
                // call the calculate function with the parameter corresponding to the chosen item
                if (newProduct.equals(items.get(0))){
                    calculate(0);
                }
                else if (newProduct.equals(items.get(1))) {
                    calculate(1);
                }
                else if (newProduct.equals(items.get(2))) {
                    calculate(2);
                }
                else if (newProduct.equals(items.get(3))) {
                    calculate(3);
                }
                else if (newProduct.equals(items.get(4))) {
                    calculate(4);
                }
                else if (newProduct.equals(items.get(5))) {
                    calculate(5);
                }
                else if (newProduct.equals(items.get(6))) {
                    calculate(6);
                }
                else {
                    System.out.println("Error");
                }
            } 
            catch(Exception ex)
            {
                System.out.println("Error");
            }
        }

    }

    // method that calculates money left and determines if the item is too expensive
    public void calculate(Integer x){
        Double totalPrice = (productQuantity * prices.get(x));
        money = money - totalPrice;
        // check if the money left would be >=0 after purchase
        if (money < 0.0){
            System.out.println("\n" + "Sorry, you don't have enough to buy this product.");
            money = money + totalPrice;
        }
        else{
            System.out.println("\n" + productQuantity.toString() + " " + newProduct + "s were added to your cart.");
            boughtItems.add(items.get(x));
            boughtQuantities.add(productQuantity);
        }
        // rounds to the nearest cent
        money = Math.round(money*100.0) / 100.0;
        moneyLeft();
        // check if user has enough money left to buy the cheapest item
        if (money > 13.99) {
            areDone();
        }
        else {
            System.out.println("You no longer have enough money to buy anything else.");
            endGame();
        }

    }
     
    // method that checks if the user wants to continue
    public void areDone(){
        System.out.println("\n" + "Do you want to continue shopping? Please input Yes or No.");
        while (done.equals("")){
            try
            {
                done = scanner.nextLine();
                if (done.equals("Yes")){
                    shopping();
                }
                else if (done.equals("No")) {
                    endGame();
                }
                else{
                    done = "";
                    areDone();
                }
            }
            catch(Exception ex)
            {
                System.out.println("Invalid Entry");
            }
        }
        
    }

    // method that prints the items bought at checkout and the money spent/left
    public void endGame() {
        System.out.println("\n" + "Below are the items you purchased:");
        for (int i = 0; i < boughtItems.size(); i++) {
            String product = "x" + (boughtQuantities.get(i)).toString() + " " + boughtItems.get(i);
            System.out.println(product);
        }
        Double moneySpent = 1000.0 - money;
        System.out.println("\n" + "You spent $"+ moneySpent + ". You have $" + money + " left.");
    }
}
