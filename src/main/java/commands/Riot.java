package commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rithms.riot.api.RiotApiException;
import riot.RiotApiGestion;

public class Riot extends ListenerAdapter {
    RiotApiGestion riotApiGestion = new RiotApiGestion();

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!information")) {
            TextChannel channel = e.getChannel();

            try {
                channel.sendMessage(riotApiGestion.getInformation()).queue();
            } catch (RiotApiException riotApiException) {
                riotApiException.printStackTrace();
            }
        }
    }
}
