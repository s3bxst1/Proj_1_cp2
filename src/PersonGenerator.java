import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {


        String ID = "";
        String fName = "";
        String lName = "";
        String title = "";
        int YOB = 0;
        String csvRec = "";
        boolean done = false;

        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        ArrayList<String> folks = new ArrayList<>();
        do {

            ID = SafeInput.getNonZeroLenString(in, "Enter the ID: ");
            fName = SafeInput.getNonZeroLenString(in, "Enter first name: ");
            lName = SafeInput.getNonZeroLenString(in, "Enter last name: ");
            title = SafeInput.getNonZeroLenString(in, "Enter title: ");
            YOB = SafeInput.getRangedInt(in, "Enter the age for the calc: ", 1000, 9999);

            csvRec = ID + ", " + fName + ", " + lName + ", " + title + ", " + YOB;
            folks.add(csvRec);
            done = SafeInput.getYNConfirm(in, "Are you done? ");

        } while (!done);
            for (String p: folks)
                System.out.println(p);
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

