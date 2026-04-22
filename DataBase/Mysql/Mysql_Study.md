# Mysql Study

## SQL

### SQL分类

| 分类 |            全称            |                          说明                          |
| :--: | :------------------------: | :----------------------------------------------------: |
| DDL  |  Data Definition Language  |        数据定义语言， 用来创建数据库，表，字段         |
| DML  | Data Manipulation Language |           数据操作语言，用来对数据进行增删改           |
| DQL  |    Data Query Language     |         数据查询语言，用来查询数据库中表的记录         |
| DCL  |   Data Control Language    | 数据控制语言，用来创建数据库用户，控制数据库的访问权限 |

### DDL语句

#### DDL 数据库操作

##### 查询

```sql
-- 查询所有数据库
SELECT DATABASES;
-- 查询当前数据库
SELECT DATABASE();
```

##### 创建

```sql
-- 创建数据库
CREATE DATABASE [IF NOT EXISTS] 数据库名 [DEFAULT CHARSET 字符集(utf8/utf8mb4)] [COLLATE 排序规则];
```

##### 删除

```sql
-- 删除数据库
DROP DATABASE [IF EXISTS] 数据库名;
```

##### 使用

```sql
USE 数据库名;
```

#### DDL 表操作

##### 查询

```sql
-- 查询当前数据库中所有表
SHOW TABLES;

-- 查询表结构
DESC 表名;

-- 查询指定表的建表语句
SHOW CREATE TABLE 表名;
```

##### 创建

```sql
-- 创建一个表
CREATE TABLE [ IF NOT EXISTS ] 表名(
			字段1 字段类型1 [COMMENT 字段1注释],
      字段2 字段类型2 [COMMENT 字段2注释],
      字段3 字段类型3 [COMMENT 字段3注释],
  		...
      字段n 字段类型n [COMMENT 字段n注释]
)[COMMENT 表注释];

-- 举例
CREATE TABLE IF NOT EXISTS person(
	id varchar(36) COMMENT 主键ID,
  name varchar(128) COMMENT 姓名,
  age bitint COMMENT 年龄,
  gender varchar(2) COMMENT 性别
)COMMENT 用户表;
```

##### 修改

```sql
-- 添加字段
ALTER TABLE 表名 ADD 字段名 类型(长度) [COMMENT 注释] [约束]; 

-- 修改字段类型
ALTER TABLE 表名 CHANGE 字段名 类型(长度) [COMMENT 注释] [约束];

-- 修改字段名 和 字段类型
ALTER TABLE 表名 CHANGE 旧字段名 新字段名 类型(长度) [COMMENT 注释] [约束];

-- 删除字段
ALTER TABLE 表名 DROP 字段名;

-- 修改表名
ALTER TABLE 表名 RENAME TO 新表名;
```

##### 删除

```sql
-- 删除表
DROP TABLE IF EXISTS 表名;

-- 删除表，并重新创建该表 (清除表中的数据)
TRUNCATE TABLE 表名;
```

##### 数据类型

1. 数值类型

   * **tinyint**：1 字节，-128~127（常用：0/1 状态）
   * **smallint**：2 字节
   * **mediumint**：3 字节
   * **int**：4 字节（最常用）
   * **bigint**：8 字节（订单 ID、用户 ID、超大数字）

   > 有无符号：`unsigned` 可让范围翻倍，如 int 0~42 亿

   * **float**：单精度浮点，4 字节（不精确）

   * **double**：双精度浮点，8 字节（较精确）

   * **decimal(M,D)**：**精确小数**，M 总位数，D 小数位  → 钱、金额必须用 `decimal`，禁止 float/double

2. 字符串类型

   * **char(n)**：固定长度字符串，n=0~255

     优点：快；缺点：浪费空间

   * **varchar(n)**：可变长度，更省空间（最常用）

   * **text**：大文本，存文章、内容

     - tinytext
     - text
     - mediumtext
     - longtext

   * **blob**：二进制大对象，存图片、文件（一般不推荐存数据库）

   * **enum**：枚举，如 enum ('male','female')

   * **set**：集合，可多选

3. 时间类型

   * **date**：日期，`YYYY-MM-DD`
   * **time**：时间，`HH:MM:SS`
   * **year**：年份
   * **datetime**：日期 + 时间 `YYYY-MM-DD HH:MM:SS`（常用）
   * **timestamp**：时间戳，自动时区转换，会随更新自动刷新 → 常用作 `create_time`、`update_time`

### DML语句

#### 添加数据（INSERT）

```sql
-- 给指定字段添加数据
INSERT INTO 表名(字段名1, 字段名2, ... , 字段名n) VALUES （值1, 值2, ... , 值n）;

-- 给全部字段添加数据
INSERT INTO 表名 VALUES （值1, 值2, ... , 值n）;

-- 批量添加数据
INSERT INTO 表名(字段名1, 字段名2, ... , 字段名n) VALUES （值1, 值2, ... , 值n）,（值1, 值2, ... , 值n）,（值1, 值2, ... , 值n）... ,（值1, 值2, ... , 值n）;
INSERT INTO 表名 VALUES （值1, 值2, ... , 值n）,（值1, 值2, ... , 值n）,（值1, 值2, ... , 值n）, ... , （值1, 值2, ... , 值n）;
```

#### 修改数据（UPDATE）

```sql
UPDATE 表名 SET 字段1=值1, 字段2=值2, ... [WHERE 条件];
```

***注意：如果没有条件则会修改表中的所有数据。***

#### 删除数据（DELETE）

```sql
DELETE FROM 表名 [WHERE 条件]
```

***注意：如果没有条件则会修改表中的所有数据。***

### DQL语句

查询关键字: ***SELECT***

```sql
SELECT 字段列表
FROM 表名列表
WHERE 条件列表
GROUP BY 分组列表
HAVING 分组后条件列表
ORDER BY 排序字段列表
LIMIT 分页参数
```

#### DQL 类别

##### 基本查询

```sql
-- 查询多个字段
SELECT 字段1, 字段2, ... , 字段n FROM 表名;

-- 查询所有字段
SELECT * FROM 表名;

-- 设置别名
SELECT 字段1 [AS 别名1] , 字段2 [AS 别名2], ... , 字段n [AS 别名n] FROM 表名;

-- 去除重复记录
SELECT DISTINCT 字段列表 FROM 表名;
```

##### 条件查询（ WHERE ）

```sql
SELECT 字段列表 FROM 表名 WHERE 条件列表;
```

###### 条件分类

1. 比较条件

   * `=` 等于
   * `!=` 或 `<>` 不等于
   * `>` 大于
   * `<` 小于
   * `>=` 大于等于
   * `<=` 小于等于

2. 范围条件

   * `BETWEEN ... AND ...` 在区间内 (含最小值、最大值)

   * `NOT BETWEEN ... AND ...` 不在区间内

3. 集合匹配

   * `IN(值1,值2,...)` 在列表中
   * `NOT IN(...)` 不在列表中

4. 模糊查询（字符串）

   * `LIKE 'xxx%'` 以 xxx 开头
   * `LIKE '%xxx'` 以 xxx 结尾
   * `LIKE '%xxx%'` 包含 xxx
   * `NOT LIKE` 不匹配

5. 空值判断

   * `IS NULL` 为空
   * `IS NOT NULL` 不为空

6. 逻辑条件

   * `AND` 或者 `&&`  -->   并且（同时满足）
   * `OR`  或者 `||`   -- >  或者（满足其一）
   * `NOT` 或者 `!`  -->  取反

7. 其他常用

   * `EXISTS(子查询)` 子查询有结果则为真
   * `NOT EXISTS` 子查询无结果则为真

##### 聚合函数 （ COUNT, MAX, MIN, AVG, SUM, ...）

##### 分组查询（ GROUP BY）

##### 排序查询（ ORDER BY ）

##### 分页查询（ LIMIT ）