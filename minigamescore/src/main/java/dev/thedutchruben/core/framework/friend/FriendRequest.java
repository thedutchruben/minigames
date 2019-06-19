package dev.thedutchruben.core.framework.friend;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class FriendRequest {
    private UUID requester;
    private Date date;
}
