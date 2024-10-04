import java.io.*;

public class lab6 {
    public static void main(String[] args) {

        String path = "D:\\Mini Desktop\\code\\Lab Assignment\\JAVA\\LAB-6\\";
        String rename = "Dilesh";

        for (int i = 1; i <= 5; i++) {
            String fileName = path + "input_file" + i + ".txt";
            String newFileName = path + rename + i + ".txt";

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.replaceAll("[^a-zA-Z0-9\\s]", " lab6 ");
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
           
            File oldFile = new File(fileName);
            File newFile = new File(newFileName);
            if (oldFile.exists() && !newFile.exists()) {
                if (oldFile.renameTo(newFile)) {
                    System.out.println("The files are renamed successfully");
                } else {
                    System.out.println("The failure occur to rename file");
                }
            }
        }
    }
}
