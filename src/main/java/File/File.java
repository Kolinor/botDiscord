package File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class File {
    private java.io.File fichier;
    private FileReader reader;
    private String path;
    private String strFile;

    public File(String path) throws IOException {
        fichier = null;
        reader = new FileReader(path);
        this.path = path;
        this.strFile = this.read();
    }

    public String read() throws IOException {

        int i = 0;
        String str = "";
        while((i=reader.read())!=-1) {
            str += (char)i;
        }
        return str;
    }

    public String getDiscordToken() throws IOException {
        String[] str = this.strFile.split("\n");
        String[] discord = str[0].split(":");
        return discord[1].trim();
    }

    public String getApiRiotToken() throws IOException {
        String strFile = this.read();

        String[] str = this.strFile.split("\n");
        String[] riotApi = str[1].split(":");
        return riotApi[1].trim();
    }
}
