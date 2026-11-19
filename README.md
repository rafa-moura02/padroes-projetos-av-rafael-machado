# Padrões de Projetos - Avaliação Semestral

Rafael Moura Machado - Universidade Positivo

Feito em Java (usei o JDK 21), sem bibliotecas externas.

## Questão 1 - Provisionamento Multinuvem

Padrões usados:
- **Abstract Factory**: `ProvedorFactory` com `AwsFactory` e `AzureFactory`, cada uma cria a máquina virtual, o armazenamento e a fila do seu provedor. Assim não tem como misturar recursos de provedores diferentes, e o `Provisionador` só recebe a fábrica sem saber qual é.
- **Singleton**: `Configuracao` (lê o `config.properties` uma vez só) e `HistoricoAuditoria` (com `synchronized` para funcionar com as threads).

Para rodar:
```
cd questao1
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Questão 2 - Casa de Leilões Online

Padrões usados:
- **Observer**: o `Lote` avisa os `ObservadorLance` (participante, painel do leiloeiro e auditor) a cada lance aceito.
- **Strategy**: `RegraComissao` com uma classe para cada categoria, dá para trocar a regra do lote durante o leilão.
- **Decorator**: os serviços adicionais (`ServicoAdicional`) vão embrulhando a `CompraBase`, em qualquer ordem e quantidade.

Para rodar:
```
cd questao2
javac -d out $(find src -name "*.java")
java -cp out Main
```

Os diagramas de classes estão em `questao1/diagrama` e `questao2/diagrama` (PlantUML).
