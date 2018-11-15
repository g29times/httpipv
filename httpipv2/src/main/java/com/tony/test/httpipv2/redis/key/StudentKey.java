package com.tony.test.httpipv2.redis.key;

public class StudentKey extends BasePrefix {

    public StudentKey(String prefix, String key) {
        super(prefix, key);
    }

    public StudentKey(int expireSeconds, String prefix, String key) {
        super(expireSeconds, prefix, key);
    }

    public static StudentKey STU_CLASS = new StudentKey("stu", "class");
    public static StudentKey STU_TASK = new StudentKey("stu", "task");
    
}
