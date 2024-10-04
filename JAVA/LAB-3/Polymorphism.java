// interface Shape {
//     float calculateArea();
//     void display();
// }

// class Circle implements Shape {
//     float radius;

//     public Circle(float radius) {
//         this.radius = radius;
//     }

//     public float calculateArea() {
//         return (float) (Math.PI * radius * radius);
//     }

//     public void display() {
//         System.out.println("Area of Circle: " + calculateArea());
//     }
// }

// class Rectangle implements Shape {
//     float length, width;

//     public Rectangle(float length, float width) {
//         this.length = length;
//         this.width = width;
//     }

//     public float calculateArea() {
//         return length * width;
//     }

//     public void display() {
//         System.out.println("Area of Rectangle: " + calculateArea());
//     }
// }

// class Triangle implements Shape {
//     float base, height;

//     public Triangle(float base, float height) {
//         this.base = base;
//         this.height = height;
//     }

//     public float calculateArea() {
//         return (float) (0.5 * base * height);
//     }

//     public void display() {
//         System.out.println("Area of Triangle: " + calculateArea());
//     }
// }

// public class Polymorphism {
//     public static void main(String[] args) {

//         Shape[] shapes = { new Circle(10), new Rectangle(10, 20), new Triangle(5, 10) };

//         for (Shape x : shapes) {
//             x.display();
//         }
//     }
// }
