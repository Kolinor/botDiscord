package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Calculate extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!calculate")) {

            switch(messageSent[1]) {
                case "add":
                    if (messageSent.length == 2) {
                        sendMessageCalculate(e);
                    } else {
                        float res = 0;
                        for (int i = 2; i < messageSent.length; i++) {
                            res += Float.parseFloat(messageSent[i]);
                        }
                        e.getChannel().sendMessage(" The resultat is : " + res).queue();
                    }
                    break;
//                case "sub":

            }
        }
    }

    private void sendMessageCalculate(GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage("Pour utiliser cette commande, essaye : !calculate [add] [first-num] [second-num] ...").queue();
    }
}
