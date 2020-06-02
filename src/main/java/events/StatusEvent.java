package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class StatusEvent extends ListenerAdapter {
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent e) {
        OnlineStatus on = e.getGuild().getMember(e.getUser()).getOnlineStatus();

        e.getGuild().getTextChannelsByName("codeur", true).get(0).sendMessage(
                new EmbedBuilder()
                        .setColor(Color.cyan)
                        .setDescription("User **" + e.getUser().getAsMention() + "** is now **" + on + "**!")
                        .build()
        ).queue();
    }

    public void onUserUpdateName(UserUpdateNameEvent e) {
        System.out.println("Je suis trop fort");
        e.getJDA().getTextChannelsByName("codeur", true).get(0).sendMessage(
                new EmbedBuilder()
                        .setColor(Color.red)
                        .setDescription("User **" + e.getUser().getAsMention() + " rename " + "**" + e.getOldName() + "** " + " to **" + e.getNewName() + "**!")
                        .build()
        ).queue();
    }

    public void onUserUpdateAvatar(UserUpdateAvatarEvent e) {
        e.getJDA().getTextChannelsByName("codeur", true).get(0).sendMessage("Colin t'es le meilleur!");
    }
}
