package music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final ArrayList<AudioTrack> tracks;

    private PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        this.tracks = new ArrayList<>();

        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {
        long guildId = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Ajouter à la queue " + track.getInfo().title).queue();

                play(musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                channel.sendMessage("Ajouter à la queue " + firstTrack.getInfo().title + " (première music de la playlist " + playlist.getName() + ")").queue();
                play(musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Aucune musique trouver à l'url : " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendMessage("Impossible de charger la musique " + e.getMessage()).queue();
            }
        });
    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {
        musicManager.scheduler.queue(track);
        tracks.add(track);
    }

    public void skip(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        musicManager.scheduler.nextTrack();
        tracks.remove(0);
        channel.sendMessage("Musique passer à la suivante !");
    }

    public void stop(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        musicManager.scheduler.stopTrack();
        tracks.clear();
    }

    public void getQueue(TextChannel channel) {
        String track = "";
//        for (AudioTrack audioTrack : tracks) {
//            track += audioTrack.getInfo(). + audioTrack.getInfo().title;
//        }
        for (int i = 0; i < tracks.size(); i++) {
            String title = tracks.get(i).getInfo().title;
            track += i + " : " + title + "\n";
        }
        channel.sendMessage(track).queue();
    }

    public static synchronized PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
}
