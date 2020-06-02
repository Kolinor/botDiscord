package File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class File {
    private java.io.File fichier;
    private FileReader reader;

    public File(String path) throws FileNotFoundException {
        fichier = null;
        reader = new FileReader(path);
    }

    public String read() throws IOException {

        int i;
        String str = "";
        while((i=reader.read())!=-1) {
            str += (char)i;
        }
        return str;
    }
}
