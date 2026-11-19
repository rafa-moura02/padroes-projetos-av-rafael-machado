# Padrões de Projetos — Avaliação Semestral

Universidade Positivo — Padrões de Projetos.

Java 17+ (testado com OpenJDK 21), apenas a biblioteca padrão.

## Questão 1 — Provisionamento Multinuvem

| Requisito | Padrão | Onde |
|---|---|---|
| RNF01, RNF02, RNF03, RNF06 | **Abstract Factory** | `ProvedorFactory` (AbstractFactory), `AwsFactory`/`AzureFactory` (ConcreteFactory), `MaquinaVirtual`/`Armazenamento`/`Fila` (AbstractProduct), `Aws*`/`Azure*` (ConcreteProduct), `Provisionador` (Client) |
| RNF04 | **Singleton** | `config.Configuracao` (lê `config.properties` uma única vez) |
| RNF05 | **Singleton** thread-safe | `auditoria.HistoricoAuditoria` (métodos `synchronized`) |

Diagrama: `questao1/diagrama/diagrama_classes.puml`

```bash
cd questao1
javac -d out $(find src -name '*.java')
java -cp out Main
```
