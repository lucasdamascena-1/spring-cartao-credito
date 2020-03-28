# Cartão de Crédito FIAP

A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos alunos e para isso necessita de um sistema para gerenciamento e integração com outras empresas.

## Começando

Esse repositório fornecerá uma cópia do projeto em quesão para download em sua máquina local para fins de desenvolvimento e teste.
Por favor leia o arquivo atentamente antes de iniciar qualquer tipo de ação.

### Pré-Requisitos

Eclipse (Versão mínimia 2019-03);
Web Browser (Google Chrome, Microsoft Edge, Mozilla Firefox, etc).

### Instalação

No Eclipse:
- Procure pelo Menu File -> Import;
- Clique em Maven (Você também pode digitar na busca);
- Selecione a opção de projeto existente;
- Busque o Projeto;
- Finalize e espere a solução ser carregada.

![](http://g.recordit.co/yjW5uXKND7.gif)

Existe um BUG no Eclipse quando trabalha com Spring que é possível de ocorrer: Apontamento de ERRO na Primeira Linha do arquivo POM.xml

Como resolver?
- Procure pelo Menu Help -> Install New Software 
- Colar repositório o repositório: https://download.eclipse.org/m2e-wtp/releases/1.4/ 
- Instalar todos os plugins 
- Aceitar Termos
- Reiniciar Eclipse

## Executando os testes

Como executar os testes?

### Testes de ponta a ponta

Explicar o que testam e motivo

```
Exemplo
```

### Testes de Estilo de Codificação

Explicar o que testam e motivo

```
Exemplo
```

## Compilação

A aplicação foi documentada com Swagger, então é importante visualizar quais as URLs recebem comunicação e o que é necessário para o funcionamento correto.

* **Após compilação**
```
http://localhost:8081/swagger-ui.html#/
```

* **Para comunicação com a Base de Dados H2**
```
http://localhost:8081/h2/
```

## Construído com:

* **[Spring Framework]** - (https://spring.io/) - (Spring Boot, Spring Data, Spring Security e Spring Batch);
* **[Flyway]** - (https://flywaydb.org/) - Versionador da Base de Dados;
* **[H2 Database]** - (https://www.h2database.com/html/main.html) - Base de dados;
* **[Maven]** - (https://maven.apache.org/) - Gerenciador de Dependências.

## Versionamento

- Utilizamos este repositório como forma de versionamento do projeto;
- Na aplicação existe um plugin chamado FLYWAY útil para versionamento de Base da Dados. Para melhor compreensão é sugerido a leitura de sua documentação.

## Authors

* **Guilherme Almeida** - (https://github.com/guiarese)
* **Henrique MLaker** - (https://github.com/henriquemlaker)
* **Lucas Damascena** - (https://github.com/lucasdamascena)

## License

* **GNU General Public License (GPL)**

"A GPL é a licença para software livre com maior utilização. Ela se baseia em 4 liberdades: (1) a liberdade de executar o programa, para qualquer propósito; (2) a liberdade de estudar como o programa funciona e adaptá-lo às suas necessidades; (3) a liberdade de redistribuir cópias de modo que você possa ajudar ao seu próximo; e (4) a liberdade de aperfeiçoar o programa, e liberar suas modificações, de modo que toda a comunidade se beneficie delas.
O acesso ao código-fonte é um pré-requisito para seus objetivos serem atingidos. Com a garantia destas liberdades, a GPL permite que os programas sejam distribuídos e reaproveitados, mantendo, porém, os direitos do autor(es)".
