package commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Sort extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!sort")) {
            TextChannel channel = e.getChannel();
            ArrayList<Integer> tab = new ArrayList<Integer>();
            StringBuilder str = new StringBuilder();

            for(int i = 1; i < messageSent.length; i++) {
                tab.add(Integer.parseInt(messageSent[i]));
            }

            Collections.sort(tab);

            for (Integer integer : tab) str.append(integer.toString()).append(" ");

            channel.sendMessage(str.toString()).queue();
        }
    }
}
