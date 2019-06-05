# Middleware_JMS_RMI

Server：
program arguments : -Djava.rmi.server.useCodebaseOnly=false
VM arguments : -Djava.rmi.server.codebase=http://localhost:3000/

Client:
VM arguments : -Djava.security.manager -Djava.security.policy="/Users/Bullshit/Documents/workspace/MiddlewareClient/src/tp1/rmi.policy"(emplacement du fichier de policy)

感觉大晚上的脑子不太清楚
#### Client
println("Enter username") -> username
println("Enter password") -> pwd
logIn(username, password)
菜单


#### Serveur
事先设定几个user&pwd 和 durableChat里的topic（不知道是不是对应projet要求里的groupe）
+ RMI
    + logIn (username,  password) -> ?我不确定 成功就输出菜单：e.g. 1. demande list 2. s'abonner groupe 3. ouvrir groupe 错误就再试一次吧
    + choose(int index) 用来get menu的选项？每个选项写成一个什么什么service的子类，这样choose返回值是这个service？
    + string getPseudo()
    + string setPseudo(string newPseudo)
	+ demander la liste actuelle des groupes de discussion
	+ s'abonner(groupe)
	+ 打开已订阅groupe -> jms
+ JMS -> durable chat
    * exit 退出当前组（回到rmi的菜单部分）
    * 发言
    * 