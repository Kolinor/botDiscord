package commands;

import music.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Music extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");
        PlayerManager manager = PlayerManager.getInstance();

        if (messageSent[0].equalsIgnoreCase("!music")) {
            switch(messageSent[1]) {
                case "volume":
                    if (messageSent[2].length() > 0) {
                        int volume = Integer.parseInt(messageSent[2]);
                        if (volume >= 0 && volume <= 100) {
                            manager.getGuildMusicManager(e.getGuild()).player.setVolume(volume);
                            e.getChannel().sendMessage("Mon volume est passé à " + volume).queue();
                        } else {
                            e.getChannel().sendMessage("Impossible de passer le volume à " + volume).queue();
                        }
                    }
                    break;
                case "add":
                    if (messageSent[2].length() > 0) {
                        manager.loadAndPlay(e.getChannel(), messageSent[2]);
                    }
                    break;
                case "skip":
                    manager.skip(e.getChannel());
                    break;
                case "stop":
                    manager.stop(e.getChannel());
                        break;
                case "queue":
                    manager.getQueue(e.getChannel());
                    break;
                case "play":
                    manager.stop(e.getChannel());
                    manager.loadAndPlay(e.getChannel(), messageSent[2]);
                    break;
            }
        }
    }

}
