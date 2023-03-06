import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Streams {


    private static void processFile(
            final String filename
    ){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            Stream<String> fileStream = Files.lines(Paths.get(filename));
            fileStream.forEach(x -> {
                if (x.length() == 0){
                    return;
                }
                try {
                    writer.write(x.toString());
                    writer.write("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getFilename(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Log file not specified.");
            System.exit(1);
        }

        return args[0];
    }


    public static void main(String[] args) {
        final String filename = getFilename(args);

        processFile(filename);


    }
}
