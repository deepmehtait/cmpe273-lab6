package edu.sjsu.cmpe.cache.client;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
       CacheServiceInterface cache = new DistributedCacheService(
                "http://localhost:3000");
        CacheServiceInterface cache1 = new DistributedCacheService(
                "http://localhost:3001");
        CacheServiceInterface cache2 = new DistributedCacheService(
                "http://localhost:3002");
        // Adding key, values
        cache.put(1, "foo");
        cache1.put(2, "deep");
        cache2.put(3, "mehta");
        System.out.println("put(1 => foo)");
        System.out.println("put(2 => deep)");
        System.out.println("put(3 => Mehta)");
        // Retriving key, values
        String value = cache.get(1);
        String value1 = cache1.get(2);
        String value2 = cache2.get(3);
        System.out.println("get(1) => " + value);
        
        System.out.println("get(2) => " + value1);
        System.out.println("get(3) => " + value2);

        System.out.println("Existing Cache Client...");
    }

}
