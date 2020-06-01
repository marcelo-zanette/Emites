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

`java -jar ./target/EmitesServer-1.0-SNAPSHOT.jar 4545
`

O parâmetro passado (no caso 4545) se refere à porta de conexão em que o módulo servidor ficará aguardando as conexões dos módulos clientes.

Tenha em mente o IP da máquina onde o servidor está sendo executado - esta informação será útil posteriormente para iniciar os módulos clientes.

#### Como Executar o EmitesClient (Cliente)

Execute o comando a seguir para iniciar o EmitesClient:

`java -jar ./target/EmitesClient-1.0-SNAPSHOT.jar 127.0.0.1 4545
`


Os parâmetros utilizados são:

* 127.0.0.1 - IP da máquina onde o servidor está sendo executado
* 4545 - porta em que o módulo servidor está aguardando as conexões
