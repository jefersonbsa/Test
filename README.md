## Conference Track Management

### Pre Requisitos 
* Java 1.8
* Maven 
* JUnit 


### Como compilar

```bash
cd /path/to/diretorio-projeto
mvn package
```

Maven ir� compilar o codigo, o jar ser� copiado para o diretorio `target/` 

### Como executar

```bash
java -jar /path/to/conference-track-management-1.0.jar /path/to/input
```

### Algoritmo

- Foi identificado um problema de aloca��o de mem�rio igual a Firt,Best e Worst Fit para o teste.
  Na solu��o foi utilizado o Firt Fit com ordena��o da lista para melhorar a performance para encontrar o a melhor Talk para o espa�o
  e tamb�m otimizar a itera��o na lista de Talk


#### ConferenceSchedulerApp

- A aplica��o `ConferenceSchedulerApp` consite na cria��o de uma app para organizar uma agenda de palestras
  Ela depende de um Leitor de Arquivos de Talk `FileTalkReader`e tamb�m de um validador de argumentos da entrada da aplica��o `ArgumentValidation`.
- Essa app possui uma conferencia `Conference` e a mesma � respons�vel por organizar suas paletras.
  Uma conferencia possui uma lista de `Track` que � usada para preencher com as Talks que por sua vez possui uma lista de Sess�es `Session`.
  As sess�es podem ser de Manh� ou a Tarde e cada uma possui uma sess�o especial sendo Almo�o ou mesa redonda.




