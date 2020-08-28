import File.File;
import commands.*;
import events.HelloEvent;
import events.StatusEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.rithms.riot.api.RiotApiException;
import riot.RiotApiGestion;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException, InterruptedException, RiotApiException {

        String pathToken = "C:\\Users\\Niloc\\IdeaProjects\\botDiscord\\src\\main\\java\\File\\token.txt";
        File file = new File(pathToken);
        JDA jda = new JDABuilder(file.read()).build();


        jda.awaitReady();


        //
        // events
        //

        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new StatusEvent());

        //
        // commands
        //

        jda.addEventListener(new Calculate());
        jda.addEventListener(new Help());
        jda.addEventListener(new Music());
        jda.addEventListener(new Join());
        jda.addEventListener(new Leave());
        jda.addEventListener(new Ping());
        jda.addEventListener(new Dice());
        jda.addEventListener(new Sort());
        jda.addEventListener(new Riot());

        //
        // Set bot
        //

        jda.getPresence().setActivity(Activity.playing("Colin le meilleur !"));
    }
}

