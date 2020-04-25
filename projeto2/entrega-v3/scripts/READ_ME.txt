colocar os scripts na pasta do kafka

Ordem de execução,
1º Correr o script run_servers
2º Correr o script create topics

o script create topics só é necessario caso os topicos não existam
e o utilizador queira correr um consumer antes de correr o collectEntity
que cria os topicos caso estes não existam
