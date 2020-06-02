package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("salut") || messageSent[0].equalsIgnoreCase("hi")) {
            if (!e.getAuthor().isBot()) {
                e.getChannel().sendMessage("hi").queue();
            }
        }
    }
}
