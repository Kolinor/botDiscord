package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utilities.HelpManager;

import java.awt.*;

public class Help extends ListenerAdapter {
    HelpManager helpmanager = new HelpManager();

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContentRaw().equalsIgnoreCase("!help")) {
            e.getChannel().sendMessage(helpmanager.helpMessage()).queue();
        } else if(e.getMessage().getContentRaw().equalsIgnoreCase("!help music")) {
            e.getChannel().sendMessage(helpmanager.helpMessageMusic()).queue();
        }
    }
}
