package dev.thedutchruben.core.framework.party;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Party {
    private String name;
    private UUID owner;
    private List<UUID> members;
}
