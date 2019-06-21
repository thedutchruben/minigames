package dev.thedutchruben.core.framework.friend;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Friend {
    private UUID uuid;
    private Date friendData;
}
