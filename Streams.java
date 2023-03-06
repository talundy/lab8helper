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
            // create stream of input lines
            Stream<String> fileStream = Files.lines(Paths.get(filename));
            // convert to a Point stream, where we perform the necessary operations
            Stream<Point> pointStream = fileStream
                    .map(x -> {
                        String[] s = x.split(",");
                        return new Point(
                                Double.parseDouble(s[0]),
                                Double.parseDouble(s[1]),
                                Double.parseDouble(s[2]));})
                    .filter(pt -> !(pt.z > 2))
                    .map(pt -> new Point(pt.x/2 - 150, pt.y/2 - 37, pt.z));
            // map back to a String stream for printing
            Stream<String> returnStream = pointStream
                    .map(pt -> pt.x + ", " + pt.y + ", " + pt.z);
            // write to output file
            returnStream.forEach(str -> {
                try {
                    writer.write(str);
                    writer.write("\n");
                } catch (IOException e) {
                    System.out.println("Exception " + e + "thrown trying to write.");
                    throw new RuntimeException(e);
                }});
            // always close file!
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception " + e + "thrown trying to process file. ");
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
