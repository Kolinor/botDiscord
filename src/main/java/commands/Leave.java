package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class Leave extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!leave")) {
            TextChannel channel = e.getChannel();
            AudioManager audioManager = e.getGuild().getAudioManager();

            if (!audioManager.isConnected()) {
                channel.sendMessage("Je ne suis pas connecté sur un channel").queue();
                return;
            }

//            VoiceChannel voiceChannel = audioManager.getConnectedChannel();
//
//            if (!voiceChannel.getMembers().contains(e.getMember())) {
//                channel.sendMessage("Tu as besoin d'être dans le même channel que moi").queue();
//                return;
//            }

            audioManager.closeAudioConnection();
            channel.sendMessage("Déconnexion de votre channel");
        }
    }
}
