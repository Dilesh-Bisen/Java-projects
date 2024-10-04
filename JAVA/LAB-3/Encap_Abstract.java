// class Person {
//     private String name;
//     private int age;
//     private String address;

//     public Person(String name, int age, String address) {
//         this.name = name;
//         this.age = age;
//         this.address = address;
//     }

//     public String get_name() {
//         return name;
//     }

//     public int get_age() {
//         return age;
//     }

//     public String get_address() {
//         return address;
//     }

//     public void set_name(String name) {
//         this.name = name;
//     }

//     public void set_age(int age) {
//         this.age = age;
//     }

//     public void set_address(String address) {
//         this.address = address;
//     }

//     public void display() {
//         System.out.println(name);
//         System.out.println(age);
//         System.out.println(address);
//     }
// }

// abstract class Shape {
//     public abstract double calculate_area();

//     public abstract double calculate_perimeter();
// }

// class Circle extends Shape {

//     private double radius;

//     public Circle(double radius) {
//         this.radius = radius;
//     }

//     public double calculate_area() {
//         return Math.PI * radius * radius;
//     }

//     public double calculate_perimeter() {
//         return 2 * Math.PI * radius;
//     }
// }

// class Rectangle extends Shape {
//     private double length;
//     private double width;

//     public Rectangle(double length, double width) {
//         this.length = length;
//         this.width = width;
//     }

//     public double calculate_area() {
//         return length * width;
//     }

//     public double calculate_perimeter() {
//         return 2 * (length + width);
//     }
// }

// public class Encap_Abstract {
//     public static void main(String[] args) {
//         Person person1 = new Person("Dilesh Bisen", 20, "Maharashtra");
//         Person person2 = new Person("Jay Singh", 30, "Delhi");

//         person1.display();
//         person2.set_age(40);
//         person2.display();

//         Circle circle = new Circle(10.0);
//         Rectangle rectangle = new Rectangle(10.0, 20.0);

//         System.out.println("\nCircle :- ");
//         System.out.println("Area: " + circle.calculate_area() + ", Perimeter: " + circle.calculate_perimeter());
//         System.out.println("\nRectangle :- ");
//         System.out.println("Area: " + rectangle.calculate_area() + ", Perimeter: " + rectangle.calculate_perimeter());
//     }
// }
