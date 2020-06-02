package commands;

import music.PlayerManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class Join extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] messageSent = e.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!join")) {
            TextChannel channel = e.getChannel();
            AudioManager audioManager = e.getGuild().getAudioManager();

            if (audioManager.isConnected()) {
                channel.sendMessage("Déjà connecté sur un channel").queue();
                return;
            }

            GuildVoiceState memberVoiceState = e.getMember().getVoiceState();

            if (!memberVoiceState.inVoiceChannel()) {
                channel.sendMessage("Rejoingnez un channel avant tout").queue();
                return;
            }

            VoiceChannel voiceChannel = memberVoiceState.getChannel();
            Member selfMember = e.getGuild().getSelfMember();

            if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
                channel.sendMessage("Je n'ai pas les droits pour rejoindre le channel").queue();
                return;
            }

            audioManager.openAudioConnection(voiceChannel);
            channel.sendMessage("J'ai rejoin le channel").queue();
        }
    }
}
