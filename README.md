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

## Questão 2 — Casa de Leilões Online

| Requisito | Padrão | Onde |
|---|---|---|
| RNF01 | **Observer** | `SujeitoLance` (Subject), `Lote` (ConcreteSubject), `ObservadorLance` (Observer), `Participante`/`PainelLeiloeiro`/`Auditor` (ConcreteObserver) |
| RNF02 | **Strategy** | `RegraComissao` (Strategy), `ComissaoArte`/`ComissaoVeiculos`/`ComissaoImoveis`/`ComissaoEletronicos` (ConcreteStrategy), `Lote` (Context) |
| RNF03, RNF04 | **Decorator** | `Compra` (Component), `CompraBase` (ConcreteComponent), `ServicoAdicional` (Decorator), `SeguroTransporte`/`CertificadoAutenticidade`/`EmbalagemEspecial`/`EntregaExpressa` (ConcreteDecorator) |
| RNF05 | Pacotes independentes | `observador`, `comissao` e `servico` não dependem uns dos outros |

Diagrama: `questao2/diagrama/diagrama_classes.puml`

```bash
cd questao2
javac -d out $(find src -name '*.java')
java -cp out Main
```
