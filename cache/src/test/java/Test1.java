import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

public class Test1 {
    
    @Test
    public void test1() throws InterruptedException {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");
        
        // 3. 创建元素
        Element element = new Element("key1", "value1");
        
        // 4. 将元素添加到缓存
        cache.put(element);
        
        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());
        
        // 6. 删除元素
        cache.remove("key1");
        for (int i = 0; ; i++) {
            Person p1 = new Person("小明",18,"杭州");
            Element pelement = new Element("xm", p1);
            cache.put(pelement);
            Element pelement2 = cache.get("xm");
            System.out.println(pelement2.getObjectValue());

            System.out.println(cache.getSize());

            // 7. 刷新缓存
            cache.flush();
        }

    }

    class Person{
        String name;
        int age;
        String location;

        public Person(String name, int age, String location) {
            this.name = name;
            this.age = age;
            this.location = location;
        }
    }
}
