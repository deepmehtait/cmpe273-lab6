package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        /*CacheServiceInterface cache = new DistributedCacheService(
                "http://localhost:3000");
        CacheServiceInterface cache1 = new DistributedCacheService(
                "http://localhost:3001");
        CacheServiceInterface cache2 = new DistributedCacheService(
                "http://localhost:3002");
        cache.put(1, "foo");
        cache1.put(2, "deep");
        cache2.put(3, "mehta");
        System.out.println("put(1 => foo)");
        System.out.println("put(2 => deep)");
        System.out.println("put(3 => Mehta)");
        String value = cache.get(1);
        String value1 = cache1.get(2);
        String value2 = cache2.get(3);
        System.out.println("get(1) => " + value);
        
        System.out.println("get(2) => " + value1);
        System.out.println("get(3) => " + value2);
        */
        // Values to store in cache
        char[] value = {
            '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'
        };
        List < DistributedCacheService > Cluster = new ArrayList < DistributedCacheService > ();
        // Adding three servers
        Cluster.add(new DistributedCacheService("http://localhost:3000"));
        Cluster.add(new DistributedCacheService("http://localhost:3001"));
        Cluster.add(new DistributedCacheService("http://localhost:3002"));
        for (int putkey = 1; putkey <= 10; putkey++) {
            int key = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(putkey)), Cluster.size());
            Cluster.get(key).put(putkey, Character.toString(value[putkey]));
            System.out.println("The key value pair " + putkey + "-" + value[putkey] + " is assigned to server " + key);
        }
        for (int getkey = 1; getkey <= 10; getkey++) {
            int key2 = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(getkey)), Cluster.size());
            System.out.println("The key value pair " + getkey + "-" + Cluster.get(key2).get(getkey) + " is received to server " + key2);

        }
        System.out.println("Existing Cache Client...");
    }

}
