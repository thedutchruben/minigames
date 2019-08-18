package dev.thedutchruben.core.server;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ServerInfo {
    private double tps;
    private long freeMem;

    @Override
    public String toString() {
        return "ServerInfo{" +
                "tps=" + tps +
                ", freeMem=" + freeMem +
                '}';
    }
}
