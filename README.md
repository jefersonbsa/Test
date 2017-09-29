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

Maven irá compilar o codigo, o jar será copiado para o diretorio `target/` 

### Como executar

```bash
java -jar /path/to/conference-track-management-1.0.jar /path/to/input
```

### Algoritmo

- Foi identificado um problema de alocação de memório igual a Firt,Best e Worst Fit para o teste.
  Na solução foi utilizado o Firt Fit com ordenação da lista para melhorar a performance para encontrar o a melhor Talk para o espaço
  e também otimizar a iteração na lista de Talk


#### ConferenceSchedulerApp

- A aplicação `ConferenceSchedulerApp` consite na criação de uma app para organizar uma agenda de palestras
  Ela depende de um Leitor de Arquivos de Talk `FileTalkReader`e também de um validador de argumentos da entrada da aplicação `ArgumentValidation`.
- Essa app possui uma conferencia `Conference` e a mesma é responsável por organizar suas paletras.
  Uma conferencia possui uma lista de `Track` que é usada para preencher com as Talks que por sua vez possui uma lista de Sessões `Session`.
  As sessões podem ser de Manhã ou a Tarde e cada uma possui uma sessão especial sendo Almoço ou mesa redonda.




