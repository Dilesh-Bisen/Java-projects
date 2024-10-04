import java.io.*;

public class file_handling {

    public static void main(String[] args) {

        // Reading and Writing to Files

        try {
            FileReader ipfile = new FileReader("input_file.txt");
            FileWriter opfile = new FileWriter("output_file.txt");

            int chr;
            while ((chr = ipfile.read()) != -1) {
                opfile.write(chr);
            }

            ipfile.close();
            opfile.close();

            System.out.println("File has been read.");
        } catch (IOException e) {
            System.out.println("Error occur.");
            e.printStackTrace();
        }

        // 2. Serialization

        try {
            FileOutputStream out_file = new FileOutputStream("serializedObject.ser");
            ObjectOutputStream out = new ObjectOutputStream(out_file);
            String message = "Hello I am Dilesh Bisen.";
            out.writeObject(message);
            out.close();
            out_file.close();
            System.out.println("Object has been serialized.");

            FileInputStream in_file = new FileInputStream("serializedObject.ser");
            ObjectInputStream in = new ObjectInputStream(in_file);
            String deserializedMessage = (String) in.readObject();
            in.close();
            in_file.close();
            System.out.println("Deserialized Object: " + deserializedMessage);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred during serialization or deserialization.");
            e.printStackTrace();
        }
    }
}

