import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class parseLine {
    public static void main(String[] args) throws IOException {
        String objLine = "project1!branch1!obj1;project2!branch2!obj2";

       /* File file = new File("d:/Idea/myfile.data");
        file.createNewFile();
        System.out.println("exists=" + file.exists());
        System.out.println(file.isFile());*/


       Path path = Paths.get("d:/Idea/myfile1.data");
        Path file = Files.createFile(path);
        System.out.println("exists=" + Files.exists(file));
        System.out.println("isFile=" + Files.isRegularFile(file));
       // Files.writeString(file,"sdgssgs");
        //Path file = Paths.get("the-file-name.txt");
        //List<String> lines = Arrays.asList("The first line", "The second line");
        List<String> lines = null;



        if(!objLine.isEmpty()) {
            String[] objectsForAdj = objLine.split(";");
            if(objectsForAdj.length>0) {
            for (String obj:objectsForAdj) {
               lines = Arrays.asList(obj.split("!"));
                //Files.write(file, lines, StandardCharsets.UTF_8);
                System.out.println(lines.get(0)+"\t"+lines.get(1)+
                        "\t"+lines.get(2)+"\n");

                Files.write(file,(lines.get(0) + "\t"
                        + lines.get(1) + "\t"
                        +lines.get(2) + "\n").getBytes());
            }

            }

        }


    }


}
