# Middleware_JMS_RMI

Server：
program arguments : -Djava.rmi.server.useCodebaseOnly=false
VM arguments : -Djava.rmi.server.codebase=http://localhost:3000/

Client:
VM arguments : -Djava.security.manager -Djava.security.policy="/Users/Bullshit/Documents/workspace/MiddlewareClient/src/tp1/rmi.policy"(emplacement du fichier de policy)


#### Serveur
+ RMI
    + logIn (username,  password) 
    + string getPseudo()
    + string setPseudo(string newPseudo)
	+ demander la liste actuelle des groupes de discussion
	+ s'abonner(groupe)
	+ 打开已订阅groupe -> jms
+ JMS -> durable chat
    * exit 退出当前组（回到rmi的菜单部分）
    * 发言
    * 