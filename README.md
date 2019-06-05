# Middleware_JMS_RMI

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