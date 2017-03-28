#Minecraft-QQGroupPlubin

食用教程：http://mcbbs.net/thread-682777-1-1.html

=================================

协议食用指南：

插件通过socket通讯，使用2333端口

客户端：minecraft插件实现

服务端：cq插件实现

===========================

通讯协议举例：

客户端与服务端每秒发送/接收一次数据

客户端→服务端（minecraft插件→cq插件）发送字符串举例：xxxxxxxx]][[ccccc]][[dawdasdasdasdasd]][[asadsdasdasd

分割符号为“]][[”（如果这一秒内有多条消息的话会这样）

服务端→客户端（cq插件→minecraft插件）发送字符串举例：|||||xxxxxxxx|||||command>ccccc|||||<player>dawdasdasdasdasd

分割符号为“|||||”（如果这一秒内有多条消息的话会这样），当分割开后，字符串中包含“<”符号的，将直接在服务器全局发送；字符串中包含“command>”符号且不包含“<”符号的，将作为命令发送到控制台，其他数据将舍弃（如xxxxxxxx那一个字符串）。

================================

by晨旭 chenxublog.com

糖拌苦力怕服务器sweetcreeper.com
