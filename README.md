# Desafio-StarWarsApi
## Desafio Star Wars  
###### A imagem do container deste projeto pode ser encontrada em **[Docker hub](https://hub.docker.com/r/r3n4nm/star-wars-api)**

  
### Projeto desenvolvido utilzando:  
- Java 17  
- Spring boot 2.7.1 
- Apache Maven

### Considerações  
- API desenvolvida com Ecossistema Spring (Spring Boot, Spring WebFlux);
- Optei por usar Webflux por acreditar numa maior performance, já que são feitas chamadas encadeadas e assíncronas afim de retornar o elemento pesquisado com suas sugestões.
- Testes unitários desenvolvidos para as camadas de controle e serviço.


### Case
Um site especializado em Star Wars deseja construir uma aplicação para consulta de 
informações relacionadas aos filmes da série. O desejo é que essa aplicação seja 
capaz de:
- Realizar consultas por diferentes elementos (personagens, naves, planetas, 
filmes, etc)
- Ao realizar uma consulta, baseado no que foi pesquisado a aplicação deve 
sugerir outros 3 itens relacionados. Por exemplo: ao pesquisar um personagem a 
aplicação deve, além de retornar aquele personagem, apresentar outras 3 
sugestões de personagens semelhantes (da mesma espécie, ou do mesmo 
planeta, ou que viaja na mesma nave, etc).
- Sua aplicação deve consumir as APIs de https://swapi.dev/ para consultar as 
informações. Escolha qualquer entidade que queira trabalhar (filme, planeta, 
personagem, espécie, naves ou todas) e construa um mecanismo de busca. Sua 
busca deve retornar, além do resultado, outras 3 sugestões relacionadas, de 
acordo com descrição acima.


### Regras de negócio
- As sugestões mostradas se baseiam no próprio retorno da api para um determinado recurso:
- Para um personagem: as sugestões são personagens que tem o mesmo planeta natal.  
- Para um planeta: as sugestões são personagens que residem no mesmo planeta.  
- Para um veículo: as sugestões são personagens que usaram o mesmo veículo.  
- Para uma espécie: as sugestões são personagens que tem a mesma espécie.  
- Para uma nave: as sugestões são personagens que usaram a mesma nave. 

* As sugestões assumem um valor máximo de 3 itens.
* Se nenhuma sugestão for encontrada, o retorno só tratá informações do objeto 'pai', sem quaisquer sugestões.


  
### Utilizando os recursos  
As requisições devem ser encaminhadas a localhost:8080/api/

| Info  | Method  | URI  | Content-Type |
|---|---|---|---|
| Buscando people por ID | GET | people/{id} | application/json  |
| Buscando planets por ID | GET | planets/{id} | application/json |
| Buscando vehicles por ID | GET | vehicles/{id} |  application/json |
| Buscando species por ID | GET | species/{id} | application/json |
| Buscando starships por ID | GET | starships/{id} | application/json |

  
  
 
