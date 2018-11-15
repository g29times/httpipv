package com.tony.test.httpipv2.redis.client;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * http://redisdoc.com/
 */
public interface RedisAPI {

    // ************************* Key *************************
    boolean exists(String key);

    /**
     * 删除给定的一个或多个 key 。
     * <p>
     * 不存在的 key 会被忽略。
     *
     * @param key
     * @return
     */
    boolean del(String key);

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     *
     * @param key
     * @param expireTime
     * @param unit
     * @return
     */
    boolean expire(String key, long expireTime, TimeUnit unit);

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    long ttl(String key);

    /**
     * SCAN 命令及其相关的 SSCAN 命令、 HSCAN 命令和 ZSCAN 命令都用于增量地迭代（incrementally iterate）一集元素（a collection of elements）：
     * <p>
     * SCAN 命令用于迭代当前数据库中的数据库键。
     * SSCAN 命令用于迭代集合键中的元素。
     * HSCAN 命令用于迭代哈希键中的键值对。
     * ZSCAN 命令用于迭代有序集合中的元素（包括元素成员和元素分值）。
     * 以上列出的四个命令都支持增量式迭代， 它们每次执行都只会返回少量元素， 所以这些命令可以用于生产环境， 而不会出现像 KEYS 命令、 SMEMBERS 命令带来的问题 —— 当 KEYS 命令被用于处理一个大的数据库时， 又或者 SMEMBERS 命令被用于处理一个大的集合键时， 它们可能会阻塞服务器达数秒之久。
     * <p>
     * 不过， 增量式迭代命令也不是没有缺点的： 举个例子， 使用 SMEMBERS 命令可以返回集合键当前包含的所有元素， 但是对于 SCAN 这类增量式迭代命令来说， 因为在对键进行增量式迭代的过程中， 键可能会被修改， 所以增量式迭代命令只能对被返回的元素提供有限的保证 （offer limited guarantees about the returned elements）。
     * <p>
     * 因为 SCAN 、 SSCAN 、 HSCAN 和 ZSCAN 四个命令的工作方式都非常相似， 所以这个文档会一并介绍这四个命令， 但是要记住：
     * <p>
     * SSCAN 命令、 HSCAN 命令和 ZSCAN 命令的第一个参数总是一个数据库键。
     * 而 SCAN 命令则不需要在第一个参数提供任何数据库键 —— 因为它迭代的是当前数据库中的所有数据库键。
     *
     * @param cursor
     * @return
     */
    Object scan(int cursor);

    // ************************* String *************************

    /**
     * 返回 key 所关联的字符串值。
     * <p>
     * 如果 key 不存在那么返回特殊值 nil 。
     * <p>
     * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 将字符串值 value 关联到 key 。
     * <p>
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     * <p>
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     * <p>
     * 可选参数
     * <p>
     * 从 Redis 2.6.12 版本开始， SET 命令的行为可以通过一系列参数来修改：
     * <p>
     * EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
     * PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
     * NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     * XX ：只在键已经存在时，才对键进行设置操作。
     * 因为 SET 命令可以通过参数来实现和 SETNX 、 SETEX 和 PSETEX 三个命令的效果，所以将来的 Redis 版本可能会废弃并最终移除 SETNX 、 SETEX 和 PSETEX 这三个命令。
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
     * <p>
     * 如果 key 已经存在， SETEX 命令将覆写旧值。
     * <p>
     * 这个命令类似于以下两个命令：
     * <p>
     * SET key value
     * EXPIRE key seconds  # 设置生存时间
     * 不同之处是， SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
     *
     * @param key
     * @param value
     * @param expireTime
     * @param unit
     */
    void setex(String key, Object value, long expireTime, TimeUnit unit);

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * <p>
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     * <p>
     * SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
     *
     * @param key
     * @param value
     * @param expireTime
     * @param unit
     * @return
     */
    boolean setnx(String key, Object value, long expireTime, TimeUnit unit);

    /**
     * 将 key 中储存的数字值增一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * <p>
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * <p>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     *
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * 将 key 中储存的数字值减一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
     *
     * @param key
     * @return
     */
    long decr(String key);

    long strlen(String key);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
     *
     * @param key
     */
    void setbit(String key, long offset, boolean value);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
     *
     * @param key
     * @param offset
     * @return
     */
    boolean getbit(String key, long offset);

    /**
     * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destkey 上。
     * <p>
     * operation 可以是 AND 、 OR 、 NOT 、 XOR 这四种操作中的任意一种：
     * <p>
     * BITOP AND destkey key [key ...] ，对一个或多个 key 求逻辑并，并将结果保存到 destkey 。
     * BITOP OR destkey key [key ...] ，对一个或多个 key 求逻辑或，并将结果保存到 destkey 。
     * BITOP XOR destkey key [key ...] ，对一个或多个 key 求逻辑异或，并将结果保存到 destkey 。
     * BITOP NOT destkey key ，对给定 key 求逻辑非，并将结果保存到 destkey 。
     * 除了 NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入。
     *
     * @param operation
     * @param destkey
     * @param keys
     */
    void bitop(String operation, String destkey, String... keys);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     * <p>
     * 一般情况下，给定的整个字符串都会被进行计数，通过指定额外的 start 或 end 参数，可以让计数只在特定的位上进行。
     * <p>
     * start 和 end 参数的设置和 GETRANGE 命令类似，都可以使用负数值： 比如 -1 表示最后一个字节， -2 表示倒数第二个字节，以此类推。
     * <p>
     * 不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
     *
     * @param key
     * @return
     */
    long bitcount(String key);

    // ************************* Hash *************************

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param hkey
     * @return
     */
    Object hget(String key, String hkey);

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * <p>
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * <p>
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param hkey
     * @param hval
     * @return
     */
    Object hset(String key, String hkey, String hval);

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * <p>
     * 在Redis2.4以下的版本里， HDEL 每次只能删除单个域，如果你需要在一个原子时间内删除多个域，请将命令包含在 MULTI / EXEC 块内。
     *
     * @param key
     * @param hkeys
     * @return
     */
    long hdel(String key, String... hkeys);

    int hlen(String key);

    Collection<Object> hkeys(String key);

    Collection<Object> hvals(String key);

    /**
     * 详细信息请参考 SCAN 命令。
     *
     * @param key
     * @param type
     * @return
     */
    Iterator hscan(String key, String type);

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。
     * <p>
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * <p>
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @param key
     * @param hkeys
     * @return
     */
    Map hmget(String key, Set hkeys);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * <p>
     * 此命令会覆盖哈希表中已存在的域。
     * <p>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param map
     */
    void hmset(String key, Map map);

    // ************************* List *************************

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     * <p>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
     * <p>
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * <p>
     * 当 key 存在但不是列表类型时，返回一个错误。
     * <p>
     * 在Redis 2.4版本以前的 LPUSH 命令，都只接受单个 value 值。
     *
     * @param key
     * @param value
     */
    void lpush(String key, Object value);

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @param key
     * @return
     */
    Object lpop(String key);

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * <p>
     * 注意LRANGE命令和编程语言区间函数的区别
     * <p>
     * 假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数。
     * <p>
     * 超出范围的下标
     * <p>
     * 超出范围的下标值不会引起错误。
     * <p>
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表。
     * <p>
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    List lrange(String key, int start, int stop);

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * <p>
     * count 的值可以是以下几种：
     * <p>
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    boolean lrem(String key, int count, Object value);

    boolean rpush(String key, Object value);

    Object rpop(String key);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value 。
     * <p>
     * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
     * <p>
     * 关于列表下标的更多信息，请参考 LINDEX 命令。
     *
     * @param key
     * @param index
     * @param value
     */
    void lset(String key, int index, Object value);

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
     * <p>
     * 当 pivot 不存在于列表 key 时，不执行任何操作。
     * <p>
     * 当 key 不存在时， key 被视为空列表，不执行任何操作。
     * <p>
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @param key
     * @param pivot
     * @param value
     * @param isBefore
     * @return
     */
    int linsert(String key, Object pivot, Object value, boolean isBefore);

    int llen(String key);

    /**
     * 返回列表 key 中，下标为 index 的元素。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * <p>
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @param key
     * @param index
     * @return
     */
    Object lindex(String key, int index);

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * <p>
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
     * <p>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * <p>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * <p>
     * 当 key 不是列表类型时，返回一个错误。
     * <p>
     * LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用，举个例子：
     * <p>
     * LPUSH log newest_log
     * LTRIM log 0 99
     * 这个例子模拟了一个日志程序，每次将最新日志 newest_log 放到 log 列表中，并且只保留最新的 100 项。注意当这样使用 LTRIM 命令时，时间复杂度是O(1)，因为平均情况下，每次只有一个元素被移除。
     *
     * @param key
     * @param from
     * @param to
     */
    void ltrim(String key, int from, int to);

    // ************************* Set *************************

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * <p>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     * <p>
     * 当 key 不是集合类型时，返回一个错误。
     * <p>
     * 在Redis2.4版本以前， SADD 只接受单个 member 值。
     *
     * @param key
     * @param value
     * @return
     */
    boolean sadd(String key, Object value);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     * <p>
     * 当 key 不是集合类型，返回一个错误。
     *
     * @param key
     * @param value
     * @return
     */
    boolean srem(String key, Object value);

    /**
     * 详细信息请参考 SCAN 命令。
     *
     * @param key
     * @return
     */
    Iterator sscan(String key);

    // ************************* SortedSet *************************

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * <p>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     * <p>
     * score 值可以是整数值或双精度浮点数。
     * <p>
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * <p>
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * <p>
     * 对有序集的更多介绍请参见 sorted set 。
     *
     * @param key
     * @param score
     * @param value
     * @return
     */
    boolean zadd(String key, double score, Object value);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * <p>
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * <p>
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * <p>
     * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     * <p>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * 超出范围的下标并不会引起错误。
     * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
     * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
     * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
     * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    Collection zrange(String key, int start, int stop);

    /**
     * 详细信息请参考 SCAN 命令。
     *
     * @param key
     * @return
     */
    Iterator zscan(String key);

    // ************************* HyperLogLog *************************

    /**
     * 将任意数量的元素添加到指定的 HyperLogLog 里面。
     * <p>
     * 作为这个命令的副作用， HyperLogLog 内部可能会被更新， 以便反映一个不同的唯一元素估计数量（也即是集合的基数）。
     * <p>
     * 如果 HyperLogLog 估计的近似基数（approximated cardinality）在命令执行之后出现了变化， 那么命令返回 1 ， 否则返回 0 。 如果命令执行时给定的键不存在， 那么程序将先创建一个空的 HyperLogLog 结构， 然后再执行命令。
     * <p>
     * 调用 PFADD 命令时可以只给定键名而不给定元素：
     * <p>
     * 如果给定键已经是一个 HyperLogLog ， 那么这种调用不会产生任何效果；
     * 但如果给定的键不存在， 那么命令会创建一个空的 HyperLogLog ， 并向客户端返回 1 。
     *
     * @param key
     * @param values
     * @return
     */
    boolean pfadd(String key, Collection values);

    /**
     * 当 PFCOUNT 命令作用于单个键时， 返回储存在给定键的 HyperLogLog 的近似基数， 如果键不存在， 那么返回 0 。
     * <p>
     * 当 PFCOUNT 命令作用于多个键时， 返回所有给定 HyperLogLog 的并集的近似基数， 这个近似基数是通过将所有给定 HyperLogLog 合并至一个临时 HyperLogLog 来计算得出的。
     * <p>
     * 通过 HyperLogLog 数据结构， 用户可以使用少量固定大小的内存， 来储存集合中的唯一元素 （每个 HyperLogLog 只需使用 12k 字节内存，以及几个字节的内存来储存键本身）。
     * <p>
     * 命令返回的可见集合（observed set）基数并不是精确值， 而是一个带有 0.81% 标准错误（standard error）的近似值。
     * <p>
     * 举个例子， 为了记录一天会执行多少次各不相同的搜索查询， 一个程序可以在每次执行搜索查询时调用一次 PFADD ， 并通过调用 PFCOUNT 命令来获取这个记录的近似结果。
     *
     * @param key
     * @return
     */
    long pfcount(String key);

    /**
     * 将多个 HyperLogLog 合并（merge）为一个 HyperLogLog ， 合并后的 HyperLogLog 的基数接近于所有输入 HyperLogLog 的可见集合（observed set）的并集。
     * <p>
     * 合并得出的 HyperLogLog 会被储存在 destkey 键里面， 如果该键并不存在， 那么命令在执行之前， 会先为该键创建一个空的 HyperLogLog 。
     *
     * @param newKey
     * @param oldKeys
     */
    void pfmerge(String newKey, String oldKeys);

}
