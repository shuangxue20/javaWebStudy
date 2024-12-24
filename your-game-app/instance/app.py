import sqlite3

# 连接到 SQLite 数据库
# 如果文件不存在，会自动在当前目录创建一个数据库文件
conn = sqlite3.connect('your_app.db')

# 创建一个 Cursor 对象并通过它执行 SQL 命令
cursor = conn.cursor()

# 创建表
cursor.execute('''
CREATE TABLE user (
    id INTEGER PRIMARY KEY,
    username TEXT NOT NULL
)
''')

# 提交事务
conn.commit()

# 关闭连接
conn.close()