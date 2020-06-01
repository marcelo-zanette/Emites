# Emites Java Challenge - Manual Execução

A aplicação está dividida em 2 módulos: EmitesServer e EmitesClient, ambos disponíveis ao baixar o repositório completo.

Requisitos de sistema: Java 8, Git (_para checkout do repositório_)

Roteiro de execução:

1. Download do repositório:
```
git clone https://github.com/marcelo-zanette/Emites.git
```
2. Entre no diretório Emites
```
cd Emites
```

3. Geração dos jars correspondentes
```
mvn clean package
```

4. Executar o módulo servidor
```
cd EmitesServer
java -jar ./target/EmitesServer-1.0-SNAPSHOT.jar 4545
```

5. Executar o módulo cliente
```
cd EmitesClient
java -jar ./target/EmitesClient-1.0-SNAPSHOT.jar 127.0.0.1 4545
```

6. Efetuar a pesquisa desejada no módulo cliente
```
[....]
Response from server:
emites-server-hi
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
Exemplo de pesquisa sobre o termo "matrix".

