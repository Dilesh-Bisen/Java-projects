public class lab_2 {

    public static void main(String[] args) {

        // Inialize variables
        System.out.println("\nVariables :");
        int a = 45;
        float b = 45.34f;
        double c = 56.67434;
        char d = 'z';
        String e = "Hello";
        boolean f = true;

        System.out.println("integer : " + a);
        System.out.println("float   : " + b);
        System.out.println("double  : " + c);
        System.out.println("char    : " + d);
        System.out.println("String  : " + e);
        System.out.println("boolean : " + f);

        // Data Types :
        System.out.println("\nData Types :");

        byte p = 56;
        System.out.println(p);

        int q = 546;
        System.out.println(q);

        double r = 4556.82345678;
        System.out.println(r);

        char s = 'A';
        System.out.println(s);

        String t = "Java";
        System.out.println(t);

        // Arithmetic operators
        int num1 = 41, num2 = 20;

        System.out.print("The addition of num1 and num2 is ");
        System.out.println(num1 + num2);

        System.out.print("The subtraction of num1 and num2 is ");
        System.out.println(num1 - num2);

        System.out.print("The multiplication of num1 and num2 is ");
        System.out.println(num1 * num2);

        System.out.print("The division of of num1 and num2 is ");
        System.out.println(num1 / num2);

        System.out.print("The remainder when num1 is divided by num2 is ");
        System.out.println(num1 % num2);

        System.out.println();

        // Logical operators
        boolean y = true, z = true;

        System.out.println(y && z);
        System.out.println(y || z);
        System.out.println(!y);
        System.out.println(!z);

        // Comparison operators
        int m = 9, n = 3;
        System.out.print("IS m is greater than n             : ");
        System.out.println(m > n);
        System.out.print("IS m is less than n                : ");
        System.out.println(m < n);
        System.out.print("IS m is greater than or equal to n : ");
        System.out.println(m >= n);
        System.out.print("IS m is  less than or equal to n   : ");
        System.out.println(m <= n);
        System.out.print("IS m is equal to n                 : ");
        System.out.println(m == n);
        System.out.print("IS m is not equal to n             : ");
        System.out.println(m != n);

    }
}
