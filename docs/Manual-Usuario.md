# Emites Java Challenge - Manual do Usuário

#### Descritivo Geral

A solução está composta por duas aplicações:

* **EmitesServer**: recebe as conexões dos aplicativos EmitesClient, efetua a chamada para o servidor IMDb para realizar a busca, empacota o conteúdo e devolve para os _clients_ conforme o protocolo proposto.
* **EmitesClient**: conecta no EmitesServer e permite aos usuários efetuarem a busca amigável no conteúdo do servidor IMDb.


#### Roteiro Básico

Para colocar os serviços no ar é necessário seguir os seguintes passos na sequencia:

1. Executar o EmitesServer (para ele ficar ativo e responder aos clientes remotos)
2. Executar o EmitesClient (abre a linha de comando para efetuar as pesquisas)
3. Digitar o conteúdo a ser pesquisado

#### Como Executar o EmitesServer (Servidor)

Execute o comando a seguir para iniciar o EmitesServer:

```
java -jar ./target/EmitesServer-1.0-SNAPSHOT.jar 4545

-------------------------
  EmitesServer starting
-------------------------
Listening on port 4545
[Thread 11] EmitesServer started.
[Thread 11] Request: 16:emites-client-hi
[Thread 11] Response: 16:emites-server-hi
```

O parâmetro passado (no caso 4545) se refere à porta de conexão em que o módulo servidor ficará aguardando as conexões dos módulos clientes.

Tenha em mente o IP da máquina onde o servidor está sendo executado - esta informação será útil posteriormente para iniciar os módulos clientes.

#### Como Executar o EmitesClient (Cliente)

Execute o comando a seguir para iniciar o EmitesClient:

```
java -jar ./target/EmitesClient-1.0-SNAPSHOT.jar 127.0.0.1 4545

-------------------------
  EmitesClient starting
-------------------------
Trying to connect to 127.0.0.1 on port 4545
Client search for [emites-client-hi]
EmitesClient connected.
Response from server:
emites-server-hi
```


Os parâmetros utilizados são:

* **127.0.0.1** - IP da máquina onde o servidor está sendo executado
* **4545** - porta em que o módulo servidor está aguardando as conexões


#### Como efetuar as pesquisas no módulo cliente

Basta digitar o nome/ou parte do filme buscado e teclar [Enter].

Exemplo:

```
matrix
Client search for [matrix]
Response from server:
tt0133093 - The Matrix - 1999
tt10838180 - The Matrix 4 - 2021
tt0234215 - The Matrix Reloaded - 2003
tt0242653 - The Matrix Revolutions - 2003
tt0295432 - The Matrix Revisited - 2001
tt0106062 - Matrix - 1993
tt11749868 - Matrix - 2020
tt0270841 - Cyber Wars - 2004
```

Os resultados da pesquisa contém as seguintes informações:
* Código do item encontrado: tt0133093
* Nome do filme/ator: The Matrix
* Ano de referência: 1999

Dessa forma, se for o caso de buscar maiores informações, basta acessar a url do serviço IMBd compondo com o código obtido, conforme o exemplo abaixo.

http://www.imdb.com/title/tt0133093
