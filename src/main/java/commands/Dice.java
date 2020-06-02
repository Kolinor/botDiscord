package commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Dice extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!dice")) {
            TextChannel channel = e.getChannel();
            int max = 6;
            int min = 1;
            int range = max - min + 1;
            int rand = (int)(Math.random() * range) + min;

            switch (rand) {
                case 1:
                    channel.sendMessage(":one:").queue();
                    break;
                case 2:
                    channel.sendMessage(":two:").queue();
                    break;
                case 3:
                    channel.sendMessage(":three:").queue();
                    break;
                case 4:
                    channel.sendMessage(":four:").queue();
                    break;
                case 5:
                    channel.sendMessage(":five:").queue();
                    break;
                case 6:
                    channel.sendMessage(":six:").queue();
                    break;
            }
        }
    }
}
