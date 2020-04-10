# Cartão de Crédito FIAP

A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos alunos e para isso necessita de um sistema para gerenciamento e integração com outras empresas.

## Começando

Esse repositório fornecerá uma cópia do projeto em quesão para download em sua máquina local para fins de desenvolvimento e teste.
Por favor leia o arquivo atentamente antes de iniciar qualquer tipo de ação.

### Pré-Requisitos

Eclipse (Versão mínima 2019-03);
Web Browser (Google Chrome, Microsoft Edge, Mozilla Firefox, etc).

### Instalação

No Eclipse:
- Procure pelo Menu File -> Import;
- Clique em Maven (Você também pode digitar na busca);
- Selecione a opção de projeto existente;
- Busque o Projeto;
- Finalize e espere a solução ser carregada.

![](http://g.recordit.co/yjW5uXKND7.gif)

Versões antigas do Eclipse quando trabalham com Spring podem ter um BUG: Apontamento de ERRO na Primeira Linha do arquivo POM.xml

Como resolver?
* **Procure pelo Menu Help -> Install New Software**
* **Colar repositório o repositório: https://download.eclipse.org/m2e-wtp/releases/1.4/**
* **Instalar todos os plugins**
* **Aceitar Termos**
* **Reiniciar Eclipse**

### Acesso aos Endpoints

* A aplicação utiliza JWT (JSON Web Token) então em seu primeiro acesso é fundamental seguir o passo-a-passo:

1 - Criar usuário: http://localhost:8081/users/

RequestBody:
```
{
	"username":"testeone",
	"password":"1234"
}
```

2 - Efetuar o login: http://localhost:8081/users/login

```
{
	"username":"testeone",
	"password":"1234"
}
```

3- O token será apresentado como response. Copie-o.

4- Antes de efetuar a chamada de qualquer funcionalidade de **students** ou **details**. Vincular a autenticação na chamada do método. 

Se estiver utilizando o Postman, popule:
```
Type: Bearer Token 
Token: (Colar token copiado no passo 3)
```

## Executando os testes

Como executar os testes?

Uma das dependências adicionadas no pom.xml é o JUnit 4.12. Apesar do Eclipse possuir a library por Default, não gostaríamos que o usuário gastasse tempo configurando o ambiente.

Então a única configuração necessária é neste momento porque por Default o Eclipse procura o JUnit 5, mas estamos o usando a biblioteca **4.12** devido o suporte a soluções ser mais completo. A tendência é que ao rodar os testes seja emitida a seguinte mensagem: **"No tests found with test runner 'JUnit 5'"**, neste caso:

* **Pressione a lateral do Botão Runner;**
* **Run Configuration;**
* **Na Aba Tests -> Mude Test Runner: JUnit 5 para JUnit 4.**

### Testes de Integração

Objetivo: Relação do RestController com os componentes HTTP (GET, POST, PATCH, DELETE).

```
	@Test
	public void returnAllStudents() throws Exception {
		String uri = "/students/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		CreditCard[] arrCreditCard = super.mapFromJson(content, CreditCard[].class);
		assertTrue(arrCreditCard.length > 0);
	}
```

Exemplo que lista todos os estudantes cadastrados no Banco de Dados:
* **O mvcResult recebe a String URI e comunica-se aceitando receber um valor JSON como retorno.**
* **O código de retorno esperado pelo teste é o 200 (OK) e será comparado com o valor exibido pela URL.**
* **Diante disso um array é montado e como o Banco de Dados está pré-preenchido então obrigatoriamente o resultado final precisa demonstrar um Array de tamanho maior que zero.**

### Testes Unitários

Camada de Persistência

```
	@Test
	public void create() {	
		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("08/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA");
		objCreditCardDetailsDTO.setOperationId(4);
		objCreditCardDetailsDTO.setStudentId("3095564 200-33");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(4000.00));
		
		CreditCardDetails detailCard = new CreditCardDetails(objCreditCardDetailsDTO);
		detailCardRepository.save(detailCard);			
	}
```
Exemplo que cadastra o histórico de compra:
* **Um DTO (Data Transfer Object) é montado e em seguida está sendo salvo.**
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

* **[Spring Framework]** - (https://spring.io/) - (Spring Boot, Spring Data e Spring Security);
* **[Flyway]** - (https://flywaydb.org/) - Versionador da Base de Dados;
* **[H2 Database]** - (https://www.h2database.com/html/main.html) - Banco de Dados em Memória;
* **[Maven]** - (https://maven.apache.org/) - Gerenciador de Dependências.

## Justificativa

Nossa equipe optou por H2 pela praticidade de não ser necessário ter um ambiente específico para executar essa aplicação, realmente foi uma preocupação muito grande desde o início do projeto. Temos total conhecimento que uma das grandes praticidades do Spring está na fácil alteração de Banco de Dados via propriedades.

O Flyway utilizamos não somente como versionador. Foi muito útil para inicializar a carga da base com script.

## Versionamento

- Utilizamos este repositório como forma de versionamento do projeto;
- Na aplicação existe um plugin chamado FLYWAY útil para versionamento de Base da Dados. Para melhor compreensão é sugerido a leitura de sua documentação.

## Autores

* **Guilherme Almeida** - (https://github.com/guiarese)
* **Henrique MLaker** - (https://github.com/henriquemlaker)
* **Lucas Damascena** - (https://github.com/lucasdamascena)

## Licença

* **GNU General Public License (GPL)**

"A GPL é a licença para software livre com maior utilização. Ela se baseia em 4 liberdades: (1) a liberdade de executar o programa, para qualquer propósito; (2) a liberdade de estudar como o programa funciona e adaptá-lo às suas necessidades; (3) a liberdade de redistribuir cópias de modo que você possa ajudar ao seu próximo; e (4) a liberdade de aperfeiçoar o programa, e liberar suas modificações, de modo que toda a comunidade se beneficie delas.
O acesso ao código-fonte é um pré-requisito para seus objetivos serem atingidos. Com a garantia destas liberdades, a GPL permite que os programas sejam distribuídos e reaproveitados, mantendo, porém, os direitos do autor(es)".
