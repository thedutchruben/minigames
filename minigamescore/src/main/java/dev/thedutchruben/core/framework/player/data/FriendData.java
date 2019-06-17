package dev.thedutchruben.core.framework.player.data;

import dev.thedutchruben.core.framework.friend.Friend;
import dev.thedutchruben.core.framework.friend.FriendRequest;
import dev.thedutchruben.core.framework.player.MinigamesPlayer;
import dev.thedutchruben.core.framework.player.PlayerData;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FriendData implements PlayerData {

    private List<Friend> friends;
    private List<FriendRequest> friendRequests;

    public static FriendData create(MinigamesPlayer player) {
        return new FriendData(new ArrayList<>(), new ArrayList<>());
    }


    public List<Friend> getFriends() {
        return friends;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }
}
