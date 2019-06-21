package dev.thedutchruben.core.framework.database;

import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDb {

    private JedisPool pool;



    // A private constructor so instances cannot be created from out of the class.
    public RedisDb() {
        pool = new JedisPool("bungee-redis-1");
        pool.getResource().connect();

        try (Jedis connection = pool.getResource()) {
            if (connection.isConnected()) {
            } else {
                Bukkit.getServer().shutdown();
                return;
            }
        }


    }

    public JedisPool getPool() {
        return pool;
    }
}
