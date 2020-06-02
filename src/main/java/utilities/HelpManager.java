package utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class HelpManager {
    public MessageEmbed helpMessage() {
        return new EmbedBuilder()
                .setColor(new Color(22, 138, 233))
                .setTitle("Panel de command")
                .addField("Commandes :",
                        "`calculate:` Permet de calculer\n" +
                                "`ping:` Permet de Ping le bot\n" +
                                "`join:` Permet de faire joindre le bot dans le channel\n" +
                                "`leave:` Permet de faire quitter le bot du channel\n" +
                                "`music:` !help music pour plus d'information\n" +
                                "`dice:` permet de simuler un lancé de dés", false)
                .build();
    }

    public MessageEmbed helpMessageMusic() {
        return new EmbedBuilder()
                .setColor(new Color(22, 138, 233))
                .setTitle("Aide musique")
                .addField("Commandes",
                        "`add:` Permet d'ajouter une musique à la queue\n" +
                                "`stop:` Permet de stopper la music en cour\n" +
                                "`volume [0-100]:` Permet d'ajuster le volume du bot\n" +
                                "`play:` Permet de jouer une musique", false)
                .build();
    }
}
