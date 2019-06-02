package dev.thedutchruben.framework.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    @Getter private ChatColor color = null;
    @Getter private String nameTag = null;
    @Getter
    private org.bukkit.scoreboard.Scoreboard board;
    private Objective objective;
    private String tabText = null;
    private String title;
    private List<String> values = new ArrayList<>();
    private List<String> oldValues = new ArrayList<>();
    private Boolean firstRun = true;
    @Setter
    public Consumer<Scoreboard> onInitialize;

    public Scoreboard(String title) {
            this.board = Bukkit.getScoreboardManager().getNewScoreboard();
            this.title = title;
    }

    public Scoreboard addLine(String text) {
        values.add(ChatColor.translateAlternateColorCodes('&', text));
        return this;
    }

    public Scoreboard setTitle(String title){
        this.title = title;
        return this;
    }

    public Scoreboard setTabPrefix(String text) {
        this.tabText = ChatColor.translateAlternateColorCodes('&', text);
        return this;
    }

    public Scoreboard setTeamColor(ChatColor color) {
        this.color = color;
        return this;
    }

    public void apply(Player p) {
        if (this.objective == null) {
            this.objective = this.board.registerNewObjective("scoreboard", "dummy",this.title);
            this.objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.title));
            this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        this.oldValues.forEach(oldVal -> {
            if (!values.contains(oldVal)) {
                this.board.resetScores(oldVal);
            }
        });

        this.oldValues.clear();
        this.oldValues.addAll(values);
        int line = values.size();
        for (String s : values) {
            objective.getScore(s).setScore(line--);
        }

        if (firstRun) {
            p.setScoreboard(this.board);
            if (onInitialize != null) {
                onInitialize.accept(this);
            }
        }

        firstRun = false;
        this.values.clear();

        if (tabText != null) {
            p.setPlayerListName(tabText);
        }
    }
}
