package com.tony.test.httpipv2.redis.key;

public class PageRankKey extends BasePrefix {

    public PageRankKey(String prefix, String key) {
        super(prefix, key);
    }

    public PageRankKey(int expireSeconds, String prefix, String key) {
        super(expireSeconds, prefix, key);
    }

    public static PageRankKey WEB_PAGE_RANK = new PageRankKey("web", "page_rank");
    public static PageRankKey APP_PAGE_RANK = new PageRankKey("app", "page_rank");
    
}
